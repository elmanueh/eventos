package eventos.modelo;

public class EspacioFisico {

	private int id;
	private String nombre;
	private int capacidad;
	private String direccion;
	private float longitud;
	private float latitud;
	private PuntoInteres[] puntosInteres;
	private String descripcion;
	private EspacioFisicoEstado estado;

	public EspacioFisico(int id, String nombre, int capacidad, String direccion, float longitud, float latitud,
			PuntoInteres[] puntosInteres, String descripcion, EspacioFisicoEstado estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.puntosInteres = puntosInteres;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	
	public EspacioFisico(){
		
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public float getLongitud() {
		return longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public PuntoInteres[] getPuntosInteres() {
		return puntosInteres;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public EspacioFisicoEstado getEstado() {
		return estado;
	}

}
