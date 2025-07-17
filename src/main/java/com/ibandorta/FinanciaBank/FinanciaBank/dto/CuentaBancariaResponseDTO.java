package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class CuentaBancariaResponseDTO {
    private String iban;
    private BigDecimal saldo;
    private String moneda;
    private boolean activa;

    public CuentaBancariaResponseDTO() {
    }

    public CuentaBancariaResponseDTO(String iban, BigDecimal saldo, String moneda, boolean activa) {
        this.iban = iban;
        this.saldo = saldo;
        this.moneda = moneda;
        this.activa = activa;
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }
}
