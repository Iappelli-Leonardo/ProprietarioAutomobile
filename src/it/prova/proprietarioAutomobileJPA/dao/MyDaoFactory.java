package it.prova.proprietarioAutomobileJPA.dao;

import it.prova.proprietarioAutomobileJPA.dao.automobile.AutomobileDao;
import it.prova.proprietarioAutomobileJPA.dao.automobile.AutomobileDaoImpl;
import it.prova.proprietarioAutomobileJPA.dao.proprietario.ProprietarioDao;
import it.prova.proprietarioAutomobileJPA.dao.proprietario.ProprietarioDaoImpl;

public class MyDaoFactory {

	// rendiamo questo factory SINGLETON
	private static AutomobileDao abitanteDAOInstance = null;
	private static ProprietarioDao proprietarioDAOInstance = null;

	public static AutomobileDao getAbitanteDAOInstance() {
		if (abitanteDAOInstance == null)
			abitanteDAOInstance = new AutomobileDaoImpl();
		return abitanteDAOInstance;
	}

	public static ProprietarioDao getProprietarioDAOInstance(){
		if(proprietarioDAOInstance == null)
			proprietarioDAOInstance= new ProprietarioDaoImpl();
		return proprietarioDAOInstance;
	}

}
