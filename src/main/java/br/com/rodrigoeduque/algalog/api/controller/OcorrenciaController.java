package br.com.rodrigoeduque.algalog.api.controller;

import br.com.rodrigoeduque.algalog.api.mapper.OcorrenciaMapper;
import br.com.rodrigoeduque.algalog.api.model.OcorrenciaModel;
import br.com.rodrigoeduque.algalog.api.model.request.OcorrenciaRequest;
import br.com.rodrigoeduque.algalog.api.service.BuscaEntregaService;
import br.com.rodrigoeduque.algalog.api.service.RegistroOcorrenciaService;
import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import br.com.rodrigoeduque.algalog.domain.model.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private RegistroOcorrenciaService ocorrenciaService;

    @Autowired
    private OcorrenciaMapper mapper;

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long id , @Valid @RequestBody OcorrenciaRequest request) {

        Ocorrencia ocorrenciaRegistrada = ocorrenciaService.registrar(id , request.getDescricao());

        return mapper.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long id){

        Entrega entrega = buscaEntregaService.buscar(id);

        List<Ocorrencia> ocorrencias = entrega.getOcorrencias();

        List<OcorrenciaModel> ocorrenciaModels = mapper.toModelList(ocorrencias);

        return ocorrenciaModels;
    }

}

