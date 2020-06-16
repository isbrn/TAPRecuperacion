package Controllers;


import java.text.SimpleDateFormat;
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

import Entities.Tarea;
import Repositories.TareaRepository;

@RestController
@RequestMapping(path = "/tarea")
public class TareaController {

	@Autowired
    private TareaRepository tareaRepository;

	
    @PostMapping(path="/")
    public @ResponseBody Tarea añadirTarea (@RequestParam int idUsuario,
                                            @RequestParam int idLista,
                                            @RequestParam int idPrioridad,
                                            @RequestParam String nombreTarea,
                                            @RequestParam String descripcion,
                                            @RequestParam String fecha,
                                            @RequestParam String estado) {
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
            Tarea nuevaTarea = new Tarea(idUsuario, idLista, idPrioridad, nombreTarea, descripcion, formatter.parse(fecha), estado);
            tareaRepository.save(nuevaTarea);
            return nuevaTarea;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
        }
    }
	
    @GetMapping(path="/")
    public @ResponseBody Iterable<Tarea> getAllTareas() {
        return tareaRepository.findAll();
    }

    @GetMapping(path="/{idTarea}")
    public @ResponseBody 
    Optional<Tarea> getTareas(@PathVariable int idTarea) {
        return tareaRepository.findById(idTarea);
    }

    
    @PutMapping(path="/{idTarea}")
    public @ResponseBody Tarea modificarTarea(@PathVariable int idTarea,
                                           @RequestParam String nombreTarea,
                                           @RequestParam String descripcion,
                                           @RequestParam String estado,
                                           @RequestParam int idPrioridad,
                                           @RequestParam String fecha) {
    	
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
    	Tarea tareaMod = tareaRepository.findById(idTarea).get();
    	
        if(tareaMod instanceof Tarea) {
        	try {
        		tareaMod.setNombreTarea(nombreTarea);
        		tareaMod.setDescripcion(descripcion);
        		tareaMod.setEstado(estado);
        		tareaMod.setIdPrioridad(idPrioridad);
        		tareaMod.setFecha(formatter.parse(fecha));
        		
        		tareaRepository.save(tareaMod);
        		
        		return tareaMod;
        		
        	}catch(Exception e) {
        		e.printStackTrace();
        		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
        	}
        	
        }
        	return tareaMod;
    }
    
    
    @DeleteMapping(path = "/{idTarea}")
    public @ResponseBody String borrarTarea(@PathVariable int idTarea) {
        Optional<Tarea> dbTask = tareaRepository.findById(idTarea);
        if (dbTask.isPresent()) {
            Tarea borrarTarea = dbTask.get();
            tareaRepository.delete(borrarTarea);

            throw new ResponseStatusException(HttpStatus.OK, "Eliminada");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
   
}
