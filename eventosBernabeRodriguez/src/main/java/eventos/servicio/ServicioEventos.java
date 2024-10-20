package eventos.servicio;

import java.time.LocalDateTime;
import java.util.List;

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

	@Override // CORRECTO
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

	@Override // CORRECTO
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

	@Override // CORRECTO
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

			repositorioEvento.update(evento);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // CORRECTO
	public String eventosDelMes(int mes, int año) {
		List<Evento> eventos = repositorioEvento.getEventosDelMes(mes, año);
		String resumenEventos = "";

		for (Evento e : eventos) {
			resumenEventos += e.toString() + "\n";
		}

		return resumenEventos;
	}

}