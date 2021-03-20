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
	public final static String ERRORE_QUANTITA_GIORNALIERA = "Errore : vendita non inserita \nla quantita' del prodotto in vendita comporta il superamento della quantita' vendibile giornalmente dell'impiegato";
	/**
	 * messaggio di errore per il vincolo di vendita annuale del prodotto che è legato al vincolo in questione
	 */
	public final static String ERRORE_QUANTITA_ANNUALE = "Errore : vendita non inserita \nla quantita' del prodotto in vendita comporta il superamento della quantita' vendibile annualmente dell'impiegato";
	/**
	 * messaggio di errore per il vincolo di codice già utilizzato da delegare ad una vendita.
	 */
	public final static String ERRORE_CODICE_ESISTENTE = "Errore: vendita non inserita. \nCodice inserito gia' in uso";
	/**
	 * messaggio di errore per il vincolo di codice vuoto (non inserito) da delegare ad una vendita.
	 */
	public final static String ERRORE_CODICE_VUOTO = "Errore: vendita non inserita. \nCodice non inserito";
	/**
	 * messaggio di errore per il vincolo di quantita' vuota (non inserita) da delegare ad una vendita.
	 */
	public final static String ERRORE_QUANTITA_VUOTA = "Errore: vendita non inserita. \nQuantita non inserita";
	/**
	 * messaggio di errore per il vincolo di quantita' inserita non valida da delegare ad una vendita.
	 */
	public final static String ERRORE_QUANTITA_NON_VALIDA = "Errore: vendita non inserita. \nQuantita non valida";
	
}
