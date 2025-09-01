package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import lombok.Builder;

@Builder
public record RegisterRequestRecord(
        String username,
        String password
) {}