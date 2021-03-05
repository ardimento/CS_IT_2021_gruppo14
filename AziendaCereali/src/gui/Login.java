package gui;
import java.awt.*;
import java.util.Map;

import javax.swing.*;

import impiegato.Impiegato;

/**
 * 
 * @author Fortunato Giuseppe 724309
 *
 *
 */
public class Login extends JFrame{
	
	private JFrame login;
	private JPanel panelImage;
	private JPanel panelForm;
	private JLabel labelAccess;
	private JLabel labelCode;
	private JTextField textCode;
	private JLabel labelWelcome;
	
	
	/**
	 * 
	 * @param impiegati
	 */
	public Login (Map<String, Impiegato> impiegati) {
		super();
		this.login = new JFrame();	
		setLogin();
	}
	
	/**
	 * 
	 */
	private void setLogin() {
		
		login.setResizable(false);
		login.setVisible(true);
		login.setTitle("Azienda Cereali");
		login.setSize(800,600);
		login.setLocationRelativeTo(null);
		login.setBackground(SystemColor.window);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		panelImage = new JPanel();
		panelImage.setBounds(0, 0, 398, 600);
		panelImage.setBackground(new Color(244,164,96));
		panelImage.setVisible(true);
		login.getContentPane().add(panelImage);
		panelImage.setLayout(null);
		
		panelForm = new JPanel();
		panelForm.setBackground(SystemColor.window);
		panelForm.setBounds(398,0,396,600);
		login.getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		labelAccess = new JLabel("ACCESSO: ");
		panelForm.add(labelAccess);
		labelAccess.setBounds(66,293,265,31);
		labelAccess.setVisible(false);
		
		labelCode = new JLabel("Codice accesso: ");
		labelCode.setBounds(66,190,265,36);
		labelCode.setFont(new Font("Tahoma",Font.PLAIN,20));
		panelForm.add(labelCode);
		
		textCode = new JTextField();
		textCode.setBounds(66, 231, 265, 31);
		panelForm.add(textCode);
		
		labelWelcome = new JLabel("BENVENUTO", SwingConstants.CENTER);
		labelWelcome.setBounds(60, 68, 265, 69);
		labelWelcome.setForeground(Color.BLACK);
		labelWelcome.setFont(new Font("Tahoma",Font.PLAIN,25));
		panelForm.add(labelWelcome);
		
		
		
	}
	
}
