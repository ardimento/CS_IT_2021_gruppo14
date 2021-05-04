package gui;

import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
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
	protected JScrollPane scrollPaneTabella;
	/**
	 * Gruppo per gestire il layout delle componenti grafiche relative alla visualizzazione delle vendite
	 */
	private GroupLayout glPanelVisualizza;
	/**
	 * Costruttore di SchermataVisuallizaVendite, 
	 * partendo dal layout e le funzionalità base della schermata 'SchermataImpiegato'
	 * aggiunge e rende visibili gli elementi necessari all'operazione di visualizzazione delle vendite.
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * @param frame frame della schermata Login, chiamata nel caso di un eventuale logout.
	 * 
	 * @see gui.SchermataImpiegato
	 */
	public SchermataVisualizzaVendite(Impiegato impiegato, JFrame frame) {
		super(impiegato, frame);
		visualizzaVendite(impiegato);
		
	}
	
	/**
	 * Metodo che imposta le componenti grafiche della nostra interfaccia di visualizzazione.
	 * Aggiunge le componenti grafiche di visualizzazione e il loro layout 
	 * nel pannello 'panelOperazioni' della superclasse ('SchermataImpiegato')
	 * 
	 * @see gui.SchermataImpiegato#panelOperazioni
	 * @see gui.SchermataImpiegato
	 */
	private void setSchermataVisualizzaVendite() {
		
		
		labelTitoloOperazione.setText("Visualizza vendite :");
		
		tabellaVendite = new JTable();
		
		scrollPaneTabella = new JScrollPane(tabellaVendite);
		scrollPaneTabella.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneTabella.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		
		glPanelVisualizza = new GroupLayout(super.panelOperazioni);
		setGlPanelVisualizza();
		super.panelOperazioni.setLayout(glPanelVisualizza);

	}
	/**
	 *  Metodo che setta il layout della tabella 
	 *  Per funzionare correttamnte bisogna richiamare 
	 *  tale metodo dopo aver inserito i valori nella tabella.
	 */
	private void setTabella() {
		
		tabellaVendite.setBounds(0,0,2000,200);
		tabellaVendite.setMaximumSize(new Dimension(2000,200));
		tabellaVendite.setEnabled(false);
		tabellaVendite.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DefaultTableCellRenderer renderer = new  DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);
		for(int i = 0; i < tabellaVendite.getColumnCount(); i++ ) {
			tabellaVendite.getColumnModel().getColumn(i).setCellRenderer(renderer);
			tabellaVendite.getColumnModel().getColumn(i).setMinWidth(110);
		}
		
	}
	/**
	 * Metodo che setta il layout di 'panelOperazioni' per l'operazione di visualizzazione
	 * 
	 * @see gui.SchermataImpiegato#panelOperazioni
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
										.addComponent(btnTornaIndietro,GroupLayout.DEFAULT_SIZE,62,Short.MAX_VALUE)
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
	 * Si assegna al bottone 'btnMostraVendite' l'azione per :
	 * Rendere invisibili i bottoni della schermata "base" (superclasse @see SchermataImpiegato) escluso il bottone di logout.
	 * Rendere visibili gli elementi grafici necessari per l'operazione di visualizzazione delle vendite.
	 * Riempire la tabella delle vendite.
	 * 
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 * 
	 * @see gui.SchermataImpiegato#btnMostraVendite
	 * @see gui.SchermataImpiegato
	 */
	private void visualizzaVendite(Impiegato impiegato) {
		btnMostraVendite.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent b) {		
				btnMostraVendite.setVisible(false);
				btnEffettuaVendita.setVisible(false);
				
				labelTitoloOperazione.setVisible(true);
				btnTornaIndietro.setVisible(true);
				
				setSchermataVisualizzaVendite();
				ModificaDimensioniDinamicamenteVendite();
				tornaSchermataImpiegato();
				riempiTabella(impiegato);
				setTabella();			
				
			}
		
		}); 
	}
	/**
	 * Metodo che modifica a runtime :
	 * il font delle delle label e della tabella
	 * la dimensione delle righe e delle colonne della tabella 
	 * 
	 */
	private void ModificaDimensioniDinamicamenteVendite () {
		scrollPaneTabella.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent componentEvent) {
				int valoreCalcolato =0;
				Font font1 = null,font2 = null;
				if( (scrollPaneTabella.getHeight() > 500.0000 )||(scrollPaneTabella.getWidth() >700.00) ) {
					valoreCalcolato = scrollPaneTabella.getWidth()/35;
					font1 = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
					valoreCalcolato = scrollPaneTabella.getWidth()/40;
					font2= new Font("Tahoma", Font.PLAIN, valoreCalcolato);
					
				} else {
					valoreCalcolato = scrollPaneTabella.getWidth()/25;
					font1 = new Font("Tahoma", Font.PLAIN, valoreCalcolato);
					valoreCalcolato = scrollPaneTabella.getWidth()/35;
					font2= new Font("Tahoma", Font.PLAIN, valoreCalcolato);
				}
				labelTitoloOperazione.setFont(font1);
				btnTornaIndietro.setFont(font1);
				
				tabellaVendite.setRowHeight(scrollPaneTabella.getHeight()/5);
				tabellaVendite.setFont(font2);
				tabellaVendite.getTableHeader().setFont(font2);
				
				valoreCalcolato = scrollPaneTabella.getWidth()/tabellaVendite.getColumnCount();
				
				for(int i=0; i<tabellaVendite.getColumnCount();i++) {
					tabellaVendite.getColumnModel().getColumn(i).setPreferredWidth(valoreCalcolato);
				}
				
			}
		});
	}
	/**
	 * Metodo che riempie la tabella con le vendite effettuate dall'impiegato.
	 * 
	 * @param impiegato istanza contenente le informazioni dell' impiegato
	 */
	private void riempiTabella(Impiegato impiegato) {
		/**
		 * Array di nomi per le colonne della tabella
		 */
		String [] titoliColonne = new String [] {"Codice","Data Vendita","Cereale","Quantita'","Prezzo","Imbalaggio","Scadenza"};
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
	
	
}
