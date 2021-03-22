package cereale;

import eccezioni.EccezioniVendita;
import eccezioni.MessaggiErroreVendita;

/**
 * @autor Ben Sidi Alessio 717848
 * 
 * Classe Concreta dell'Avena che rappresenta il prodotto vendibile dall'azienda
 * estende la classe astratta @see AbstractCereali
 *  
 */
public class Avena extends AbstractCereale {
	
	/**Valore intero che indica quanti giorni il cereale in questione puo mantenere la sua freschezza dala data di imballaggio*/
	private final static int GIORNI_FRESCHEZZA = 10;
	/**Valore double prezzo di vendita del cereale*/
	private final static double PREZZO = 10.00;
	/**Stringa che indica il nome della classe, utile per identificare la classe in alcuni controlli*/
	protected final static String NOME_CLASSE = "avena";
	/**Valore double che indica il vincolo di vendita massima giornaliera di questo cereale*/
	private final static double QUANTITA_GIORNALIERA_MAX = 30.00;
	
	/**
	 * costruttore della classe Avena a cui viene associato un controllo per la possibile vendita dello stesso attraverso il metodo @seeisCerealeVendibile.
	 * 
	 * @param proprietaNutritive Stringa che elenca le proprietà nutritive del cereale in costruzione
	 * @param quantitaCereale valore Double del peso del cereale in vendita
	 * @param quantitaVenduta valore Double della somma di tutti i cereali già venduti per lo stesso impiegato
	 * @pre la somma tra quantitaVenduta e la quantitaCereale non deve superare il valore di @seeQUANTITA_GIORNALIERA_MAX
	 * @post inizializzazione e costruzione dell'oggetto Avena.
	 * 
	 */
	public Avena(String proprietaNutritive, Double quantitaCereale, Double quantitaVenduta) throws EccezioniVendita {
		super(proprietaNutritive, GIORNI_FRESCHEZZA, PREZZO, NOME_CLASSE);
		
		if(!isCerealeVendibile(quantitaCereale,quantitaVenduta)) {
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_GIORNALIERA, new EccezioniVendita());
		}
	}
	/**
	 * Metodo che si limita a creare una nuova istanza di "Avena" con i valori dei parametri presi dai dati presenti sul DataBase per 
	 * caricare i dati all'avvio del Sistema.
	 * @param proprietaNutritive Stringa che raccoglie l'elenco delle proprietà nutritive del prodotto "Avena"
	 * @param quantitaCereale	valore Double che indica la quantità del cereale venduto in Kilogrammi
	 * 
	 * @post istanzazione di un oggetto di tipo Avena
	 */
	public Avena(String proprietaNutritive, Double quantitaCereale) {
		super(proprietaNutritive, GIORNI_FRESCHEZZA, PREZZO, NOME_CLASSE);
		
	}
	/**
	 * Override del metodo "isCerealeVendibile" della classe astratta @see AbstractCereale che effettua il controllo sul vincolo
	 * del peso giornaliero massimo legato all'avena.
	 * 
	 * @param quantitaCereale valore Double del peso in vendita del cereale in questione
	 * @param quantitaVenduta valore Double della somma del peso di tutte le vendite dell'impiegato che sta effettuando la vendita dello specifico cereale
	 * @return True se la somma tra quantitaVenduta e quantitaCereale è maggiore o uguale di @seeQUANTITA_GIORNALIERA_MAX quindi la vendita è effettuabile,
	 * 	False se @seeQUANTITA_GIORNALIERA_MAX è minore della somma tra quantitaCereale e quantitaVenduta, quindi la vendita non è effettuabile.
	 */
	@Override
	protected boolean isCerealeVendibile(Double quantitaCereale, Double quantitaVenduta) {
		// TODO Auto-generated method stub
		return QUANTITA_GIORNALIERA_MAX >= quantitaCereale + quantitaVenduta;
	}
	
	
} 
