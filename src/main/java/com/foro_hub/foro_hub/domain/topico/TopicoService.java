package com.foro_hub.foro_hub.domain.topico;

import com.foro_hub.foro_hub.domain.curso.ICursoRepository;
import com.foro_hub.foro_hub.infra.errores.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
