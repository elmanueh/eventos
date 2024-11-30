package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import eventos.modelo.EventoCategoria;
import eventos.servicio.IServicioEventos;
import eventos.servicio.ServicioEventos;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AltaEventoWeb implements Serializable{
	private String nombre;
    private String descripcion;
	private String organizador;
	private int numPlazas;
	private String categoria; 
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String espacioFisicoID;

	private IServicioEventos servicioEventos;
    
    private Map<String, String> categorias = new HashMap<>();

    public AltaEventoWeb() {
    	servicioEventos = new ServicioEventos();
    }
    
    public void load() {
    	categorias = new HashMap<>();
    	categorias.put("ACADEMICOS", "ACADEMICOS");
        categorias.put("CULTURALES", "CULTURALES");
        categorias.put("DEPORTES", "DEPORTES");
        categorias.put("ENTRETENIMIENTO", "ENTRETENIMIENTO");
        categorias.put("OTROS", "OTROS");
    }
    public void altaEvento() { // FALTA PARSEAR FECHA Y EVENTO-CATEGORIA
        // comprobación de campos

        try {
           String resultado = servicioEventos.altaEvento(nombre, descripcion, organizador, EventoCategoria.valueOf(categoria), 
        		   fechaInicio, fechaFin, numPlazas, espacioFisicoID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Map<String, String> getCategorias() {
		return categorias;
	}
	public void setCategorias(Map<String, String> categorias) {
		this.categorias = categorias;
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
	public String getOrganizador() {
		return organizador;
	}
	public void setOrganizador(String organizador) {
		this.organizador = organizador;
	}
	public int getNumPlazas() {
		return numPlazas;
	}
	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}
	
	public String getEspacioFisicoID() {
		return espacioFisicoID;
	}
	public void setEspacioFisicoID(String espacioFisicoID) {
		this.espacioFisicoID = espacioFisicoID;
	}
}
