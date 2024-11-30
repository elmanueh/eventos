package eventos.web;

import java.io.Serializable;
import java.util.List;

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
public class MisEspaciosFisicosWeb implements Serializable {
	private List<EspacioFisicoDTO> espaciosFisicos;

	private String propietario;

	private IServicioEspacios ServicioEspacios;

	@Inject
	protected FacesContext facesContext;

	public MisEspaciosFisicosWeb() {
		ServicioEspacios = new ServicioEspacios();
		
		//Inicialización inicial
		propietario = "Público";
	}

	public void load() {
		try {				
			espaciosFisicos = ServicioEspacios.obtenerMisEspaciosFisicos(propietario);
			
			System.out.println(espaciosFisicos.toString());
		} catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los espacios físicos."));
		}
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
