package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioFisicoDTO;
import eventos.modelo.EventoCategoria;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.IServicioEventos;
import eventos.servicio.ServicioEspacios;
import eventos.servicio.ServicioEventos;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class AltaEventoWeb implements Serializable {
	private String nombre;
	private String descripcion;
	private String organizador;
	private int numPlazas;
	private String categoria;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String espacioFisicoID;

	private IServicioEventos servicioEventos;
    private IServicioEspacios servicioEspacios;

	private List<String> categorias = new ArrayList<>();

    @Inject
	protected FacesContext facesContext;

	private ResourceBundle bundle;

	public AltaEventoWeb() {
		servicioEventos = new ServicioEventos();
        servicioEspacios = new ServicioEspacios();
	}

	@PostConstruct
	public void initBundle() {
		bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());
	}

	public void load() {
		categorias.addAll(List.of(EventoCategoria.values()).stream().map(Enum::name).collect(Collectors.toList()));
	}

	public void altaEvento() {
		try {
			servicioEventos.altaEvento(nombre, descripcion, organizador, EventoCategoria.valueOf(categoria), fechaInicio, fechaFin, numPlazas, espacioFisicoID);
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("alta"), bundle.getString("evento")));
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
		}
	}

    public List<EspacioFisicoDTO> getEspaciosFisicos() {
        if (fechaInicio == null || fechaFin == null || numPlazas <= 0) return new ArrayList<>();

        return servicioEspacios.buscarEspaciosFisicosLibres(fechaInicio, fechaFin, numPlazas);
    }

	public String getEstadoTexto(String estado) {
		return bundle.getString(estado);
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

	public List<String> getCategorias() {
		return categorias.stream()
                .map(categoria -> bundle.containsKey(categoria) ? bundle.getString(categoria) : categoria)
                .collect(Collectors.toList());
	}

	public void setCategorias(List<String> categorias) {
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
