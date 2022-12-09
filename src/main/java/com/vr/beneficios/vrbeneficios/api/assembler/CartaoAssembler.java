package com.vr.beneficios.vrbeneficios.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import com.vr.beneficios.vrbeneficios.api.model.CartaoModel;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartaoAssembler {

    @Autowired
	private ModelMapper modelMapper;
	
	public CartaoModel toModel(Cartao cartao) {
		return modelMapper.map(cartao, CartaoModel.class);
	}
	
    
}
