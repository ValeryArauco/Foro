package com.aluracursos.foro.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico (
        @NotNull
        Long id,

        String mensaje,

        String nombreCurso,

        String titulo
) {
}
