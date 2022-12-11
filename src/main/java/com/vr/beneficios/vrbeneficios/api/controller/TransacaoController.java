package com.vr.beneficios.vrbeneficios.api.controller;


import javax.validation.Valid;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.service.TransacaoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transacoes")
@AllArgsConstructor
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity adicionar(@RequestBody @Valid TransacaoInput transacaoInput) {
		transacaoService.realizarTransacao(transacaoInput);
		return ResponseEntity.ok("OK");
	}
    
}
