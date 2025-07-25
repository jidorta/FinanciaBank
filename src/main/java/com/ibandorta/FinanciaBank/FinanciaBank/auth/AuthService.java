package com.ibandorta.FinanciaBank.FinanciaBank.auth;

import com.ibandorta.FinanciaBank.FinanciaBank.config.JwtService;
import com.ibandorta.FinanciaBank.FinanciaBank.model.CustomUserDetails;
import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import com.ibandorta.FinanciaBank.FinanciaBank.model.User;
import com.ibandorta.FinanciaBank.FinanciaBank.model.UserRole;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRepository;
import com.ibandorta.FinanciaBank.FinanciaBank.repository.UserRoleRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserRoleRepository userRoleRepository;


    public AuthService (UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.userRoleRepository = userRoleRepository;
    }

    public AuthResponse register(AuthRequest request) {
        UserRole userRole = userRoleRepository.findByName(Role.USER)
                .orElseThrow(() -> new RuntimeException("Role no encontrado"));

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(new UserRole()))
                .build();
        userRepository.save(user);

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request){
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);

        String token = jwtService.generateToken(userDetails);
        return new AuthResponse(token);
    }


}
