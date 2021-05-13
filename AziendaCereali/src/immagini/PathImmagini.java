package immagini;

/**
 * Classe utilizzata dal programma per richiamare le immagini volute.
 * 
 * Invece di indicare il path in ogni punto dove è richiesta una certa immagine, 
 * richiamando il path indicato qui dentro,se in futuro dovvesse avvenire una modifica del path
 * (ad esempio cambio nome all'immagine) tale modifica andrà riportata solamente in questa classe.
 * (elimino la dipendenza tra le immagini utilizzate e la GUI che le richiama).
 *
 * @author Alemanno Giuseppe 716262
 *
 */
public class PathImmagini {

	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata nella schermata Login 
	*	@see gui.Login
	*/
	public final static String IMMAGINE_LOGIN = "/immagini/immagine2.jpg";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona nella finestra del programma 
	 * 
	 * @see gui.SchermataImpiegato#setImmagini()
	 */
	public final static String IMMAGINE_ICONA_PROGRAMMA = "/immagini/iconaFinestra.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona nei bottoni per tornare indieto */
	public final static String IMMAGINE_BOTTONE_INDIETRO = "/immagini/freccia1.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona dell'utente */
	public final static String IMMAGINE_ICONA_UTENTE = "/immagini/icone possibili/male.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona del bottone @see btnMakeSell */
	public final static String IMMAGINE_ICONA_VENDI = "/immagini/icone possibili/wheat-sack-money.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona del bottone  @see btnShowSell */
	public final static String IMMAGINE_ICONA_VISUALIZZA_VENDITE = "/immagini/icone possibili/archive-wheat-sack.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona delle vendite giornaliere dell'impiegato */
	public final static String IMMAGINE_ICONA_VENDITE_GIORNATA = "/immagini/icone possibili/wheat-sack-day.png";
	
	/** valore alfanumerico che rappresenta il path dell'immagine utilizzata come icona delle vendite annuali dell'impiegato */
	public final static String IMMAGINE_ICONA_VENDITE_ANNO= "/immagini/icone possibili/wheat-sack-year.png";
}
