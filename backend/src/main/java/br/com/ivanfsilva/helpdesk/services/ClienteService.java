package br.com.ivanfsilva.helpdesk.services;

import br.com.ivanfsilva.helpdesk.domain.Pessoa;
import br.com.ivanfsilva.helpdesk.domain.Cliente;
import br.com.ivanfsilva.helpdesk.domain.dtos.ClienteDTO;
import br.com.ivanfsilva.helpdesk.repositories.ClienteRepository;
import br.com.ivanfsilva.helpdesk.repositories.PessoaRepository;
import br.com.ivanfsilva.helpdesk.services.exceptions.DataIntegrityViolationException;
import br.com.ivanfsilva.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById( Integer id ) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id ));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCPFeEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);

        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);

        if(!objDTO.getSenha().equals(oldObj.getSenha()))
            objDTO.setSenha(encoder.encode(objDTO.getSenha()));

        validaPorCPFeEmail(objDTO);
        oldObj = new Cliente(objDTO);

        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser excluido!");
        }
        repository.deleteById(id);
    }

    private void validaPorCPFeEmail(ClienteDTO objDTO) {
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
