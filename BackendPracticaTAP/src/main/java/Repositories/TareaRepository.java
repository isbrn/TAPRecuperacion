package Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import Entities.Tarea;

@Service
public interface TareaRepository extends CrudRepository<Tarea, Integer>{
	
}
