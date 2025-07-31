package com.ibandorta.FinanciaBank.FinanciaBank.service;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRepository;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {



    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
    }

    public List<User>obtenerTodosLosUsuarios(){
        return userRepository.findAll();
    }

    public User crearUsuario(User user, Set<Role> roles) {
        Set<UserRole> userRoles = new HashSet<>();

        for (Role roleEnum : roles) {
            UserRole userRole = userRoleRepository.findByName(roleEnum)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + roleEnum));
            userRoles.add(userRole);
        }
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Aquí podrías cifrar la contraseña antes de guardar si no lo haces ya en otro lado
        return userRepository.save(user);
    }
}
