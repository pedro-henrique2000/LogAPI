package com.projects.logapi.api.controller;

import com.projects.logapi.api.assembler.OcorrenciaAssembler;
import com.projects.logapi.api.model.OcorrenciaModel;
import com.projects.logapi.api.model.input.OcorrenciaInput;
import com.projects.logapi.domain.model.Entrega;
import com.projects.logapi.domain.model.Ocorrencia;
import com.projects.logapi.domain.service.BuscaEntregaService;
import com.projects.logapi.domain.service.RegistroOcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    RegistroOcorrenciaService registroOcorrenciaService;

    @Autowired
    OcorrenciaAssembler assembler;

    @Autowired
    BuscaEntregaService buscaEntregaService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId, @RequestBody @Valid OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
        return assembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);
        List<Ocorrencia> ocorrencias = entrega.getOcorrencias();

        return assembler.toModelCollection(ocorrencias);
    }

}
