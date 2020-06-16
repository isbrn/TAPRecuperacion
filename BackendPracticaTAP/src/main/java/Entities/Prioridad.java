package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prioridad")
public class Prioridad {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_prioridad", unique = true, nullable = false)
	private int idPrioridad;

	@Column(name = "nombre")
	private String nombre;
	
	//Constructores
	public Prioridad() {
		
	}

	public Prioridad(int idPrioridad, String nombre) {
		this.idPrioridad = idPrioridad;
		this.nombre = nombre;
	}

	//Getters
	public int getIdPrioridad() {
		return idPrioridad;
	}

	public String getNombre() {
		return nombre;
	}
	
}
