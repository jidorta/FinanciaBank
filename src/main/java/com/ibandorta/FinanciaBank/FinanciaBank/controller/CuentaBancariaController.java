package com.ibandorta.FinanciaBank.FinanciaBank.controller;

import com.ibandorta.FinanciaBank.FinanciaBank.dto.CuentaBancariaResponseRecord;
import com.ibandorta.FinanciaBank.FinanciaBank.model.CuentaBancaria;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.service.CuentaBancariaService;
import com.ibandorta.FinanciaBank.FinanciaBank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cuenta-bancaria")
@RequiredArgsConstructor
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;
    private final UserService userService;

    @GetMapping
    public List<CuentaBancariaResponseRecord>obtenerCuentas(@AuthenticationPrincipal UserDetails userDetails){
        User usuario = userService.findByUsername(userDetails.getUsername());

        return cuentaBancariaService.obtenerCuentasDeUsuario(usuario)
                .stream()
                .map(cuenta -> CuentaBancariaResponseRecord.builder()
                        .iban(cuenta.getIban())
                        .saldo(cuenta.getSaldo())
                        .moneda(cuenta.getMoneda())
                        .activa(cuenta.isActiva())
                        .build())
                .collect(Collectors.toList());


    }

    @PostMapping
    public CuentaBancariaResponseRecord crearCuenta(@AuthenticationPrincipal UserDetails userDetails){
        User usuario = userService.findByUsername(userDetails.getUsername());

        CuentaBancaria nuevaCuenta = CuentaBancaria.builder()
                .iban("ES" + UUID.randomUUID().toString().substring(0,20).replaceAll("-",""))
                .saldo(BigDecimal.ZERO)
                .moneda("EUR")
                .activa(true)
                .usuario(usuario)
                .build();

        CuentaBancaria guardada = cuentaBancariaService.guardarCuenta(nuevaCuenta);

        return CuentaBancariaResponseRecord.builder()
                .iban(guardada.getIban())
                .saldo(guardada.getSaldo())
                .moneda(guardada.getMoneda())
                .activa(guardada.isActiva())
                .build();

    }

    @PostMapping("/deposito")
    public ResponseEntity<String>depositar(
            @RequestParam Long cuentaId,
            @RequestParam BigDecimal monto
    ){
        cuentaBancariaService.depositar(cuentaId,monto);
        return ResponseEntity.ok("Deposito realizado con éxito.");
    }
}


