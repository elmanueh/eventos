package eventos.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class InicioWeb implements Serializable{
	private String nombre;
	
	@Inject
	protected FacesContext facesContext;
	
	private ResourceBundle bundle;

	@PostConstruct
	public void initBundle() {
	    bundle = ResourceBundle.getBundle("i18n.text", facesContext.getViewRoot().getLocale());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void cambiarAOrganizador() {
		if (nombre != null && !nombre.isEmpty()) {
			try {
			    facesContext.getExternalContext().redirect("eventos/espaciosFisicosFiltrados.xhtml");
			} catch (IOException e) {
			    facesContext.addMessage(null,
			                    new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
			    e.printStackTrace();
			}
		}
		else
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), bundle.getString("nombreVacio")));

	}
	
	public void cambiarAPropietario() {
		if (nombre != null && !nombre.isEmpty()) {
			try {
			    facesContext.getExternalContext().redirect("eventos/espaciosFisicosCreados.xhtml?nombre="+ nombre);
			} catch (IOException e) {
			    facesContext.addMessage(null,
			                    new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), e.getMessage()));
			    e.printStackTrace();
			}
		}
		else
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("error"), bundle.getString("nombreVacio")));
	}
	
}
