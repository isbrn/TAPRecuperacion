package Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Entities.Lista;
import Repositories.ListaRepository;

@RestController
@RequestMapping(path = "/lista")
public class ListaController {

    @Autowired
    private ListaRepository listaRepository;
    
    
    @PostMapping(path="/")
    public @ResponseBody Lista añadirLista (@RequestParam String nombreLista) {
    	Lista nuevaLista = new Lista(nombreLista);
        try {
        	listaRepository.save(nuevaLista);
            return nuevaLista;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
        }
    }

    @GetMapping(path="/")
    public @ResponseBody Iterable<Lista> getAll() {
        return listaRepository.findAll();
    }

    @GetMapping(path="/{idLista}")
    public @ResponseBody
    Optional<Lista> getLista(@PathVariable int idLista) {
        return listaRepository.findById(idLista);
    }

    @PutMapping(path="/{idLista}")
    public @ResponseBody Lista modificarLista(@PathVariable int idLista, @RequestParam String nombreLista) {
        Optional<Lista> dbLista = listaRepository.findById(idLista);
        if (dbLista.isPresent()) {
            Lista listaModificada = dbLista.get();
            listaModificada.setNombreLista(nombreLista);

            listaRepository.save(listaModificada);

            return listaModificada;
        }

        throw new ResponseStatusException(HttpStatus.NOT_MODIFIED, "Unable to find resource");
    }

    @DeleteMapping(path = "/{idLista}")
    public @ResponseBody String borrarLista(@PathVariable int idLista) {
        Optional<Lista> dbList = listaRepository.findById(idLista);
        if (dbList.isPresent()) {
            Lista listaBorrada = dbList.get();
            listaRepository.delete(listaBorrada);

            throw new ResponseStatusException(HttpStatus.OK, "Eliminada");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
}
