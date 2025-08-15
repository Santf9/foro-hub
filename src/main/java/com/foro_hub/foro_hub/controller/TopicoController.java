package com.foro_hub.foro_hub.controller;

import com.foro_hub.foro_hub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid DatosRegistroTopicoDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        
        var topicoCreado = topicoService.crearTopico(datos);
        var respuesta = new DatosRespuestaTopicoDTO(topicoCreado);
        
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoCreado.getId()).toUri();
        
        return ResponseEntity.created(uri).body(respuesta); // devuelve 201 Created con la ubicación del nuevo recurso
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopicoDTO>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {

        var paginaTopicos = topicoService.listarTopicosPaginados(paginacion);
        var respuesta = paginaTopicos.map(DatosRespuestaTopicoDTO::new);

        return ResponseEntity.ok(respuesta);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity obtenerTopicoPorId(@PathVariable Long id) {

        var topico = topicoService.obtenerTopicoPorId(id);
        var respuesta = new DatosRespuestaTopicoDTO(topico);

        return ResponseEntity.ok(respuesta);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid ActualizarTopicoDTO datos) {

        var topicoActualizado = topicoService.actualizarTopico(id, datos);
        var respuesta = new DatosRespuestaTopicoDTO(topicoActualizado);

        return ResponseEntity.ok(respuesta);
    }
}
