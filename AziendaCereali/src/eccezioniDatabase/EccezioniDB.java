package eccezioniDatabase;

/**
 * 
 * 
 * @author BenSidi Alessio 717848
 *
 *
 *	Le Eccezioni lanciate con questa classe permettono di controllare se vanno o meno a buon fine l'apertura e la chiusura al database
 *
 */
 public class EccezioniDB extends Exception {
	/**
	 * attributo per la serializzazione della classe.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public EccezioniDB() {}
	
	/**
	 * 
	 * @param message Stringa che descrive l'errore che viene lanciato.
	 * @param cause Istanza dell'eccezione inizializzata.
	 */
	public EccezioniDB(String message,Throwable cause) {
		super(message, cause);
	}
}