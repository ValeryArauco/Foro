package com.aluracursos.foro.domain.topico;

import java.util.Date;

public record DatosListadoTopico(
        String titulo,
        String mensaje,
        Date fechaCreacion,
        String estado,
        //String autor,
        String curso

) {
    public DatosListadoTopico(Topico topico){
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getCurso());
    }
}
