package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;



public class ClientGUI {
	JFrame fenetre;
	JPanel mainList;
	JButton boutonConnexion;
	JButton mute;
	ArrayList<String> listePlayer;
	public ClientGUI(MuteListener muteListener){
		listePlayer = new ArrayList<String>();
		fenetre = new JFrame("Confr");
		fenetre.setSize(200,250);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		mainList = new JPanel();
		mainList.setLayout(new GridLayout(6,1));
        
       
        
		//boutonConnexion = new JButton("Connexion");
		mute = new JButton("Mute");
		mute.addActionListener(muteListener);

		//fenetre.add(boutonConnexion,BorderLayout.NORTH);
		fenetre.add(mainList, BorderLayout.CENTER);
		fenetre.add(mute,BorderLayout.SOUTH);
		fenetre.setVisible(true);
	}
	
	public void addPlayer(String name){
		listePlayer.add(name);
		JPanel panel = new JPanel();
        panel.add(new JLabel(name));
        panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));
        mainList.add(panel);
        fenetre.validate();
        fenetre.repaint();
	}
	public void removePlayer(String name){
		ArrayList<String> l = new ArrayList<String>();
		mainList.removeAll();
		for(String s : listePlayer){
			if(s != name){
				addPlayer(s);
				l.add(s);
			}
		}
	}
	
}
