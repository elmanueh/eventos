package eventos.modelo;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import eventos.repositorio.Identificable;

@Entity
@Table(name = "Evento")
public class Evento implements Identificable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String nombre;
	
	@Lob //Texto más largo de JPA
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	
	@Enumerated
	private EventoCategoria categoria;
	
	@OneToOne
	private Ocupacion ocupacion;
	
	public Evento() {
		
	}

	public Evento(String nombre, String descripcion, String organizador, int plazas, EventoCategoria categoria,
			Ocupacion ocupacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.cancelado = false;
		this.categoria = categoria;
		this.ocupacion = ocupacion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getOrganizador() {
		return organizador;
	}

	public int getPlazas() {
		return plazas;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public EventoCategoria getCategoria() {
		return categoria;
	}

	public Ocupacion getOcupacion() {
		return ocupacion;
	}

	public boolean entreFechas(int mes, int año) {
		return ocupacion.entreFechas(mes, año);
	}

	public void setCancelado() {
		cancelado = true;
	}
}
