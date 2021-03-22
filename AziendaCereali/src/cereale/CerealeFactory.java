package cereale;

import eccezioni.EccezioniVendita;

public class CerealeFactory {

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
 