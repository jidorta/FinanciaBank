package com.ibandorta.FinanciaBank.FinanciaBank.repository;

import com.ibandorta.FinanciaBank.FinanciaBank.model.CuentaBancaria;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria,Long> {

    List<CuentaBancaria> findByUsuario(User  usuario);
    Optional<CuentaBancaria> findByIban(String iban);
}
