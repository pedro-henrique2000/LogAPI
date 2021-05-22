package com.projects.logapi.api.assembler;

import com.projects.logapi.api.model.OcorrenciaModel;
import com.projects.logapi.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaAssembler {

    @Autowired
    ModelMapper modelMapper;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toModelCollection(List<Ocorrencia> ocorrenciaList) {
        return ocorrenciaList.stream().map(ocorrencia -> modelMapper.map(ocorrencia, OcorrenciaModel.class)).collect(Collectors.toList());
    }

}
