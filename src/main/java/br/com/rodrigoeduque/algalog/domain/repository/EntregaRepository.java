package br.com.rodrigoeduque.algalog.domain.repository;

import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

}
