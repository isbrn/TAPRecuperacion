package Entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tarea")
public class Tarea {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_tarea", unique = true, nullable = false)
	private int idTarea;

	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "id_lista")
	private int idLista;
	
	@Column(name = "id_prioridad")
	private int idPrioridad;
	
	@Column(name = "nombre_tarea")
	private String nombreTarea;
	
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_limite")
	private Date fecha;
	
	@Column(name = "estado")
	private String estado;
	
	//Constructores
	public Tarea() {
		
	}

	public Tarea(int idTarea, int idUsuario, int idLista, int idPrioridad, String nombreTarea, String descripcion,
			Date fecha, String estado) {
		super();
		this.idTarea = idTarea;
		this.idUsuario = idUsuario;
		this.idLista = idLista;
		this.idPrioridad = idPrioridad;
		this.nombreTarea = nombreTarea;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estado = estado;
	}
	
	public Tarea(int idUsuario, int idLista, int idPrioridad, String nombreTarea, String descripcion,
			Date fecha, String estado) {
		this.idUsuario = idUsuario;
		this.idLista = idLista;
		this.idPrioridad = idPrioridad;
		this.nombreTarea = nombreTarea;
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.estado = estado;
	}

	//Getters y setters
	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdLista() {
		return idLista;
	}

	public void setIdLista(int idLista) {
		this.idLista = idLista;
	}

	public int getIdPrioridad() {
		return idPrioridad;
	}

	public void setIdPrioridad(int idPrioridad) {
		this.idPrioridad = idPrioridad;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
    @Override
    public String toString() {
        return "Tarea{" +
                "id = " + idTarea +
                ", idUsuario = " + idUsuario +
                ", idLista=" + idLista +
                ", idPrioridad = " + idPrioridad +
                ", nombre = '" + nombreTarea + '\'' +
                ", descripcion = '" + descripcion + '\'' +
                ", estado = '" + estado + '\'' +
                ", fechaLimite = " + fecha +
                '}';
    }
}
