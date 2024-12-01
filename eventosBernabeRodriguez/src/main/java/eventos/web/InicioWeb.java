package eventos.web;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class InicioWeb implements Serializable{
	private String nombre;
	
	@Inject
	protected FacesContext facesContext;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void cambiarAOrganizador() {
		try {
		    facesContext.getExternalContext().redirect("eventos/espaciosFisicosFiltrados.xhtml");
		} catch (IOException e) {
		    facesContext.addMessage(null,
		                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
		    e.printStackTrace();
		}
	}
	
	public void cambiarAPropietario() {
		if (nombre != null && !nombre.isEmpty()) {
			try {
			    facesContext.getExternalContext().redirect("eventos/espaciosFisicosCreados.xhtml?nombre="+ nombre);
			} catch (IOException e) {
			    facesContext.addMessage(null,
			                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
			    e.printStackTrace();
			}
		}
		else
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre no puede ser vacío"));
	}
	
}
