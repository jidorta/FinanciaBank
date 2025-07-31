package com.ibandorta.FinanciaBank.FinanciaBank.model;

import jakarta.persistence.*;

@Entity
@Table(name= "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private Role name;

    public UserRole() {
    }

    public UserRole(Long id, Role name) {
        this.id = id;
        this.name = name;
    }

    public Role getName() {
        return name;
    }

    public void setName(Role name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
