package com.ibandorta.FinanciaBank.FinanciaBank.config;


import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final UserRoleRepository userRoleRepository; // final para que Lombok genere el constructor

    @PostConstruct
    public void initRoles() {
        try {
            if (userRoleRepository.count() == 0) {
                userRoleRepository.save(new UserRole(null, Role.USER));
                userRoleRepository.save(new UserRole(null, Role.ADMIN));
                System.out.println("Roles inicializados correctamente");
            }
        } catch (Exception e) {
            System.err.println("Error inicializando roles: " + e.getMessage());
            e.printStackTrace();
        }
    }
}