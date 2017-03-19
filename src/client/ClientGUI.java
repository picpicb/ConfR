package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;


public class ClientGUI {
	JFrame fenetre;
	JPanel mainList;
	JButton mute;
	ArrayList<Player> listePlayer;
	
	public ClientGUI(MuteListener muteListener){
		listePlayer = new ArrayList<Player>();
		fenetre = new JFrame("Confr");
		fenetre.setSize(200,250);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		mainList = new JPanel();
		mainList.setLayout(new GridLayout(6,1));



		mute = new JButton("Couper le micro");
		mute.addActionListener(muteListener);

		fenetre.add(mainList, BorderLayout.CENTER);
		fenetre.add(mute,BorderLayout.SOUTH);
		fenetre.setVisible(true);
	}

	/*
	 * Ajout d'une nouvelle voix dans la liste
	 * Chaque personne connecté peut être coupé
	 * 
	 */
	
	public void addPlayer(Player p){
		String name = p.getPlayerName();
		listePlayer.add(p);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel(name),BorderLayout.WEST);
		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
				boolean selected = abstractButton.getModel().isSelected();
				p.mute(selected);
			}
		};

		JCheckBox ch = new JCheckBox("mute");
		ch.addActionListener(actionListener);
		panel.add(ch, BorderLayout.EAST);
		
		mainList.add(panel);
		fenetre.validate();
		fenetre.repaint();
	}

	public void clearList(){
		mainList.removeAll();
	}

	public void removePlayer(String name){
		ArrayList<Player> l = listePlayer;
		listePlayer = null;
		mainList.removeAll();
		for(Player pl : l){
			if(pl.getName() != name){
				addPlayer(pl);
			}
		}
	}
	
	public JFrame getFrame(){
		return fenetre;
	}

}
