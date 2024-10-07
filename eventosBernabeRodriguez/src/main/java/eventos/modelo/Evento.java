package eventos.modelo;

public class Evento {

	private String id;
	private String nombre;
	private String descripcion;
	private String organizador;
	private int plazas;
	private boolean cancelado;
	private EventoCategoria categoria;
	private Ocupacion ocupacion;

	public Evento(String id, String nombre, String descripcion, String organizador, int plazas, boolean cancelado,
			EventoCategoria categoria, Ocupacion ocupacion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.organizador = organizador;
		this.plazas = plazas;
		this.cancelado = cancelado;
		this.categoria = categoria;
		this.ocupacion = ocupacion;
	}
	
	public Evento(){
		
	}

	public String getId() {
		return id;
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

}
