package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoInteres;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.FactoriaRepositorios;
import eventos.repositorio.IRepositorio;
import eventos.repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {

	private IRepositorio<EspacioFisico, String> repositorioEspacioFisico;
	private IRepositorio<Ocupacion, String> repositorioOcupacion;

	public ServicioEspacios() {
		try {
			repositorioEspacioFisico = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
			repositorioOcupacion = FactoriaRepositorios.getRepositorio(Ocupacion.class);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al instanciar los repositorios");
		}
	}

	@Override // CORRECTO
	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal,
			double latitud, double longidud, String descripcion) {

		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccionPostal, latitud, longidud,
				descripcion);

		String id = null;
		try {
			id = repositorioEspacioFisico.add(espacio);
		} catch (RepositorioException e) {
			e.printStackTrace();
			System.out.println("Error al añadir el espacio físico");
		}
		return id;
	}

	@Override // CORRECTO
	public void asignarPuntosDeInteres(String idEspacioFisico, LinkedList<PuntoInteres> puntosInteres) {
		EspacioFisico espacio;
		try {
			espacio = repositorioEspacioFisico.getById(idEspacioFisico);
			espacio.setPuntosInteres(puntosInteres);
			repositorioEspacioFisico.update(espacio);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // CORRECTO
	public void modificarEspacioFisico(String idEspacioFisico, String nombre, int capacidad, String descripcion) {

		try {
			EspacioFisico espacio = repositorioEspacioFisico.getById(idEspacioFisico);
			espacio.setNombre(nombre);
			espacio.setCapacidad(capacidad);
			espacio.setDescripcion(descripcion);
			repositorioEspacioFisico.update(espacio);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}

	}

	@Override // CORRECTO --> CONSIDERAR UTILIZAR LO OTRO
	public void bajaEspacioFisico(String idEspacioFisico) { // coge el espacio y pone el estado a cerrado temporalmente
		try {
			boolean cerrar = true;
			for (Ocupacion ocu : repositorioOcupacion.getAll()) {
				if (ocu.getEspacioFisico().getId().equals(idEspacioFisico) && ocu.isActiva()) {
					cerrar = false;
				}
			}
			if (cerrar) {
				EspacioFisico espacio = repositorioEspacioFisico.getById(idEspacioFisico);
				espacio.cerrar();
				repositorioEspacioFisico.update(espacio);
			}
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // CORRECTO
	public void activarEspacioFisico(String idEspacioFisico) {
		try {
			EspacioFisico espacio = repositorioEspacioFisico.getById(idEspacioFisico);
			espacio.activar();
			repositorioEspacioFisico.update(espacio);
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override // UTILIZAR OTRO REPOSITORIO
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
