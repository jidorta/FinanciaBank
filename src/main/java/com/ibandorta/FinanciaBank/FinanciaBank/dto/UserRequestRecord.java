package com.ibandorta.FinanciaBank.FinanciaBank.dto;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Role;
import lombok.Builder;

import java.util.Set;

@Builder
public record UserRequestRecord(

     String username,
     String email,
     String password,
     Set<Role> roles

){}