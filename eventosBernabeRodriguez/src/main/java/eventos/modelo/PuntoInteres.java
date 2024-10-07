package eventos.modelo;

public class PuntoInteres {

	private String nombre;
	private String descripcion;
	private int distancia;
	private String url;

	public PuntoInteres(String nombre, String descripcion, int distancia, String url) {
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

	public int getDistancia() {
		return distancia;
	}

	public String getUrl() {
		return url;
	}

}
