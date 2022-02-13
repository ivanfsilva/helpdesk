package br.com.ivanfsilva.helpdesk.resources;

import br.com.ivanfsilva.helpdesk.domain.Tecnico;
import br.com.ivanfsilva.helpdesk.domain.dtos.TecnicoDTO;
import br.com.ivanfsilva.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id ) {
        Tecnico obj = service.findById(id);

        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }
}
