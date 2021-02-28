
public class CerealeFactory {

	public static Cereale creatoreCereale (String cereale) {
		Cereale cerealeDaIstanziare;
		switch (cereale) {
		
		case "avena" : {
			cerealeDaIstanziare = new Avena(ProprietaNutritive.PROPRIETA_AVENA);
			
		}
		return cerealeDaIstanziare;
		
		}
	}
}
