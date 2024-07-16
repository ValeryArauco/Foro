package com.aluracursos.foro.controller;

import com.aluracursos.foro.domain.usuario.DatosRegistroUsuario;
import com.aluracursos.foro.domain.usuario.Usuario;
import com.aluracursos.foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario){
        usuarioRepository.save(new Usuario(datosRegistroUsuario));
        System.out.println(datosRegistroUsuario);
    }
}
