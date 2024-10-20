package eventos.servicio;

import java.util.List;

import eventos.modelo.PuntoInteres;

public interface IPuntosDeInteres {

	List<PuntoInteres> obtenerPuntosInteres(double latitud, double longitud);
}
