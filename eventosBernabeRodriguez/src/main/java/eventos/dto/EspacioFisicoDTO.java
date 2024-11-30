package eventos.dto;

public class EspacioFisicoDTO {
	private String id;
    private String nombre;
    private String propietario;
    private int capacidad;
    private String direccion;
    private String descripcion;
    
	public EspacioFisicoDTO(String id, String nombre, String propietario, int capacidad, String direccion, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.capacidad = capacidad;
		this.direccion = direccion;
		this.descripcion = descripcion;
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
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "EspacioFisicoDTO [id=" + id + ", nombre=" + nombre + ", propietario=" + propietario + ", capacidad=" + capacidad + ", direccion="
				+ direccion + ", descripcion=" + descripcion + "]";
	}
	
	
	
	
}
