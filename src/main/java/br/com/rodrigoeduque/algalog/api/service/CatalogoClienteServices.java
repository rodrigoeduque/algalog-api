package br.com.rodrigoeduque.algalog.api.service;

import br.com.rodrigoeduque.algalog.domain.exception.NegocioException;
import br.com.rodrigoeduque.algalog.domain.model.Cliente;
import br.com.rodrigoeduque.algalog.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
          .orElseThrow(() -> new NegocioException("Código do Cliente não encontrado ou não existe"));
    }


    @Transactional
    public Cliente salvarCliente(Cliente cliente){

        Boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
          .stream()
          .anyMatch(clienteEmail -> !clienteEmail.equals(cliente));

        if(emailExistente){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail : " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id){
        clienteRepository.deleteById(id);
    }

}
