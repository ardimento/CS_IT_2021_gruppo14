package impiegato;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import vendita.Vendita;
import vendita.VenditaInterfaccia;

/**
 * Classe Impiegato che rappresenta lo stato e il comportamento che un impiegato generico possiede.
 * @author Giuseppe Alemanno 716262
 *
 */
public class Impiegato {
	
	/** valore che indica il codice identificativo di un impiegato */
	private String codiceImpiegato  = "";
	/** valore che rappresenta la massima quantità (in peso) di Cereali vendibili dall'impiegato in un anno*/
	private double pesoAnnuoMassimo = 0.0;
	/** collezione di vendite (di cereali) effettuate dall'impiegato */
	private Set <VenditaInterfaccia> vendite = new HashSet<VenditaInterfaccia>();
	
	
	/**
	 * Costruttore della classe 
	 * consente di istanziare un impiegato con tutti i suoi attributi di istanza 
	 * passando i valori da inserire come parametri di input.
	 * 
	 * @param codiceImpiegato valore alfanumerico che rappresenta il codice dell'impiegato (precondizione: il codice sia univoco) 
	 * @param pesoAnnuoMassimo valore double che rappresenta la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 * @param vendite collezione di vendite compiute dall'impiegato (precondizione : le vendite presenti rispettino tutti i vincoli ad esse applicabili secondo le scelte progettuali imposte)
	 *
	 */
	public Impiegato(String codiceImpiegato, double pesoAnnuoMassimo, Set<VenditaInterfaccia> vendite) {
		super();
		this.codiceImpiegato = codiceImpiegato;
		this.pesoAnnuoMassimo = pesoAnnuoMassimo;
		this.vendite = vendite;
	}


	/**
	 * Restituisce la collezioni di vendite effettuate dall'impiegato 
	 * @return la collezione di vendite 
	 */
	public Set<VenditaInterfaccia> getVendite() {
		return vendite;
	}


	/**
	 * Restituisce il codice identificativo dell'impiegato
	 * @return il codice identificativo dell'impiegato
	 */
	public String getCodiceImpiegato() {
		return codiceImpiegato;
	}

	
	/**
	 * Restituisce la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 * @return la massima quantità (in peso)  di Cereali vendibili dall'impiegato in un anno
	 */
	public double getPesoAnnuoMassimo() {
		return pesoAnnuoMassimo;
	}


	public boolean cercaVendita() {
		return false;
		//? mi serve che venga fatta una cosa in AbstractCereale
	}
	
	/**
	 * Effettua l'aggiunta di una vendita all'impiegato 
	 * se l'impegato non ha raggiunto (o con tale vendita supera) la quantità massima di cereali venduti durante l'anno.
	 * 
	 * @param quantitaCereale valore double che rappresenta la quantità di cereale che si vuole vendere
	 * @param codVendita valore alfanumerico che rapresenta il codice identificativo della vendita (precondizione : sia univoco )
	 * @param cerealeScelto valore che rappresenta il cereale che si vuole vendere (precondizione : tale stringa sia = all'attributo 'NOME_CLASSE' di un cereale esistente)
	 * @param data valore alfanumerico che rappresenta la data in cui si è effettuata tale vendita (precondizione : la data sia nel formato AAAA-MM-GG)
	 * @return restituisce true se si è verificata la condizione per cui la vendita non deve essere creata, false altrimenti.
	 */
	public boolean creaVendita(double quantitaCereale, String codVendita, String cerealeScelto, String data) {
		boolean esito = false; //ok
		double somma = 0.0;
		
		somma = quantitaVendutaAnnua(data);
		somma = somma + quantitaCereale;
		
		if ( somma > pesoAnnuoMassimo) {
			esito = true; //la creazione presenta un problema 
		} else {
			vendite.add(new  Vendita(quantitaCereale, codVendita, cerealeScelto, data));
		}
		
		return esito;
		
	}

	/**
	 * Restituisce la quantità di cereali venduti dall'impiegato in una certo anno 
	 * tramite il passaggio di una data come parametro.
	 * 
	 * @param dataVendita valore alfanumerico che rappresenta la data presa in considerazione (precondizione : la data sia nel formato AAAA-MM-GG)
	 * @return restituisce la quantità di cereali venduti dall'impiegato nell'anno della data passata.
	 */
	public double quantitaVendutaAnnua (String dataVendita) {
		double quantita = 0.0;
		LocalDate data1 = LocalDate.parse(dataVendita);
		
		Iterator<VenditaInterfaccia> iterator = vendite.iterator();
		while (iterator.hasNext()) {
			VenditaInterfaccia vendita = iterator.next();
			LocalDate data2 = LocalDate.parse(vendita.getDataVendita());
			if (data1.getYear() == data2.getYear()) {
				quantita = quantita + vendita.getQuantitaCereale();
			}
		}
		return quantita;
		
	}
	
	


	
	
}
