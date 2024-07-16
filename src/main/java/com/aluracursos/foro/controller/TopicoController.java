package com.aluracursos.foro.controller;

import com.aluracursos.foro.domain.topico.*;
import com.aluracursos.foro.domain.usuario.Usuario;
import com.aluracursos.foro.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Optional<Usuario> autorOptional = usuarioRepository.findById(datosRegistroTopico.idUsuario());
        System.out.println(datosRegistroTopico.idUsuario());
        System.out.println("-----------------------------");
        if (!autorOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        Usuario autor = autorOptional.get();

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, autor));
        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(topico);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosListadoTopico);
    }


    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> ListadoTopicos(@PageableDefault(size = 10)Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListadoTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable("id") @Positive Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        var datosTopico = new DatosListadoTopico(topicoOptional.get());
        return ResponseEntity.ok(datosTopico);
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Optional<Topico> topico = Optional.of(topicoRepository.getReferenceById(datosActualizarTopico.id()));
        topico.get().actualizarDatos(datosActualizarTopico);
        return ResponseEntity.ok(datosActualizarTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable("id") @Positive Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
