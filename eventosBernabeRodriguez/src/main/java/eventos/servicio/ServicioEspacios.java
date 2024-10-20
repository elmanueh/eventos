package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import eventos.modelo.EspacioFisico;
import eventos.modelo.PuntoInteres;
import eventos.repositorio.EntidadNoEncontrada;
import eventos.repositorio.FactoriaRepositorios;
import eventos.repositorio.IRepositorio;
import eventos.repositorio.RepositorioException;

public class ServicioEspacios implements IServicioEspacios {

	private IRepositorio<EspacioFisico, String> repositorioEspacioFisico;

	public ServicioEspacios() {
		try {
			repositorioEspacioFisico = FactoriaRepositorios.getRepositorio(EspacioFisico.class);
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

	@Override // CORRECTO
	public void bajaEspacioFisico(String idEspacioFisico) { // coge el espacio y pone el estado a cerrado temporalmente
		try {
			if (repositorioEspacioFisico.isAlgunaOcupacion(idEspacioFisico)) {
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

	@Override // CORRECTO
	public List<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin,
			int capacidad) {
		return repositorioEspacioFisico.getEspaciosFisicoLibres(fechaInicio, fechaFin, capacidad);
	}

}
