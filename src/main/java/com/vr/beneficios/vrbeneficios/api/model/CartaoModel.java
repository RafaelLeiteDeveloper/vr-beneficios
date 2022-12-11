package com.vr.beneficios.vrbeneficios.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartaoModel {
    
    private Long numeroCartao;
    private String senha;
    private BigDecimal saldo;
}
