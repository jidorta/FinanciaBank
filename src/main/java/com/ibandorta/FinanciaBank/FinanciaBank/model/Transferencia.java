package com.ibandorta.FinanciaBank.FinanciaBank.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transferencias")
@Builder
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cuenta_origen_id", nullable = false)
    private CuentaBancaria cuentaOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cuenta_destino_id",nullable = false)
    private CuentaBancaria cuentaDestino;

    public Transferencia() {
    }

    public Transferencia(Long id, BigDecimal monto, LocalDateTime fecha, CuentaBancaria cuentaOrigen, CuentaBancaria cuestaDestino) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuestaDestino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }
}
