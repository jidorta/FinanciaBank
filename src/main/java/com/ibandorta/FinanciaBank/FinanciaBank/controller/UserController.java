package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class UserController {

    @GetMapping("/public")
    public ResponseEntity<String>publicEndpoint(){
        return ResponseEntity.ok("Endpoint public sin autenticación");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String>userEndpoint(){
        return ResponseEntity.ok("Acceso permitido a User");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN'")
    public ResponseEntity<String>adminEndpoint(){
        return ResponseEntity.ok("Acceso permitido solo a ADMIN");
    }
    @GetMapping("/both")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<String>bothEndpoint(){
        return ResponseEntity.ok("Acceso para USER o ADMIN");
    }

}
