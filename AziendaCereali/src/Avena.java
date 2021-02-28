
public class Avena extends AbstractCereali {
	
	private final static int GIORNI_FRESCHEZZA = 10;
	private final static double PREZZO = 10.00;
	protected final static String NOME_CLASSE = "avena";
	private final static double QUANTITA_GIORNALIERA_MAX = 30.00;
	
	public Avena(String proprietaNutritive) {
		super(proprietaNutritive, GIORNI_FRESCHEZZA, PREZZO, NOME_CLASSE);
	}
}
