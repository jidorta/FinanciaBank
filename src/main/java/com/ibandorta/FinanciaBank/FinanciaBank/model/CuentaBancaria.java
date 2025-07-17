package com.ibandorta.FinanciaBank.FinanciaBank.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
@Table(name ="cuentas_bancarias")
@Builder
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(unique = true, nullable = false)
   private String iban;

   @Column(nullable = false)
    private BigDecimal saldo;

   @Column(nullable = false)
   private String moneda;

   @Column(nullable = false)
   private boolean activa = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable = false)
    private User usuario;

    public CuentaBancaria() {
    }

    public CuentaBancaria(Long id, String iban, BigDecimal saldo, String moneda, boolean activa, User usuario) {
        this.id = id;
        this.iban = iban;
        this.saldo = saldo;
        this.moneda = moneda;
        this.activa = activa;
        this.usuario = usuario;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
