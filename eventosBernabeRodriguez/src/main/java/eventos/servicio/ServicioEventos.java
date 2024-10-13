package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.EventoCategoria;
import eventos.modelo.Ocupacion;

public class ServicioEventos {

	public ServicioEventos() {
		// TODO Auto-generated constructor stub
	}

			int idEspacioFisico /* FALTA CATEGORIA */) {
		String id = "13234213212";
	public String altaEvento(String nombre, String descripcion, String organizador, EventoCategoria categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int numPlazas, String idEspacioFisico) {
		Ocupacion ocupacion = new Ocupacion(fechaInicio, fechaFin, idEspacioFisico);

		if (numPlazas > ocupacion.getEspacioFisico().getCapacidad())
			throw new IllegalArgumentException("numPlazas: debe ser menos a la ocupación del espacio físico.");

		Evento evento = new Evento(id, nombre, descripcion, organizador, numPlazas, false, categoria, ocupacion);
	}

	public void modificarEvento(String idEvento, Map<String, Object> atributos) { // INCOMPLETO
		/**
		 * Modificar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y los datos a modificar. Se podrá modificar alguno de los
		 * siguientes datos: descripción, fecha de inicio, fecha de fin, número de
		 * plazas y espacio físico. }
		 */

		/*
		 * for (Map.Entry<String, Object> entry : atributos.entrySet()) {
		 * 
		 * }
		 */

	}

	public void cancelarEvento(String idEvento) {
		/**
		 * Cancelar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y lo pone a cancelado. La cancelación de un evento implica el
		 * borrado de la ocupación para el espacio físico del mismo.
		 */

	}

		return new LinkedList<Evento>();
	public String eventosDelMes(int mes, int año) {
	}
}