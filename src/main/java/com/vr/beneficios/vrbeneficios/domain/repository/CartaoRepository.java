package com.vr.beneficios.vrbeneficios.domain.repository;

import com.vr.beneficios.vrbeneficios.domain.model.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    
}
