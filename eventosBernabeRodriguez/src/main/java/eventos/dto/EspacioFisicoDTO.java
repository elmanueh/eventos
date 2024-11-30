package eventos.dto;

import java.util.List;

public class EspacioFisicoDTO {
	private String id;
    private String nombre;
    private String propietario;
    private int capacidad;
    private String direccion;
    private double longitud;
    private double latitud;
    private String descripcion;
    private String estado;
    private List<PuntoInteresDTO> puntosInteres;
    
	public EspacioFisicoDTO(String id, String nombre, String propietario, int capacidad, String direccion, double longitud, double latitud,
			String descripcion, String estado, List<PuntoInteresDTO> puntosInteres) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.longitud = longitud;
		this.latitud = latitud;
		this.descripcion = descripcion;
		this.estado = estado;
		this.puntosInteres = puntosInteres;
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
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPropietario() {
		return propietario;
	}
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<PuntoInteresDTO> getPuntosInteres() {
		return puntosInteres;
	}
	public void setPuntosInteres(List<PuntoInteresDTO> puntosInteres) {
		this.puntosInteres = puntosInteres;
	}
    
    
}
