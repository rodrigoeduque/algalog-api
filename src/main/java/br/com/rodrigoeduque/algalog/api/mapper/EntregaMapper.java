package br.com.rodrigoeduque.algalog.api.mapper;

import br.com.rodrigoeduque.algalog.api.model.EntregaModel;
import br.com.rodrigoeduque.algalog.api.model.request.EntregaRequest;
import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntregaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {

        EntregaModel entregaModel = modelMapper.map(entrega , EntregaModel.class);
        return entregaModel;
    }

    public List<EntregaModel> toModelList(List<Entrega> entregas) {

        List<EntregaModel> models = entregas.stream().map(this::toModel).collect(Collectors.toList());
        return models;
    }

    public Entrega toEntity(EntregaRequest request) {

        Entrega entregaEntity = modelMapper.map(request , Entrega.class);
        return entregaEntity;
    }

}
