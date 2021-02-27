
public class AbstractCereali implements Cereali {
	
	private String proprietaNutritive;
	private int giorniFreschezza;
	private Double prezzo;
	private String nomeCereale;
	
	public AbstractCereali(String proprietaNutritive, int giorniFreschezza, Double prezzo, String nomeCereale) {
		this.proprietaNutritive = proprietaNutritive;
		this.giorniFreschezza = giorniFreschezza;
		this.prezzo = prezzo;
		this.nomeCereale = nomeCereale;
	}

	@Override
	public Double getPrezzo() {
		return prezzo;
	}

	@Override
	public int getTempoFreschezza() {
		return giorniFreschezza;
	}

	@Override
	public String getProprietaNutritive() {
		return proprietaNutritive;
	}

	@Override
	public String getNomeCereale() {
		return nomeCereale;
	}
	
	
	
	
}
