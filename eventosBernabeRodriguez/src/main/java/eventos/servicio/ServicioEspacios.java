package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoInteres;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.FactoriaRepositorios;
import eventos.repositorio.IRepositorioAdHocEspacioFisico;
import eventos.repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {

	private IRepositorioAdHocEspacioFisico repositorioEspacioFisico;

	public ServicioEspacios() {
		repositorioEspacioFisico = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
	}

	@Override
	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal, double latitud, double longidud,
			String descripcion) throws RepositorioException {
		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccionPostal, latitud, longidud, descripcion);

		try {
			String id = repositorioEspacioFisico.add(espacio);
			return id;
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al dar de alta el espacio físico con nombre " + nombre + ".");
		}
	}

	@Override
	public void asignarPuntosDeInteres(String idEspacioFisico, LinkedList<PuntoInteres> puntosInteres)
			throws RepositorioException, EntidadNoEncontrada {
		try {
			EspacioFisico espacioFisico = repositorioEspacioFisico.getById(idEspacioFisico);
			espacioFisico.setPuntosInteres(puntosInteres);
			repositorioEspacioFisico.update(espacioFisico);
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al asignar los puntos de interés al espacio fisico con id " + idEspacioFisico + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el espacio físico con id " + idEspacioFisico + ".");
		}
	}

	@Override
	public void modificarEspacioFisico(String idEspacioFisico, String nombre, int capacidad, String descripcion)
			throws RepositorioException, EntidadNoEncontrada {
		try {
			EspacioFisico espacioFisico = repositorioEspacioFisico.getById(idEspacioFisico);

			if (nombre != null)
				espacioFisico.setNombre(nombre);
			if (capacidad != -1)
				espacioFisico.setCapacidad(capacidad);
			if (descripcion != null)
				espacioFisico.setDescripcion(descripcion);

			repositorioEspacioFisico.update(espacioFisico);
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al modificar el espacio físico con id " + idEspacioFisico + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el espacio físico con id " + idEspacioFisico + ".");
		}
	}

	@Override
	public void bajaEspacioFisico(String idEspacioFisico) throws RepositorioException, EntidadNoEncontrada {
		if (repositorioEspacioFisico.isAlgunaOcupacion(idEspacioFisico)) {
			try {
				EspacioFisico espacioFisico = repositorioEspacioFisico.getById(idEspacioFisico);
				espacioFisico.cerrar();
				repositorioEspacioFisico.update(espacioFisico);
			} catch (RepositorioException e) {
				throw new RepositorioException("Error al dar de baja el espacio físico con id " + idEspacioFisico + ".");
			} catch (EntidadNoEncontrada e) {
				throw new EntidadNoEncontrada("Error al encontrar el espacio físico con id " + idEspacioFisico + ".");
			}
		}
	}

	@Override
	public void activarEspacioFisico(String idEspacioFisico) throws RepositorioException, EntidadNoEncontrada {
		try {
			EspacioFisico espacioFisico = repositorioEspacioFisico.getById(idEspacioFisico);
			espacioFisico.activar();
			repositorioEspacioFisico.update(espacioFisico);
		} catch (RepositorioException e) {
			throw new RepositorioException("Error al activar el espacio físico con id " + idEspacioFisico + ".");
		} catch (EntidadNoEncontrada e) {
			throw new EntidadNoEncontrada("Error al encontrar el espacio físico con id " + idEspacioFisico + ".");
		}
	}

	@Override
	public List<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad) {
		return repositorioEspacioFisico.getEspaciosFisicoLibres(fechaInicio, fechaFin, capacidad);
	}

}
