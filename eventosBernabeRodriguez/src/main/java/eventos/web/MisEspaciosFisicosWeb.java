package eventos.web;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import eventos.dto.EspacioFisicoDTO;
import eventos.modelo.EspacioFisicoEstado;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.RepositorioException;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.ServicioEspacios;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class MisEspaciosFisicosWeb implements Serializable {
	private List<EspacioFisicoDTO> espaciosFisicos;

	private EspacioFisicoDTO espacioSeleccionado;
	private String propietario;

	private IServicioEspacios ServicioEspacios;

	@Inject
	protected FacesContext facesContext;
	
	private ResourceBundle bundle;

	public MisEspaciosFisicosWeb() {
		ServicioEspacios = new ServicioEspacios();
	}
	
	@PostConstruct
	public void initBundle() {
	    bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());
	}

	public void load() {
		try {
			espaciosFisicos = ServicioEspacios.obtenerMisEspaciosFisicos(propietario);
		} catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
		}
	}

	public void cambiarEstado() {
		if (espacioSeleccionado != null) {
			try {
				if (espacioSeleccionado.getEstado().equals(EspacioFisicoEstado.ACTIVO.toString())) {
					ServicioEspacios.bajaEspacioFisico(espacioSeleccionado.getId());
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("cambioEstado"), bundle.getString(EspacioFisicoEstado.CERRADO_TEMPORALMENTE.name())));
				} else if (espacioSeleccionado.getEstado().equals(EspacioFisicoEstado.CERRADO_TEMPORALMENTE.name())) {
					ServicioEspacios.activarEspacioFisico(espacioSeleccionado.getId());
					facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("cambioEstado"), bundle.getString(EspacioFisicoEstado.ACTIVO.name())));
				}
				
			} catch (RepositorioException | EntidadNoEncontrada e) {
				e.printStackTrace();
				facesContext.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
			}
			load();
		}
	}
	
	public String getEstadoTexto(String estado) {
        return bundle.getString(estado);
    }

	public EspacioFisicoDTO getEspacioSeleccionado() {
		return espacioSeleccionado;
	}

	public void setEspacioSeleccionado(EspacioFisicoDTO espacioSeleccionado) {
		this.espacioSeleccionado = espacioSeleccionado;
	}

	public List<EspacioFisicoDTO> getEspaciosFisicos() {
		return espaciosFisicos;
	}

	public void setEspaciosFisicos(List<EspacioFisicoDTO> espaciosFisicos) {
		this.espaciosFisicos = espaciosFisicos;
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
}
