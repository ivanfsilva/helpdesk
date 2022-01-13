package br.com.ivanfsilva.helpdesk.repositories;

import br.com.ivanfsilva.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
