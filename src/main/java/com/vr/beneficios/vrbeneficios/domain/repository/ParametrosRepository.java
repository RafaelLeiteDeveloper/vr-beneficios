package com.vr.beneficios.vrbeneficios.domain.repository;

import java.util.Optional;

import com.vr.beneficios.vrbeneficios.domain.model.Parametros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrosRepository extends JpaRepository<Parametros, Long> {
    Optional<Parametros> findByChave(String chave);
}
