package com.ibandorta.FinanciaBank.FinanciaBank.model;

import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;
@Builder
@Table(name="users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(Long id, String username, String password, String email, String telefono, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
