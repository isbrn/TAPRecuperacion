package Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import Entities.Usuario;

@Service
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

    @Query
    Usuario findByUsuario(String usuario);
}
