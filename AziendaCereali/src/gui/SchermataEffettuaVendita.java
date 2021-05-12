package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.*;

import cereale.NomeCereali;
import eccezioni.EccezioniVendita;
import eccezioni.MessaggiErroreVendita;
import gui.eccezioniGui.EccezioniGUI;
import gui.eccezioniGui.MessaggiGUI;
import impiegato.Impiegato;
/**
 * Classe che gestisce graficamente il layout e le funzionalità aggiuntive
 * specifiche della schermata per effettuare delle vendite.
 * 
 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataEffettuaVendita extends SchermataVisualizzaVendite {
	/**
	 * Pannello contenete gli elemeti grafici necessari per l'operazione. 
	 */
	private JPanel panelVendita;
	/**
	 *  Bottoni di interazione 
	 */
	private JButton btnConferma, btnCancella;
	/**
	 *  Label, aree di testo precompilate, usate come visualizzazione testuale all'interno della schermata grafica.
	 */
	private JLabel labelCodiceVendita, labelCereale, labelQuantita, labelData;
	/**
	 *  Elemento grafico della libreria jcalendar, utilizzato per l'inserimento della data da parte dell'impiegato.
	 */
	private JDateChooser calendario;
	/**
	 *  Elemento grafico a tendina, che consente la selezione del cereale da vendere.
	 */
	private JComboBox tendinaCereali;
	/**
	 *  Aree utilizzate per l'acquisizione di testo.
	 */
	private JTextField tfCodice, tfQuantita;
	/**
	 *  Linee di separazione per gli altri elementi grafici. 
	 */
	private JSeparator separatore_1, separatore_2, separatore_3;
	/**
	 *  Area testuale della libreria jcalendar, utilizzato per la visualizzazione della data inserita tramite il calendario di tipo JDateChooser.
	 */
	private JTextFieldDateEditor editor;
	/**
	 *  Gruppi per gestire il layout delle componenti grafiche relative a tale classe.
	 */
	private GroupLayout glPanelVendita, glPanelCentroDX;

	/**
	 * Costruttore di SchermataEffettuaVendita, 
	 * partendo dal layout e le funzionalità base della schermata 'SchermataImpiegato'
	 * aggiunge e rende visibili gli elementi necessari all'operazione di inserimento di una vendita.
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * @param login frame della schermata Login, chiamata nel caso di un eventuale logout.
	 * 
	 * @see gui.SchermataImpiegato
	 */
	public SchermataEffettuaVendita(Impiegato impiegato, JFrame login) {
		super(impiegato, login);
		effettuaVendite(impiegato);
		
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia di inserimento vendite.
	 * Aggiunge le componenti grafiche necessarie all'operazione e il loro layout 
	 * nel pannello 'panelOperazioni' della superclasse ('SchermataImpiegato')
	 * 
	 * @see SchermataImpiegato#panelOperazioni
	 * @see gui.SchermataImpiegato
	 */
	private void setSchermataEffettuaVendita() {
		
		labelTitoloOperazione.setVisible(true);
		labelTitoloOperazione.setText("Effettua Vendite :");
		
		btnTornaIndietro.setVisible(true);
		
		panelVendita = new JPanel();
		
		labelCereale = new JLabel("Cereale :");
		labelCereale.setFont(new Font ("Tahoma",Font.PLAIN,16));
		
		tendinaCereali = new JComboBox(NomeCereali.nomiCereali);
		tendinaCereali.setBackground(Color.white);
		
		labelQuantita = new JLabel("Quantita' :");
		labelQuantita.setFont(new Font ("Tahoma",Font.PLAIN,16));
		
		tfQuantita = new JTextField();
		tfQuantita.setColumns(10);
		
		labelData = new JLabel("Data Vendita :");
		labelData.setFont(new Font ("Tahoma",Font.PLAIN,16));
		
		btnCancella = new JButton("Cancella");
		btnCancella.setBackground(new Color(211,211,211));
		btnCancella.setFocusPainted(false);
		
		btnConferma = new JButton("Aggiungi Vendita");
		btnConferma.setBackground(new Color(244,164,96));
		btnConferma.setForeground(Color.white);
		btnConferma.setFocusPainted(false);
		
		calendario = new JDateChooser();
		
		separatore_1 = new JSeparator();
		separatore_2 = new JSeparator();
		separatore_3 = new JSeparator();
		
		tfCodice = new JTextField();
		tfCodice.setColumns(10);
		
		labelCodiceVendita = new JLabel("Codice Vendita :");
		labelCodiceVendita.setFont(new Font ("Tahoma",Font.PLAIN,16));
		
		glPanelVendita = new GroupLayout(panelVendita);		
		setGlPanelVendita();
		panelVendita.setLayout(glPanelVendita);
		
		glPanelCentroDX = new GroupLayout(super.panelOperazioni);
		setGlPanelCentroDestra();
		super.panelOperazioni.setLayout(glPanelCentroDX);
		
	}
	/**
	 * Metodo che effetua l'operazione per inserire una vendita.
	 * 
	 * Si assegna al bottone 'btnEffettuaVendita' l'azione per :
	 * Rendere invisibili i bottoni della schermata "base" (superclasse 'SchermataImpiegato') escluso il bottone di logout.
	 * Rendere visibili gli elementi grafici necessari per l'operazione di inserimento di una vendita.
	 * Riempire la tabella delle vendite.
	 * 
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * 
	 * @see gui.SchermataImpiegato#btnEffettuaVendita
	 * @see gui.SchermataImpiegato
	 */
	private void effettuaVendite(Impiegato impiegato) {
		btnEffettuaVendita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				btnMostraVendite.setVisible(false);
				btnEffettuaVendita.setVisible(false);
				
				labelTitoloOperazione.setVisible(true);
				btnTornaIndietro.setVisible(true);
				
				setSchermataEffettuaVendita();
				azioneCancella();
				gestisciInserimentoQuantita();
				gestisciInserimentoData();
				effettuaVendita(impiegato);
				ModificaDimensioniDinamicamenteVendita();
				tornaSchermataImpiegato();
			}
		});
	}
	
	/**
	 * Metodo che modifica a runtime il font delle label 
	 * 
	 */
	private void ModificaDimensioniDinamicamenteVendita () {
		
		panelVendita.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int valoreCalcolato =0;
				Font font1 = null,font2 = null;
				if( (panelVendita.getHeight() > 500.00 )||(panelVendita.getWidth() >700.00) ) {
					valoreCalcolato = panelVendita.getWidth()/35;
					font1 = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
					valoreCalcolato = panelVendita.getWidth()/40;
					font2= new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				} else {
					valoreCalcolato = panelVendita.getWidth()/25;
					font1 = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
					valoreCalcolato = panelVendita.getWidth()/35;
					font2= new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				}

					
				labelTitoloOperazione.setFont(font1);
				btnCancella.setFont(font1);
				btnConferma.setFont(font1);
				btnTornaIndietro.setFont(font1);
				
				labelCodiceVendita.setFont(font2);
				labelCereale.setFont(font2);
				labelQuantita.setFont(font2);
				labelData.setFont(font2);
				tendinaCereali.setFont(font2);
				tfCodice.setFont(font2);
				tfQuantita.setFont(font2);
				calendario.setFont(font2);
				
			}
		});
	}
	/**
	 * Metodo con il quale si assegna al bottone l'azione di cancellare i campi 
	 * utilizzati per l'acquisizione dei dati relativi all'inseriemto di una vendita. 
	 */
	private void azioneCancella () {
		btnCancella.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cancella();	
			}

		});
	}	
	/**
	 * Metodo che cancella i dati nei campi  di acquisizione. 
	 */
	private void cancella () {
		tfCodice.setText("");
		tfQuantita.setText("");
		calendario.setDate(null);
		tfQuantita.setBackground(Color.white);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void rendiInvisibiliComponentiSchermata() {
		if(panelVendita!=null)
			panelVendita.setVisible(false);
		if(scrollPaneTabella!=null)
			scrollPaneTabella.setVisible(false);
		panelOperazioni.setLayout(glPanelOperazioni);
	}
	
	/**
	 * Metodo che setta il layout di 'panelOperazioni' per l'operazione di inserimento di una vendita.
	 * 
	 * @see gui.SchermataImpiegato#panelOperazioni
	 */
	private void setGlPanelCentroDestra() {
		
		glPanelCentroDX.setHorizontalGroup(
				glPanelCentroDX.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanelCentroDX.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								glPanelCentroDX.createParallelGroup(Alignment.LEADING)
								.addGroup(
										glPanelCentroDX.createSequentialGroup()
										.addComponent(panelVendita, GroupLayout.DEFAULT_SIZE, 510,Short.MAX_VALUE)
										.addContainerGap()
								)
								.addGroup(
										Alignment.TRAILING,glPanelCentroDX.createSequentialGroup()
										.addComponent(btnTornaIndietro, GroupLayout.DEFAULT_SIZE,62, Short.MAX_VALUE)
										.addGap(88)
										.addComponent(labelTitoloOperazione, GroupLayout.DEFAULT_SIZE,181,Short.MAX_VALUE)
										.addGap(171)
								)
						)
				)
		);
		
		glPanelCentroDX.setVerticalGroup(
				glPanelCentroDX.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanelCentroDX.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								glPanelCentroDX.createParallelGroup(Alignment.LEADING)
								.addComponent(labelTitoloOperazione)
								.addComponent(btnTornaIndietro)
						)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelVendita, GroupLayout.PREFERRED_SIZE,341,Short.MAX_VALUE)
						.addGap(32)
				)
		);
		
	}
	/**
	 * Metodo che setta il layout di 'panelVendita' per l'operazione di inserimento di una vendita.
	 * @see SchermataEffettuaVendita#panelVendita
	 */
	public void setGlPanelVendita() {
		
		glPanelVendita.setHorizontalGroup(
			
			glPanelVendita.createSequentialGroup()
			.addContainerGap()
			.addGroup(
				glPanelVendita.createParallelGroup(Alignment.LEADING)
				.addGroup(
					Alignment.TRAILING,glPanelVendita.createSequentialGroup()
					.addGroup(
						glPanelVendita.createParallelGroup(Alignment.LEADING)
						.addGroup(
							glPanelVendita.createParallelGroup(Alignment.TRAILING)
							.addComponent(labelCodiceVendita, GroupLayout.PREFERRED_SIZE,139,Short.MAX_VALUE)
							.addComponent(labelCereale,GroupLayout.PREFERRED_SIZE,138,Short.MAX_VALUE)
							.addComponent(labelQuantita,GroupLayout.PREFERRED_SIZE,138,Short.MAX_VALUE)
							.addComponent(labelData,Alignment.LEADING,GroupLayout.DEFAULT_SIZE,138,Short.MAX_VALUE)
							.addComponent(btnCancella,Alignment.LEADING,GroupLayout.DEFAULT_SIZE,138,Short.MAX_VALUE)
						)			
					)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(
						glPanelVendita.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(tfCodice,GroupLayout.DEFAULT_SIZE,314,Short.MAX_VALUE)
								.addContainerGap()
						)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(tendinaCereali,0,314,Short.MAX_VALUE)
								.addContainerGap()
						)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(tfQuantita,GroupLayout.DEFAULT_SIZE,314,Short.MAX_VALUE)
								.addContainerGap()
						)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(calendario,GroupLayout.DEFAULT_SIZE,314,Short.MAX_VALUE)
								.addContainerGap()
						)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(btnConferma,GroupLayout.PREFERRED_SIZE,314,Short.MAX_VALUE)
								.addContainerGap()
						)
					)
				)
				.addGroup(
						glPanelVendita.createSequentialGroup()
						.addComponent(separatore_1, GroupLayout.DEFAULT_SIZE,457,Short.MAX_VALUE)
						.addContainerGap()
				)
				.addGroup(
						glPanelVendita.createSequentialGroup()
						.addComponent(separatore_2,GroupLayout.DEFAULT_SIZE,457,Short.MAX_VALUE)
						.addContainerGap()
				)
				.addGroup(
						glPanelVendita.createSequentialGroup()
						.addComponent(separatore_3,GroupLayout.DEFAULT_SIZE,456,Short.MAX_VALUE)
						.addContainerGap()
				)
			)
			
		);
		
		glPanelVendita.setVerticalGroup(
			
			glPanelVendita.createSequentialGroup()
			.addContainerGap()
			.addGroup(
					glPanelVendita.createParallelGroup(Alignment.TRAILING)
					.addComponent(labelCodiceVendita,GroupLayout.DEFAULT_SIZE,46,90)
					.addComponent(tfCodice,GroupLayout.DEFAULT_SIZE,46,90)
			)
			
			.addGap(5)
			.addComponent(separatore_1,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
			.addGap(5)
			
			.addGroup(
					glPanelVendita.createParallelGroup(Alignment.TRAILING)
					.addComponent(labelCereale,GroupLayout.DEFAULT_SIZE,46,90)
					.addComponent(tendinaCereali,GroupLayout.DEFAULT_SIZE,46,90)
			)
				
			.addGap(5)
			.addComponent(separatore_3,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
			.addGap(5)
				
			.addGroup(
					glPanelVendita.createParallelGroup(Alignment.TRAILING)
					.addComponent(labelQuantita,GroupLayout.DEFAULT_SIZE,46,90)
					.addComponent(tfQuantita,GroupLayout.DEFAULT_SIZE,46,90)
			)
				
			.addGap(5)
			.addComponent(separatore_2,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
			.addGap(5)
				
			.addGroup(
					glPanelVendita.createParallelGroup(Alignment.TRAILING)
					.addComponent(labelData,GroupLayout.DEFAULT_SIZE,46,90)
					.addComponent(calendario, GroupLayout.DEFAULT_SIZE,46,90)
			)
				
			.addGap(10, 20, 100)
			.addGroup(
					glPanelVendita.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnCancella, GroupLayout.DEFAULT_SIZE,46,90)
					.addComponent(btnConferma,GroupLayout.DEFAULT_SIZE,46,90)	
			)
			.addGap(5)
	
		);
		
	}
	
	/**
	 * Metodo  che gestisce l'acquisizione della data di vendita.
	 * La data selezionata dall'impiegato nel calendario viene visualizzata 
	 * testualmente nella JTextFieldDateEditor editor
	 * 
	 */
	public void gestisciInserimentoData() {
		editor = (JTextFieldDateEditor) calendario.getDateEditor();
		editor.setEditable(false);
		calendario.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent s) {
				
			}
		});
	}
	/**
	 * Metodo  che gestisce l'acquisizione della quantità 
	 * Consente la scrittura all'interno della JTextField 
	 * solo se l'impiegato inserisce da tastiera un numero o un punto.
	 */
	public void gestisciInserimentoQuantita() {
		tfQuantita.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_PERIOD) {
					tfQuantita.setEditable(true);
					
				}
				else {
					tfQuantita.setEditable(false);
					tfQuantita.setBackground(Color.red);
				
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				tfQuantita.setBackground(Color.white);
			}
			
			
		});
	}
	
	/**
	 * Metodo con il quale si assegna al bottone l'azione di inserimento della vendita  
	 * Cattura l'eccezioni previste in caso di errori sui campi di acquisizione o sull'operazione di inserimento.
	 * 
	 * @param impiegato impiegato che intende effettuare la venidta
	 */
	public void effettuaVendita(Impiegato impiegato) {
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				try {
					controllaInserimentiVendita(impiegato);
				} catch(EccezioniGUI e) {
					e.printStackTrace();
				} catch (EccezioniVendita e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Metodo che controlla i campi di acquisizione e i vincoli necessari per effettuare la vendita.
	 * Lancia dei messaggi popup per notificare l'impiegato di :
	 * - eventuali errori nella compilazione dei campi.
	 * - esito dell'inserimento della vendita (in caso di esito negativo viene spiegato il fattore scaturante)
	 * 
	 * @param impiegato impiegato che intende effettuare la venidta
	 * 
	 * @throws EccezioniVendita eccezione riguardante aspetti relativi alla vendita
	 * @throws EccezioniGUI 	eccezione riguardante aspetti relativi alla mancata correttezza dei parametri necessari per la vendita
	 * 
	 */
	public void controllaInserimentiVendita(Impiegato impiegato) throws EccezioniVendita, EccezioniGUI {
		String cereale = tendinaCereali.getSelectedItem().toString();
		String quantita = tfQuantita.getText();
		
		DateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyy-MM-dd");
		String data = "";
		
		String codiceVendita = tfCodice.getText();
		Double quantitaVera = null;
		
		if(impiegato.cercaVendita(codiceVendita)) {
			
			JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_NON_EFFETTUATA + MessaggiErroreVendita.ERRORE_CODICE_ESISTENTE);
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_CODICE_ESISTENTE, new EccezioniVendita());
			
		}
		else if(codiceVendita.equals("")) {
			
			JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_NON_EFFETTUATA + MessaggiGUI.ERRORE_CODICE_VUOTO);
			throw new EccezioniGUI(MessaggiGUI.ERRORE_CODICE_VUOTO, new EccezioniGUI());
			
		} else {
			
			try {
				
				quantitaVera = Double.parseDouble(quantita);
				data = new String ( dateformatYYYYMMDD.format(calendario.getDate()) );
				
			}catch(NumberFormatException e) {
				
				if(quantita.equals("")) {
					
					JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_NON_EFFETTUATA + MessaggiGUI.ERRORE_QUANTITA_VUOTA);
					throw new EccezioniGUI(MessaggiGUI.ERRORE_QUANTITA_VUOTA, new EccezioniGUI());
					
				}
				else {
					
					JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_NON_EFFETTUATA + MessaggiGUI.ERRORE_QUANTITA_NON_VALIDA);
					throw new EccezioniGUI(MessaggiGUI.ERRORE_QUANTITA_NON_VALIDA, new EccezioniGUI());
					
				}
	
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_NON_EFFETTUATA + MessaggiGUI.ERRORE_DATA_VUOTA);
				throw new EccezioniGUI(MessaggiGUI.ERRORE_DATA_VUOTA, new EccezioniGUI());
			}
			try {
				impiegato.creaVendita(quantitaVera, codiceVendita, cereale, data);
				JOptionPane.showMessageDialog(rootPane, MessaggiGUI.VENDITA_EFFETTUATA);
				cancella ();
				//impiegato
				//inserimentoVendita(codiceVendita, impiegato.getCodiceImpiegato(), cereale, data, dataImballaggio , dataScadenza, quantitaVera);
			} 
			catch (EccezioniVendita e){
				JOptionPane.showMessageDialog(rootPane, e.getMessage());
			}
			
		}
	}
	
	
	
}
