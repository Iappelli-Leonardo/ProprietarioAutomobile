package it.prova.proprietarioAutomobileJPA.dao.proprietario;

import java.util.Date;
import java.util.List;

import it.prova.proprietarioAutomobileJPA.dao.IBaseDAO;
import it.prova.proprietarioAutomobileJPA.model.Proprietario;

public interface ProprietarioDao extends IBaseDAO<Proprietario>{
	
	public Proprietario getEagerAutomobili(Long id) throws Exception;
	
	public List<Proprietario> countAllByAnno(Date immatricolazioneInput) throws Exception;
}
