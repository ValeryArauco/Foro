package com.aluracursos.foro.topico;

import com.aluracursos.foro.respuesta.Respuesta;
import com.aluracursos.foro.usuario.DatosRegistroUsuario;
import com.aluracursos.foro.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaCreacion;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'pendiente'")
    private String status = "pendiente";

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    private String curso;

    @OneToMany(mappedBy = "topico")
    private List<Respuesta> respuestas;

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = new Date();
        }
        if (status == null) {
            status = "pendiente";
        }
    }


    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.nombreCurso();
    }

    public void actualizarDatos( DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }

        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }

        if (datosActualizarTopico.nombreCurso() != null) {
            this.curso = datosActualizarTopico.nombreCurso();
        }
    }

}
