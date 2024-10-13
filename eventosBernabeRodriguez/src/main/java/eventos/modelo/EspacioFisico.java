package eventos.modelo;

public class EspacioFisico {
import java.util.LinkedList;


	private String id;
	private String nombre;
	private String propietario;
	private int capacidad;
	private String direccion;
	private double longitud;
	private double latitud;
	private LinkedList<PuntoInteres> puntosInteres;
	private String descripcion;
	private EspacioFisicoEstado estado;

	public EspacioFisico(String nombre, String propietario, int capacidad, String direccion, double latitud,
			double longitud, String descripcion) {
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.descripcion = descripcion;

		this.estado = EspacioFisicoEstado.ACTIVO;
		this.puntosInteres = new LinkedList<PuntoInteres>();
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

	public int getCapacidad() {
		return capacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public LinkedList<PuntoInteres> getPuntosInteres() {
		return puntosInteres;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getPropietario() {
		return propietario;
	}

	public EspacioFisicoEstado getEstado() {
		return estado;
	}

}
