package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

import eventos.modelo.EspacioFisico;
import eventos.modelo.Ocupacion;
import eventos.modelo.PuntoInteres;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.IRepositorio;
import eventos.repositorio.RepositorioEspacioFisicoJPA;
import eventos.repositorio.RepositorioException;
import eventos.repositorio.RepositorioOcupacionJPA;

public class ServicioEspacios implements IServicioEspacios {
	
	private IRepositorio<EspacioFisico, String> repositorio;
	private IRepositorio<Ocupacion, String> repositorioOcupacion;
	
	public ServicioEspacios() {
		try {
			repositorio = RepositorioEspacioFisicoJPA.class.getConstructor().newInstance();
			repositorioOcupacion = RepositorioOcupacionJPA.class.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al instanciar los repositorios");
		}
	}

	@Override
	public String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal,
			double latitud, double longidud, String descripcion) {

		EspacioFisico espacio = new EspacioFisico(nombre, propietario, capacidad, direccionPostal, latitud, longidud,
				descripcion);
				
		String id = null;
		try {
			id = repositorio.add(espacio);
		} catch (RepositorioException e) {
			e.printStackTrace();
			System.out.println("Error al añadir el espacio físico");
		}
		return id;
	}

	@Override
	public void asignarPuntosDeInteres(String idEspacioFisico, LinkedList<PuntoInteres> puntosInteres) {
		EspacioFisico espacio;
			try {
				espacio = repositorio.getById(idEspacioFisico);
				espacio.setPuntosInteres(puntosInteres);
				repositorio.update(espacio);
			} catch (RepositorioException | EntidadNoEncontrada e) {
				e.printStackTrace();
			}	
	}

	@Override
	public void modificarEspacioFisico(String idEspacioFisico, Map<String, Object> atributos) {
		throw new UnsupportedOperationException("Unimplemented method 'modificarEspacioFisico'");
	}

	@Override
	public void bajaEspacioFisico(String idEspacioFisico) { // coge el espacio y pone el estado a cerrado temporalmente
		try {
			boolean cerrar = true;
			for (Ocupacion ocu : repositorioOcupacion.getAll()) {
				if (ocu.getEspacioFisico().getId().equals(idEspacioFisico) && ocu.isActiva()) {
					cerrar = false;
				}
			}
			if (cerrar) {
				EspacioFisico espacio = repositorio.getById(idEspacioFisico);
				espacio.cerrar();
				repositorio.update(espacio);
			}
		} catch (RepositorioException | EntidadNoEncontrada e) {
			e.printStackTrace();
		}
	}

	@Override
	public void activarEspacioFisico(String idEspacioFisico) {
		try {
			EspacioFisico espacio = repositorio.getById(idEspacioFisico);
			espacio.activar();
			repositorio.update(espacio);
		} catch (RepositorioException | EntidadNoEncontrada e) {
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
