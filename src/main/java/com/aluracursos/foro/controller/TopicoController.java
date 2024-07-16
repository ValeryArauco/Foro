package com.aluracursos.foro.controller;

import com.aluracursos.foro.domain.topico.*;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosListadoTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder ){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosListadoTopico datosListadoTopico = new DatosListadoTopico(topico);
        System.out.println(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
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
