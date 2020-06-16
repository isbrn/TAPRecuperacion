package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lista")
public class Lista {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_lista", unique = true, nullable = false)
	private int idLista;

	@Column(name = "nombre_lista")
	private String nombreLista;

	//Constructores
	public Lista() {
		
	}

	public Lista(int idLista, String nombreLista) {
		this.idLista = idLista;
		this.nombreLista = nombreLista;
	}

	public Lista(String nombreLista) {
		this.nombreLista = nombreLista;
	}

	//Getters y Setters
	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public String getNombreLista() {
		return nombreLista;
	}

	public void setNombreLista(String nombreLista) {
		this.nombreLista = nombreLista;
	}
	
    @Override
    public String toString() {
        return "Lista{" +
                "id = " + idLista +
                ", nombre = '" + nombreLista + '\'' +
                '}';
    }
	
}
