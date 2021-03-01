/**
 * 
 * @author Fortunato Giuseppe 724309
 *
 * Interfaccia di AbstractCereali 
 * 
 */
public interface Cereale {
	
	/**
	 * Metodo che ritorna il prezzo del cereale
	 * @return prezzo
	 */
	public Double getPrezzo();
	
	/**
	 * Metodo che ritorna il tempo di freschezza di un cereale
	 * @return giorniFreschezza
	 */
	public int getTempoFreschezza();
	
	/**
	 * Metodo che ritorna le porprieta' nutritive del cereale
	 * @return proprietaNutritive
	 */
	public String getProprietaNutritive();
	
	/**
	 * Metodo che ritorna il nome del cereale
	 * @return nomeCereale
	 */
	public String getNomeCereale();
	
}
