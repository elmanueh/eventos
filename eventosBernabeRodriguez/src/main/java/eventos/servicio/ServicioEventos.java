package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.EspacioFisicoEstado;
import eventos.modelo.Evento;
import eventos.modelo.EventoCategoria;
import eventos.modelo.Ocupacion;

public class ServicioEventos {

	public ServicioEventos() {
		// TODO Auto-generated constructor stub
	}

	public void altaEvento(String nombre, String descripcion, String organizador, EventoCategoria categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int numPlazas,
			int idEspacioFisico /* FALTA CATEGORIA */) {
		String id = "13234213212";
		Ocupacion ocupacion = new Ocupacion(fechaInicio, fechaFin, idEspacioFisico);

		if (numPlazas > ocupacion.getEspacioFisico().getCapacidad())
			throw new IllegalArgumentException("numPlazas: debe ser menos a la ocupación del espacio físico.");

		Evento evento = new Evento(id, nombre, descripcion, organizador, numPlazas, false, categoria, ocupacion);
	}

	public void modificarEvento(int idEvento) {
		/**
		 * Modificar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y los datos a modificar. Se podrá modificar alguno de los
		 * siguientes datos: descripción, fecha de inicio, fecha de fin, número de
		 * plazas y espacio físico. }
		 */
	}

	public void cancelarEvento(int idEvento) {
		/**
		 * Cancelar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y lo pone a cancelado. La cancelación de un evento implica el
		 * borrado de la ocupación para el espacio físico del mismo.
		 */

	}

	public LinkedList<Evento> eventoDelMes(int mes, int año) {
		return new LinkedList<Evento>();
	}
}