package com.vr.beneficios.vrbeneficios.api.assembler;

import com.vr.beneficios.vrbeneficios.api.model.input.CartaoInput;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoDisassembler {

    @Autowired
	private ModelMapper modelMapper;
	
	public Cartao toDomainObject(CartaoInput cartaoInput) {
		return modelMapper.map(cartaoInput, Cartao.class);
	}
    
}
