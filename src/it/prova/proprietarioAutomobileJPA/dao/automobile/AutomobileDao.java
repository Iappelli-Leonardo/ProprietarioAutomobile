package it.prova.proprietarioAutomobileJPA.dao.automobile;

import java.util.List;

import it.prova.proprietarioAutomobileJPA.dao.IBaseDAO;
import it.prova.proprietarioAutomobileJPA.model.Automobile;

public interface AutomobileDao extends IBaseDAO<Automobile>{

	public List<Automobile> findAllByCfIniziaPer(String nome) throws Exception;
}
