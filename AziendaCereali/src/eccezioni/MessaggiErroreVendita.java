package eccezioni;
/**
 * 
 * @author BenSidi Alessio 717848
 *
 * Classe utilizzata per catalogare i vari messaggi di errori riguardanti la vendita, legati ai vari vincoli legati sia ai prodotti che agli impiegati.
 * 
 */
public class MessaggiErroreVendita {
	/**
	 * messaggio di errore per il vincolo di vendita giornaliera del prodotto che è legato al vincolo in questione.
	 */
	public final static String ERRORE_QUANTITA_GIORNALIERA = "Errore : la quantita' del prodotto in vendita comporta il superamento della quantita' vendibile giornalmente dell'impiegato";
	/**
	 * messaggio di errore per il vincolo di vendita annuale del prodotto che è legato al vincolo in questione
	 */
	public final static String ERRORE_QUANTITA_ANNUALE = "Errore : la quantita' del prodotto in vendita comporta il superamento della quantita' vendibile annualmente dell'impiegato";
	/**
	 * messaggio di errore per il vincolo di codice già utilizzato da delegare ad una vendita.
	 */
	public final static String ERRORE_CODICE_ESISTENTE = "Errore: Codice inserito gia' in uso";
	/**
	 * messaggio di errore per il vincolo di quantità pari a 0.
	 */
	public final static String ERRORE_QUANTITA_NULLA= "Errore : la quantita' del prodotto in vendita deve essere superiore a 0";
	
}
