package com.ibandorta.FinanciaBank.FinanciaBank.controller;


import com.ibandorta.FinanciaBank.FinanciaBank.dto.TransferenciaRequestDTO;
import com.ibandorta.FinanciaBank.FinanciaBank.dto.TransferenciaResponseDTO;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Transferencia;
import com.ibandorta.FinanciaBank.FinanciaBank.service.CuentaBancariaService;
import com.ibandorta.FinanciaBank.FinanciaBank.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController  {

    private final TransferenciaService transferenciaService;
    private final CuentaBancariaService cuentaBancariaService;

    public TransferenciaController(TransferenciaService transferenciaService, CuentaBancariaService cuentaBancariaService) {
        this.transferenciaService = transferenciaService;
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @PostMapping
    public ResponseEntity<TransferenciaResponseDTO> realizarTransferencia(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransferenciaRequestDTO dto){


        Transferencia t = transferenciaService.realizarTransferencia(
                userDetails.getUsername(),
                dto.getCuentaOrigenId(),
                dto.getCuentaDestinoId(),
                dto.getMonto()
        );

        TransferenciaResponseDTO resp = mapToResponse(t);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);

    }

    // Get: historial de transferencias del usuario(como emisor/origen)
    @GetMapping
    public List<TransferenciaResponseDTO> obtenerTransferencias(
            @AuthenticationPrincipal UserDetails userDetails){
        return transferenciaService.obtenerTransferenciasPorUsuario(userDetails.getUsername())
                .stream()
                .map(this:: mapToResponse)
                .collect(Collectors.toList());
    }


    private TransferenciaResponseDTO mapToResponse(Transferencia t){
        TransferenciaResponseDTO r = new TransferenciaResponseDTO();
        r.setId(t.getId());
        r.setCuentaOrigenId(t.getCuentaOrigen().getId());
        r.setCuentaDestinoId(t.getCuentaDestino().getId());
        r.setMonto(t.getMonto());
        r.setFecha(t.getFecha());
        return r;
    }

}
