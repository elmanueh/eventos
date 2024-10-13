package eventos.servicio;

import java.util.LinkedList;

import eventos.modelo.PuntoInteres;

public class PuntosDeInteres {
	
	private final static String coordendas = "37.984123153048316,-1.1291804565199284";
	
	// Método para obtener puntos de interés cercanos a las coordenadas geográficas
	public LinkedList<PuntoInteres> obtenerPuntosInteres(String coordenadasGeograficas) {
		//Obtener xml de la api
		GeoNamesAPI api = new GeoNamesAPI();
		String xmlResponse = api.hacerSolicitudGeoNames(coordenadasGeograficas);

		//Porcesar xml y devolver lista de Puntos de interes
		XMLProcessor xmlProcessor = new XMLProcessor();
		return xmlProcessor.procesarXML(xmlResponse);
	}

	// Método principal para pruebas
	public static void main(String[] args) {
        PuntosDeInteres servicio = new PuntosDeInteres();
        LinkedList<PuntoInteres> puntos = servicio.obtenerPuntosInteres(coordendas);

        for (PuntoInteres punto : puntos) {
            System.out.println(punto);
        }
	}
}
