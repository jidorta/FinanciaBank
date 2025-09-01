package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransferenciaRequestRecord(

    @NotNull
     Long cuentaOrigenId,

    @NotNull
    Long cuentaDestinoId,

    @NotNull
    @DecimalMin(value = "0.01", inclusive = true, message = "El monto debe ser mayor a 0")
     BigDecimal monto

){}
