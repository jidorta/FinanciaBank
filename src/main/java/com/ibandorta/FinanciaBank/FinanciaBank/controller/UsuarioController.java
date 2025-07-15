package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UserService usuarioService;


    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<User> obtenerTodosLosUsuarios(){
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PostMapping
    public User crearUsuario(@RequestBody User usuario){
        return usuarioService.crearUsuario(usuario);
    }

}
