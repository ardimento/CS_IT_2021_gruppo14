package gui.eccezioniGui;
/**
 *
 * Classe utilizzata per catalogare i vari messaggi di errori e e segnalazione riguardanti l'interazione dell'utente con l'interfaccia grafica.
 * 
 * @author Alemanno Giuseppe 716262
 * 
 */
public class MessaggiGUI {

	/**
	 * messaggio di errore per il vincolo di codice vuoto (non inserito) da delegare ad una vendita.
	 */
	public final static String ERRORE_CODICE_VUOTO = "Errore: Codice non inserito";
	/**
	 * messaggio di errore per il vincolo di quantita' vuota (non inserita) da delegare ad una vendita.
	 */
	public final static String ERRORE_QUANTITA_VUOTA = "Errore: Quantita non inserita";
	/**
	 * messaggio di errore per il vincolo di 'controllaInserimentiVendita' (quantità non è un numero)
	 * @see gui.SchermataEffettuaVendita#controllaInserimentiVendita()
	 */
	public final static String ERRORE_QUANTITA_NON_VALIDA = "Errore: Quantita non valida";
	/**
	 * messaggio di errore per il vincolo di 'controllaInserimentiVendita'  (data mancante)
	 * @see gui.SchermataEffettuaVendita#controllaInserimentiVendita()
	 */
	public final static String ERRORE_DATA_VUOTA = "Errore: Data non inserita";
	/**
	 * messaggio di errore per il vincolo di 'setImmagine' (immagine non impostata)
	 * @see gui.SchermataImpiegato#setImmagine()
	 */
	public final static String ERRORE_IMMAGINE = "Errore: una o più immagini non sono state caricate";
	/**
	 * messaggio di segnalazione di avvenuta registrazione della vendita
	 */
	public final static String VENDITA_EFFETTUATA = "Vendita inserita";
	/**
	 * messaggio di segnalazione di mancata registrazione della vendita
	 */
	public final static String VENDITA_NON_EFFETTUATA= "Vendita NON inserita\n";
	
}
