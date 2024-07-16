package com.aluracursos.foro.domain.respuesta;

import com.aluracursos.foro.domain.topico.Topico;
import com.aluracursos.foro.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private boolean solucion;
}
