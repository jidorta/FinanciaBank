package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaResponseDTO {

    private Long id;
    private Long cuentaOrigenId;
    private Long cuentaDestinoId;

    @DecimalMin(value = "0.01", inclusive = true, message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    private LocalDateTime fecha;

    public TransferenciaResponseDTO() {
    }

    public TransferenciaResponseDTO(Long id, Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto, LocalDateTime fecha) {
        this.id = id;
        this.cuentaOrigenId = cuentaOrigenId;
        this.cuentaDestinoId = cuentaDestinoId;
        this.monto = monto;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public Long getCuentaDestinoId() {
        return cuentaDestinoId;
    }

    public void setCuentaDestinoId(Long cuentaDestinoId) {
        this.cuentaDestinoId = cuentaDestinoId;
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
}
