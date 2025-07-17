package com.ibandorta.FinanciaBank.FinanciaBank.service;

import com.ibandorta.FinanciaBank.FinanciaBank.model.CuentaBancaria;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.CuentaBancariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancariaService {
    private final CuentaBancariaRepository cuentaBancariaRepository;

    public CuentaBancariaService(CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    public List<CuentaBancaria>obtenerCuentasDeUsuario(User usuario){
        return cuentaBancariaRepository.findByUsuario(usuario);
    }

    public CuentaBancaria guardarCuenta(CuentaBancaria cuenta){
        //Validar IBAN único antes de guardar
        if (cuentaBancariaRepository.findByIban(cuenta.getIban()).isPresent()){
            throw new IllegalStateException("IBAN ya existe");
        }
        return cuentaBancariaRepository.save(cuenta);
    }


}
