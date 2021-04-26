package azienda;
import database.ConnessioneDB;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import database.ConnessioneDB;
import eccezioni.EccezioniVendita;
import gui.Login;
import impiegato.*;
import vendita.Vendita;
import vendita.VenditaInterfaccia;

/**
 * Classe AziendaAgricola che permette la creazione di un azienda di nome AziendaAgricola. In questa classe è presente il main,
 * con il quale da inizio alla creazione dell'azienda ed all' utilizzo del programma.
 * @author Fortunato Giuseppe 724309
 *
 */
public class AziendaAgricola {
	/**
	 * Valore String che indica il nome dell' azienda
	 */
	private String nomeAzienda = "AziendaAgricola";
	/**
	 * Valore int che indica il numero massimo di impiegati che può assumere l'azienda
	 */
	private static int NumeroImpiegatiMax = 20;
	/**
	 * Collezione di un gruppo di oggetti di impiegati, impostando, come default, un numero massimo di impiegati.
	 */
	private static Map<String,Impiegato> impiegati = new HashMap<String, Impiegato>(NumeroImpiegatiMax);
	
	/**
	 * Costruttore della classe AziendaAgricola. Questo costruttore fa uso di un parametro, impostato come default.
	 * Questa decisione è stata presa per associare un nome significativo al main principale.
	 * @param nomeAzienda Stringa di caratteri contenente il nome da associare all' azienda.
	 */
	public AziendaAgricola(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	
	/**
	 * Metodo con il quale si restituisce il nome dell'azienda
	 * @return il nome dell' azienda
	 */
	public String getNomeAzienda() {
		return nomeAzienda;
	}
	
	/**
	 * Metodo con il quale si crea il nome dell'azienda. Creato per eventuali modifiche future.
	 */
	public void setNomeAzienda(String nomeAzienda) {
		this.nomeAzienda = nomeAzienda;
	}
	
	/**
	 * Metodo realizzzato con un gruppo di oggetti, ognuno dei quali è associato con una chiave.
	 * L'oggetto in merito è l'impiegato, e la chiave è una stringa legata al codice dell' impiegato.
	 * @return una collezione di oggetti.
	 */
	public Map<String, Impiegato> getImpiegati(){
		return impiegati;
	}
	
	/**
	 * Main del programma. Qui avviene l'avvio del programma. Tale main realizza in primis la realizzazione dell'azienda
	 * ed in un secondo momento andrà a fare uso di tutte le funzionalità del programma.
	 * Per ora si è pensato di creare semplicemente dei test, per testare l'effettiva funzionalità del programma.
	 * @throws EccezioniVendita 
	 */
	public static void main(String[] args) throws EccezioniVendita {
		
		AziendaAgricola azienda = new AziendaAgricola("Azienda Agricola");
		
		/*
		 * 
		 * test
		 * 
		 * Vendita v1 = new Vendita(28, "0001", "avena", "2021-03-02",0.0);
		Vendita v2 = new Vendita(18, "0002", "avena", "2021-03-01",0.0);
		Set<VenditaInterfaccia> vendite = new HashSet<VenditaInterfaccia>();
		vendite.add(v1);
		vendite.add(v2);
		Impiegato impiegato = new Impiegato("1234", 1200, vendite);
		Impiegato impiegato2 = new Impiegato("5678", 1200, vendite);
		impiegati.put("1234", impiegato);
		impiegati.put("5678", impiegato2);
		
		System.out.println(vendite.toString());
		System.out.println(impiegato.toString());
		*/
		
		ConnessioneDB con = ConnessioneDB.creaConnessione();
		con.connettiDB();
		con.caricaDatiImpiegati(azienda.getImpiegati());
		
		java.util.Iterator<Entry<String,Impiegato>> iterator = azienda.impiegati.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Entry<String,Impiegato> entry = iterator.next();
			Impiegato i = entry.getValue();
			con.caricaDativendita(i.getVendite(), i);
			System.out.println(i);
		}
		con.chiudiConnessioneDB();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					Login login = new Login(azienda.getImpiegati());
				}
				catch(Exception e) {
					e.printStackTrace();
				}			
			}
		});
		
	}
	
	/**
	 * Metodo con il quale controlliamo se il codice inserito è presente tra gli impiegati.
	 * @param codice 
	 * @return true, se il codice è presente all'interno degli impiegati, altrimenti false.
	 */
	public boolean accessoImpiegato(String codice) {
		
		boolean esito = false;
		Impiegato impiegato = impiegati.get(codice);
		
		if(impiegato != null) {
			esito = true;
		}
		
		return esito;
	}
}
