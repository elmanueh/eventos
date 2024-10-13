package eventos.modelo;

public class PuntoInteres {

	private String nombre;
	private String descripcion;
	private String distancia;
	private String url;

	public PuntoInteres(String nombre, String descripcion, String distancia, String url) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.distancia = distancia;
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getDistancia() {
		return distancia;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return "PuntoInteres\n\t[nombre=" + nombre + 
				",\n\tdescripcion=" + descripcion + 
				",\n\tdistancia=" + distancia + 
				",\n\turl="
				+ url + "]\n";
	}
	
	

}
