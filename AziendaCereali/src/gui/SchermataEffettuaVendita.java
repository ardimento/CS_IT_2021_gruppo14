package gui;

import java.awt.Color;

import java.awt.Font;
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
import impiegato.Impiegato;

public class SchermataEffettuaVendita extends SchermataImpiegato{
	
	private JPanel panelVendita;
	private JButton btnTornaIndietro, btnConferma, btnCancella;
	private JLabel labelTitoloOperazione, labelCodiceVendita, labelCereale, labelQuantita, labelData;
	private JDateChooser calendario;
	private JComboBox tendinaCereali;
	private JTextField tfCodice, tfQuantita;
	private JSeparator separatore_1, separatore_2, separatore_3;
	private JTextFieldDateEditor editor;
	private GroupLayout glPanelVendita, glPanelCentroDX;

	public SchermataEffettuaVendita(Impiegato impiegato, JFrame login) {
		super(impiegato, login);
		effettuaVendite(impiegato);
	}
	
	private void setSchermataEffettuaVendita() {
		
		labelTitoloOperazione = new JLabel("Effettua Vendite :");
		labelTitoloOperazione.setFont(new Font ("Tahoma",Font.PLAIN,20));
		
		btnTornaIndietro = new JButton("Indietro");
		btnTornaIndietro.setFont(new Font ("Tahoma",Font.PLAIN,16));
		btnTornaIndietro.setForeground(Color.white);
		btnTornaIndietro.setBackground(new Color(244,164,96));
		
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
		
		btnConferma = new JButton("Aggiungi Vendita");
		btnConferma.setBackground(new Color(244,164,96));
		
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
	
	private void effettuaVendite(Impiegato impiegato) {
		btnEffettuaVendita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				btnMostraVendite.setVisible(false);
				btnEffettuaVendita.setVisible(false);
				
				setSchermataEffettuaVendita();
				
			}
		});
	}
	
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
										.addComponent(btnTornaIndietro, GroupLayout.PREFERRED_SIZE,62, Short.MAX_VALUE)
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
	
	public void setGlPanelVendita() {
		
		glPanelVendita.setHorizontalGroup(
			
			glPanelVendita.createParallelGroup(Alignment.LEADING)
			.addGroup(
				glPanelVendita.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.LEADING)
						.addGroup(
								glPanelVendita.createSequentialGroup()
								.addComponent(separatore_1, GroupLayout.DEFAULT_SIZE,457,Short.MAX_VALUE)
								.addContainerGap()
						)
						.addGroup(
								Alignment.TRAILING,glPanelVendita.createSequentialGroup()
								.addGroup(
										glPanelVendita.createParallelGroup(Alignment.LEADING)
										.addGroup(
												glPanelVendita.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnCancella,Alignment.LEADING,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)
												.addComponent(labelData,Alignment.LEADING,GroupLayout.DEFAULT_SIZE,138,Short.MAX_VALUE)
										)
										.addComponent(labelCereale,GroupLayout.PREFERRED_SIZE,139,GroupLayout.PREFERRED_SIZE)
										.addComponent(labelQuantita,GroupLayout.PREFERRED_SIZE,137,GroupLayout.PREFERRED_SIZE)
								)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										glPanelVendita.createParallelGroup(Alignment.TRAILING)
										.addGroup(
												glPanelVendita.createSequentialGroup()
												.addGap(0)
												.addComponent(btnConferma,GroupLayout.PREFERRED_SIZE,135,GroupLayout.PREFERRED_SIZE)
												.addGap(20)
										)
										.addGroup(
												glPanelVendita.createSequentialGroup()
												.addComponent(calendario,GroupLayout.DEFAULT_SIZE,315,Short.MAX_VALUE)
												.addContainerGap()
										)
										.addGroup(
												glPanelVendita.createSequentialGroup()
												.addComponent(tfQuantita,GroupLayout.DEFAULT_SIZE,316,Short.MAX_VALUE)
												.addContainerGap()
										)
										.addGroup(
												glPanelVendita.createSequentialGroup()
												.addComponent(tendinaCereali,0,314,Short.MAX_VALUE)
												.addContainerGap()
										)
								)
					)
					.addGroup(
							Alignment.TRAILING,glPanelVendita.createSequentialGroup()
							.addComponent(separatore_3,GroupLayout.DEFAULT_SIZE,456,Short.MAX_VALUE)
							.addGap(11)
					)
					.addGroup(
							Alignment.TRAILING,glPanelVendita.createSequentialGroup()
							.addComponent(separatore_2,GroupLayout.DEFAULT_SIZE,457,Short.MAX_VALUE)
							.addContainerGap()
					)
					.addGroup(
							glPanelVendita.createSequentialGroup()
							.addComponent(labelCodiceVendita, GroupLayout.PREFERRED_SIZE,139,GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tfCodice,GroupLayout.DEFAULT_SIZE,314,Short.MAX_VALUE)
							.addContainerGap()
					)
				)
			)
		);
		
		glPanelVendita.setVerticalGroup(
			
			glPanelVendita.createParallelGroup(Alignment.LEADING)
			.addGroup(
				glPanelVendita.createSequentialGroup()
				.addContainerGap()
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelCodiceVendita,GroupLayout.DEFAULT_SIZE,46,46)
						.addComponent(tfCodice,GroupLayout.PREFERRED_SIZE,46,46)
				)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(separatore_1,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.TRAILING)
						.addComponent(tendinaCereali,GroupLayout.DEFAULT_SIZE,46,46)
						.addComponent(labelCereale)
				)
				.addGap(8)
				.addComponent(separatore_3,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.TRAILING)
						.addComponent(labelQuantita,GroupLayout.PREFERRED_SIZE,46,46)
						.addComponent(tfQuantita,GroupLayout.PREFERRED_SIZE,46,46)
				)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(separatore_2,GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.LEADING)
						.addComponent(labelData,GroupLayout.PREFERRED_SIZE,46,46 )
						.addComponent(calendario, GroupLayout.PREFERRED_SIZE,46,46)
				)
				.addGap(26)
				.addGroup(
						glPanelVendita.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancella, GroupLayout.DEFAULT_SIZE,46,Short.MAX_VALUE)
						.addComponent(btnConferma,GroupLayout.DEFAULT_SIZE,46,Short.MAX_VALUE)	
				)
				.addContainerGap()
			)						
						
		);
		
	}
	
	public void gestisciInserimentoData() {
		editor = (JTextFieldDateEditor) calendario.getDateEditor();
		editor.setEditable(false);
		calendario.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent s) {
				
			}
		});
	}
	
	public void gestisciInserimentoQuantita() {
		tfQuantita.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE || ke.getKeyChar() == KeyEvent.VK_PERIOD) {
					tfQuantita.setEditable(true);
				}
				else {
					tfQuantita.setEditable(false);
				}
			}
		});
	}
	
	public void effettuaVendita(Impiegato impiegato) {
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent o) {
				try {
					controllaInserimentiVendita(impiegato);
				} catch(EccezioniVendita e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void controllaInserimentiVendita(Impiegato impiegato) throws EccezioniVendita {
		String cereale = tendinaCereali.getSelectedItem().toString();
		String quantita = tfQuantita.getText();
		
		DateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyy-MM-dd");
		String data = "";
		
		String codiceVendita = tfCodice.getText();
		Double quantitaVera = null;
		
		if(impiegato.cercaVendita(codiceVendita)) {
			
			JOptionPane.showMessageDialog(rootPane, MessaggiErroreVendita.ERRORE_CODICE_ESISTENTE);
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_CODICE_ESISTENTE, new EccezioniVendita());
			
		}
		else if(codiceVendita.equals("")) {
			
			JOptionPane.showMessageDialog(rootPane, MessaggiErroreVendita.ERRORE_CODICE_VUOTO);
			throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_CODICE_VUOTO, new EccezioniVendita());
			
		} else {
			
			try {
				
				quantitaVera = Double.parseDouble(quantita);
				data = new String ( dateformatYYYYMMDD.format(calendario.getDate()) );
				
			}catch(NumberFormatException e) {
				
				if(quantita.equals("")) {
					
					JOptionPane.showMessageDialog(rootPane, MessaggiErroreVendita.ERRORE_QUANTITA_VUOTA);
					throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_VUOTA, new EccezioniVendita());
					
				}
				else {
					
					JOptionPane.showMessageDialog(rootPane, MessaggiErroreVendita.ERRORE_QUANTITA_NON_VALIDA);
					throw new EccezioniVendita(MessaggiErroreVendita.ERRORE_QUANTITA_NON_VALIDA, new EccezioniVendita());
					
				}
	
			}
			
		}
	}
	
	
	
}
