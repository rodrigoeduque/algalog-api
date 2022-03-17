package br.com.rodrigoeduque.algalog.api.service;

import br.com.rodrigoeduque.algalog.domain.exception.NegocioException;
import br.com.rodrigoeduque.algalog.domain.exception.NotFoundException;
import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import br.com.rodrigoeduque.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscaEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega buscar(Long id) {

        Entrega entrega = entregaRepository.findById(id).orElseThrow(() -> new NotFoundException("Entrega não Encontrada"));

        return entrega;
    }

}
