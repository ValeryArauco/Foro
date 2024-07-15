package com.aluracursos.foro.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        //@NotBlank
        Integer idUsuario,
        @NotBlank
        String mensaje,
        @NotBlank
        String nombreCurso,
        @NotBlank
        String titulo
) {
}
