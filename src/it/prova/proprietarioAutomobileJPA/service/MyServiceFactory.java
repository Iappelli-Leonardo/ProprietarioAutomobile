package it.prova.proprietarioAutomobileJPA.service;

import it.prova.proprietarioAutomobileJPA.dao.MyDaoFactory;
import it.prova.proprietarioAutomobileJPA.dao.proprietario.ProprietarioDaoImpl;
import it.prova.proprietarioAutomobileJPA.service.automobile.AutomobileService;
import it.prova.proprietarioAutomobileJPA.service.automobile.AutomobileServiceImpl;
import it.prova.proprietarioAutomobileJPA.service.proprietario.ProprietarioService;
import it.prova.proprietarioAutomobileJPA.service.proprietario.ProprietarioServiceImpl;

public class MyServiceFactory {

	// rendiamo le istanze restituite SINGLETON
	private static AutomobileService automobileServiceInstance = null;
	private static ProprietarioService proprietarioServiceInstance = null;

	public static AutomobileService getAutomobileService() {
		if (automobileServiceInstance == null) {
			automobileServiceInstance = new AutomobileServiceImpl();
			automobileServiceInstance.setAutomobileDAO(MyDaoFactory.getAbitanteDAOInstance());
		}
		return automobileServiceInstance;
	}

	public static ProprietarioService getProprietarioService() {
		if (proprietarioServiceInstance == null) {
			proprietarioServiceInstance = new ProprietarioServiceImpl();
			proprietarioServiceInstance.setProprietarioDAO(MyDaoFactory.getProprietarioDAOInstance());
		}
		return proprietarioServiceInstance;
	}

}
