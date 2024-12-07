package eventos.web;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioFisicoDTO;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.ServicioEspacios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FiltrarEspacioFisicoWeb implements Serializable {
	private List<EspacioFisicoDTO> espaciosFisicos;

	private int capacidad;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;

	private IServicioEspacios ServicioEspacios;

	@Inject
	protected FacesContext facesContext;

	private ResourceBundle bundle;

	public FiltrarEspacioFisicoWeb() {
		ServicioEspacios = new ServicioEspacios();
	}

	@PostConstruct
	public void initBundle() {
		bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());
	}

	public void load() {
		try {
			espaciosFisicos = ServicioEspacios.buscarEspaciosFisicosLibres(fechaInicio, fechaFin, capacidad);
			facesContext.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("busqueda"), bundle.getString("EspaciosFisicosLibres")));
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
		}
	}

	public List<EspacioFisicoDTO> getEspaciosFisicos() {
		return espaciosFisicos;
	}

	public void setEspaciosFisicos(List<EspacioFisicoDTO> espaciosFisicos) {
		this.espaciosFisicos = espaciosFisicos;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
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
}
