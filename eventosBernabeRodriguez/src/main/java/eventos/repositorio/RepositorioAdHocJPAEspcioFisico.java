package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import eventos.modelo.EspacioFisico;
import eventos.modelo.EspacioFisicoEstado;
import eventos.utils.EntityManagerHelper;

public class RepositorioAdHocJPAEspcioFisico extends RepositorioJPA<EspacioFisico> implements IRepositorioAdHocEspacioFisico {

	@Override
	public Class<EspacioFisico> getClase() {
		return EspacioFisico.class;
	}

	@Override
	public boolean isAlgunaOcupacion(String idEspacioFisico) {
		EntityManager em = EntityManagerHelper.getEntityManager();

		String queryString = "SELECT COUNT(o.espacioFisico) FROM Ocupacion o WHERE o.espacioFisico.id = :idEspacioFisico"
				+ " AND o.fechaInicio <= CURRENT_TIMESTAMP AND o.fechaFin >= CURRENT_TIMESTAMP";

		TypedQuery<Long> typedQuery = em.createQuery(queryString, Long.class);
		typedQuery.setParameter("idEspacioFisico", idEspacioFisico);
		Long resultado = typedQuery.getSingleResult();

		EntityManagerHelper.closeEntityManager();

		return resultado == 0.0;
	}

	@Override
	public List<EspacioFisico> getEspaciosFisicoLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad) {

		EntityManager em = EntityManagerHelper.getEntityManager();

		String queryString = "SELECT e FROM EspacioFisico e WHERE e.capacidad >= :capacidadMinima AND " + "e.estado = :activo AND "
				+ "e.id NOT IN (SELECT o.espacioFisico.id FROM Ocupacion o WHERE " + "o.fechaInicio <= :fechaFin AND o.fechaFin >= :fechaInicio)";

		TypedQuery<EspacioFisico> typedQuery = em.createQuery(queryString, EspacioFisico.class);
		typedQuery.setParameter("capacidadMinima", capacidad);
		typedQuery.setParameter("fechaInicio", fechaInicio);
		typedQuery.setParameter("fechaFin", fechaFin);
		typedQuery.setParameter("activo", EspacioFisicoEstado.ACTIVO);

		List<EspacioFisico> resultado = typedQuery.getResultList();
		EntityManagerHelper.closeEntityManager();

		return resultado;
	}

	@Override
	public List<EspacioFisico> getEspaciosFisico(String propietario) {
		EntityManager em = EntityManagerHelper.getEntityManager();

		String queryString = "SELECT e FROM EspacioFisico e WHERE e.propietario = :propietario";

		TypedQuery<EspacioFisico> typedQuery = em.createQuery(queryString, EspacioFisico.class);
		typedQuery.setParameter("propietario", propietario);

		List<EspacioFisico> resultado = typedQuery.getResultList();
		EntityManagerHelper.closeEntityManager();

		return resultado;
	}

}
