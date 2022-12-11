package com.vr.beneficios.vrbeneficios.api.model.input;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class CartaoInput {

    @NotNull(message = "Número do cartão é obrigatório")
    @Min(value = 1, message = "Número do cartão deve ser maior que zero")
    private Long numeroCartao;
    
    @NotNull(message = "Senha do cartão é obrigatório")
    @NotBlank(message = "Senha do cartão é obrigatório")
    @NotEmpty(message = "Senha do cartão é obrigatório")
    private String senha;
}
