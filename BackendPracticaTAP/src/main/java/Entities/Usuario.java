package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usuario", unique = true, nullable = false)
	private int idUsuario;

	@Column(name = "nombre_usuario")
	private String usuario;

	@Column(name = "contraseña")
	private String contraseña;

	//Constructores
	public Usuario() {
		
	}

	public Usuario(int idUsuario, String usuario, String contraseña) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public Usuario( String usuario, String contraseña) {
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public Usuario( String usuario) {
		this.usuario = usuario;
	}
	
	//Getters y setters
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
