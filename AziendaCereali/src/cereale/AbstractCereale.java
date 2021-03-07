package cereale;

/**
 *
 * Classe astratta di AbstractCereali. Questa classe raccoglie tutte le proprieta' condivise 
 * dai vari cereali vendibili dall' azienda
 * 
 * @author Fortunato Giuseppe 724309
 * 
 */
public class AbstractCereale implements Cereale {
	
	/**
	 * Valore String che indica le proprieta' nutritive del cereale
	 */
	private String proprietaNutritive;
	/**
	 * Valore intero che indica i giorni di freschezza del cereale
	 */
	private int giorniFreschezza;
	/**
	 * Valore Double che indica il prezzo del cereale
	 */
	private Double prezzo;
	/**
	 * Valore String che indica il nome del cereale
	 */
	private String nomeCereale;
	
	/**
	 * Costruttore AbstractCereale
	 * @param proprietaNutritive stringa che elenca le proprieta' nutritive del cereale
	 * @param giorniFreschezza intero che indica i giorni di freschezza del cereale
	 * @param prezzo Double che indica il prezzo del cereale
	 * @param nomeCereale String che indica il nome del cereale
	 */
	public AbstractCereale(String proprietaNutritive, int giorniFreschezza, Double prezzo, String nomeCereale) {
		this.proprietaNutritive = proprietaNutritive;
		this.giorniFreschezza = giorniFreschezza;
		this.prezzo = prezzo;
		this.nomeCereale = nomeCereale;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double getPrezzo() {
		return prezzo;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getTempoFreschezza() {
		return giorniFreschezza;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getProprietaNutritive() {
		return proprietaNutritive;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomeCereale() {
		return nomeCereale;
	}
	/**
	 * Controlla se il cereale rispetta i propri vincoli di vendita legati alla quantità, se uno specifico cereale ha dei suoi particolari vincoli legati alla vendita, dovrà essere
	 * effettuato un override di questo metodo nella sua classe concreta e implementare i dovuti controlli per i suoi vincoli specifici legati alle varie quantita. Altrimenti questo vincolo
	 * restituisce sempre TRUE se non ci sono vincoli particolari legati al peso legati ad un determinato cereale.
	 * 
	 * Per vincoli diversi dal peso, sarà possibile aggiungere l'overload dello stesso metodo con parametri in base al tipo di vincolo che serve passaere per parametro
	 * e implementarlo nella classe concreta utile.
	 * 
	 * @param quantitaCereale valore Double del peso in vendita del cereale in questione
	 * @param quantitaVenduta valore Double della somma del peso di tutte le vendite dell'impiegato che sta effettuando la vendita dello specifico cereale
	 * @return true indica che la vendita è sempre effettuabile
	 */
	protected boolean isCerealeVendibile(Double quantitaCereale, Double quantitaVenduta) {
		return true;
	}
}
