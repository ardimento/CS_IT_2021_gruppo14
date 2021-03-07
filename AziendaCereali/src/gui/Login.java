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
 * @author Giuseppe Alemano 716262
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
	private JPanel panelImage,panelForm;
	/**
	 * Label, aree di testo precompilate, usate come riferimento nella realizzazione della schermata grafica.
	 */
	private JLabel labelAccess,labelCode,labelWelcome,labelImage;
	/**
	 * TextField, area di testo vuota, nel quale l'impiegato inserirà i dati per l'accesso all sua area di lavoro.
	 */
	private JTextField textCode;
	/**
	 * Bottone con il quale si esegue un evento cliccandolo.
	 */
	private JButton buttonEnter;

	
	
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
		setImageLogin();
		azioneBottone(impiegati);
	}
	
	
	/**
	 * Metodo che imposta l'immagine di login 
	 * E lancia una eccezione se l'immagine non viene creata.
	 * 
	 */
	private void setImageLogin() {
		try {
			ImageIcon immagineIcona = new ImageIcon(Login.class.getResource(immagini.PathImmagini.IMMAGINE_LOGIN));
			Image immagine = immagineIcona.getImage();
			Image immagineModificata = immagine.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(), Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(immagineModificata);
			
			labelImage.setIcon(immagineIcona);
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
		panelImage = new JPanel();
		panelImage.setBounds(0, 0, login.getWidth()/2, login.getHeight());
		panelImage.setBackground(new Color(244,164,96));
		panelImage.setVisible(true);
		login.getContentPane().add(panelImage);
		panelImage.setLayout(null);
		labelImage = new JLabel();
		labelImage.setBounds(0, 0, panelImage.getWidth(), panelImage.getHeight());
		panelImage.add(labelImage);
	
		/**
		 * componente grafica pannello(usato come contenitore del form di accesso)
		 */
		panelForm = new JPanel();
		panelForm.setBackground(SystemColor.window);
		panelForm.setBounds(login.getWidth()/2,0,login.getWidth()-panelImage.getWidth(),login.getHeight());
		login.getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		labelAccess = new JLabel();
		panelForm.add(labelAccess);
		labelAccess.setBounds(66,293,265,31);
		labelAccess.setVisible(false);
		
		labelCode = new JLabel("Codice accesso: ");
		labelCode.setBounds(68,190,265,36);
		labelCode.setFont(new Font("Tahoma",Font.PLAIN,20));
		panelForm.add(labelCode);
		
		textCode = new JTextField();
		textCode.setBounds(68, 231, 265, 31);
		panelForm.add(textCode);
		
		labelWelcome = new JLabel("BENVENUTO", SwingConstants.CENTER);
		labelWelcome.setBounds(60, 68, 265, 69);
		labelWelcome.setForeground(Color.BLACK);
		labelWelcome.setFont(new Font("Tahoma",Font.PLAIN,25));
		panelForm.add(labelWelcome);
		
		/**
		 *  componente grafica bottone di conferma accesso
		 */
		buttonEnter = new JButton("ENTRA");
		buttonEnter.setBounds(66,489,265,50);
		buttonEnter.setFont(new Font("Tahoma",Font.PLAIN,24));
		buttonEnter.setForeground(SystemColor.window);
		buttonEnter.setBackground(new Color(244,164,96));
		panelForm.add(buttonEnter);
		
	}
	
	/**
	 * Metodo che esegue l'azione che si deve verificare al click del bottone.
	 * Nel caso in cui il codice è corretto, viene visualizzato un esito e la chiamata
	 * alla schermata grafica dell'impiegato corrispondente.
	 * Nel caso in cui il codice è errato, viene visualizzato un esito negativo.
	 * @param impiegati
	 */
	private void azioneBottone(Map<String, Impiegato> impiegati) {
		buttonEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String codiceUtente = textCode.getText();
				
				if(impiegati.containsKey(codiceUtente)) {
					login.setVisible(false);
					JOptionPane.showMessageDialog(rootPane, "Accesso Eseguito");
				}
				else {
					labelAccess.setVisible(true);
					labelAccess.setForeground(Color.RED);
					labelAccess.setText("ACCESSO NEGATO");
				}
			}
		});
	}
	
}
