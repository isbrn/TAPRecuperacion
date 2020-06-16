package Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import Entities.Lista;

@Service
public interface ListaRepository extends CrudRepository<Lista, Integer>{

}
