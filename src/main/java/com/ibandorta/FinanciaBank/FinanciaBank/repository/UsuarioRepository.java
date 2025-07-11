package com.ibandorta.FinanciaBank.FinanciaBank.repository;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
