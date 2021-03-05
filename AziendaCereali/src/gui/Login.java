package gui;
import java.awt.*;
import java.util.Map;

import javax.swing.*;

import impiegato.Impiegato;

/**
 * Classe che gestisce graficamente l'accesso degli impiegati
 * alle proprie aree di lavoro
 * 
 * @author Fortunato Giuseppe 724309
 * @author Giuseppe Alemano 716262
 *
 */
public class Login extends JFrame{
	
	private JFrame login;
	
	private JPanel panelImage,panelForm;
	
	private JLabel labelAccess,labelCode,labelWelcome,labelImage;
	
	private JTextField textCode;

	
	
	/**
	 * 
	 * @param impiegati
	 */
	public Login (Map<String, Impiegato> impiegati) {
		super();
		this.login = new JFrame();	
		setLogin();
		setImageLogin();
	}
	
	
	/**
	 * Metodo che imposta l'immagine di login 
	 * E lancia una eccezione se l'immagine non viene creata.
	 * 
	 */
	private void setImageLogin() {
		try {
			ImageIcon immagineIcona = new ImageIcon(Login.class.getResource(immagini.PathImmagini.IMMAGINE_LOGIN));
			Image immagine = immagineIcona.getImage();
			Image immagineModificata = immagine.getScaledInstance(labelImage.getWidth(), labelImage.getHeight(), Image.SCALE_SMOOTH);
			immagineIcona = new ImageIcon(immagineModificata);
			
			labelImage.setIcon(immagineIcona);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Non è stato possibile caricare l'immagine di login", "Errore", JOptionPane.ERROR_MESSAGE);
		}
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
		login.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource(immagini.PathImmagini.IMMAGINE_ICONA_PROGRAMMA)));
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		
		panelImage = new JPanel();
		panelImage.setBounds(0, 0, login.getWidth()/2, login.getHeight());
		panelImage.setBackground(new Color(244,164,96));
		panelImage.setVisible(true);
		login.getContentPane().add(panelImage);
		panelImage.setLayout(null);
		
		labelImage = new JLabel();
		labelImage.setBounds(0, 0, panelImage.getWidth(), panelImage.getHeight());
		panelImage.add(labelImage);
	
		
		panelForm = new JPanel();
		panelForm.setBackground(SystemColor.window);
		panelForm.setBounds(login.getWidth()/2,0,login.getWidth()-panelImage.getWidth(),login.getHeight());
		login.getContentPane().add(panelForm);
		panelForm.setLayout(null);
		
		labelAccess = new JLabel("ACCESSO: ");
		panelForm.add(labelAccess);
		labelAccess.setBounds(66,293,265,31);
		labelAccess.setVisible(false);
		

		labelCode = new JLabel("Codice accesso: ");
		labelCode.setBounds(68,190,265,36);
		labelCode.setFont(new Font("Tahoma",Font.PLAIN,20));
		panelForm.add(labelCode);
		
		textCode = new JTextField();
		textCode.setBounds(68, 231, 265, 31);
		panelForm.add(textCode);
		
		labelWelcome = new JLabel("BENVENUTO", SwingConstants.CENTER);
		labelWelcome.setBounds(60, 68, 265, 69);
		labelWelcome.setForeground(Color.BLACK);
		labelWelcome.setFont(new Font("Tahoma",Font.PLAIN,25));
		panelForm.add(labelWelcome);

		
	}
	
}
