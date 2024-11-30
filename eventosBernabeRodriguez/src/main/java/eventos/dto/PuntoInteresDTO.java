package eventos.dto;

public class PuntoInteresDTO {
	private String id;
	private String nombre;
	private String descripcion;
	private float distancia;
	private String url;
	
	public PuntoInteresDTO(String id, String nombre, String descripcion, float distancia, String url) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.url = url;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
