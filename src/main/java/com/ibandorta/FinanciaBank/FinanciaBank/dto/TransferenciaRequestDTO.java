package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TransferenciaRequestDTO {

    @NotNull
    private Long cuentaOrigenId;

    @NotNull
    private Long cuentaDestinoId;

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    public TransferenciaRequestDTO() {
    }

    public TransferenciaRequestDTO(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto) {
        this.cuentaOrigenId = cuentaOrigenId;
        this.cuentaDestinoId = cuentaDestinoId;
        this.monto = monto;
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
}
