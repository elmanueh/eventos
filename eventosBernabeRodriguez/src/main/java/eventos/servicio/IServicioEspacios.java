package eventos.servicio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import eventos.modelo.PuntoInteres;
import eventos.modelo.EspacioFisico;

public interface IServicioEspacios {

	String altaEspacioFisico(String nombre, String propietario, int capacidad, String direccionPostal, double latitud,
			double longidud, String descripcion);

	void asignarPuntosDeInteres(String idEspacioFisico, LinkedList<PuntoInteres> puntosInteres);

	void modificarEspacioFisico(String idEspacioFisico, Map<String, Object> atributos);

	void bajaEspacioFisico(String idEspacioFisico);

	void activarEspacioFisico(String idEspacioFisico);

	List<EspacioFisico> buscarEspaciosFisicosLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad);
}
