package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoInteres;

public class ServicioEspacios {

	public ServicioEspacios() {
		// TODO Auto-generated constructor stub
	}

	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal,
			double latitud, double longidud, String descripcion) {

		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccionPostal, latitud, longidud,
				descripcion);


		return id;
	}

	public void modificarEspacioFisico(int idEspacioFisico) {

	public void modificarEspacioFisico(String idEspacioFisico, Map<String, Object> atributos) {
		throw new UnsupportedOperationException("Unimplemented method 'modificarEspacioFisico'");
	}

		// coge el espacio y pone el estado a cerrado temporalmente
	public void bajaEspacioFisico(String idEspacioFisico) { // coge el espacio y pone el estado a cerrado temporalmente
	}

		// coge el espacio y pone el estado a activo
	public void activarEspacioFisico(String idEspacioFisico) {
	}

	public LinkedList<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int capacidad) {
		/*
		 * @Query("SELECT e FROM Espacio e WHERE e.capacidad >= :capacidadMinima AND " +
		 * "e.id NOT IN (SELECT o.espacio.id FROM Ocupacion o WHERE " +
		 * "(o.fechaInicio < :fechaFin AND o.fechaFin > :fechaInicio))")
		 */
		// devuelve un
		// listado de espacios físicos que no estén ocupados en el rango de fechas
		// indicado y que
		// tengan al menos la capacidad indicada.
		return null;
	}

}
