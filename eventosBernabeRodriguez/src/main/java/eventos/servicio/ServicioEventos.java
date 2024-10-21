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

	@Override
	public String altaEvento(String nombre, String descripcion, String organizador, EventoCategoria categoria,
			LocalDateTime fechaInicio, LocalDateTime fechaFin, int numPlazas, String idEspacioFisico)
			throws RepositorioException, EntidadNoEncontrada {
		try {
			EspacioFisico espacioFisico = repositorioEspacio.getById(idEspacioFisico);

			if (numPlazas > espacioFisico.getCapacidad())
				throw new IllegalArgumentException(
						"El número de plazas supera la capacidad del espacio físico con id " + idEspacioFisico + ".");

			Ocupacion ocupacion = new Ocupacion(fechaInicio, fechaFin, espacioFisico);
			Evento evento = new Evento(nombre, descripcion, organizador, numPlazas, categoria, ocupacion);
			String id = repositorioEvento.add(evento);
			return id;
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al dar de alta el evento con nombre " + nombre + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el espacio físico con id " + idEspacioFisico + ".");
		}
	}

	@Override
	public void modificarEvento(String idEvento, String descripcion, LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int numPlazas, EspacioFisico espacioFisico) throws RepositorioException, EntidadNoEncontrada {
		try {
			Evento evento = repositorioEvento.getById(idEvento);

			if (descripcion != null)
				evento.setDescripcion(descripcion);
			if (fechaInicio != null)
				evento.setFechaInicio(fechaInicio);
			if (fechaFin != null)
				evento.setFechaFin(fechaFin);
			if (numPlazas != -1)
				evento.setPlazas(numPlazas);

			repositorioEvento.update(evento);
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al modificar el evento con id " + idEvento + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el evento con id " + idEvento + ".");
		}
	}

	@Override
	public void cancelarEvento(String idEvento) throws RepositorioException, EntidadNoEncontrada {
		try {
			Evento evento = repositorioEvento.getById(idEvento);
			evento.setCancelado();
			repositorioEvento.update(evento);
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al cancelar el evento con id " + idEvento + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el evento con id " + idEvento + ".");
		}
	}

	// TODO: Falta refactorizar el método eventosDelMes
	@Override
	public String eventosDelMes(int mes, int año) {
		List<Evento> eventos = repositorioEvento.getEventosDelMes(mes, año);
		String resumenEventos = "";

		for (Evento e : eventos) {
			resumenEventos += e.toString() + "\n";
		}

		return resumenEventos;
	}

}