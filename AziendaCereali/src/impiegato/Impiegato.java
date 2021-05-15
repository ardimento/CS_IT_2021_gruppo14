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
 * 
 * @author Giuseppe Alemanno 716262
 *
 */
public class Impiegato {
	
	/** valore che indica il codice identificativo di un impiegato */
	private String codiceImpiegato  = "";
	/** valore che rappresenta la massima quantità (in Kilogrammi) di Cereali vendibili dall'impiegato in un anno*/
	private double pesoAnnuoMassimo = 0.0;
	/** collezione di vendite (di cereali) effettuate dall'impiegato */
	private Set <VenditaInterfaccia> vendite = new HashSet<VenditaInterfaccia>();
	
	/**
	 * Costruttore della classe 
	 * 
	 * consente di istanziare un impiegato con tutti i suoi attributi di istanza 
	 * passando i valori da inserire come parametri di input.
	 *
	 * Precondizione:   il codiceImpiegato non sia già in utilizzo
	 * Precondizione:   la quantità annua sia un valore positvo
	 * 
	 * @param codiceImpiegato valore alfanumerico che rappresenta il codice dell'impiegato (precondizione: il codice sia univoco) 
	 * @param pesoAnnuoMassimo valore double che rappresenta la massima quantità (in Kilogrammi) di Cereali vendibili dall'impiegato in un anno
	 * 
	 * @author Alemanno Giuseppe 716262
	 *  
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
	 * Precondizione:   il codiceImpiegato non sia già in utilizzo
	 * Precondizione:   la quantità annua sia un valore positvo 
	 * Precondizione:   le vendite presenti rispettino tutti i vincoli ad esse applicabili secondo le scelte progettuali imposte
	 * 
	 * @param codiceImpiegato valore alfanumerico che rappresenta il codice dell'impiegato (precondizione: il codice sia univoco) 
	 * @param pesoAnnuoMassimo valore double che rappresenta la massima quantità (in Kilogrammi)  di Cereali vendibili dall'impiegato in un anno
	 * @param vendite collezione di vendite compiute dall'impiegato 
	 * 
	 * @author Alemanno Giuseppe 716262
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
	 * 
	 * @return la collezione di vendite 
	 * 
	 * @author Alemanno Giuseppe 716262
	 */
	public Set<VenditaInterfaccia> getVendite() {
		return vendite;
	}


	/**
	 * Restituisce il codice identificativo dell'impiegato
	 * 
	 * @return il codice identificativo dell'impiegato
	 * 
	 * @author Alemanno Giuseppe 716262
	 */
	public String getCodiceImpiegato() {
		return codiceImpiegato;
	}

	
	/**
	 * Restituisce la massima quantità (in kilogrammi) di Cereali vendibili dall'impiegato in un anno
	 * 
	 * @return la massima quantità (in kilogrammi) di Cereali vendibili dall'impiegato in un anno
	 * 
	 * @author Alemanno Giuseppe 716262
	 */
	public double getPesoAnnuoMassimo() {
		return pesoAnnuoMassimo;
	}


	/**
	 * Ricerca se è già presente una vendita con il codice passato come parametro.
	 * 
	 * Grazie alla ridefinizione dei metodi hashCode() e equals() 
	 * un oggetto vendita risulta confrontabile con un altro, per mezzo del codice che lo identifica.
	 * Perciò per mezzo del codice, viene creata una vendita puramente finalizzata ad essere ricercata tra la collezione di vendite effettive.
	 * 
	 * @param codiceVenditaDaCercare codice della venidta da ricercare
	 * @return restituisce true se viene trovata una vendita con il codice indicato, false altrimenti.
	 * 
	 * @throws EccezioniVendita lanciata se i vincoli riguardante la vendita dei vari cereali dell'azienda non sono rispettati
	 * 
	 * @author Alemanno Giuseppe 716262 
	 *
	 * @see Vendita#hashCode() 
	 * @see Vendita#equals(Object)
	 */
	public boolean cercaVendita(String codiceVenditaDaCercare) throws EccezioniVendita {
		VenditaInterfaccia venditaDaCercare = new Vendita(1.0, codiceVenditaDaCercare, "avena", "2000-12-12",0.0);
		boolean esito = false;
		if (getVendite().contains(venditaDaCercare)) 
			esito = true; 
		return esito;
	}
	
	/**
	 * Effettua l'aggiunta di una vendita all'impiegato. 
	 * 
	 * Questo solo se l'impegato non ha raggiunto (o con tale vendita supera) la quantità massima di cereali venduti tramite il metodo 'quantitaVendutaAnnua' durante l'anno 
	 * e durante la giornata tramite il metodo 'quantitaVendutaGiornaliera'.
	 * 
	 * Precondizione : 'quantitaCereale' sia maggiore di 0
	 * Precondizione : 'codVendita' sia univoco
	 * Precondizione : 'cerealeScelto' sia = all'attributo 'NOME_CLASSE' di un cereale esistente
	 * Precondizione : 'data' sia nel formato AAAA-MM-GG
	 * 
	 * Postcondizione : Viene registrata la vendita oppure lanciata una eccezione
	 * 
	 * @param quantitaCereale valore double che rappresenta la quantità di cereale che si vuole vendere
	 * @param codVendita valore alfanumerico che rapresenta il codice identificativo della vendita 
	 * @param cerealeScelto valore che rappresenta il cereale che si vuole vendere 
	 * @param data valore alfanumerico che rappresenta la data in cui si è effettuata tale vendita 
	 * 
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 * 
	 * @see quantitaVendutaAnnua
	 * @see quantitaVendutaGiornaliera
	 */
	public void creaVendita(double quantitaCereale, String codVendita, String cerealeScelto, String data) throws EccezioniVendita {

		double somma = 0.0;
		
		somma = quantitaVendutaAnnua(data);
		somma = somma + quantitaCereale;
		
		if ( somma > pesoAnnuoMassimo) {
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_ANNUALE, new EccezioniVendita());
			
		} else {
			somma = quantitaVendutaGiornaliera (data);
			somma = somma + quantitaCereale;
			
				vendite.add(new  Vendita(quantitaCereale, codVendita, cerealeScelto, data, quantitaVendutaGiornaliera(data)));
		}

	}

	/**
	 * Restituisce la quantità di cereali venduti dall'impiegato in una certo anno tramite il passaggio di una data come parametro.
	 * 
	 * Precondizione : 'dataVendita' sia nel formato AAAA-MM-GG
	 * 
	 * @param dataVendita valore alfanumerico che rappresenta la data presa in considerazione
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
	 * Precondizione : 'dataVendita' sia nel formato AAAA-MM-GG
	 * 
	 * @param dataVendita valore alfanumerico che rappresenta la data presa in considerazione
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
	
	/**
	 * 
	 * @param codiceVendita valore alfanumerico che rapresenta il codice identificativo della vendita 
	 * @return la vendita ricercata se trovata altrimenti null 
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 */
	private VenditaInterfaccia getVendita(String codiceVendita) throws EccezioniVendita {
		VenditaInterfaccia venditaDaCercare = new Vendita(1.0, codiceVendita, "avena", "2000-12-12",0.0);
		boolean esito=false;
		Iterator<VenditaInterfaccia> iterator = vendite.iterator();
		while (iterator.hasNext()) {
			VenditaInterfaccia vendita = iterator.next();
			if(vendita.equals(venditaDaCercare)) {
				return vendita;
			}
		}
		return null;
	}

	/**
	 * Ritorna la data di imballaggio di una specifica vendita indicata tramite codice
	 * @param codiceVendita valore alfanumerico che rapresenta il codice identificativo della vendita 
	 * @return la data di imballaggio del cereale in vendita
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 */
	public String getVenditaDataImballaggio(String codiceVendita) throws EccezioniVendita {
		if(getVendita(codiceVendita)!=null) 
			return getVendita(codiceVendita).getDataImbalaggio();
		return null;
	}
	
	/**
	 * Ritorna la data di scadenza di una specifica vendita indicata tramite codice
	 * @param codiceVendita valore alfanumerico che rapresenta il codice identificativo della vendita 
	 * @return la data di scadenza del cereale in vendita
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 */
	public String getVenditaDataScadenza(String codiceVendita) throws EccezioniVendita {
		if(getVendita(codiceVendita)!=null) 
			return getVendita(codiceVendita).getDataScadenza();
		return null;
	}
	
	/**
	 * Ritorna il prezzo di una specifica vendita indicata tramite codice
	 * @param codiceVendita valore alfanumerico che rapresenta il codice identificativo della vendita 
	 * @return il prezzo della vendita 
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 */
	public Double getPrezzoVendita(String codiceVendita) throws EccezioniVendita {
		if(getVendita(codiceVendita)!=null) 
			return getVendita(codiceVendita).getPrezzoVendita();
		return null;
	}
	
}
