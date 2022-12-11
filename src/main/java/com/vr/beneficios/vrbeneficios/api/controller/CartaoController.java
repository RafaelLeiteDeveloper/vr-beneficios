package com.vr.beneficios.vrbeneficios.api.controller;


import javax.validation.Valid;

import com.vr.beneficios.vrbeneficios.api.assembler.CartaoAssembler;
import com.vr.beneficios.vrbeneficios.api.assembler.CartaoDisassembler;
import com.vr.beneficios.vrbeneficios.api.model.CartaoModel;
import com.vr.beneficios.vrbeneficios.api.model.CartaoModelSaldo;
import com.vr.beneficios.vrbeneficios.api.model.input.CartaoInput;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.service.CartaoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cartoes")
@AllArgsConstructor
public class CartaoController {

    private final CartaoService cartaoService;
    private final CartaoAssembler cartaoAssembler;
    private final CartaoDisassembler cartaoDisassembler;

    @GetMapping("/{numeroCartao}")
	public CartaoModelSaldo buscar(@PathVariable Long numeroCartao) {
		Cartao cartao = cartaoService.buscarOuFalhar(numeroCartao);
		
		return cartaoAssembler.toModelSaldo(cartao);
	}

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CartaoModel adicionar(@RequestBody @Valid CartaoInput cartaoInput) {
		Cartao cartao = cartaoDisassembler.toDomainObject(cartaoInput);
		cartao = cartaoService.salvar(cartao);
		
		return cartaoAssembler.toModel(cartao);
	}
    
}
