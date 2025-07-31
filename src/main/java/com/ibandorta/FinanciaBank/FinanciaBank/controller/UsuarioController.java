package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import com.ibandorta.FinanciaBank.FinanciaBank.dto.UserRequest;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UserService usuarioService;


    public UsuarioController(UserService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<User> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @PostMapping("/registrar")
    public User crearUsuario(@RequestBody User user) {
        // Extraes los enums desde los UserRole del usuario
        Set<Role> roles = user.getRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());

        return usuarioService.crearUsuario(user, roles);
    }
}
