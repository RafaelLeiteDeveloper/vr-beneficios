package com.vr.beneficios.vrbeneficios.domain.service;

import javax.transaction.Transactional;

import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
	private CartaoRepository cartaoRepository;

    public Cartao buscarOuFalhar(Long numeroCartao) {
		return cartaoRepository.findById(numeroCartao)
			.orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"Cartão não encontrado"));
	}

    @Transactional
	public Cartao salvar(Cartao cartao) {
		return cartaoRepository.save(cartao);	
	}
    
}
