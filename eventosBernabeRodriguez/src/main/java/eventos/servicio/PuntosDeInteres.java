package eventos.servicio;

import java.util.LinkedList;

import eventos.modelo.PuntoInteres;

public class PuntosDeInteres implements IPuntosDeInteres {

	// Método para obtener puntos de interés cercanos a las coordenadas geográficas
	@Override
	public LinkedList<PuntoInteres> obtenerPuntosInteres(double latitud, double longitud) {
		// Obtener xml de la api
		GeoNamesAPI api = new GeoNamesAPI();
		String xmlResponse = api.hacerSolicitudGeoNames(latitud, longitud);

		// Porcesar xml y devolver lista de Puntos de interes
		XMLProcessor xmlProcessor = new XMLProcessor();
		return xmlProcessor.procesarXML(xmlResponse);
	}
}
