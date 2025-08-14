package com.foro_hub.foro_hub.controller;

import com.foro_hub.foro_hub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        
        return ResponseEntity.created(uri).body(respuesta);
    }
}
