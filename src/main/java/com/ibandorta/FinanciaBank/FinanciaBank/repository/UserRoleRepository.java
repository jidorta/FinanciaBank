package com.ibandorta.FinanciaBank.FinanciaBank.repository;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole>findByName(Role role);

}
