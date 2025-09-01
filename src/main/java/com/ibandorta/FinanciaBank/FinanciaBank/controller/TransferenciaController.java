package com.ibandorta.FinanciaBank.FinanciaBank.controller;


import com.ibandorta.FinanciaBank.FinanciaBank.dto.TransferenciaRequestRecord;
import com.ibandorta.FinanciaBank.FinanciaBank.dto.TransferenciaResponseRecord;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Transferencia;
import com.ibandorta.FinanciaBank.FinanciaBank.service.CuentaBancariaService;
import com.ibandorta.FinanciaBank.FinanciaBank.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController  {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaController.class);

    private final TransferenciaService transferenciaService;
    private final CuentaBancariaService cuentaBancariaService;

    public TransferenciaController(TransferenciaService transferenciaService, CuentaBancariaService cuentaBancariaService) {
        this.transferenciaService = transferenciaService;
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @PostMapping
    public ResponseEntity<TransferenciaResponseRecord> realizarTransferencia(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransferenciaRequestRecord dto){

        log.info("Usuario {} solicita transferencia de {} a {}",
                userDetails.getUsername(),
                dto.cuentaOrigenId(),
                dto.cuentaDestinoId());

        Transferencia t = transferenciaService.realizarTransferencia(
                userDetails.getUsername(),
                dto.cuentaOrigenId(),
                dto.cuentaDestinoId(),
                dto.monto()
        );

        TransferenciaResponseRecord resp = mapToResponse(t);

        log.info("Transferencia realizada {}", resp);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);

    }

    // Get: historial de transferencias del usuario(como emisor/origen)
    @GetMapping
    public List<TransferenciaResponseRecord> obtenerTransferencias(
            @AuthenticationPrincipal UserDetails userDetails){
        return transferenciaService.obtenerTransferenciasPorUsuario(userDetails.getUsername())
                .stream()
                .map(this:: mapToResponse)
                .collect(Collectors.toList());
    }


    private TransferenciaResponseRecord mapToResponse(Transferencia t) {
        return new TransferenciaResponseRecord(
                t.getId(),
                t.getCuentaOrigen().getId(),
                t.getCuentaDestino().getId(),
                t.getMonto(),
                t.getFecha()
        );
    }

}
