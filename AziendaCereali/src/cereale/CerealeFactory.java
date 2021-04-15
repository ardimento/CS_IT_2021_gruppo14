package cereale;

import eccezioni.EccezioniVendita;
/**
 * Classe che consente l'implementazone del concetto di factory.
 * Fornisce una modalità per creare un cereale disaccoppiando
 * l'istanziazione e quindi i dettagli creazionali del cereale, dalla classe Vendita.
 * (Vendita si limita ad accedere ai cereali mediante l'interfaccia @see Cereale)
 * 
 * @author Alemanno Giuseppe 716262
 *
 */
public class CerealeFactory {
	/**
	 * Implementazione vuota del costruttore della classe Factory
	 */
	private CerealeFactory() {}

	/**
	 * Metodo Statico che permette di essere richiamato per la creazione di un Cereale, tramite il parametro stringa "cereale" che fa da selettore
	 * per il giusto tipo di prodotto da creare.
	 * 
	 * @param cereale Stringa che indica il prodotto da dover creare una istanza
	 * @param quantitaCereale valore double che indica,in Kilogrammi, la quantita del cereale in vendita 
	 * @param quantitaVenduta valore double che indica,in Kilogrammi, la quantità di cereali già venduti per lo stesso impiegato
	 * @return una istanza del cereale selezionato
	 * 
	 * @pre il parametro @see cereale deve essere una stringa presente tra i prodotti disponibili alla vendita (di cui le loro classi siano definite)
	 * @post una istanza del cereale richiesto con i dati presi dal database
	 */
	public static Cereale creatoreCereale (String cereale, double quantitaCereale, double quantitaVenduta) throws EccezioniVendita {
		Cereale cerealeDaIstanziare = null;
		switch (cereale) {
		
			case "avena" : {
				cerealeDaIstanziare = new Avena(ProprietaNutritive.PROPRIETA_AVENA, quantitaCereale, quantitaVenduta);
			
			}

		}
		return cerealeDaIstanziare;
	}
	 
	/**
	 * Metodo Statico che permette di essere richiamato per la creazione di un Cereale, tramite il parametro stringa "cereale" che fa da selettore
	 * per il giusto tipo di prodotto da creare.
	 * 
	 * @param cereale Stringa che indica il prodotto da dover creare una istanza
	 * @param quantitaCereale valore double che indica la quantita del cereale venduta in Kilogrammi
	 * 
	 * @return una istanza del cereale selezionato
	 * 
	 * @pre il parametro @see cereale deve essere una stringa presente tra i prodotti disponibili alla vendita (di cui le loro classi siano definite)
	 * @post una istanza del cereale richiesto con i dati presi dal database
	 */
	public static Cereale creatoreCereale (String cereale, double quantitaCereale) {
		Cereale cerealeDaIstanziare = null;
		switch (cereale) {
		
			case "avena" : {
				cerealeDaIstanziare = new Avena(ProprietaNutritive.PROPRIETA_AVENA, quantitaCereale);
			
			}

		}
		return cerealeDaIstanziare;
	}
}
 