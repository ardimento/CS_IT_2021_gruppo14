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
 * Classe che gestisce graficamente il layout e le funzionalità base d
 * elle schermate relative all'impiegato 
 * 
 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataImpiegato extends JFrame{

	private Impiegato impiegato;
	private JFrame frame;
	private JPanel panelTop, panelDown, panelRight,panelLeft, panelCenterRight,panelCenterLeft;
	private JLabel labelTitleSoftware, labelToday, labelRights, labelCode;
	private JButton btnGoBack;
	private GroupLayout glPanelDown,glPanelCenterLeft;
	private JTable table;
	
	public SchermataImpiegato(Impiegato impiegato, JFrame login, String codiceImpiegato) {
		this.frame = new JFrame();
		this.impiegato = impiegato;
		setSchermataImpiegato();
		setImageBtnGoBack();
		searchDate ();
		azioneBottoneIndietro(login);
		
	}
	
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
		
		panelTop = new JPanel();
		panelTop.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelTop, BorderLayout.NORTH);
		labelTitleSoftware = new JLabel("Software Azienda Cereale");
		panelTop.add(labelTitleSoftware);
		
		panelDown = new JPanel();
		panelDown.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelDown, BorderLayout.SOUTH);
		
		labelToday = new JLabel("Data: ");
		
		btnGoBack = new JButton("Logout");	
		btnGoBack.setFont(new Font("Tahoma", Font.PLAIN, 16) );
		btnGoBack.setForeground(Color.WHITE);
		btnGoBack.setBackground(new Color(244,164,96));
		btnGoBack.setBounds(-100,0,30,30);
		
		
		labelRights = new JLabel ("    By Alemanno, Ben Sidi, Fortunato");
		
		glPanelDown = new GroupLayout (panelDown);
		setGlPanelDown();
		panelDown.setLayout(glPanelDown);
		
		panelRight = new JPanel();
		panelRight.setBackground(SystemColor.controlShadow);
		frame.getContentPane().add(panelRight, BorderLayout.EAST);
		
		
		panelLeft = new JPanel();
		panelLeft.setBackground(SystemColor.control);
		frame.getContentPane().add(panelLeft, BorderLayout.CENTER);
		
		panelCenterLeft = new JPanel();
		panelCenterLeft.setBackground(SystemColor.scrollbar);
		setGlPanelCenterLeft();
		panelCenterLeft.setLayout(glPanelCenterLeft);
		
		
	}
	private void setGlPanelCenterLeft() {
		
		glPanelCenterLeft.setHorizontalGroup( 
				glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
				.addGroup(
							glPanelCenterLeft.createSequentialGroup() 
							.addContainerGap()
							.addGroup(
								glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
								.addComponent(table, GroupLayout.DEFAULT_SIZE,157,350)
								.addComponent(labelCode, Alignment.CENTER,GroupLayout.PREFERRED_SIZE,157,350)
							)
							.addContainerGap()
				)
		);	
		
		glPanelCenterLeft.setVerticalGroup(
				glPanelCenterLeft.createParallelGroup(Alignment.CENTER)
				.addGroup(glPanelCenterLeft.createSequentialGroup()
						.addGap(75)
						.addComponent(labelCode)
						.addGap(47)
						.addComponent(table,GroupLayout.PREFERRED_SIZE,144,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(261,Short.MAX_VALUE)
						
				)
				
		);
		
	}
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
						.addGroup(glPanelDown.createParallelGroup(Alignment.BASELINE))
						.addComponent(btnGoBack)
						.addComponent(labelToday, GroupLayout.PREFERRED_SIZE,30,GroupLayout.PREFERRED_SIZE)
						.addComponent(labelRights, GroupLayout.PREFERRED_SIZE,28,GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE,0,GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE,Short.MAX_VALUE)					
			)
		);
				
	}
	
	/**
	 * Metodo che imposta l'immagine del bottone btnGoBack 
	 * E lancia una eccezione se l'immagine non viene creata.
	 * 
	 */
	private void setImageBtnGoBack() {
		try {
			Image immagine = ImageIO.read(getClass().getResource(PathImmagini.IMMAGINE_BOTTONE_INDIETRO));
			Image immagineModificata = immagine.getScaledInstance(btnGoBack.getWidth(), btnGoBack.getHeight(), Image.SCALE_SMOOTH);
			btnGoBack.setIcon(new ImageIcon(immagineModificata));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Non è stato possibile caricare l'immagine del bottone", "Errore", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * 
	 * @param login
	 */
	private void azioneBottoneIndietro (JFrame login ) {
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e ) {
				login.setVisible(true);
				frame.dispose();
			}
		});
	}
	
	private void searchDate () {
		LocalDate data = LocalDate.now();
		String dataString = data.toString();
		labelToday.setText("Data :"+ dataString);
	}
}

