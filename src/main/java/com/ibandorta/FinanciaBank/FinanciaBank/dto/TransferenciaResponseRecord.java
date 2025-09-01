package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransferenciaResponseRecord(

     Long id,
     Long cuentaOrigenId,
     Long cuentaDestinoId,
    @DecimalMin(value = "0.01", inclusive = true, message = "El monto debe ser mayor a 0")
     BigDecimal monto,
     LocalDateTime fecha
){}
