package gui;

import javax.swing.*;

import impiegato.Impiegato;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;

/**
 * 
 * @author Fortunato Giuseppe 724309
 * @author Alemanno Giuseppe 716262
 *
 */
public class SchermataImpiegato extends JFrame{

	private Impiegato impiegato;
	private JFrame frame;
	private JPanel panelTop, panelDown;
	private JLabel labelTitleSoftware, labelToday;
	
	public SchermataImpiegato(Impiegato impiegato, JFrame login, String codiceImpiegato) {
		this.frame = new JFrame();
		this.impiegato = impiegato;
		setSchermataImpiegato();
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
		
		panelTop = new JPanel();
		panelTop.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelTop, BorderLayout.NORTH);
		labelTitleSoftware = new JLabel("Software Azienda Cereale");
		panelTop.add(labelTitleSoftware);
		
		panelDown = new JPanel();
		panelDown.setBackground(new Color(244,164,96));
		frame.getContentPane().add(panelDown, BorderLayout.SOUTH);
		
		labelToday = new JLabel("Data: ");
		

		
	}
}

