package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.EventoCategoria;
import eventos.modelo.Ocupacion;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.RepositorioMemoria;

public class ServicioEventos implements IServicioEventos {

	private RepositorioMemoria<EspacioFisico> repositorioEspacio;
	private RepositorioMemoria<Ocupacion> repositorioOcupacion;
	private RepositorioMemoria<Evento> repositorioEvento;

	public ServicioEventos() {
		repositorioEspacio = new RepositorioMemoria<EspacioFisico>();
		repositorioOcupacion = new RepositorioMemoria<Ocupacion>();
		repositorioEvento = new RepositorioMemoria<Evento>();
	}

	@Override
	public String altaEvento(String nombre, String descripcion, String organizador, EventoCategoria categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int numPlazas, String idEspacioFisico) {

		EspacioFisico espacio;
		try {
			espacio = repositorioEspacio.getById(idEspacioFisico);
			if (numPlazas > espacio.getCapacidad()) // Corregir
				throw new IllegalArgumentException("numPlazas: debe ser menos a la ocupación del espacio físico.");

			Ocupacion ocupacion = new Ocupacion(fechaInicio, fechaFin, espacio);
			repositorioOcupacion.add(ocupacion);

			Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, ocupacion);

			String id = repositorioEvento.add(evento);

			return id;
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
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

	@Override
	public void cancelarEvento(String idEvento) {
		/**
		 * Cancelar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y lo pone a cancelado. La cancelación de un evento implica el
		 * borrado de la ocupación para el espacio físico del mismo.
		 */
		Evento evento;
		try {
			evento = repositorioEvento.getById(idEvento);
			evento.setCancelado();
			repositorioOcupacion.delete(evento.getOcupacion());
			repositorioEvento.update(evento);
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ELIMINAR DE LA BASE DE DATOS evento.getOcupacion();
	}

	@Override
	public String eventosDelMes(int mes, int año) {
		LinkedList<Evento> eventos = new LinkedList<Evento>(repositorioEvento.getAll());
		String cadena = "";

		eventos.stream().filter(e -> e.entreFechas(mes, año)).forEach(e -> cadena.concat(e.toString()));

		return cadena;
	}

}