package it.prova.proprietarioAutomobileJPA.service.proprietario;

import java.util.Date;
import java.util.List;

import it.prova.proprietarioAutomobileJPA.dao.proprietario.ProprietarioDao;
import it.prova.proprietarioAutomobileJPA.model.Proprietario;

public interface ProprietarioService {

	public List<Proprietario> listAllProprietari() throws Exception;

	public Proprietario caricaSingoloProprietario(Long id) throws Exception;

	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception;

	public void aggiorna(Proprietario proprietarioInstance) throws Exception;

	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception;

	public void rimuovi(Proprietario proprietarioInstance) throws Exception;

	public List<Proprietario> contaProprietariConImmatricolazioneMaggioreDi
	(Date immatricolazioneInput) throws Exception;

	// per injection
	public void setProprietarioDAO(ProprietarioDao proprietarioDao);
}
