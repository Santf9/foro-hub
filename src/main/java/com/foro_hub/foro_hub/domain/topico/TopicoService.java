package com.foro_hub.foro_hub.domain.topico;

import com.foro_hub.foro_hub.domain.curso.ICursoRepository;
import com.foro_hub.foro_hub.infra.errores.ValidacionException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private ITopicoRepository topicoRepository;
    
    @Autowired
    private ICursoRepository cursoRepository;

    public Topico crearTopico(DatosRegistroTopicoDTO datos) {
        // Validar que no existe un tópico duplicado
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje");
        }

        // Buscar el curso por nombre
        var curso = cursoRepository.findByNombre(datos.curso())
                .orElseThrow(() -> new ValidacionException("El curso especificado no existe: " + datos.curso()));

        // Crear el nuevo tópico usando el DTO
        var topico = new Topico(datos, curso);
        
        return topicoRepository.save(topico);
    }
    
    public Page<Topico> listarTopicosPaginados(Pageable pageable) {
        return topicoRepository.findAll(pageable);
    }
    
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("No se encontró el tópico con ID: " + id));
    }
    
    @Transactional
    public Topico actualizarTopico(Long id, ActualizarTopicoDTO datos) {
        // Verificar que el tópico existe
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("No se encontró el tópico con ID: " + id));
        
        // Validar que no existe otro tópico con el mismo título y mensaje (excluyendo el actual)
        if (!topico.getTitulo().equals(datos.titulo()) || !topico.getMensaje().equals(datos.mensaje())) {
            if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
                throw new ValidacionException("Ya existe otro tópico con el mismo título y mensaje");
            }
        }
        
        // Buscar el curso por nombre
        var curso = cursoRepository.findByNombre(datos.curso())
                .orElseThrow(() -> new ValidacionException("El curso especificado no existe: " + datos.curso()));
        
        // Actualizar los datos del tópico
        topico.actualizarDatos(datos, curso);
        
        // No es necesario save() porque la entidad está gestionada por JPA
        // Los cambios se sincronizan automáticamente al finalizar la transacción
        return topico;
    }
}
