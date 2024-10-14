package eventos.servicio;

import java.util.LinkedList;

import eventos.modelo.PuntoInteres;

public interface IPuntosDeInteres {

	LinkedList<PuntoInteres> obtenerPuntosInteres(double latitud, double longitud);
}
