package it.prova.proprietarioAutomobileJPA.service.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.proprietarioAutomobileJPA.dao.EntityManagerUtil;
import it.prova.proprietarioAutomobileJPA.dao.automobile.AutomobileDao;
import it.prova.proprietarioAutomobileJPA.model.Automobile;

public class AutomobileServiceImpl implements AutomobileService{

	private AutomobileDao automobileDao;

	public void setAutomobileDAO(AutomobileDao automobileDao) {
		this.automobileDao = automobileDao;
		
	}

	@Override
	public List<Automobile> listAllAutomobili() throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// uso l'injection per il dao
					automobileDao.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					return automobileDao.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
	}

	@Override
	public Automobile caricaSingolaAutomobile(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Automobile automobileInstance) throws Exception {
		// questo è come una connection
				EntityManager entityManager = EntityManagerUtil.getEntityManager();

				try {
					// questo è come il MyConnection.getConnection()
					entityManager.getTransaction().begin();

					// uso l'injection per il dao
					automobileDao.setEntityManager(entityManager);

					// eseguo quello che realmente devo fare
					automobileDao.update(automobileInstance);

					entityManager.getTransaction().commit();
				} catch (Exception e) {
					entityManager.getTransaction().rollback();
					e.printStackTrace();
					throw e;
				} finally {
					EntityManagerUtil.closeEntityManager(entityManager);
				}
		
	}

	@Override
	public void inserisciNuovo(Automobile automobileInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobileDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobileDao.insert(automobileInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public void rimuovi(Automobile automobileInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			automobileDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			automobileDao.delete(automobileInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
		
	}

	@Override
	public List<Automobile> cercaTutteAutomobiliConCfIniziaPer(String nomeInput) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			automobileDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return automobileDao.findAllByCfIniziaPer(nomeInput);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		} 
	}


}
