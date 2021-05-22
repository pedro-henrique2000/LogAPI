package com.projects.logapi.domain.service;

import com.projects.logapi.domain.exception.EntidadeNaoEncontradaException;
import com.projects.logapi.domain.model.Entrega;
import com.projects.logapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaEntregaService {

    @Autowired
    EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {
        return entregaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
