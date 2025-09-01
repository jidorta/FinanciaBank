package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CuentaBancariaResponseRecord(
        String iban,
        BigDecimal saldo,
        String moneda,
        boolean activa
) {}