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
	/**
	 * Gruppo per gestire il layout delle componenti grafiche relative alla visualizzazione delle vendite
	 */
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
		visualizzaVendite(impiegato);
		
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia di visualizzazione.
	 * Aggiunge le componenti grafiche di visualizzazione e il loro layout 
	 * nel pannello 'panelCenterRight' della superclasse ('SchermataImpiegato')
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
		setGlPanel_visualizza();
		super.panelCenterRight.setLayout(glPanel_visualizza);

		}
	/**
	 * Metodo che setta il layout di panelCenterRight per l'operazione di visualizzazione
	 */
	private  void setGlPanel_visualizza() {
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
	 * 
	 * Si assegna al bottone 'btnShowSell' l'azione per :
	 * Rendere invisibili i bottoni della schermata "base" (superclasse @see SchermataImpiegato) escluso il bottone di logout.
	 * Rendere visibili gli elementi grafici necessari per l'operazione di visualizzazione delle vendite.
	 * Riempire la tabella delle vendite.
	 * 
	 * @param impiegato istanza contenente le informazioni dell' impiegato
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
	
	/**
	 * Metodo che riempie la tabella con le vendite effettuate dall'impiegato.
	 * 
	 * 
	  * @param impiegato istanza contenente le informazioni dell' impiegato
	 */
	private void riempiTabella(Impiegato impiegato) {
		/**
		 * Array di nomi per le colonne della tabella
		 */
		String [] titoliColonne = new String [] {"Codice Vendita","Data Vendita","Cereale","Quantita'","Prezzo","Data Imbalaggio","Data Scadenza"};
		/**
		 * Matrice di oggetti 
		 * L'elenco di vendite viene rappresentato sottoforma di una matrice di oggetti di tipo diverso.
		 * (ogni componente della matrice avrà un proprio tipo, ovvero quello che gli è stato assegnato nella vendita).
		 */
		Object [] [] datiVendite = new Object [impiegato.getVendite().size()][titoliColonne.length];
		int i = 0;
		for(Iterator <VenditaInterfaccia> it = impiegato.getVendite().iterator(); it.hasNext();i++) {
			VenditaInterfaccia vendita = it.next();		
			/**
			 * Trasformo le informazioni della vendita in un array di oggetti
			 * per poterle riportare nella matrice.
			 */
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
