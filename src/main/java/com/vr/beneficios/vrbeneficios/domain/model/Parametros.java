package com.vr.beneficios.vrbeneficios.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parametros {

    @Id
    @EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String chave;
    private String valor;
    
}
