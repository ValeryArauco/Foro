package com.aluracursos.foro.controller;

import com.aluracursos.foro.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        topicoRepository.save(new Topico(datosRegistroTopico));
        System.out.println(datosRegistroTopico);
    }

    @GetMapping
    public List<DatosListadoTopico> ListadoTopicos(){
        return topicoRepository.findAll().stream().map(DatosListadoTopico::new).toList();
    }

    @GetMapping("/{id}")
    public DatosListadoTopico obtenerTopicoPorId(@PathVariable("id") @Positive Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            return new DatosListadoTopico(topicoOptional.get());
        } else {
            String mensaje = "Tópico con id " + id + " no encontrado";
            System.out.println(mensaje);
            /* return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); */
        }
        return null;
    }


    @PutMapping
    @Transactional
    public void actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarDatos(datosActualizarTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarTopico(@PathVariable("id") @Positive Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
        } else {
            String mensaje = "Tópico con id " + id + " no encontrado";
            System.out.println(mensaje);
            /* return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); */
        }
    }
}
