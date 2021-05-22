package com.projects.logapi.domain.service;

import com.projects.logapi.domain.model.Entrega;
import com.projects.logapi.domain.model.StatusEntrega;
import com.projects.logapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizacaoEntregaService {

    @Autowired
    BuscaEntregaService buscaEntregaService;

    @Autowired
    EntregaRepository entregaRepository;

    public void finalizar(Long id) {
        Entrega entrega = buscaEntregaService.buscar(id);
        entrega.finalizar();
        entregaRepository.save(entrega);
    }

}
