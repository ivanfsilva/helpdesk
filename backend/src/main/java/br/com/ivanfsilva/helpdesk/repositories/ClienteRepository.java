package br.com.ivanfsilva.helpdesk.repositories;

import br.com.ivanfsilva.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
