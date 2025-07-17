package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import com.ibandorta.FinanciaBank.FinanciaBank.dto.CuentaBancariaResponseDTO;
import com.ibandorta.FinanciaBank.FinanciaBank.model.CuentaBancaria;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.service.CuentaBancariaService;
import com.ibandorta.FinanciaBank.FinanciaBank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cuenta-bancaria")
@RequiredArgsConstructor
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;
    private final UserService userService;

    @GetMapping
    public List<CuentaBancariaResponseDTO>obtenerCuentas(@AuthenticationPrincipal UserDetails userDetails){
        User usuario = userService.findByUsername(userDetails.getUsername());

        return cuentaBancariaService.obtenerCuentasDeUsuario(usuario)
                .stream()
                .map(cuenta ->CuentaBancariaResponseDTO.builder()
                        .iban(cuenta.getIban())
                        .saldo(cuenta.getSaldo())
                        .moneda(cuenta.getMoneda())
                        .activa(cuenta.isActiva())
                        .build())
                .collect(Collectors.toList());


    }

    @PostMapping
    public CuentaBancariaResponseDTO crearCuenta(@AuthenticationPrincipal UserDetails userDetails){
        User usuario = userService.findByUsername(userDetails.getUsername());

        CuentaBancaria nuevaCuenta = CuentaBancaria.builder()
                .iban("ES" + UUID.randomUUID().toString().substring(0,20).replaceAll("-",""))
                .saldo(BigDecimal.ZERO)
                .moneda("EUR")
                .activa(true)
                .usuario(usuario)
                .build();

        CuentaBancaria guardada = cuentaBancariaService.guardarCuenta(nuevaCuenta);

        return CuentaBancariaResponseDTO.builder()
                .iban(guardada.getIban())
                .saldo(guardada.getSaldo())
                .moneda(guardada.getMoneda())
                .activa(guardada.isActiva())
                .build();

    }

    public User crearUsuario(@RequestBody User user){
        return userService.crearUsuario(user);
    }

}


