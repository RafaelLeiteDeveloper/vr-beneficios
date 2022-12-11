package com.vr.beneficios.vrbeneficios.api.model.input;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class CartaoInput {

    @DecimalMin(value = "0", message = "Número do cartão deve ser maior que zero")
    @NotNull(message = "Número do cartão é obrigatório")
    private Long numeroCartao;
    
    @NotNull(message = "Senha do cartão é obrigatório")
    @NotBlank(message = "Senha do cartão é obrigatório")
    @NotEmpty(message = "Senha do cartão é obrigatório")
    private String senha;
}
