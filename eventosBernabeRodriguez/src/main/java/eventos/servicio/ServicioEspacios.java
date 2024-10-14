package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoInteres;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.RepositorioMemoria;

public class ServicioEspacios implements IServicioEspacios {

	private RepositorioMemoria<EspacioFisico> repositorioEspacio;
	private RepositorioMemoria<Ocupacion> repositorioOcupacion;

	public ServicioEspacios() {
		repositorioEspacio = new RepositorioMemoria<EspacioFisico>();
		repositorioOcupacion = new RepositorioMemoria<Ocupacion>();
	}

	@Override
	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal,
			double latitud, double longidud, String descripcion) {

		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccionPostal, latitud, longidud,
				descripcion);

		String id = repositorioEspacio.add(espacio);

		return id;
	}

	@Override
	public void asignarPuntosDeInteres(String idEspacioFisico, LinkedList<PuntoInteres> puntosInteres) {
		EspacioFisico espacio;
		try {
			espacio = repositorioEspacio.getById(idEspacioFisico);
			espacio.setPuntosInteres(puntosInteres);
			repositorioEspacio.update(espacio);
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modificarEspacioFisico(String idEspacioFisico, Map<String, Object> atributos) {
		throw new UnsupportedOperationException("Unimplemented method 'modificarEspacioFisico'");
	}

	@Override
	public void bajaEspacioFisico(String idEspacioFisico) { // coge el espacio y pone el estado a cerrado temporalmente
		boolean cerrar = true;

		LinkedList<Ocupacion> ocupaciones = new LinkedList<>(repositorioOcupacion.getAll());
		for (Ocupacion ocu : ocupaciones) {
			if (ocu.getEspacioFisico().getId().equals(idEspacioFisico) && ocu.isActiva()) {
				cerrar = false;
			}
		}
		if (cerrar) {
			EspacioFisico espacio;
			try {
				espacio = repositorioEspacio.getById(idEspacioFisico);
				espacio.cerrar();
				repositorioEspacio.update(espacio);
			} catch (EntidadNoEncontrada e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void activarEspacioFisico(String idEspacioFisico) {
		EspacioFisico espacio;
		try {
			espacio = repositorioEspacio.getById(idEspacioFisico);
			espacio.activar();
			repositorioEspacio.update(espacio);
		} catch (EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override
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
