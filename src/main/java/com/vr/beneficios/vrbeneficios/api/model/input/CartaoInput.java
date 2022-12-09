package com.vr.beneficios.vrbeneficios.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartaoInput {

    @NotNull(message = "Número do cartão é obrigatório")
    private Long numeroCartao;
    
    @NotNull(message = "Senha do cartão é obrigatório")
    @NotBlank(message = "Senha do cartão é obrigatório")
    @NotEmpty(message = "Senha do cartão é obrigatório")
    private String senha;
}
