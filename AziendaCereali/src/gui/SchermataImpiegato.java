package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	private JPanel panelTop, panelDown, panelRight,panelLeft,panelCenterLeft;
	protected JPanel panelCenterRight;
	/**
	 * Label, aree di testo precompilate, usate come visualizzazione testuale all'interno della schermata grafica.
	 */
	private JLabel labelTitleSoftware, labelToday, labelRights,labelIconUser,labelDatiImpiegato, labelCode, labelQuanGiornaliera, labelQuanAnnua;
	/**
	 * Bottoni di interazione, con i quali si accede ad aree diverse del programma(schermata visualizza, crea vendita e ritorno al Login)
	 */
	protected JButton btnGoBack, btnShowSell, btnMakeSell;
	/**
	 * Gruppi per gestire i layout delle componenti grafiche disposte nei vari pannelli(label, textbox ecc..)
	 */
	private GroupLayout glPanelDown,glPanelLeft,glPanelCenterRight,glPanelCenterLeft;
	
	/**
	 * Costruttore di schermata impiegato, crea nuovo frame, ed imposta le nuove componenti.
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * @param login frame della schermata Login, chiamata nel caso di un eventuale logout.
	 */
	public SchermataImpiegato(Impiegato impiegato, JFrame login) {
		this.frame = new JFrame();
		this.impiegato = impiegato;
		setSchermataImpiegato();
		azioneBottoneIndietro(login);
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
		frame.setMinimumSize(new Dimension(800,600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(SystemColor.window);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource(immagini.PathImmagini.IMMAGINE_ICONA_PROGRAMMA)));
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//---------------------------------------------------------------
		panelTop = new JPanel();
		panelTop.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelTop, BorderLayout.NORTH);
		labelTitleSoftware = new JLabel("Software Azienda Cereale");
		panelTop.add(labelTitleSoftware);
		
		//---------------------------------------------------------------
		panelDown = new JPanel();
		panelDown.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelDown, BorderLayout.SOUTH);
		
		labelToday = new JLabel("Data :"+ searchDate());
		
		btnGoBack = new JButton("Logout");	
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16) );
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setBackground(new Color(244,164,96));
		btnGoBack.setBounds(-100,0,30,30);
		
		labelRights = new JLabel ("    By Alemanno, Ben Sidi, Fortunato");
		
		glPanelDown = new GroupLayout (panelDown);
		setGlPanelDown();
		panelDown.setLayout(glPanelDown);
		
		//---------------------------------------------------------------
		panelRight = new JPanel();
		panelRight.setBackground(SystemColor.controlShadow);
		frame.getContentPane().add(panelRight, BorderLayout.EAST);
		
		//---------------------------------------------------------------	
		panelLeft = new JPanel();
		panelLeft.setBackground(SystemColor.control);
		frame.getContentPane().add(panelLeft, BorderLayout.CENTER);
		
		//---------------------------------------------------------------
		panelCenterLeft = new JPanel();
		panelCenterLeft.setBackground(SystemColor.scrollbar);
		
		
		labelIconUser = new JLabel();
		labelIconUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelDatiImpiegato = new JLabel("DATI IMPIEGATO :");
		labelDatiImpiegato.setHorizontalAlignment(SwingConstants.CENTER);
		labelCode = new JLabel();
		labelQuanGiornaliera = new JLabel();
		labelQuanAnnua = new JLabel();

		
		glPanelCenterLeft = new GroupLayout (panelCenterLeft);
		setGlPanelCenterLeft();
		panelCenterLeft.setLayout(glPanelCenterLeft);
		
		//---------------------------------------------------------------
		panelCenterRight = new JPanel();
		panelCenterRight.setBackground(new Color(245,222,179));

		glPanelLeft = new GroupLayout (panelLeft);
		setGlPanelLeft();
		btnMakeSell = new JButton("Effettua vendite");
		btnMakeSell.setBackground(SystemColor.controlHighlight);
		
		btnShowSell = new JButton("Visualizza vendite effettuate");
		btnShowSell.setBackground(SystemColor.controlHighlight);
		
		glPanelCenterRight = new GroupLayout(panelCenterRight);
		setGlPanelCenterRight();
		panelCenterRight.setLayout(glPanelCenterRight);

		//---------------------------------------------------------------
		panelLeft.setLayout(glPanelLeft);
		

	}
	
	/**
	 * Metodo che modifica a runtime il font delle label e la dimensione delle icone / immagini 
	 * 
	 */
	private void ModificaDimensioniDinamicamente () {
		
		panelLeft.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int valoreCalcolato = frame.getWidth()/40;
				Font nuovoFont = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				btnMakeSell.setFont(nuovoFont);
				btnShowSell.setFont(nuovoFont);
				
				valoreCalcolato = labelCode.getWidth()/12;
				nuovoFont = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				labelCode.setFont(nuovoFont);
				labelDatiImpiegato.setFont(nuovoFont);
				labelQuanGiornaliera.setFont(nuovoFont);
				labelQuanAnnua.setFont(nuovoFont);
					
				
				valoreCalcolato = labelIconUser.getWidth()/2;
				Image immagine = setImage(PathImmagini.IMMAGINE_ICONA_UTENTE,valoreCalcolato, valoreCalcolato);
				if(immagine!=null)
					labelIconUser.setIcon(new ImageIcon(immagine));
				
				valoreCalcolato = btnMakeSell.getHeight()/2;;
				immagine = setImage(PathImmagini.IMMAGINE_ICONA_VENDI, valoreCalcolato, valoreCalcolato);
				if(immagine!=null)
					btnMakeSell.setIcon(new ImageIcon(immagine));
				
				immagine = setImage(PathImmagini.IMMAGINE_ICONA_VISUALIZZA_VENDITE, valoreCalcolato, valoreCalcolato);
				if(immagine!=null)
					btnShowSell.setIcon(new ImageIcon(immagine));
			}
		});
	}
	
	
	/**
	 * Metodo che setta le informazioni mostrate a video dell'impiegato. 
	 */
	private void setInfoImpiegato() {
		labelCode.setText("Codice Impiegato : "+impiegato.getCodiceImpiegato());
		labelQuanGiornaliera.setText("Quantita' Giornaliera: "+impiegato.quantitaVendutaGiornaliera(searchDate()));
		labelQuanAnnua.setText("Quantita' Annua: " + impiegato.quantitaVendutaAnnua(searchDate()));
	}
	
	/**
	 * Metodo che setta il layout di panelLeft
	 */
	private void setGlPanelLeft() {
		glPanelLeft.setHorizontalGroup(
				glPanelLeft.createParallelGroup(Alignment.CENTER)
				.addGroup( 
					glPanelLeft.createSequentialGroup()
					.addGap(0)
					.addComponent(panelCenterLeft,GroupLayout.DEFAULT_SIZE,177,400)
					.addGap(35)
					.addComponent(panelCenterRight,GroupLayout.DEFAULT_SIZE,530,1300)
					.addGap(35)
				)
		);

		glPanelLeft.setVerticalGroup(
				glPanelLeft.createParallelGroup(Alignment.CENTER)
				.addGroup( 
					glPanelLeft.createSequentialGroup()
					.addComponent(panelCenterLeft,GroupLayout.PREFERRED_SIZE,400,Short.MAX_VALUE)
					.addGap(0)
				)
				.addGroup( 
					glPanelLeft.createSequentialGroup()
					.addGap(35)	
					.addComponent(panelCenterRight,GroupLayout.PREFERRED_SIZE,400,700)
					.addGap(35)
				)
		);
	}
	/**
	 * Metodo che setta il layout di panelCenterRight
	 */
	private void setGlPanelCenterRight() {
		glPanelCenterRight.setHorizontalGroup( 
				glPanelCenterRight.createParallelGroup(Alignment.CENTER)
				.addGroup(
						Alignment.CENTER, glPanelCenterRight.createSequentialGroup() 
						.addGap(50)
						.addGroup(
							glPanelCenterRight.createParallelGroup(Alignment.CENTER)
							.addComponent(btnMakeSell, Alignment.CENTER,GroupLayout.DEFAULT_SIZE,100,Short.MAX_VALUE)
							.addComponent(btnShowSell, Alignment.CENTER,GroupLayout.DEFAULT_SIZE,100,Short.MAX_VALUE)
						)
						.addGap(50)
				)
		);
		
		glPanelCenterRight.setVerticalGroup( 
				glPanelCenterRight.createParallelGroup(Alignment.CENTER)
				.addGroup(
						Alignment.CENTER, glPanelCenterRight.createSequentialGroup() 
						.addGap(50)
						.addComponent(btnShowSell, GroupLayout.PREFERRED_SIZE,100,Short.MAX_VALUE)
						.addGap(22)
						.addComponent(btnMakeSell, GroupLayout.PREFERRED_SIZE,100,Short.MAX_VALUE )
						.addGap(50)
				)
		);
	}
	/**
	 * Metodo che setta il layout di panelCenterLeft
	 */
	private void setGlPanelCenterLeft() {
		
		glPanelCenterLeft.setHorizontalGroup( 
				glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
				.addGroup(
							glPanelCenterLeft.createSequentialGroup() 
							.addContainerGap()
							.addGroup(
								glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
								.addComponent(labelIconUser, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelDatiImpiegato, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelCode, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelQuanGiornaliera, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
								.addComponent(labelQuanAnnua, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,200,Short.MAX_VALUE)
							)
							.addContainerGap()
				)
		);	
		
		glPanelCenterLeft.setVerticalGroup(
				glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
				.addGroup(
						glPanelCenterLeft.createSequentialGroup()
						.addGap(25)
						.addComponent(labelIconUser,GroupLayout.DEFAULT_SIZE,200,Short.MAX_VALUE)
						.addComponent(labelDatiImpiegato,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
						.addGap(20)
						.addComponent(labelCode,GroupLayout.DEFAULT_SIZE,30,Short.MAX_VALUE)
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
	private void setGlPanelDown() {
		glPanelDown.setHorizontalGroup( 
			glPanelDown.createParallelGroup(Alignment.LEADING)
			.addGroup(
						Alignment.TRAILING,glPanelDown.createSequentialGroup() 
						.addGap(37)
						.addComponent(btnGoBack)
						.addComponent(labelRights)
						.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE,0,GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED,522,Short.MAX_VALUE)
						.addComponent(labelToday, GroupLayout.PREFERRED_SIZE,120,GroupLayout.PREFERRED_SIZE)
						.addGap(41)
			)
		);
		
		glPanelDown.setVerticalGroup( 
			glPanelDown.createParallelGroup(Alignment.LEADING)
			.addGroup(
						glPanelDown.createSequentialGroup()
						.addContainerGap()
						.addGroup(glPanelDown.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGoBack)
						.addComponent(labelToday, GroupLayout.PREFERRED_SIZE,30,GroupLayout.PREFERRED_SIZE)
						.addComponent(labelRights, GroupLayout.PREFERRED_SIZE,28,GroupLayout.PREFERRED_SIZE))
						.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE,0,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE))					
			
		);		
	}
	
	
	/**
	 * Metodo che aggiunge le immagini alla schermata 
	 */
	private void setImmagini() {	
		Image immagine = null;	
		int valore;
		
		valore = btnGoBack.getWidth();
		immagine = setImage(PathImmagini.IMMAGINE_BOTTONE_INDIETRO, valore, valore);
		if(immagine!=null)
			btnGoBack.setIcon(new ImageIcon(immagine));
		
		valore = 100;
		immagine = setImage(PathImmagini.IMMAGINE_ICONA_UTENTE, valore, valore);
		if(immagine!=null)
			labelIconUser.setIcon(new ImageIcon(immagine));
		
		valore = 70;
		immagine = setImage(PathImmagini.IMMAGINE_ICONA_VENDI, valore, valore);
		if(immagine!=null)
			btnMakeSell.setIcon(new ImageIcon(immagine));
		
		valore = 70;
		immagine = setImage(PathImmagini.IMMAGINE_ICONA_VISUALIZZA_VENDITE, valore, valore);
		if(immagine!=null)
			btnShowSell.setIcon(new ImageIcon(immagine));
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
	 */
	private Image setImage(String Path, int dimensione1, int dimensione2) {
		Image immagineModificata = null;
		try {
			Image immagine = ImageIO.read(getClass().getResource(Path));
			immagineModificata = immagine.getScaledInstance(dimensione1, dimensione2, Image.SCALE_SMOOTH);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Non è stato possibile caricare l'immagine "+ Path, "Errore", JOptionPane.ERROR_MESSAGE);
		}
		return immagineModificata;
		
	}
	
	
	/**
	 * Metodo con il quale si assegna al bottone l'azione di effettuare il logout.
	 * Rendiamo visibile il frame Login e distruggiamo dalla memoria il frame Schermata Impiegato dell'utente loggato.
	 * @param login frame che viene richiamato 
	 */
	private void azioneBottoneIndietro (JFrame login ) {
		btnGoBack.addActionListener(new ActionListener() {
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
	private String searchDate () {
		LocalDate data = LocalDate.now();
		String dataString = data.toString();
	
		return dataString;
	}
	
}

