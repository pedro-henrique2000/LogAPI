package com.projects.logapi.api.assembler;

import com.projects.logapi.api.model.EntregaModel;
import com.projects.logapi.api.model.input.EntregaInput;
import com.projects.logapi.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaAssembler {

    @Autowired
    ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregaList) {
        return entregaList.stream().map(this::toModel).collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput input) {
        return modelMapper.map(input, Entrega.class);
    }

}
