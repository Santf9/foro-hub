package com.foro_hub.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        EstadoTopico estado,
        String autor,
        String curso
) {
    public DatosRespuestaTopicoDTO(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getEstado(),
            topico.getAutor(),
            topico.getCurso().getNombre()
        );
    }
}
