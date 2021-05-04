/**
 * 
 * 
 * @autor BenSidi Alessio 717848
 * 
 * Classe Vendita che permette di effettuare la vendita dei cereali da parte di un impiegato attraverso l'implementazione
 * dell'interfaccia @see VenditaInterfaccia
 */
package vendita;
import java.time.LocalDate;

import cereale.Cereale;
import cereale.CerealeFactory;
import eccezioni.EccezioniVendita;
import eccezioni.MessaggiErroreVendita;
import gui.eccezioniGui.EccezioniGUI;
import gui.eccezioniGui.MessaggiGUI;

public class Vendita implements VenditaInterfaccia {
	/** valore che indica la quantità in kg di cereali che è stata venduta*/
	private double quantitaCereale = 0.0;
	/** è l'oggetto che rappresenta il cereale implicato nella vendita, da esso saranno presi i suoi dati attraverso i metodi
	 * implementati dall'interfaccia @see Cereale */
	Cereale cerealeInVendita = null;
	/** Stringa che indica la data in cui è stata effettuata la vendita*/
	private String dataVendita = "";
	/** data in cui viene effettuato l'imballaggio. E' un dato ricavato sulla base del valore della @see dataVendita*/
	private String dataImballaggio = "";
	/** data in cui il prodotto in vendita perde le sue qualità organolettiche. Il valore di questo dato è ricavato sulla base
	 * del valore della @see dataVendita e il valore del dato dei giorni di freschezza relativo allo specifico cereale venduto*/
	private String dataScadenza = "";
	/** valore che indica il prezzo della vendita effettuata. Dato ricavato dalla quantita venduta con il prezzo al kg del prodotto coinvolto nella vendita*/
	private double prezzoVendita = 0.0;
	/** codice univoco che identifica la singola vendita effettuata*/
	private String codVendita = "";
	
	/**
	 * Costruttore della classe Vendita dove viene effettuata la creazione del cereale scelto attraverso l'inserimento di una stringa
	 * e, in oltre, viene calcolato sulla base dei dati del cereale scelto, la data di scadenza dello stesso prodotto basandosi sulla data di vendita, non chè la data di imballaggio.
	 * Si presume che per politiche aziendali, la data di imballaggio coincide con il giorno antecedente alla data di vendita.
	 * Quindi la data di scadenza si basa sulla data di imballaggio, aggiungendo poi i giorni di freschezza del prodotto coinvolto nella vendita.
	 * 
	 * @param quantitaCereale valore double che indica in kg il peso del prodotto venduto
	 * @param codVendita Stringa di caratteri alfanumerico che identifica univocamente la vendita effettuata con successo
	 * @param cerealeScelto Stringa di caratteri che rappresenta il cereale venduto
	 * @param data Stringa che indica la data di vendita, il formato è rappresentato in forma YYYY-MM-GG
	 * @param quantitaVenduta somma delle quantità vendute dal venditore che sta creando la vendita
	 * 
	 * @pre devono essere rispettati i vincoli legati al cereale scelto per permettere che la vendita venga istanziata con successo
	 * @pre per la data deve essere rispettato il seguente formato: YYYY-MM-GG
	 * @pre la data di imballaggio viene stabilita in base alla data di vendita (un giorno precedente alla data di vendita)
	 * @pre la data di scadenza viene stabilita in base alla data di imballaggio (varia in base alla giorni di freschezza del cereale)
	 * @post viene creato l'oggetto vendita
	 */
	public Vendita(double quantitaCereale, String codVendita, String cerealeScelto, String data, double quantitaVenduta) throws EccezioniVendita {
		
		this.cerealeInVendita = CerealeFactory.creatoreCereale(cerealeScelto, quantitaCereale, quantitaVenduta);
		
		if(quantitaCereale == 0) 
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_NULLA, new EccezioniVendita());


		this.quantitaCereale = quantitaCereale;
		LocalDate dataOdierna = LocalDate.parse(data);
		LocalDate dataImballaggio = dataOdierna.minusDays(1);
		LocalDate dataScadenza = dataImballaggio.plusDays(cerealeInVendita.getTempoFreschezza());
			
		this.dataVendita = dataOdierna.toString();
		this.dataImballaggio = dataImballaggio.toString();
		this.dataScadenza = dataScadenza.toString();
			
		this.prezzoVendita = cerealeInVendita.getPrezzo() * quantitaCereale;
		this.codVendita = codVendita;
	}
	
	/**
	 * Costruttore di vendita riservato all'istanzazione di una vendita caricata dal database
	 * 
	 * @param quantitaCereale valore double che indica in kg il peso del prodotto venduto
	 * @param codVendita Stringa di caratteri alfanumerico che identifica univocamente la vendita effettuata con successo
	 * @param cerealeScelto Stringa di caratteri che rappresenta il cereale venduto
	 * @param dataVendita Stringa che indica la data di vendita, il formato è rappresentato in forma YYYY-MM-GG
	 * @param quantitaVenduta somma delle quantità vendute dal venditore che sta creando la vendita
	 * @param dataImballaggio data in cui è stato effettuato l'imballaggio del prodotto
	 * @param dataScadenza stringa che rappresenta la data in cui il prodotto perde la sua freschezza
	 * @param prezzoVendita valore double che indica il prodotto tra la quantita del prodotto venduto per il prezzo unitario del prodotto
	 * @throws EccezioniVendita 
	 * 
	 * @post creazione di un oggetto di tipo Vendita
	 * 
	 */
	
	public Vendita(String codVendita, String cerealeScelto, String dataVendita, String dataImballaggio, String dataScadenza, double quantitaCereale, double prezzoVendita) throws EccezioniVendita {
	
		this.codVendita = codVendita;
		this.dataVendita = dataVendita;
		this.dataImballaggio = dataImballaggio;
		this.dataScadenza = dataScadenza;
		this.cerealeInVendita = CerealeFactory.creatoreCereale(cerealeScelto,quantitaCereale);
		
		if(quantitaCereale == 0)
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_NULLA, new EccezioniVendita());
		
		this.quantitaCereale = quantitaCereale;
		this.prezzoVendita = prezzoVendita;
	}
	/**
	 * Metodo che richiama il cereale coinvolto nella vendita @see cerealeInVendita e richiama la funzione implementata
	 * dall'interfaccia @see Cereale che permette di restituire la stringa di caratteri
	 * rappresentante l'elenco delle proprietà nutritive del cereale in oggetto
	 * */
	@Override
	public String getProprietaNutritive() {
		// TODO Auto-generated method stub
		return cerealeInVendita.getProprietaNutritive(); 
	}
	/**
	 * restituisce il valore della quantita di prodotto venduta in oggetto preso dall'attributo @see quantitaCereale
	 * 
	 */
	@Override
	public Double getQuantitaCereale() {
		// TODO Auto-generated method stub
		return quantitaCereale;
	}
	/**
	 * restituisce la stringa che rappresenta la data di vendita @see dataVendita del prodotto.
	 * il formato della data è YYYY-MM-GG
	 * 
	 */
	@Override
	public String getDataVendita() {
		// TODO Auto-generated method stub
		return dataVendita;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codVendita == null) ? 0 : codVendita.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendita other = (Vendita) obj;
		if (codVendita == null) {
			if (other.codVendita != null)
				return false;
		} else if (!codVendita.equals(other.codVendita))
			return false;
		return true;
	} 
	
	
	/** {@inheritDoc} 
	 * 
	 */
	@Override
	public Object [] arrayDiOggetti () {
		Object array [] = {
				codVendita,
				dataVendita,
				cerealeInVendita.getNomeCereale(),
				quantitaCereale,
				prezzoVendita,
				dataImballaggio,
				dataScadenza
		};
		return array;
	}
	
}
