package com.projects.logapi.domain.repository;

import com.projects.logapi.domain.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
}
