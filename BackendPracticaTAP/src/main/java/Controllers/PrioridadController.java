package Controllers;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Entities.Prioridad;
import Repositories.PrioridadRepository;


@RestController
@RequestMapping(path = "/prioridad")
public class PrioridadController {

    @Autowired
    private PrioridadRepository prioridadRepository;
    
    @GetMapping(path="/")
    public @ResponseBody Iterable<Prioridad> getAllPrioridades() {
        return prioridadRepository.findAll();
    }
    
    @GetMapping(path="/{idPrioridad}")
    public @ResponseBody Map<String, String> getPrioridad(@PathVariable int idPrioridad) {
        Optional<Prioridad> prioridad =  prioridadRepository.findById(idPrioridad);
        if (prioridad.isPresent()) {
            return Collections.singletonMap("nombre", prioridad.get().getNombre());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
}
