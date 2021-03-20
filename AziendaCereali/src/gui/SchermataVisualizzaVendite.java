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
 * specifiche della schermata di visualizzazione delle vendite.

 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataVisualizzaVendite extends SchermataImpiegato {
	/**
	 * Tabella per la visualizzazione delle vendite
	 */
	private JTable tabellaVendite;
	/**
	 * Pannello che consente lo scroll del suo contenuto 
	 */
	private JScrollPane scrollPaneTabella;
	/**
	 * Label che rappresenta il nome dell'operazione che si sta effettuando
	 */
	private JLabel labelTitoloOperazione;
	/**
	 * Bottone per ritornare alla schermata "base" dell'impiegato.(SchermataImpiegato) 
	 */
	private JButton btnTornaIndietro;
	/**
	 * Gruppo per gestire il layout delle componenti grafiche relative alla visualizzazione delle vendite
	 */
	private GroupLayout glPanelVisualizza;
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
		
		labelTitoloOperazione = new JLabel("Visualizza vendite :");
		labelTitoloOperazione.setFont(new Font ("Tahoma",Font.PLAIN,20));
		
		tabellaVendite = new JTable();
		
		tabellaVendite.setBounds(0,0,2000,200);
		tabellaVendite.setMaximumSize(new Dimension(2000,200));
		
		scrollPaneTabella = new JScrollPane(tabellaVendite);
		scrollPaneTabella.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTabella.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		btnTornaIndietro = new JButton("Indietro");
		btnTornaIndietro.setFont(new Font ("Tahoma",Font.PLAIN,16));
		btnTornaIndietro.setForeground(Color.white);
		btnTornaIndietro.setBackground(new Color(244,164,96));
		
		
		glPanelVisualizza = new GroupLayout(super.panelOperazioni);
		setGlPanelVisualizza();
		super.panelOperazioni.setLayout(glPanelVisualizza);

		}
	/**
	 * Metodo che setta il layout di panelCenterRight per l'operazione di visualizzazione
	 */
	private  void setGlPanelVisualizza() {
		glPanelVisualizza.setHorizontalGroup(
				glPanelVisualizza.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanelVisualizza.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								glPanelVisualizza.createParallelGroup(Alignment.LEADING)
								.addGroup(
										glPanelVisualizza.createSequentialGroup()
										.addComponent(scrollPaneTabella,GroupLayout.DEFAULT_SIZE,510,Short.MAX_VALUE)
										.addContainerGap()
								)
								.addGroup(
										Alignment.TRAILING,glPanelVisualizza.createSequentialGroup()
										.addComponent(btnTornaIndietro,GroupLayout.PREFERRED_SIZE,62,Short.MAX_VALUE)
										.addGap(88)
										.addComponent(labelTitoloOperazione,GroupLayout.DEFAULT_SIZE,181,Short.MAX_VALUE)
										.addGap(171)
										
								)
						)
						
				)
				
		);
		
		glPanelVisualizza.setVerticalGroup(
				glPanelVisualizza.createParallelGroup(Alignment.LEADING)
				.addGroup(
						glPanelVisualizza.createSequentialGroup()
						.addContainerGap()	
						.addGroup(
								glPanelVisualizza.createParallelGroup(Alignment.LEADING)
								.addComponent(labelTitoloOperazione)
								.addComponent(btnTornaIndietro)
						)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneTabella,GroupLayout.PREFERRED_SIZE,341,Short.MAX_VALUE)
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
		btnMostraVendite.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {		
				btnMostraVendite.setVisible(false);
				btnEffettuaVendita.setVisible(false);
				
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
		
		tabellaVendite.setModel(new DefaultTableModel(datiVendite,titoliColonne) {
			Class [] tipoColonne = new Class [] {String.class,String.class,String.class,Double.class,Double.class,String.class,String.class};
			public Class getColumnClass(int indiceColonne) {return tipoColonne[indiceColonne];};
		});
	}
	
	private void tornaSchermataImpiegato() {
		btnTornaIndietro.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {
				scrollPaneTabella.setVisible(false);
				btnTornaIndietro.setVisible(false);
				labelTitoloOperazione.setVisible(false);
				
				btnMostraVendite.setVisible(true);
				btnEffettuaVendita.setVisible(true);
				
			}
		}); 
	}
	
}
