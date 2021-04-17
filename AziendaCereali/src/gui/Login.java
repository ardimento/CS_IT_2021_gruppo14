package gui;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import impiegato.Impiegato;

/**
 * Classe che gestisce graficamente l'accesso degli impiegati
 * alle proprie aree di lavoro 
 * 
 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class Login extends JFrame{
	/**
	 * Frame di nome login che visualizza la schermata di accesso alle aree di lavoro agli impiegati.
	 */
	private JFrame login;
	/**
	 * Pannelli di uso comune inseriti all'interno del frame per la realizzazione della schermata grafica.
	 */
	private JPanel panelImmagine,panelForm;
	/**
	 * Label, aree di testo precompilate, usate come riferimento nella realizzazione della schermata grafica.
	 */
	private JLabel labelAccesso,labelCodice,labelBenvenuto,labelImmagine;
	/**
	 * TextField, area di testo vuota, nel quale l'impiegato inserirà i dati per l'accesso all sua area di lavoro.
	 */
	private JTextField tfCodice;
	/**
	 * Bottone con il quale si esegue un evento cliccandolo.
	 */
	private JButton btnAccesso;

	
	
	/**
	 * Costruttore della classe grafica Login. Questa classe serve ad eseguire l'accesso alle aree di lavoro
	 * degli impiegati.
	 * Questo costruttore fa uso del parametro Map<String, Impiegato> impiegati per controllare se effettivamente
	 * l'utente che cerca di accedere è un impiegato o meno.
	 * @param impiegati
	 */
	public Login (Map<String, Impiegato> impiegati) {
		super();
		this.login = new JFrame();	
		setLogin();
		setImmagineDiLogin();
		azioneBottone(impiegati);
		azioneTastiera(impiegati);
	}
	
	
	/**
	 * Metodo che imposta l'immagine di login 
	 * E lancia una eccezione se l'immagine non viene creata.
	 * 
	 */
	private void setImmagineDiLogin() {
		try {
			ImageIcon immagineIcona = new ImageIcon(Login.class.getResource(immagini.PathImmagini.IMMAGINE_LOGIN));
			Image immagine = immagineIcona.getImage();
			Image immagineModificata = immagine.getScaledInstance(labelImmagine.getWidth(), labelImmagine.getHeight(), Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(immagineModificata);
			
			labelImmagine.setIcon(immagineIcona);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Non è stato possibile caricare l'immagine di login", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia.
	 */
	private void setLogin() {
		/**
		 * componente grafica login(frame)
		 */
		login.setResizable(false);
		login.setVisible(true);
		login.setTitle("Azienda Cereali");
		login.setSize(800,600);
		login.setLocationRelativeTo(null);
		login.setBackground(SystemColor.window);
		login.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource(immagini.PathImmagini.IMMAGINE_ICONA_PROGRAMMA)));
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		/**
		 * componente grafica pannello(usato come contenitore dell' immagine)
		 */
		panelImmagine = new JPanel();
		panelImmagine.setBounds(0, 0, login.getWidth()/2, login.getHeight());
		panelImmagine.setBackground(new Color(244,164,96));
		panelImmagine.setVisible(true);
		login.getContentPane().add(panelImmagine);
		panelImmagine.setLayout(null);
		labelImmagine = new JLabel();
		labelImmagine.setBounds(0, 0, panelImmagine.getWidth(), panelImmagine.getHeight());
		panelImmagine.add(labelImmagine);
	
		/**
		 * componente grafica pannello(usato come contenitore del form di accesso)
		 */
		panelForm = new JPanel();
		panelForm.setBackground(SystemColor.window);
		panelForm.setBounds(login.getWidth()/2,0,login.getWidth()-panelImmagine.getWidth(),login.getHeight());
		login.getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		labelAccesso = new JLabel();
		panelForm.add(labelAccesso);
		labelAccesso.setBounds(66,293,265,31);
		labelAccesso.setVisible(false);
		
		labelCodice = new JLabel("Codice accesso: ");
		labelCodice.setBounds(68,190,265,36);
		labelCodice.setFont(new Font("Tahoma",Font.PLAIN,20));
		panelForm.add(labelCodice);
		
		tfCodice = new JTextField();
		tfCodice.setBounds(68, 231, 265, 31);
		panelForm.add(tfCodice);
		
		labelBenvenuto = new JLabel("BENVENUTO", SwingConstants.CENTER);
		labelBenvenuto.setBounds(60, 68, 265, 69);
		labelBenvenuto.setForeground(Color.BLACK);
		labelBenvenuto.setFont(new Font("Tahoma",Font.PLAIN,25));
		panelForm.add(labelBenvenuto);
		
		/**
		 *  componente grafica bottone di conferma accesso
		 */
		btnAccesso = new JButton("ENTRA");
		btnAccesso.setBounds(66,489,265,50);
		btnAccesso.setFont(new Font("Tahoma",Font.PLAIN,24));
		btnAccesso.setForeground(SystemColor.window);
		btnAccesso.setBackground(new Color(244,164,96));
		btnAccesso.setFocusPainted(false);
		panelForm.add(btnAccesso);
		
	}
	
	/**
	 * Metodo che esegue l'azione che si deve verificare al click del bottone.
	 * Nel caso in cui il codice è corretto, viene visualizzato un esito e la chiamata
	 * alla schermata grafica dell'impiegato corrispondente.
	 * Nel caso in cui il codice è errato, viene visualizzato un esito negativo.
	 * @param impiegati
	 */
	private void azioneBottone(Map<String, Impiegato> impiegati) {
		btnAccesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chiamaSchermataImpiegato(impiegati);
			}
		});
	}
	/**
	 * Metodo che esegue l'azione cdi verifica del codice inserito 
	 * nel momento che l'utente durante l'inserimento del codice, preme invio.
	 * Il metodo rende anche invisibile l'esito della verifica quando l'impiegato si appresta a scrivere.
	 *  @param impiegati collezione di impiegati.
	 */
	private void azioneTastiera(Map<String, Impiegato> impiegati) {
		tfCodice.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent tastoPremuto) {
				labelAccesso.setVisible(false);
				if(tastoPremuto.getKeyCode() == KeyEvent.VK_ENTER)
					chiamaSchermataImpiegato(impiegati);
			}
		});
	}
	
	/**
	 * Metodo che esegue la verifica del codice inserito nella JTextField 
	 * Nel caso in cui il codice è corretto (appartiene ad un impiegato), viene visualizzato un esito e la chiamata
	 * alla schermata grafica dell'impiegato corrispondente.
	 * Nel caso in cui il codice è errato, viene reso visibile un esito negativo.
	 * @param impiegati collezione di impiegati.
	 */
	private void chiamaSchermataImpiegato(Map<String, Impiegato> impiegati) {
		String codiceUtente = tfCodice.getText();
		
		if(impiegati.containsKey(codiceUtente)) {
			login.setVisible(false);
			JOptionPane.showMessageDialog(rootPane, "Accesso Eseguito");
			SchermataEffettuaVendita imp = new SchermataEffettuaVendita(impiegati.get(codiceUtente),login);
		}
		else {
			labelAccesso.setVisible(true);
			labelAccesso.setForeground(Color.RED);
			labelAccesso.setText("ACCESSO NEGATO");
		}
	}
}
