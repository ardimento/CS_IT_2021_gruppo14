package azienda;
import database.ConnessioneDB;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.text.html.HTMLDocument.Iterator;
import database.ConnessioneDB;
import eccezioni.EccezioniVendita;
import eccezioniDatabase.EccezioniDB;
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
	 * Messaggio pop-up utilizzato nel cas in cui non avviene il collegamento con il server.
	 */
	static JOptionPane schermataAvviso;
	
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
	 * @param nomeAzienda
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
	 * Main del programma. Qui avviene l'avvio del programma. Tale main realizza in primis la creazione dell'azienda
	 * ed in un secondo momento andrà a fare uso di tutte le funzionalità del programma riportate sotto.
	 * @throws EccezioniVendita 
	 */
	public static void main(String[] args) throws EccezioniVendita {
		
		//Nome dell'azienda a cui si cederà il software
		AziendaAgricola azienda = new AziendaAgricola("Azienda Agricola");
			
		/**
		 * Connessione con il Database
		 */
		ConnessioneDB con = ConnessioneDB.creaConnessione();
		
		try {
			con.connettiDB();
		} catch (EccezioniDB | SQLException e1) {
			e1.printStackTrace();
			schermataAvviso = new JOptionPane("Connessione al server fallita");
		}
		
		try {
			con.caricaDatiImpiegati(azienda.getImpiegati());
		} catch (SQLException e1) {	
			e1.printStackTrace();
		}
			
		java.util.Iterator<Entry<String,Impiegato>> iterator = azienda.impiegati.entrySet().iterator();
			
		while(iterator.hasNext()) {
			
			Entry<String,Impiegato> entry = iterator.next();
			Impiegato i = entry.getValue();
			
			try {
				con.caricaDativendita(i.getVendite(), i);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println(i);
		}
			
		/**
		 * Invocazione della schermata Login
		 */
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
	 * @return esito true, se il codice è presente all'interno degli impiegati, altrimenti false.
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
