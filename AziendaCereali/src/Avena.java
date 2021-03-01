/*
 * @autor Ben Sidi Alessio 717848
 * 
 * Classe Concreta dell'Avena che rappresenta il prodotto vendibile dall'azienda
 * estende la classe astratta @see AbstractCereali
 *  
 */
public class Avena extends AbstractCereali {
	
	//Valore intero che indica quanti giorni il cereale in questione puo mantenere la sua freschezza dala data di imballaggio
	private final static int GIORNI_FRESCHEZZA = 10;
	//Valore double prezzo di vendita del cereale
	private final static double PREZZO = 10.00;
	//Stringa che indica il nome della classe, utile per identificare la classe in alcuni controlli
	protected final static String NOME_CLASSE = "avena";
	//Valore double che indica il vincolo di vendita massima giornaliera di questo cereale
	private final static double QUANTITA_GIORNALIERA_MAX = 30.00;
	
	/*
	 * costruttore della classe Avena
	 * @param proprietaNutritive Stringa che elenca le proprietà nutritive del cereale in costruzione
	 * @pre proprietaNutritive deve essere una stringa
	 * @post inizializzazione e costruzione della classe astratta @see AbstractCereali
	 * 
	 */
	public Avena(String proprietaNutritive) {
		super(proprietaNutritive, GIORNI_FRESCHEZZA, PREZZO, NOME_CLASSE);
	}
}
