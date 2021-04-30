package impiegato;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cereale.Avena;
import eccezioni.EccezioniVendita;
import eccezioni.MessaggiErroreVendita;
import gui.eccezioniGui.EccezioniGUI;
import vendita.Vendita;
import vendita.VenditaInterfaccia;

/**
 * Classe Impiegato che rappresenta lo stato e il comportamento che un impiegato generico possiede.
 * @author Giuseppe Alemanno 716262
 *
 */
public class Impiegato {
	
	/** valore che indica il codice identificativo di un impiegato */
	private String codiceImpiegato  = "";
	/** valore che rappresenta la massima quantità (in peso) di Cereali vendibili dall'impiegato in un anno*/
	private double pesoAnnuoMassimo = 0.0;
	/** collezione di vendite (di cereali) effettuate dall'impiegato */
	private Set <VenditaInterfaccia> vendite = new HashSet<VenditaInterfaccia>();
	
	/**
	 * Costruttore della classe 
	 * 
	 * consente di istanziare un impiegato con tutti i suoi attributi di istanza 
	 * passando i valori da inserire come parametri di input.
	 * 
	 * @param codiceImpiegato valore alfanumerico che rappresenta il codice dell'impiegato (precondizione: il codice sia univoco) 
	 * @param pesoAnnuoMassimo valore double che rappresenta la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 */
	public Impiegato(String codiceImpiegato, double pesoAnnuoMassimo) {
		super();
		this.codiceImpiegato = codiceImpiegato;
		this.pesoAnnuoMassimo = pesoAnnuoMassimo;
	}

	
	
	/**
	 * Costruttore della classe 
	 * 
	 * consente di istanziare un impiegato con tutti i suoi attributi di istanza 
	 * passando i valori da inserire come parametri di input.
	 * 
	 * @param codiceImpiegato valore alfanumerico che rappresenta il codice dell'impiegato (precondizione: il codice sia univoco) 
	 * @param pesoAnnuoMassimo valore double che rappresenta la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 * @param vendite collezione di vendite compiute dall'impiegato (precondizione : le vendite presenti rispettino tutti i vincoli ad esse applicabili secondo le scelte progettuali imposte)
	 *
	 */
	public Impiegato(String codiceImpiegato, double pesoAnnuoMassimo, Set<VenditaInterfaccia> vendite) {
		super();
		this.codiceImpiegato = codiceImpiegato;
		this.pesoAnnuoMassimo = pesoAnnuoMassimo;
		this.vendite = vendite;
	}


	/**
	 * Restituisce la collezioni di vendite effettuate dall'impiegato 
	 * @return la collezione di vendite 
	 */
	public Set<VenditaInterfaccia> getVendite() {
		return vendite;
	}


	/**
	 * Restituisce il codice identificativo dell'impiegato
	 * @return il codice identificativo dell'impiegato
	 */
	public String getCodiceImpiegato() {
		return codiceImpiegato;
	}

	
	/**
	 * Restituisce la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 * @return la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 */
	public double getPesoAnnuoMassimo() {
		return pesoAnnuoMassimo;
	}


	/**
	 * Ricerca se è già presente una vendita con il codice passato come parametro.
	 * 
	 * Grazie alla ridefinizione dei metodi @see Vendita#hashCode()  e  @see Vendita#equals() 
	 * un oggetto vendita risulta confrontabile con un altro, per mezzo del codice che lo identifica.
	 * Perciò per mezzo del codice, viene creata una vendita puramente finalizzata ad essere ricercata tra la collezione di vendite effettive.
	 * 
	 * @param codiceVenditaDaCercare
	 * @return restituisce true se viene trovata una vendita con il codice indicato, false altrimenti.
	 * @throws EccezioniVendita lanciata se i vincoli riguardante la vendita dei vari cereali dell'azienda non sono rispettati
	 * 
	 */
	public boolean cercaVendita(String codiceVenditaDaCercare) throws EccezioniVendita {
		VenditaInterfaccia venditaDaCercare = new Vendita(0.0, codiceVenditaDaCercare, "avena", "2000-12-12",0.0); //da rivedere ...
		boolean esito = false;
		if (getVendite().contains(venditaDaCercare)) 
			esito = true; 
		return esito;
	}
	
	/**
	 * Effettua l'aggiunta di una vendita all'impiegato. 
	 * 
	 * Questo solo se l'impegato non ha raggiunto (o con tale vendita supera) la quantità massima di cereali venduti tramite il metodo @seequantitaVendutaAnnua durante l'anno e durante la giornata tramite il metodo @seequantitaVendutaGiornaliera.
	 * 
	 * @param quantitaCereale valore double che rappresenta la quantità di cereale che si vuole vendere
	 * @param codVendita valore alfanumerico che rapresenta il codice identificativo della vendita (precondizione : sia univoco )
	 * @param cerealeScelto valore che rappresenta il cereale che si vuole vendere (precondizione : tale stringa sia = all'attributo 'NOME_CLASSE' di un cereale esistente)
	 * @param data valore alfanumerico che rappresenta la data in cui si è effettuata tale vendita (precondizione : la data sia nel formato AAAA-MM-GG)
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 * @return restituisce true se si è verificata la condizione per cui la vendita non deve essere creata, false altrimenti.
	 */
	public boolean creaVendita(double quantitaCereale, String codVendita, String cerealeScelto, String data) throws EccezioniVendita {
		boolean esito = false; //ok
		double somma = 0.0;
		
		somma = quantitaVendutaAnnua(data);
		somma = somma + quantitaCereale;
		
		if ( somma > pesoAnnuoMassimo) {
			esito = true;
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_ANNUALE, new EccezioniVendita());
			
		} else {
			somma = quantitaVendutaGiornaliera (data);
			somma = somma + quantitaCereale;
			
				vendite.add(new  Vendita(quantitaCereale, codVendita, cerealeScelto, data, quantitaVendutaGiornaliera(data)));
			
			
		}
		
		return esito;
		
	}

	/**
	 * Restituisce la quantità di cereali venduti dall'impiegato in una certo anno tramite il passaggio di una data come parametro.
	 * 
	 * @param dataVendita valore alfanumerico che rappresenta la data presa in considerazione (precondizione : la data sia nel formato AAAA-MM-GG)
	 * @return restituisce la quantità di cereali venduti dall'impiegato nell'anno della data passata.
	 */
	public double quantitaVendutaAnnua (String dataVendita) {
		double quantita = 0.0;
		LocalDate data1 = LocalDate.parse(dataVendita);
		
		Iterator<VenditaInterfaccia> iterator = vendite.iterator();
		while (iterator.hasNext()) {
			VenditaInterfaccia vendita = iterator.next();
			LocalDate data2 = LocalDate.parse(vendita.getDataVendita());
			if (data1.getYear() == data2.getYear()) {
				quantita = quantita + vendita.getQuantitaCereale();
			}
		}
		return quantita;
		
	}
	
	
	/**
	 * Restituisce la quantità di cereali venduti dall'impiegato in una data indicata come parametro.
	 * 
	 * @param dataVendita valore alfanumerico che rappresenta la data presa in considerazione (precondizione : la data sia nel formato AAAA-MM-GG)
	 * @return restituisce la quantità di cereali venduti dall'impiegato nella data passata.
	 */
	public double quantitaVendutaGiornaliera (String dataVendita) {
		double quantita = 0.0;
		
		Iterator<VenditaInterfaccia> iterator = vendite.iterator();
		while (iterator.hasNext()) {
			VenditaInterfaccia vendita = iterator.next();

			if (vendita.getDataVendita().equals(dataVendita)) {
				quantita = quantita + vendita.getQuantitaCereale();
			}
		}
		return quantita;
		
	}


	
	
}
