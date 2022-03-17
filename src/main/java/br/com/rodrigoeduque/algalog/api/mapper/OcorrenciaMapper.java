package br.com.rodrigoeduque.algalog.api.mapper;

import br.com.rodrigoeduque.algalog.api.model.EntregaModel;
import br.com.rodrigoeduque.algalog.api.model.OcorrenciaModel;
import br.com.rodrigoeduque.algalog.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OcorrenciaMapper {

    @Autowired
    private ModelMapper mapper;

    public OcorrenciaModel toModel (Ocorrencia ocorrencia){

        OcorrenciaModel model = mapper.map(ocorrencia , OcorrenciaModel.class);
        return model;
    }

    public List<OcorrenciaModel> toModelList (List<Ocorrencia> ocorrencias){

        List<OcorrenciaModel> models = ocorrencias.stream().map(this::toModel).collect(Collectors.toList());
        return models;
    }

}
