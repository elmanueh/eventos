package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import eventos.modelo.EspacioFisico;

public interface IRepositorioAdHocEspacioFisico extends IRepositorioString<EspacioFisico> {

	boolean isAlgunaOcupacion(String idEspacioFisico);

	List<EspacioFisico> getEspaciosFisicoLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad);

	List<EspacioFisico> getEspaciosFisico(String propietario);
}
