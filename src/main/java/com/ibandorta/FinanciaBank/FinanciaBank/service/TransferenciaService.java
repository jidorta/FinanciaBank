package com.ibandorta.FinanciaBank.FinanciaBank.service;

import com.ibandorta.FinanciaBank.FinanciaBank.model.CuentaBancaria;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Transferencia;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.CuentaBancariaRepository;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.TransferenciaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class TransferenciaService {

    private static final Logger log = LoggerFactory.getLogger(TransferenciaService.class);
    private final TransferenciaRepository transferenciaRepository;
    private final CuentaBancariaRepository cuentaBancariaRepository;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, CuentaBancariaRepository cuentaBancariaRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Transactional
    public Transferencia realizarTransferencia(String username, Long cuentaOrigenId,
                                               Long cuentaDestinoId, BigDecimal monto){


        if(cuentaOrigenId.equals(cuentaDestinoId)){

            throw new IllegalArgumentException("No se puede transferir a la misma cuenta.");
        }
        CuentaBancaria cuentaOrigen = cuentaBancariaRepository.findById(cuentaOrigenId)
                .orElseThrow(()->new IllegalArgumentException("Cuenta origen no encontrada"));

        CuentaBancaria cuentaDestino = cuentaBancariaRepository.findById(cuentaDestinoId)
                .orElseThrow(()-> new IllegalArgumentException("Cuenta destino no encontrada"));

        log.info("Usuario solicita transferencia de {} a {}", cuentaOrigen, cuentaDestino);
        System.out.println("==> CuentaDestino id=" + cuentaDestino.getId() +
                " saldo=" + cuentaDestino.getSaldo());
        //Comprobar que la cuenta origen pertenece al usuario autenticado
        if(!cuentaOrigen.getUsuario().getUsername().equals(username)){
            throw new IllegalArgumentException("No tienes permisos para operar cone esta cuenta de origen");
        }

        //Validar saldo suficiente
        if(cuentaOrigen.getSaldo().compareTo(monto)< 0){
            throw new IllegalArgumentException("Saldo insuficiente en la cuenta origen.");
        }

        //Actualizar saldos
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(monto));
        cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(monto));

        cuentaBancariaRepository.save(cuentaOrigen);
        cuentaBancariaRepository.save(cuentaDestino);

        Transferencia transferencia = Transferencia.builder()
                .monto(monto)
                .fecha(LocalDateTime.now())
                .cuentaOrigen(cuentaOrigen)
                .cuentaDestino(cuentaDestino)
                .build();
        log.info("Transferencia realizada {}", transferencia);
        return transferenciaRepository.save(transferencia);


    }
    public List<Transferencia> obtenerTransferenciasPorUsuario(String username){
        return transferenciaRepository.findByCuentaOrigenUsuarioUsername(username);
    }
}
