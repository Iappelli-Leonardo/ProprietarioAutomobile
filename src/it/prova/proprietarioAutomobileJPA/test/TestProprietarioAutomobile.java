package it.prova.proprietarioAutomobileJPA.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.LazyInitializationException;

import it.prova.proprietarioAutomobileJPA.dao.EntityManagerUtil;
import it.prova.proprietarioAutomobileJPA.model.Automobile;
import it.prova.proprietarioAutomobileJPA.model.Proprietario;
import it.prova.proprietarioAutomobileJPA.service.MyServiceFactory;
import it.prova.proprietarioAutomobileJPA.service.automobile.AutomobileService;
import it.prova.proprietarioAutomobileJPA.service.proprietario.ProprietarioService;

public class TestProprietarioAutomobile {

	public static void main(String[] args) {

		ProprietarioService proprietarioService = MyServiceFactory.getProprietarioService();
		AutomobileService automobileService = MyServiceFactory.getAutomobileService();

		try {

			// ora con il service posso fare tutte le invocazioni che mi servono

			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testRimuoviProprietario(proprietarioService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testInserisciAutomobile(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testRimozioneAutomobile(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			testLazyInitExc(proprietarioService, automobileService);

		
			testCercaTutteAutomobiliConCfIniziaPer(proprietarioService, automobileService);
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

			tesCountAllByAnno(proprietarioService, automobileService);
			
			System.out.println("In tabella Proprietario ci sono " + proprietarioService.listAllProprietari().size()
					+ " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserisciProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testInserisciProprietario inizio.............");
		// creo nuovo Proprietario
		Proprietario nuovoProprietario = new Proprietario("carlo", "giancarli", "cdfft254jdg1g0", new Date());
		if (nuovoProprietario.getId() != null)
			throw new RuntimeException("testInserisciProprietario fallito: record già presente ");

		// salvo
		proprietarioService.inserisciNuovo(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovoProprietario.getId() == null)
			throw new RuntimeException("testInserisciProprietario fallito ");

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}

	private static void testRimuoviProprietario(ProprietarioService proprietarioService) throws Exception {
		System.out.println(".......testRimuoviProprietario inizio.............");
		// creo nuovo Proprietario
		Proprietario nuovoProprietario = new Proprietario("peppee", "franceschetti", "cdfft254jdg1g0", new Date());

		proprietarioService.inserisciNuovo(nuovoProprietario);

		Long idNuovoProprietario = nuovoProprietario.getId();
		proprietarioService.rimuovi(proprietarioService.caricaSingoloProprietario(idNuovoProprietario));
		// proviamo a vedere se è stato rimosso
		if (proprietarioService.caricaSingoloProprietario(idNuovoProprietario) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");

		proprietarioService.rimuovi(nuovoProprietario);
		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)

		System.out.println(".......testInserisciProprietario fine: PASSED.............");
	}

	private static void testInserisciAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testInserisciAutomobile inizio.............");

		// creo nuovo Automobile ma prima mi serve un Proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testInserisciAutomobile fallito: non ci sono Proprietari a cui collegarci ");

		Automobile nuovaAutomobile = new Automobile("toyota", "yaris", "Vxh237ss", new Date());
		// lo lego al primo Proprietario che trovo
		nuovaAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo Automobile
		automobileService.inserisciNuovo(nuovaAutomobile);

		// da questa riga in poi il record, se correttamente inserito, ha un nuovo id
		// (NOVITA' RISPETTO AL PASSATO!!!)
		if (nuovaAutomobile.getId() == null)
			throw new RuntimeException("testInserisciAutomobile fallito ");

		// il test fallisce anche se non è riuscito a legare i due oggetti
		if (nuovaAutomobile.getProprietario() == null)
			throw new RuntimeException("testInserisciAutomobile fallito: non ha collegato il Proprietario ");

		System.out.println(".......testInserisciAutomobile fine: PASSED.............");
	}

	private static void testRimozioneAutomobile(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testRimozioneAutomobile inizio.............");

		// inserisco un Automobile che rimuoverò
		// creo nuovo Automobile ma prima mi serve un Proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testRimozioneAutomobile fallito: non ci sono Proprietari a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("audi", "a1", "ag633hs", new Date());
		// lo lego al primo Proprietario che trovo
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(0));

		// salvo il nuovo Automobile
		automobileService.inserisciNuovo(nuovoAutomobile);

		Long idAutomobileInserito = nuovoAutomobile.getId();
		automobileService.rimuovi(automobileService.caricaSingolaAutomobile(idAutomobileInserito));
		// proviamo a vedere se è stato rimosso
		if (automobileService.caricaSingolaAutomobile(idAutomobileInserito) != null)
			throw new RuntimeException("testRimozioneAutomobile fallito: record non cancellato ");
		System.out.println(".......testRimozioneAutomobile fine: PASSED.............");
	}

	private static void testLazyInitExc(ProprietarioService proprietarioService, AutomobileService automobileService)
			throws Exception {
		System.out.println(".......testLazyInitExc inizio.............");

		// prima mi serve un Proprietario
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException("testLazyInitExc fallito: non ci sono Proprietari a cui collegarci ");

		Proprietario ProprietarioSuCuiFareIlTest = listaProprietariPresenti.get(0);
		// se interrogo la relazione devo ottenere un'eccezione visto che sono LAZY
		try {
			ProprietarioSuCuiFareIlTest.getAutomobili().size();
			// se la riga sovrastante non da eccezione il test fallisce
			throw new RuntimeException("testLazyInitExc fallito: eccezione non lanciata ");
		} catch (LazyInitializationException e) {
			// 'spengo' l'eccezione per il buon fine del test
		}
		// una LazyInitializationException in quanto il contesto di persistenza è chiuso
		// se usiamo un caricamento EAGER risolviamo...dipende da cosa ci serve!!!
		// proprietarioService.caricaSingoloProprietarioConAbitanti(...);
		System.out.println(".......testLazyInitExc fine: PASSED.............");
	}

	private static void testCercaTutteAutomobiliConCfIniziaPer(ProprietarioService proprietarioService,
			AutomobileService automobileService) throws Exception {
		System.out.println(".......testCercaTutteLeAutoConCF inizio.............");

		// inserisco un paio di abitanti di test
		// prima mi serve un municipio
		List<Proprietario> listaProprietariPresenti = proprietarioService.listAllProprietari();
		if (listaProprietariPresenti.isEmpty())
			throw new RuntimeException(
					"testCercaTutteLeAutoConCF fallito: non ci sono municipi a cui collegarci ");

		Automobile nuovoAutomobile = new Automobile("FORD", "Bassi", "asf", new Date());
		Automobile nuovoAutomobile2 = new Automobile("FORD", "Nato", "asgq", new Date());
		// lo lego al primo municipio che trovo
		nuovoAutomobile.setProprietario(listaProprietariPresenti.get(0));
		nuovoAutomobile2.setProprietario(listaProprietariPresenti.get(0));

		// salvo i nuovi abitante
		automobileService.inserisciNuovo(nuovoAutomobile);
		automobileService.inserisciNuovo(nuovoAutomobile2);

	
		automobileService.cercaTutteAutomobiliConCfIniziaPer("c");
	
		// clean up code
		automobileService.rimuovi(nuovoAutomobile);
		automobileService.rimuovi(nuovoAutomobile2);

		System.out.println(".......testCercaTutteLeAutoConCF fine: PASSED.............");
	}

	private static void tesCountAllByAnno(ProprietarioService proprietarioService, AutomobileService automobileService) throws Exception {
		System.out.println(".......tesCountAllByAnno inizio.............");

		// inserisco un paio di abitanti di test
		// prima mi serve un Proprietario

		Proprietario nuovoProprietario = new Proprietario("peppee", "franceschetti", "cdfft254jdg1g0", new Date());

		proprietarioService.inserisciNuovo(nuovoProprietario);

		if (nuovoProprietario.getId() == null) {
			throw new RuntimeException("tesCountAllByAnno fallito: non ci sono proprietari a cui collegarci ");
		}
		Automobile nuovoAutomobile = new Automobile("hyundai", "i30", "sd624dhds", new Date());
		Automobile nuovoAutomobile2 = new Automobile("vw", "golf", "xb527sh", new Date());
		// lo lego al primo Proprietario che trovo
		nuovoAutomobile.setProprietario(nuovoProprietario);
		nuovoAutomobile2.setProprietario(nuovoProprietario);

		String dataDaControllare = "2010-10-02";
		Date dataCreatedNum = new SimpleDateFormat("yyyyy-MM-dd").parse(dataDaControllare);
		
		if (proprietarioService.contaProprietariConImmatricolazioneMaggioreDi(dataCreatedNum).size() != 2)
			throw new RuntimeException("tesCountAllByAnno fallito: numero record inatteso ");

		// clean up code
		automobileService.rimuovi(nuovoAutomobile);
		automobileService.rimuovi(nuovoAutomobile2);
		System.out.println(".......tesCountAllByAnno fine: PASSED.............");
	}

}
