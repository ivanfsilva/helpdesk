package br.com.ivanfsilva.helpdesk.services;

import br.com.ivanfsilva.helpdesk.domain.Tecnico;
import br.com.ivanfsilva.helpdesk.repositories.TecnicoRepository;
import br.com.ivanfsilva.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById( Integer id ) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }
}
