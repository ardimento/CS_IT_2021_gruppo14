package eccezioni;
/**
 * 
 * 
 * @author BenSidi Alessio 717848
 *
 *
 *	Le Eccezioni lanciate con questa classe permettono di controllare i vari vincoli riguardante la vendita
 *	dei vari cereali dell'azienda, vincoli legati al singolo cereale o legati a colui che effettua la vendita
 *
 */
public class EccezioniVendita extends Exception {
	
	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public EccezioniVendita() {}
	
	/**
	 * 
	 * @param message Stringa che descrive l'errore che viene lanciato.
	 * @param cause Istanza dell'eccezione inizializzata.
	 */
	public EccezioniVendita(String message,Throwable cause) {
		super(message, cause);
	}
}
