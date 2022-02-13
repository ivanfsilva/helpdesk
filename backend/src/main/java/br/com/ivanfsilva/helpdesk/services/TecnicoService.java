package br.com.ivanfsilva.helpdesk.services;

import br.com.ivanfsilva.helpdesk.domain.Pessoa;
import br.com.ivanfsilva.helpdesk.domain.Tecnico;
import br.com.ivanfsilva.helpdesk.domain.dtos.TecnicoDTO;
import br.com.ivanfsilva.helpdesk.repositories.PessoaRepository;
import br.com.ivanfsilva.helpdesk.repositories.TecnicoRepository;
import br.com.ivanfsilva.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.ivanfsilva.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById( Integer id ) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCPFeEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);

        return repository.save(newObj);
    }

    private void validaPorCPFeEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
