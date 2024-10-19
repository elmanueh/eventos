package eventos.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		return typedQuery.getSingleResult() == 0.0;
	}
}
