package it.prova.proprietarioAutomobileJPA.dao.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.prova.proprietarioAutomobileJPA.model.Proprietario;

public class ProprietarioDaoImpl implements ProprietarioDao {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		proprietarioInstance = entityManager.merge(proprietarioInstance);
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);

	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietarioInstance));

	}
	
	@Override
	public List<Proprietario> countAllByAnno(Date annoInput) throws Exception {
		TypedQuery<Proprietario> query = entityManager.createQuery("select count (*) from Proprietario p inner join fetch p.automobile a where a.annoImmatricolazione > = ?1", Proprietario.class);
		return query.getResultList();
	}

	@Override
	public Proprietario getEagerAutomobili(Long id) throws Exception {
		//join restituirebbe solo i municipi con abitanti (LAZY)
				//join fetch come sopra ma valorizza anche la lista di abitanti (EAGER)
				//left join fetch come sopra ma cerca anche tra i municipi privi di abitanti (EAGER)
				TypedQuery<Proprietario> query = entityManager
						.createQuery("from Proprietario p left join fetch p.automobili where p.id = ?1", Proprietario.class);
				query.setParameter(1, id);
				
				//return query.getSingleResult() ha il problema che se non trova elementi lancia NoResultException
				return query.getResultStream().findFirst().orElse(null);
	}

}
