package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import gui.eccezioniGui.EccezioniGUI;
import gui.eccezioniGui.MessaggiGUI;
import immagini.PathImmagini;
import impiegato.Impiegato;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * Classe che gestisce graficamente il layout e le funzionalità base
 * delle schermate relative all'impiegato 
 * 
 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataImpiegato extends JFrame{
	/**
	 * Impiegato che ha eseguito l'accesso
	 */
	private Impiegato impiegato;
	/**
	 * Frame di nome frame che visualizza la schermata basica (di partenza) dell' impiegato.
	 */
	private JFrame frame;
	/**
	 * Pannelli utilizzati per la creazione e l'inserimento delle varie componenti grafiche per la realizzazione della schermata.
	 */
	private JPanel panelSuperiore, panelInferiore, panelDestro,panelSinistro,panelInfoImpiegato;
	protected JPanel panelOperazioni;
	/**
	 * Label, aree di testo precompilate, usate come visualizzazione testuale all'interno della schermata grafica.
	 */
	private JLabel labelTitloProgramma, labelDataOdierna, labelDirittiProgramma,labelIconaImpiegato,labelDatiImpiegato, labelCodice, labelQuanGiornaliera, labelQuanAnnua;
	/**
	 * Label che rappresenta il nome dell'operazione che si sta effettuando
	 */
	protected JLabel labelTitoloOperazione;
	/**
	 * Bottoni di interazione, con i quali si accede ad aree diverse del programma(schermata visualizza, crea vendita e ritorno al Login)
	 */
	protected JButton btnTornaAlLogin, btnMostraVendite, btnEffettuaVendita,btnTornaIndietro;
	/**
	 * Gruppi per gestire i layout delle componenti grafiche disposte nei vari pannelli(label, textbox ecc..)
	 */
	private GroupLayout glPanelInferiore,glPanelSinistro,glPanelInfoImpiegato;
	protected GroupLayout glPanelOperazioni;
	
	
	/**
	 * Costruttore di schermata impiegato, crea nuovo frame, ed imposta le nuove componenti.
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * @param login frame della schermata Login, chiamata nel caso di un eventuale logout.
	 */
	public SchermataImpiegato(Impiegato impiegato, JFrame login) {
		this.frame = new JFrame();
		this.impiegato = impiegato;
		setSchermataImpiegato();
		tornaAlLogin(login);
		setInfoImpiegato();	
		setImmagini();
		ModificaDimensioniDinamicamente();
		
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia.
	 */
	public void setSchermataImpiegato() {
		
		/**
		 * componente grafica frame(Schermata Impigato)
		 */
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setTitle("Azienda Cereali");
		frame.getContentPane().setBackground(new Color(244,164,96));
		frame.getContentPane().setLayout(new BorderLayout(0,0));
		frame.setMinimumSize(new Dimension(850,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(SystemColor.window);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource(immagini.PathImmagini.IMMAGINE_ICONA_PROGRAMMA)));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//---------------------------------------------------------------
		panelSuperiore = new JPanel();
		panelSuperiore.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelSuperiore, BorderLayout.NORTH);
		labelTitloProgramma = new JLabel("Software Azienda Cereale");
		panelSuperiore.add(labelTitloProgramma);
		
		//---------------------------------------------------------------
		panelInferiore = new JPanel();
		panelInferiore.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelInferiore, BorderLayout.SOUTH);
		
		labelDataOdierna = new JLabel("Data :"+ getDataOdierna());
		
		btnTornaAlLogin = new JButton("Logout");	
		btnTornaAlLogin.setFont(new Font("Tahoma", Font.PLAIN, 16) );
		btnTornaAlLogin.setForeground(Color.WHITE);
		btnTornaAlLogin.setBackground(new Color(244,164,96));
		btnTornaAlLogin.setBounds(-100,0,30,30);
		btnTornaAlLogin.setFocusPainted(false);
		
		labelDirittiProgramma = new JLabel ("    By Alemanno, Ben Sidi, Fortunato");
		
		glPanelInferiore = new GroupLayout (panelInferiore);
		setGlPanelInferiore();
		panelInferiore.setLayout(glPanelInferiore);
		
		//---------------------------------------------------------------
		panelDestro = new JPanel();
		panelDestro.setBackground(SystemColor.controlShadow);
		frame.getContentPane().add(panelDestro, BorderLayout.EAST);
		
		//---------------------------------------------------------------	
		panelSinistro = new JPanel();
		panelSinistro.setBackground(SystemColor.control);
		frame.getContentPane().add(panelSinistro, BorderLayout.CENTER);
		
		//---------------------------------------------------------------
		panelInfoImpiegato = new JPanel();
		panelInfoImpiegato.setBackground(SystemColor.scrollbar);
		
		
		labelIconaImpiegato = new JLabel();
		labelIconaImpiegato.setHorizontalAlignment(SwingConstants.CENTER);
		labelDatiImpiegato = new JLabel("DATI IMPIEGATO :");
		labelDatiImpiegato.setHorizontalAlignment(SwingConstants.CENTER);
		labelCodice = new JLabel();
		labelQuanGiornaliera = new JLabel();
		labelQuanAnnua = new JLabel();

		
		glPanelInfoImpiegato = new GroupLayout (panelInfoImpiegato);
		setGlPanelInfoImpiegato();
		panelInfoImpiegato.setLayout(glPanelInfoImpiegato);
		
		//---------------------------------------------------------------
		panelOperazioni = new JPanel();
		panelOperazioni.setBackground(new Color(245,222,179));

		glPanelSinistro = new GroupLayout (panelSinistro);
		setGlPanelSinistro();
		btnEffettuaVendita = new JButton("Effettua vendite");
		btnEffettuaVendita.setBackground(SystemColor.controlHighlight);
		btnEffettuaVendita.setFocusPainted(false);
		
		btnMostraVendite = new JButton("Visualizza vendite effettuate");
		btnMostraVendite.setBackground(SystemColor.controlHighlight);
		btnMostraVendite.setFocusPainted(false);
		
		glPanelOperazioni = new GroupLayout(panelOperazioni);
		setGlPanelOperazioni();
		panelOperazioni.setLayout(glPanelOperazioni);

		//---------------------------------------------------------------
		panelSinistro.setLayout(glPanelSinistro);
		
		labelTitoloOperazione = new JLabel("");
		labelTitoloOperazione.setFont(new Font ("Tahoma",Font.PLAIN,20));
		labelTitoloOperazione.setVisible(false);
		
		btnTornaIndietro = new JButton("Indietro");
		btnTornaIndietro.setFont(new Font ("Tahoma",Font.PLAIN,16));
		btnTornaIndietro.setForeground(Color.white);
		btnTornaIndietro.setBackground(new Color(244,164,96));
		btnTornaIndietro.setFocusPainted(false);
		btnTornaIndietro.setVisible(false);
	}
	
	/**
	 * Metodo che modifica a runtime il font delle label e la dimensione delle icone / immagini 
	 * 
	 */
	private void ModificaDimensioniDinamicamente () {
		
		panelSinistro.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int valoreCalcolato = frame.getWidth()/40;
				Font nuovoFont = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				btnEffettuaVendita.setFont(nuovoFont);
				btnMostraVendite.setFont(nuovoFont);
				
				valoreCalcolato = labelCodice.getWidth()/12;
				nuovoFont = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				labelCodice.setFont(nuovoFont);
				labelDatiImpiegato.setFont(nuovoFont);
				labelQuanGiornaliera.setFont(nuovoFont);
				labelQuanAnnua.setFont(nuovoFont);
					
				try {	
					valoreCalcolato = labelIconaImpiegato.getWidth()/2;
					Image immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_UTENTE,valoreCalcolato, valoreCalcolato);
					if(immagine!=null)
						labelIconaImpiegato.setIcon(new ImageIcon(immagine));
					
					valoreCalcolato = btnEffettuaVendita.getHeight()/2;;
					immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_VENDI, valoreCalcolato, valoreCalcolato);
					if(immagine!=null)
						btnEffettuaVendita.setIcon(new ImageIcon(immagine));
					
					immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_VISUALIZZA_VENDITE, valoreCalcolato, valoreCalcolato);
					if(immagine!=null)
						btnMostraVendite.setIcon(new ImageIcon(immagine));
				} catch(EccezioniGUI e) {
					//qui non è necessario che si abbia un qualche tipo di avviso
				}
			}
		});
	}
	
	
	/**
	 * Metodo che setta le informazioni mostrate a video dell'impiegato. 
	 */
	private void setInfoImpiegato() {
		labelCodice.setText("Codice Impiegato : "+impiegato.getCodiceImpiegato());
		labelQuanGiornaliera.setText("Quantita' Giornaliera: "+impiegato.quantitaVendutaGiornaliera(getDataOdierna()));
		labelQuanAnnua.setText("Quantita' Annua: " + impiegato.quantitaVendutaAnnua(getDataOdierna()));
	}
	
	/**
	 * Metodo che setta il layout di panelLeft
	 */
	private void setGlPanelSinistro() {
		glPanelSinistro.setHorizontalGroup(
				glPanelSinistro.createParallelGroup(Alignment.CENTER)
				.addGroup( 
						glPanelSinistro.createSequentialGroup()
					.addGap(0)
					.addComponent(panelInfoImpiegato,GroupLayout.DEFAULT_SIZE,177,400)
					.addGap(35)
					.addComponent(panelOperazioni,GroupLayout.DEFAULT_SIZE,530,1300)
					.addGap(35)
				)
		);

		glPanelSinistro.setVerticalGroup(
				glPanelSinistro.createParallelGroup(Alignment.CENTER)
				.addGroup( 
						glPanelSinistro.createSequentialGroup()
					.addComponent(panelInfoImpiegato,GroupLayout.PREFERRED_SIZE,400,Short.MAX_VALUE)
					.addGap(0)
				)
				.addGroup( 
						glPanelSinistro.createSequentialGroup()
					.addGap(35)	
					.addComponent(panelOperazioni,GroupLayout.PREFERRED_SIZE,400,700)
					.addGap(35)
				)
		);
	}
	/**
	 * Metodo che setta il layout di panelCenterRight
	 */
	private void setGlPanelOperazioni() {
		glPanelOperazioni.setHorizontalGroup( 
				glPanelOperazioni.createParallelGroup(Alignment.CENTER)
				.addGroup(
						Alignment.CENTER, glPanelOperazioni.createSequentialGroup() 
						.addGap(50)
						.addGroup(
								glPanelOperazioni.createParallelGroup(Alignment.CENTER)
							.addComponent(btnEffettuaVendita, Alignment.CENTER,GroupLayout.DEFAULT_SIZE,100,Short.MAX_VALUE)
							.addComponent(btnMostraVendite, Alignment.CENTER,GroupLayout.DEFAULT_SIZE,100,Short.MAX_VALUE)
						)
						.addGap(50)
				)
		);
		
		glPanelOperazioni.setVerticalGroup( 
				glPanelOperazioni.createParallelGroup(Alignment.CENTER)
				.addGroup(
						Alignment.CENTER, glPanelOperazioni.createSequentialGroup() 
						.addGap(50)
						.addComponent(btnMostraVendite, GroupLayout.PREFERRED_SIZE,100,Short.MAX_VALUE)
						.addGap(22)
						.addComponent(btnEffettuaVendita, GroupLayout.PREFERRED_SIZE,100,Short.MAX_VALUE )
						.addGap(50)
				)
		);
	}
	/**
	 * Metodo che setta il layout di panelCenterLeft
	 */
	private void setGlPanelInfoImpiegato() {
		
		glPanelInfoImpiegato.setHorizontalGroup( 
				glPanelInfoImpiegato.createParallelGroup(Alignment.CENTER)
				.addGroup(
						glPanelInfoImpiegato.createSequentialGroup() 
							.addContainerGap()
							.addGroup(
									glPanelInfoImpiegato.createParallelGroup(Alignment.CENTER)
								.addComponent(labelIconaImpiegato, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelDatiImpiegato, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelCodice, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelQuanGiornaliera, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelQuanAnnua, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
							)
							.addContainerGap()
				)
		);	
		
		glPanelInfoImpiegato.setVerticalGroup(
				glPanelInfoImpiegato.createParallelGroup(Alignment.CENTER)
				.addGroup(
						glPanelInfoImpiegato.createSequentialGroup()
						.addGap(25)
						.addComponent(labelIconaImpiegato,GroupLayout.DEFAULT_SIZE,200,Short.MAX_VALUE)
						.addComponent(labelDatiImpiegato,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
						.addGap(20)
						.addComponent(labelCodice,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelQuanGiornaliera,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(labelQuanAnnua,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
						.addGap(311)
						
				)
				
		);
	}
	/**
	 * Metodo che setta il layout di panelDown
	 */
	private void setGlPanelInferiore() {
		glPanelInferiore.setHorizontalGroup( 
				glPanelInferiore.createParallelGroup(Alignment.LEADING)
			.addGroup(
						Alignment.TRAILING,glPanelInferiore.createSequentialGroup() 
						.addGap(37)
						.addComponent(btnTornaAlLogin)
						.addComponent(labelDirittiProgramma)
						.addComponent(btnTornaAlLogin, GroupLayout.PREFERRED_SIZE,0,GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED,522,Short.MAX_VALUE)
						.addComponent(labelDataOdierna, GroupLayout.PREFERRED_SIZE,120,GroupLayout.PREFERRED_SIZE)
						.addGap(41)
			)
		);
		
		glPanelInferiore.setVerticalGroup( 
				glPanelInferiore.createParallelGroup(Alignment.LEADING)
			.addGroup(
					glPanelInferiore.createSequentialGroup()
						.addContainerGap()
						.addGroup(glPanelInferiore.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTornaAlLogin)
						.addComponent(labelDataOdierna, GroupLayout.PREFERRED_SIZE,30,GroupLayout.PREFERRED_SIZE)
						.addComponent(labelDirittiProgramma, GroupLayout.PREFERRED_SIZE,28,GroupLayout.PREFERRED_SIZE))
						.addComponent(btnTornaAlLogin, GroupLayout.PREFERRED_SIZE,0,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))					
			
		);		
	}
	
	
	/**
	 * Metodo che aggiunge le immagini alla schermata 
	 */
	private void setImmagini() {	
		Image immagine = null;	
		int valore;
		try {	
			valore = btnTornaAlLogin.getWidth();
			immagine = setImmagine(PathImmagini.IMMAGINE_BOTTONE_INDIETRO, valore, valore);
			if(immagine!=null)
				btnTornaAlLogin.setIcon(new ImageIcon(immagine));
			
			valore = 100;
			immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_UTENTE, valore, valore);
			if(immagine!=null)
				labelIconaImpiegato.setIcon(new ImageIcon(immagine));
			
			valore = 70;
			immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_VENDI, valore, valore);
			if(immagine!=null)
				btnEffettuaVendita.setIcon(new ImageIcon(immagine));
			
			valore = 70;
			immagine = setImmagine(PathImmagini.IMMAGINE_ICONA_VISUALIZZA_VENDITE, valore, valore);
			if(immagine!=null)
				btnMostraVendite.setIcon(new ImageIcon(immagine));
			
		} catch(EccezioniGUI e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(rootPane, MessaggiGUI.ERRORE_IMMAGINE);
		}
	
	}
	/**
	 * Metodo che ritorna l'immagine del path indicato, ridimensionata secondo le dimensini indicate come parametri 
	 * 
	 * E lancia una eccezione se l'immagine non viene caricata o ridimensionata.
	 * 
	 * @param Path Stringa che rappresenta il path dell'immagine che deve essere caricata e modificata 
	 * @param dimensione1 larghezza dell'immagine 
	 * @param dimensione1 altezza   dell'immagine
	 * 
	 * @return immagine scelta con le dimensioni volute (null in caso di eccezione)
	 * @throws EccezioniGUI eccezione lanciata in caso di problemi nel ridimensionamento o caricamento dell'immagine
	 */
	private Image setImmagine(String Path, int dimensione1, int dimensione2) throws EccezioniGUI{
		Image immagineModificata = null;
		try {
			Image immagine = ImageIO.read(getClass().getResource(Path));
			immagineModificata = immagine.getScaledInstance(dimensione1, dimensione2, Image.SCALE_SMOOTH);
			
		} catch (Exception e) {
			throw new EccezioniGUI(MessaggiGUI.ERRORE_IMMAGINE, new EccezioniGUI());
		}
		return immagineModificata;
		
	}
	
	
	/**
	 * Metodo con il quale si assegna al bottone l'azione di effettuare il logout.
	 * Rendiamo visibile il frame Login e distruggiamo dalla memoria il frame Schermata Impiegato dell'utente loggato.
	 * @param login frame che viene richiamato 
	 */
	private void tornaAlLogin (JFrame login ) {
		btnTornaAlLogin.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				login.setVisible(true);
				frame.dispose();
			}
		});
	}
	
	/**
	 * Metodo che restituisce la data odierna come stringa.
	 * @return dataString data odierna sottoforma di stringa.
	 */
	private String getDataOdierna () {
		LocalDate data = LocalDate.now();
		String dataString = data.toString();
	
		return dataString;
	}
	
	/**
	 * Metodo con il quale si assegna al bottone l'azione di tornare alla schermata base dell'impiegato.
	 * Rendiamo invisibili le componenti grafiche dell'operazione
	 *  e mostriamo i bottoni della schermata base.
	 */
	protected void tornaSchermataImpiegato() {
		btnTornaIndietro.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {
				
				btnMostraVendite.setVisible(true);
				btnEffettuaVendita.setVisible(true);
				
				
				btnTornaIndietro.setVisible(false);
				labelTitoloOperazione.setVisible(false);
				rendiInvisibiliComponentiSchermata();
				
			}

		}); 
	}
	
	/**
	 *  Metodo che rende invisibili le componenti aggiuntive delle schermate figlie 
	 *  di 'SchermataImpiegato'.
	 *  Il metodo è vuoto perché verrà riscritto nelle figlie con al suo interno le
	 *  componenti aggiuntive da dover rendere invisibili. 
	 */
    protected void rendiInvisibiliComponentiSchermata() {

	}
	
}

