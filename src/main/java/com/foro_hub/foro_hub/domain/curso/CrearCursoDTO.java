package com.foro_hub.foro_hub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearCursoDTO(
        @NotBlank String nombre,
        @NotNull String categoria) {
}
