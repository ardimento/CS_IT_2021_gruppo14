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
	
}
