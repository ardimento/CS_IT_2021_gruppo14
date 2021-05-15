package vendita;
/**
 * Interfaccia che è implementata dalla classe @see Vendita dove per la maggior parte ritroviamo metodi che restituiscono
 * i dati di una singola vendita
 * 
 * @author BenSidi Alessio 717848
 *
 */
public interface VenditaInterfaccia {
	
	/** fornisce in formato stringa i dati della vendita*/
	public String toString();
	/** permette di ricavare la stringa che rappresenta l'elenco delle proprietà nutritive del cereale coinvolto nella vendita*/
	public String getProprietaNutritive();
	/** restituisce il valore double in kg del peso della vendita del cereale coinvolto*/
	public Double getQuantitaCereale();
	/** restrituisce una stringa con la data della vendita nel formato YYYY-MM-GG*/
	public String getDataVendita();
	/** restituisce le informazioni della vendita sottoforma di un array di oggetti */
	public Object [] arrayDiOggetti ();
	/** restrituisce una stringa con la data dell'imballaggio nel formato YYYY-MM-GG*/
	public String getDataImbalaggio();
	/** restrituisce una stringa con la data della scadenza nel formato YYYY-MM-GG*/
	public String getDataScadenza();
	/** restrituisce un Double con il prezzo della vendita*/
	public Double getPrezzoVendita();
}
