package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.PuntoInteres;

public class ServicioEspacios {

	public ServicioEspacios() {
		// TODO Auto-generated constructor stub
	}

	public String altaEspacioFisico() {
		return new String();
	}

	public void asignarPuntosDeInteres(int idEspacioFisico, LinkedList<PuntoInteres> puntosInteres) {

	}

	public void modificarEspacioFisico(int idEspacioFisico) {

	}

	public void bajaEspacioFisico(int idEspacioFisico) {
		// coge el espacio y pone el estado a cerrado temporalmente
	}

	public void activarEspacioFisico(int idEspacioFisico) {
		// coge el espacio y pone el estado a activo
	}

	public void buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad) {
		// devuelve un
		// listado de espacios físicos que no estén ocupados en el rango de fechas
		// indicado y que
		// tengan al menos la capacidad indicada.
	}

}
