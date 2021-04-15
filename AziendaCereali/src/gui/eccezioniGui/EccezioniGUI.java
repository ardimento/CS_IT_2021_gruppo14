package gui.eccezioniGui;
/**
 * 
 * @author Alemanno Giuseppe 716262
 *
 *	Classe utilizzata per lanciare eccezioni che riguardano la GUI
 *  e più in particolare l'acquisizione delle informazioni per effettuare la vendita. 
 *
 */
public class EccezioniGUI extends Exception {
	
	/**
	 * attributo per la serializzazione della classe.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * costruttore aparametrico dell'eccezione
	 */
	public EccezioniGUI() {}
	
	/**
	 * 
	 * @param messaggio Stringa che descrive l'errore che viene lanciato.
	 * @param causa Istanza dell'eccezione inizializzata.
	 */
	public EccezioniGUI(String messaggio,Throwable causa) {
		super(messaggio, causa);
	}
}
