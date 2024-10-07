package eventos.modelo;

import java.time.LocalDateTime;

public class Ocupacion {

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private int idEspacioFisico;
	private EspacioFisico espacioFisico;
	// private boolean activa; // propiedad calculada

	public Ocupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, int idEspacioFisico) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idEspacioFisico = idEspacioFisico;
		this.espacioFisico = new EspacioFisico();
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public EspacioFisico getEspacioFisico() {
		return espacioFisico;
	}

	// Propieda Calculada
	public boolean getActiva() {
		LocalDateTime fechaActual = LocalDateTime.now();
		return fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFin);
	}

}
