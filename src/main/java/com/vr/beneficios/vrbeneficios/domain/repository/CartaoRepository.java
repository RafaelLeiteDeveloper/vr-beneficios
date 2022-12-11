package com.vr.beneficios.vrbeneficios.domain.repository;

import java.math.BigDecimal;
import java.util.Optional;

import com.vr.beneficios.vrbeneficios.domain.model.Cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    Optional<Cartao> findByNumeroCartaoAndSaldoGreaterThanEqual(Long numeroCartao, BigDecimal valor);
    Optional<Cartao> findByNumeroCartaoAndSenha(Long numeroCartao, String senha);
}
