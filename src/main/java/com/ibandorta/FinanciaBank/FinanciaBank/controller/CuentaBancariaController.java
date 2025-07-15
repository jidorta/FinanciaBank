package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cuenta-bancaria")
public class CuentaBancariaController {

    @GetMapping
    public ResponseEntity<String>obtenerInfo(){
        return ResponseEntity.ok("Acceso autorizado a cuenta bancaria");
    }
}
