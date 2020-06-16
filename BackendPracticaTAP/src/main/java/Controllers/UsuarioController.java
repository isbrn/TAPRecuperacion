package Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Entities.Usuario;
import Repositories.UsuarioRepository;

@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {

	@Autowired
    private UsuarioRepository usuarioRepository;
	
    @PostMapping(path="/")
    public @ResponseBody Usuario añadirUsuario (@RequestParam String usuario, @RequestParam String contraseña) {
    	Usuario nuevoUsuario = new Usuario(usuario, contraseña);
        try {
        	nuevoUsuario = usuarioRepository.save(nuevoUsuario);
            return nuevoUsuario;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
        }
    }   
    
    @PostMapping(path="/login")
    public @ResponseBody Usuario login(@RequestParam String nombreUsuario, @RequestParam String contraseña) {
        Usuario usuario = usuarioRepository.findByUsuario(nombreUsuario);

        System.out.println(usuario.toString());
        System.out.println("Usuario: " + nombreUsuario + " Contraseña: " + contraseña);
        if (nombreUsuario.equals(usuario.getUsuario()) && contraseña.equals(usuario.getContraseña())){
            return usuario;
        }
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
    }

    @PutMapping(path="/{idUsuario}")
    public @ResponseBody Usuario modificarUsuario(@PathVariable int idUsuario, @RequestParam String contraseña) {

        Usuario userMod = usuarioRepository.findById(idUsuario).get();
        if (userMod instanceof Usuario) {
        	
        	try{
        		userMod.setContraseña(contraseña);
        		usuarioRepository.save(userMod);

        		return userMod;
            
        	} catch (Exception e) {
        		e.printStackTrace();
        		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Error");
        	}
        }
        return userMod;
    }
    
    @DeleteMapping(path = "/{idUsuario}")
    public @ResponseBody String borrarUsuario(@PathVariable int idUsuario) {
        Optional<Usuario> dbUser = usuarioRepository.findById(idUsuario);
        if (dbUser.isPresent()) {
        	Usuario borrarUsuario = dbUser.get();
        	usuarioRepository.delete(borrarUsuario);

            throw new ResponseStatusException(HttpStatus.OK, "Eliminado");
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
    }
}
