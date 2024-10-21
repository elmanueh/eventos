package eventos.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import eventos.modelo.EspacioFisico;
import eventos.modelo.EspacioFisicoEstado;
import eventos.modelo.Evento;
import eventos.utils.EntityManagerHelper;

public interface IRepositorio<T, K> {

	K add(T entity) throws RepositorioException;

	void update(T entity) throws RepositorioException, EntidadNoEncontrada;

	void delete(T entity) throws RepositorioException, EntidadNoEncontrada;

	T getById(K id) throws RepositorioException, EntidadNoEncontrada;

	List<T> getAll() throws RepositorioException;

	List<K> getIds() throws RepositorioException;

	public default boolean isAlgunaOcupacion(String idEspacioFisico) {
		EntityManager em = EntityManagerHelper.getEntityManager();

		String queryString = "SELECT COUNT(o.espacioFisico) FROM Ocupacion o WHERE o.espacioFisico.id = :idEspacioFisico"
				+ " AND o.fechaInicio <= CURRENT_TIMESTAMP AND o.fechaFin >= CURRENT_TIMESTAMP";

		TypedQuery<Long> typedQuery = em.createQuery(queryString, Long.class);
		typedQuery.setParameter("idEspacioFisico", idEspacioFisico);
		Long resultado = typedQuery.getSingleResult();

		EntityManagerHelper.closeEntityManager();

		return resultado == 0.0;
	}

	public default List<EspacioFisico> getEspaciosFisicoLibres(LocalDateTime fechaInicio, LocalDateTime fechaFin, int capacidad) {

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

	public default List<Evento> getEventosDelMes(int mes, int año) {

		EntityManager em = EntityManagerHelper.getEntityManager();

		String queryString = "SELECT e FROM Evento e "
				+ "WHERE :mes BETWEEN EXTRACT(MONTH FROM e.ocupacion.fechaInicio) AND EXTRACT(MONTH FROM e.ocupacion.fechaFin) "
				+ "AND :año BETWEEN EXTRACT(YEAR FROM e.ocupacion.fechaInicio) AND EXTRACT(YEAR FROM e.ocupacion.fechaFin)";

		TypedQuery<Evento> typedQuery = em.createQuery(queryString, Evento.class);
		typedQuery.setParameter("mes", mes);
		typedQuery.setParameter("año", año);

		List<Evento> resultado = typedQuery.getResultList();
		EntityManagerHelper.closeEntityManager();

		return resultado;
	}
}
