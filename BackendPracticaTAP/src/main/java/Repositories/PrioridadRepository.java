package Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import Entities.Prioridad;

@Service
public interface PrioridadRepository extends CrudRepository<Prioridad, Integer>{

	@Query
	Prioridad findByIdPrioridad(int idPrioridad);
	
	@Query
	Prioridad findByNombre(String nombre);
}
