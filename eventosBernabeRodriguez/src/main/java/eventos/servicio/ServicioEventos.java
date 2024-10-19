package eventos.servicio;

import java.time.LocalDateTime;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Evento;
import eventos.modelo.EventoCategoria;
import eventos.modelo.Ocupacion;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.FactoriaRepositorios;
import eventos.repositorio.IRepositorio;
import eventos.repositorio.RepositorioException;

public class ServicioEventos implements IServicioEventos {

	private IRepositorio<EspacioFisico, String> repositorioEspacio;
	private IRepositorio<Evento, String> repositorioEvento;

	public ServicioEventos() {
		repositorioEspacio = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
		repositorioEvento = FactoriaRepositorios.getRepositorio(Evento.class);
	}

	@Override // CORRECTO || MIRAR REPOSITORIO OCUPACION
	public String altaEvento(String nombre, String descripcion, String organizador, EventoCategoria categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int numPlazas, String idEspacioFisico) {

		EspacioFisico espacio;
		try {
			espacio = repositorioEspacio.getById(idEspacioFisico);
			if (numPlazas > espacio.getCapacidad()) // Corregir
				throw new IllegalArgumentException("numPlazas: debe ser menor a la ocupación del espacio físico.");

			Ocupacion ocupacion = new Ocupacion(fechaInicio, fechaFin, espacio);

			// repositorioOcupacion.add(ocupacion);
			Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, ocupacion);

			String id = repositorioEvento.add(evento);

			return id;
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override // CORRECTO || MIRAR REPOSITORIO OCUPACION
	public void modificarEvento(String idEvento, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int numPlazas, EspacioFisico espacioFisico) {
		try {
			Evento evento = repositorioEvento.getById(idEvento);
			evento.setDescripcion(descripcion);
			evento.setFechaInicio(fechaInicio);
			evento.setFechaFin(fechaFin);
			evento.setPlazas(numPlazas);
			evento.setEspacioFisico(espacioFisico);
			repositorioEvento.update(evento);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // CORRECTO || UTILIZAR OTRO REPOSITORIO (OCUPACION)
	public void cancelarEvento(String idEvento) {
		/**
		 * Cancelar un evento. Esta operación recibe como parámetro el identificador de
		 * un evento y lo pone a cancelado. La cancelación de un evento implica el
		 * borrado de la ocupación para el espacio físico del mismo.
		 */
		Evento evento;
		try {
			evento = repositorioEvento.getById(idEvento);
			// Ocupacion ocu = evento.getOcupacion();
			evento.setCancelado();

			repositorioEvento.update(evento);
			// repositorioOcupacion.delete(ocu);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // UTILIZAR OTRO REPOSITORIO
	public String eventosDelMes(int mes, int año) {
		/**
		 * Devuelve un listado de eventos que se celebran en el mes y año indicados.
		 */
		return null;
	}

}