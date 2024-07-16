package com.aluracursos.foro.infra.security;

public record DatosJWTToken(String jwTtoken) {}
//@RestController
//@RequestMapping("/login")
//public class AutenticacionController {
//
//    @Autowired
//    private AuthenticationManager manager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @PostMapping
//    public ResponseEntity  realizarLogin(@RequestBody @Valid DatosAutenticacion datos) {
//        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
//        var authentication = manager.authenticate(authenticationToken);
//
//        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
//
//        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
//    }


