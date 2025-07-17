package com.ibandorta.FinanciaBank.FinanciaBank.repository;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia,Long> {
    List<Transferencia> findByCuentaOrigenUsuarioUsername(String username);
}
