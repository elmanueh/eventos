package eventos.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import eventos.repositorio.Identificable;

@Entity
@Table(name = "Ocupacion")
public class Ocupacion implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;

	@ManyToOne
	private EspacioFisico espacioFisico;

	public Ocupacion() {

	}

	public Ocupacion(LocalDateTime fechaInicio, LocalDateTime fechaFin, EspacioFisico espacioFisico) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.espacioFisico = espacioFisico;
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

	public boolean entreFechas(int mes, int año) {
		LocalDateTime a = LocalDateTime.of(año, mes, 1, 0, 0);
		return fechaInicio.isBefore(a) && fechaFin.isAfter(a);
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		if (fechaInicio != null)
			this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		if (fechaFin != null)
			this.fechaFin = fechaFin;
	}

	public void setEspacioFisico(EspacioFisico espacioFisico) {
		if (espacioFisico != null)
			this.espacioFisico = espacioFisico;
	}

	@Override
	public String toString() {
		return "Ocupacion [fechaInicio=" + fechaInicio + espacioFisico.toString() + "]";
	}

}
