package it.prova.proprietarioAutomobileJPA.service.proprietario;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import it.prova.proprietarioAutomobileJPA.dao.EntityManagerUtil;
import it.prova.proprietarioAutomobileJPA.dao.proprietario.ProprietarioDao;
import it.prova.proprietarioAutomobileJPA.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDao proprietarioDao;

	public void setProprietarioDAO(ProprietarioDao proprietarioDao) {
		this.proprietarioDao = proprietarioDao;

	}
	
	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDao.getEagerAutomobili(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDao.update(proprietarioInstance);

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
	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDao.insert(proprietarioInstance);

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
	public void rimuovi(Proprietario proprietarioInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDao.delete(proprietarioInstance);

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
	public List<Proprietario> contaProprietariConImmatricolazioneMaggioreDi(Date immatricolazioneInput)
			throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDao.countAllByAnno(immatricolazioneInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	

}
