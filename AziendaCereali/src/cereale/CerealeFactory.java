package cereale;

import eccezioni.EccezioniVendita;

public class CerealeFactory {

	public static Cereale creatoreCereale (String cereale, double quantitaCereale, double quantitaVenduta) throws EccezioniVendita {
		Cereale cerealeDaIstanziare = null;
		switch (cereale) {
		
			case "avena" : {
				cerealeDaIstanziare = new Avena(ProprietaNutritive.PROPRIETA_AVENA, quantitaCereale, quantitaVenduta);
			
			}
		
		
		}
		return cerealeDaIstanziare;
	}
}
 