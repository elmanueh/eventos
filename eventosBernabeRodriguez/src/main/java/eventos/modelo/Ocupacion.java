package eventos.modelo;

import java.time.LocalDateTime;

public class Ocupacion {

	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String id;
	private String idEspacioFisico;
	// private boolean activa; // propiedad calculada

	public Ocupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, String idEspacioFisico) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.id = "1";
		this.idEspacioFisico = idEspacioFisico;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public String getIdEspacioFisico() {
		return idEspacioFisico;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// Propieda Calculada
	public boolean isActiva() {
		LocalDateTime fechaActual = LocalDateTime.now();
		return fechaActual.isAfter(fechaInicio) && fechaActual.isBefore(fechaFin);
	}

}
