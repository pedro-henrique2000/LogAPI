package com.projects.logapi.domain.service;

import com.projects.logapi.api.model.input.EntregaInput;
import com.projects.logapi.domain.model.Cliente;
import com.projects.logapi.domain.model.Entrega;
import com.projects.logapi.domain.model.StatusEntrega;
import com.projects.logapi.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    EntregaRepository entregaRepository;

    @Autowired
    CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

}
