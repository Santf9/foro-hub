package com.foro_hub.foro_hub.controller;

import com.foro_hub.foro_hub.domain.usuario.DatosAutenticacionUsuario;
import com.foro_hub.foro_hub.domain.usuario.Usuario;
import com.foro_hub.foro_hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager; // Control de acceso para autenticación
    
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {

        var authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(), datosAutenticacionUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var usuario = (Usuario) usuarioAutenticado.getPrincipal();
        var tokenJWT = tokenService.generarToken(usuario);

        return ResponseEntity.ok(new DatosJWTToken(tokenJWT));
    }
}
