package com.vr.beneficios.vrbeneficios.api.model.input;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransacaoInput {
    
    @NotNull(message = "Número do cartão é obrigatório")
    @Min(value = 1, message = "Número do cartão deve ser maior que zero")
    private Long numeroCartao;
      
    @NotNull(message = "Senha do cartão é obrigatório")
    @NotBlank(message = "Senha do cartão é obrigatório")
    @NotEmpty(message = "Senha do cartão é obrigatório")
    private String senhaCartao;

    @NotNull(message = "Número do cartão é obrigatório")
    @Min(value = 1, message = "Número do cartão deve ser maior que zero")
    private BigDecimal valor;

}
