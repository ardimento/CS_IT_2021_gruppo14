package cereale;

public class CerealeFactory {

	public static Cereale creatoreCereale (String cereale) {
		Cereale cerealeDaIstanziare = null;
		switch (cereale) {
		
			case "avena" : {
				cerealeDaIstanziare = new Avena(ProprietaNutritive.PROPRIETA_AVENA);
			
			}
		
		
		}
		return cerealeDaIstanziare;
	}
}
