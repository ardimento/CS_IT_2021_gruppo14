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
	 * 
	 */
	public Vendita(double quantitaCereale, String codVendita, String cerealeScelto, String data) {
		
		this.cerealeInVendita = CerealeFactory.creatoreCereale(cerealeScelto);
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

}
