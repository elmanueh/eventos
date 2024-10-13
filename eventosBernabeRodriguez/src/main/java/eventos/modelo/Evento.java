package eventos.modelo;

import eventos.repositorio.Identificable;

public class Evento implements Identificable {

	private String id;
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	private EventoCategoria categoria;
	private Ocupacion ocupacion;

	public Evento(String nombre, String descripcion, String organizador, int plazas, EventoCategoria categoria,
			Ocupacion ocupacion) {
		this.id = "1";
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.cancelado = false;
		this.categoria = categoria;
		this.ocupacion = ocupacion;
	}

	public Evento() {

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
