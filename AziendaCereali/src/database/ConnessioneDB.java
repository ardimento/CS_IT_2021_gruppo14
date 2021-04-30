/**
 * 
 * @author Ben Sidi Alessio 717848
 * 
 * Classe che permette di connettersi al database dell'azienda
 * La classe puo essere istanziata solo una volta per avvio dato che è stata strutturata come una classe singleton.
 * 
 * Importante, prima di effettuare ogni tipo di utilizzo, è importante connettesi al database tramite la funzione @seeconnettiDB
 * 
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import eccezioni.EccezioniVendita;
import impiegato.Impiegato;
import vendita.Vendita;
import vendita.VenditaInterfaccia;

public class ConnessioneDB {
	private String nomeDB = "aziendaagricola";
	private String user = "admin";
	private String pass = "S3rv3rd4t4b4s3";
	private String host = "database-aziendaagricola.ctwio3xvhbrt.us-east-2.rds.amazonaws.com";
	private int port = 3306;
	private String url = "jdbc:mysql://" + host + ":" + port + "/" + nomeDB + "?serverTimezone=UTC";

	private static ConnessioneDB connessioneDB;
	
	private Connection connettore;
	
	private ConnessioneDB() {}
	
	
	/**
	 * metodo che permette di instanziare il singolo oggetto della classe ConnessioneDB
	 * @pre non devono esistere altri oggetti istanziati della classe ConnessioneDB
	 * @return un oggetto di tipo connessioneDB
	 */
	public static ConnessioneDB creaConnessione() {
		if(connessioneDB==null) {
			connessioneDB = new ConnessioneDB();
		}
		return connessioneDB;
	}
	
	/**
	 * permette di connettere l'applicativo al database attraverso l'attributo @seeurl
	 * @pre per poter aprire la connessione con il database bisogna essere certi che questa sia chiusa.
	 * 
	 * @return true se la connessione è stata aperta con successo, false se non è stato possibile aprire la connessione
	 */
	public boolean connettiDB() {
		if(connettore==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connettore = DriverManager.getConnection(url, user, pass);
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * permette di chiudere la connessione al database precedentemente aperta
	 * @pre la connessione al database deve essere aperta per poterla chiuderla
	 * 
	 * @return true se la connessione è stata chiusa con successo, false se non è stato possibile chiudere la connessione
	 */
	public boolean chiudiConnessioneDB() {
		if(connettore != null) {
			try {
				connettore.close();	
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * carica i dati di tutti gli impiegati e restituisce una matrice di stringhe per caricare la map di impiegati nell'azienda
	 * @param impiegati mappa che colleziona i dati degli impiegati dell'azienda
	 * @return True se il caricamento dei dati degli impiegati è avvenuto con successo, False se il caricamento dei dati non è fallito.
	 */
	
	public boolean caricaDatiImpiegati(Map<String, Impiegato> impiegati) {
		
		Impiegato i;
		if(connettore != null) {
			try {
				PreparedStatement pstm = connettore.prepareStatement("SELECT * FROM impiegato");
				ResultSet result = pstm.executeQuery();
				while(result.next()) {
					i = new Impiegato(result.getString(1), result.getDouble(2));
					impiegati.put(i.getCodiceImpiegato(), i);
				}
				pstm.close();
				result.close();
				return true;
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * carica dal database i dati di tutte le vendita di ogni impiegato tramite il suo id
	 * @pre l'applicativo deve essere connesso al database tramite il metodo @seeConnettiDB
	 * @param vendite contenitore di istanze di vendite legate all'impiegato
	 * @param impiegato istanza di un impiegato associato alle vendite da caricare
	 * @return True se il caricamento dal database delle vendite dell'impiegato specificato siano caricate con successo, False il caricamento delle vendita dal database è fallito
	 */
	public boolean caricaDativendita(Set<VenditaInterfaccia> vendite, Impiegato i) throws EccezioniVendita{
		
		VenditaInterfaccia v;
		//l'oggetto impiegato passato per argomento deve correttamente istanziato
		if(i!=null) {
			if(connettore != null) {
				try {
					PreparedStatement pstm = connettore.prepareStatement("Select * From vendita WHERE impiegato = " + i.getCodiceImpiegato() + ";");
					ResultSet result = pstm.executeQuery();
					while(result.next()) {
						v = new Vendita(result.getString(1), result.getString(3), result.getString(4), result.getString(5), result.getString(6),result.getDouble(7),result.getDouble(8));
						vendite.add(v);
						System.out.println(v);
					}
					pstm.close();
					result.close();
					return true;
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * inserisce nel database un impiegato correttamente istanziato inviando al database i suoi dati correlati
	 * @param id stringa di caratteri alfanumerici che identifica univocamente un impiegato dell'azienda
	 * @param quantitaMaxAnnua quantità massima di cereali espressa in kg che un impiegato puo vendere annualmente
	 * @pre la connessione al database deve essere aperta precedentemente la chiamata a questo metodo attraverso il metodo @see connettiDB
	 * @post i dati passati come parametro verranno salvati sul database connesso in precedenza
	 * @return True se l'inserimento dei dati è avvenuta con successo, False se l'inserimento dei dati e fallita.
	 */
	public boolean inserimentoImpiegato(String id, Double quantitaMaxAnnua) {
		if(connettore != null) {
			try {
				PreparedStatement pstm = connettore.prepareStatement("INSERT INTO Impiegati VALUES(? ?)");
				pstm.setString(1, id);
				pstm.setDouble(2, quantitaMaxAnnua);
				pstm.executeQuery();
				pstm.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * inserisce una nuova vendita nel database
	 * @pre la connessione al database deve essere attivata tramite il metodo @seeconnettiDB
	 * @post i dati passati come parametro verranno salvati sul database connesso in precedenza
	 * @param codVendita Stringa alfanumerica che identifica univocamente la vendita
	 * @param codImpiegato Stringa alfanumerica che identifica univocamente l'impiegato che ha effettuato la vendita
	 * @param cereale Stringa che indica il cereale messo in vendita
	 * @param dataVendita Stringa che rappresenta la data in cui viene effettuata la vendita nel formato YYYY-MM-GG
	 * @param dataImballaggio Stringa che rappresenta la data in cui viene imballato il prodotto venduto nel formato YYYY-MM-GG
	 * @param dataScadenza Stringa che rappresenta la data in cui il prodotto perde la sua freschezza, formato YYY-MM-GG
	 * @param quantitaCereale valore Double che indica la quantità in kg del cereale venduto in una singola vendita
	 * @return True se l'inserimento della vendita viene effettuata con successo, False se l'inserimento della vendita fallisce.
	 */
	//AGGIUNGERE PREZZO VENDITA QUI E AGGIUNGERE LA COLONNA SUL DATABASE IN "VENDITA"
	public boolean inserimentoVendita(String codVendita, String codImpiegato, String cereale, String dataVendita, String dataImballaggio, String dataScadenza, Double quantitaCereale) {
		if(connettore != null) {
			try {
				PreparedStatement pstm = connettore.prepareStatement("INSERT INTO Impiegati VALUES(? ? ? ? ? ? ?);");
				pstm.setString(1, codVendita);
				pstm.setString(2, codImpiegato);
				pstm.setString(3, cereale);
				pstm.setString(4, dataVendita);
				pstm.setString(5, dataImballaggio);
				pstm.setString(6, dataScadenza);
				pstm.setDouble(7, quantitaCereale);
				pstm.close();
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
