package br.com.rodrigoeduque.algalog.api.service;

import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import br.com.rodrigoeduque.algalog.domain.model.Ocorrencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroOcorrenciaService {

    @Autowired
    private BuscaEntregaService entregaService;

    @Transactional
    public Ocorrencia registrar(Long id , String descricao) {

        Entrega entrega = entregaService.buscar(id);

        Ocorrencia ocorrencia = entrega.adicionarOcorrencia(descricao);

        return ocorrencia;
    }

}
