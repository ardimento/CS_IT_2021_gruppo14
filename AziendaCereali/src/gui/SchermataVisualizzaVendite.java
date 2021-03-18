package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import impiegato.Impiegato;
import vendita.VenditaInterfaccia;

/**
 * Classe che gestisce graficamente il layout e le funzionalità aggiuntive
 * specifiche della schermata di visualizzazione delle venidte.

 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataVisualizzaVendite extends SchermataImpiegato {
	/**
	 * Tabella per la visualizzazione delle vendite
	 */
	private JTable tableSells;
	/**
	 * Pannello che consente lo scroll del suo contenuto 
	 */
	private JScrollPane tableScrollPane;
	/**
	 * Label che rappresenta il nome dell'operazione che si sta effettuando
	 */
	private JLabel labelTitle;
	/**
	 * Bottone per ritornare alla schermata "base" dell'impiegato.(SchermataImpiegato) 
	 */
	private JButton buttonBack;
	
	private GroupLayout glPanel_visualizza;
	/**
	 * Costruttore di SchermataVisuallizaVendite, 
	 * partendo dal layout e le funzionalità base della schermata @see SchermataImpiegato
	 * aggiunge e rende vidsibili gli elementi necessari all'operazione di visualizzazione delle vendite.
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * @param frame frame della schermata Login, chiamata nel caso di un eventuale logout.
	 */
	public SchermataVisualizzaVendite(Impiegato impiegato, JFrame frame) {
		super(impiegato, frame);
		//setSchermataVisualizzaVendite();
		visualizzaVendite(impiegato);
		
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia.
	 */
	private void setSchermataVisualizzaVendite() {
		
		labelTitle = new JLabel("Visualizza vendite :");
		labelTitle.setFont(new Font ("Tahoma",Font.PLAIN,20));
		
		tableSells = new JTable();
		
		tableSells.setBounds(0,0,2000,200);
		tableSells.setMaximumSize(new Dimension(2000,200));
		
		tableScrollPane = new JScrollPane(tableSells);
		tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		buttonBack = new JButton("Indietro");
		buttonBack.setFont(new Font ("Tahoma",Font.PLAIN,16));
		buttonBack.setForeground(Color.white);
		buttonBack.setBackground(new Color(244,164,96));
		
		
		glPanel_visualizza = new GroupLayout(super.panelCenterRight);
		setGlPanelCenterRight();
		super.panelCenterRight.setLayout(glPanel_visualizza);

		
		}

	private  void setGlPanelCenterRight() {
		glPanel_visualizza.setHorizontalGroup(
				glPanel_visualizza.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanel_visualizza.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								glPanel_visualizza.createParallelGroup(Alignment.LEADING)
								.addGroup(
										glPanel_visualizza.createSequentialGroup()
										.addComponent(tableScrollPane,GroupLayout.DEFAULT_SIZE,510,Short.MAX_VALUE)
										.addContainerGap()
								)
								.addGroup(
										Alignment.TRAILING,glPanel_visualizza.createSequentialGroup()
										.addComponent(buttonBack,GroupLayout.PREFERRED_SIZE,62,Short.MAX_VALUE)
										.addGap(88)
										.addComponent(labelTitle,GroupLayout.DEFAULT_SIZE,181,Short.MAX_VALUE)
										.addGap(171)
										
								)
						)
						
				)
				
		);
		
		glPanel_visualizza.setVerticalGroup(
				glPanel_visualizza.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanel_visualizza.createSequentialGroup()
						.addContainerGap()	
				
						.addGroup(
								glPanel_visualizza.createParallelGroup(Alignment.LEADING)
								.addComponent(labelTitle)
								.addComponent(buttonBack)
						)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(tableScrollPane,GroupLayout.PREFERRED_SIZE,341,Short.MAX_VALUE)
						.addGap(32)
				)
		);
	}

	
	/**
	 * Metodo che effetua l'operazione di visualizzazione delle vendite.
	 * Si assegna al bottone 'btnShowSell' l'azione per visualizzare la schermata della vendita.
	 * Rendiamo invisibili i bottoni azione della schermata "base" (escluso il bottone di logout)
	 * Rendiamo visibili gli elementi grafici necessari per l'operazione di visualizzazione delle vendite.
	 * Riempiamo la tabella delle vendite.
	 */
	private void visualizzaVendite(Impiegato impiegato) {
		btnShowSell.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {		
				btnShowSell.setVisible(false);
				btnMakeSell.setVisible(false);
				
				setSchermataVisualizzaVendite();
				
				riempiTabella(impiegato);
				tornaSchermataImpiegato();
			}
		
		}); 
	}
	
	private void riempiTabella(Impiegato impiegato) {
		
		String [] titoliColonne = new String [] {"Codice Vendita","Data Vendita","Cereale","Quantita'","Prezzo","Data Imbalaggio","Data Scadenza"};
		Object [] [] datiVendite = new Object [impiegato.getVendite().size()][titoliColonne.length];
		int i = 0;
		for(Iterator <VenditaInterfaccia> it = impiegato.getVendite().iterator(); it.hasNext();i++) {
			VenditaInterfaccia vendita = it.next();
			Object array [] = vendita.arrayDiOggetti(); 
			for (int j = 0; j< array.length; j++) {
				datiVendite [i] [j] = array [j];
			}
		}
		
		tableSells.setModel(new DefaultTableModel(datiVendite,titoliColonne) {
			Class [] columnTypes = new Class [] {String.class,String.class,String.class,Double.class,Double.class,String.class,String.class};
			public Class getColumnClass(int columnIndex) {return columnTypes[columnIndex];};
		});
	}
	
	private void tornaSchermataImpiegato() {
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {
				tableScrollPane.setVisible(false);
				buttonBack.setVisible(false);
				labelTitle.setVisible(false);
				
				btnShowSell.setVisible(true);
				btnMakeSell.setVisible(true);
				
			}
		}); 
	}
	
}
