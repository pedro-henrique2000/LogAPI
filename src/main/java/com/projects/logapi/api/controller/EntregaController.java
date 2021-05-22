package com.projects.logapi.api.controller;

import com.projects.logapi.api.assembler.EntregaAssembler;
import com.projects.logapi.api.model.EntregaModel;
import com.projects.logapi.api.model.input.EntregaInput;
import com.projects.logapi.domain.model.Entrega;
import com.projects.logapi.domain.repository.EntregaRepository;
import com.projects.logapi.domain.service.FinalizacaoEntregaService;
import com.projects.logapi.domain.service.SolicitacaoEntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    SolicitacaoEntregaService solicitacaoEntregaService;

    @Autowired
    EntregaRepository entregaRepository;

    @Autowired
    FinalizacaoEntregaService finalizacaoEntregaService;

    @Autowired
    EntregaAssembler assembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entrega) {
        Entrega novaEntrega = assembler.toEntity(entrega);
        Entrega solicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return assembler.toModel(solicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaModel> listar() {
        List<Entrega> entregaList = entregaRepository.findAll();
        return assembler.toCollectionModel(entregaList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    return ResponseEntity.ok(assembler.toModel(entrega));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
