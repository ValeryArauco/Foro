package com.aluracursos.foro.usuario;

import com.aluracursos.foro.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
