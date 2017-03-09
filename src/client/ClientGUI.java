package client;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientGUI {
	JFrame fenetre;
	JPanel panelUser;
	JButton boutonConnexion;
	JButton mute;
	public ClientGUI(){
		fenetre = new JFrame("Confr");
		fenetre.setSize(300,400);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		panelUser = new JPanel();
		boutonConnexion = new JButton("Connexion");
		mute = new JButton("Mute");
		//boutonConnexion.addActionListener(new MuteListener(c));
		//mute.addActionListener(new MuteListener(c));

		fenetre.add(boutonConnexion,BorderLayout.NORTH);
		fenetre.add(panelUser, BorderLayout.CENTER);
		fenetre.add(mute,BorderLayout.SOUTH);
		fenetre.setVisible(true);
	}
}
