package azienda;
import java.awt.EventQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

import gui.Login;
import impiegato.*;
import vendita.Vendita;
import vendita.VenditaInterfaccia;

/**
 * Classe AziendaAgricola che permette la creazione di un azienda di nome AziendaAgricola. In questa classe � presente il main,
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
	 * Valore int che indica il numero massimo di impiegati che pu� assumere l'azienda
	 */
	private static int NumeroImpiegatiMax = 20;
	/**
	 * Collezione di un gruppo di oggetti di impiegati, impostando, come default, un numero massimo di impiegati.
	 */
	private static Map<String,Impiegato> impiegati = new HashMap<String, Impiegato>(NumeroImpiegatiMax);
	
	/**
	 * Costruttore della classe AziendaAgricola. Questo costruttore fa uso di un parametro, impostato come default.
	 * Questa decisione � stata presa per associare un nome significativo al main principale.
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
	 * Metodo realizzzato con un gruppo di oggetti, ognuno dei quali � associato con una chiave.
	 * L'oggetto in merito � l'impiegato, e la chiave � una stringa legata al codice dell' impiegato.
	 * @return una collezione di oggetti.
	 */
	public Map<String, Impiegato> getImpiegati(){
		return impiegati;
	}
	
	/**
	 * Main del programma. Qui avviene l'avvio del programma. Tale main realizza in primis la realizzazione dell'azienda
	 * ed in un secondo momento andr� a fare uso di tutte le funzionalit� del programma.
	 * Per ora si � pensato di creare semplicemente dei test, per testare l'effettiva funzionalit� del programma.
	 */
	public static void main(String[] args) {
		
		AziendaAgricola azienda = new AziendaAgricola("Azienda Agricola");
		Vendita v1 = new Vendita(28, "0001", "avena", "2021-03-02");
		Vendita v2 = new Vendita(18, "0002", "avena", "2021-03-01");
		Set<VenditaInterfaccia> vendite = new HashSet<VenditaInterfaccia>();
		vendite.add(v1);
		vendite.add(v2);
		Impiegato impiegato = new Impiegato("1234", 1200, vendite);
		
		System.out.println(vendite.toString());
		System.out.println(impiegato.toString());
		
		
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
	 * Metodo con il quale controlliamo se il codice inserito � presente tra gli impiegati.
	 * @param codice 
	 * @return true, se il codice � presente all'interno degli impiegati, altrimenti false.
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
