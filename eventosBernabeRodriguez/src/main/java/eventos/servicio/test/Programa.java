package eventos.servicio.test;

import eventos.servicio.IPuntosDeInteres;
import eventos.servicio.IServicioEspacios;
import eventos.servicio.ServicioEspacios;
import eventos.servicio.IServicioEventos;
import eventos.servicio.ServicioEventos;
import eventos.servicio.PuntosDeInteres;
import java.time.LocalDateTime;
import eventos.modelo.EventoCategoria;
import eventos.modelo.PuntoInteres;

import java.util.LinkedList;

public class Programa {

	public static void main(String[] args) {

		IServicioEspacios servicioEspacios = new ServicioEspacios();
		IServicioEventos servicioEventos = new ServicioEventos();
		IPuntosDeInteres servicioPuntosDeInteres = new PuntosDeInteres();
		
		// PRUEBAS ESPACIO FISICO
		/*
		// 1. Dar de alta un espacio físico con todos sus datos. Obtener el id asignado.
		String idEspacio = servicioEspacios.altaEspacioFisico("nombre", "propietario", 10, "direccion",
				37.984123153048316, -1.1291804565199284, "descripcion");
		System.out.println("Espacio creado con id: " + idEspacio);

		// 2. Buscar puntos de interés dadas unas coordenadas.
		double latitud = 37.984123153048316;
		double longitud = -1.1291804565199284;
		LinkedList<PuntoInteres> puntos = servicioPuntosDeInteres.obtenerPuntosInteres(latitud, longitud);

		// 3. Seleccionar parte de esos puntos de interés y asignarlos al espacio físico
		// anteriormente creado.
		servicioEspacios.asignarPuntosDeInteres(idEspacio, puntos);

		// 4. Dar de baja espacio físico.
		servicioEspacios.bajaEspacioFisico(idEspacio);
		
		// 5. Reactivar espacio físico.
		servicioEspacios.activarEspacioFisico(idEspacio);
		
		// 6. Modificar espacio físico.
		servicioEspacios.modificarEspacioFisico(idEspacio, null, -1, null);
		
		// 7. BuscarEspaciosFisicosLibres
		
		*/
		
		/*
		// 8. Dar de alta un evento en dicho espacio físico. Obtener el id asignado.
		// Aquí falla pq en servicioEventos no está el Espacio en el repositorioEspacio, 
		// por tanto no encuentra nada y lanza excepción
		String idEvento;
		idEvento = servicioEventos.altaEvento("nombre", "descripcion", "organizador", EventoCategoria.OTROS,
				LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 3, 31, 0, 0), 10, idEspacio);
		System.out.println("Evento creado con id: " + idEvento);
		
		
		
		/*
		// Obviamente

		// 7. Buscar eventos durante Febrero 2025.
		System.out.println("Los siguientes eventos se celebran en Febrero de 2025:");
		System.out.println(servicioEventos.eventosDelMes(2, 2025));

		// 8. Cancelar evento.
		//servicioEventos.cancelarEvento(idEvento);
		 * */
		
		System.out.println("Fin del programa de pruebas.");
	}

}
