package client;

import java.awt.BorderLayout;
import java.awt.Checkbox;
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
		mute = new JButton("Couper le micro");
		mute.addActionListener(muteListener);

		//fenetre.add(boutonConnexion,BorderLayout.NORTH);
		fenetre.add(mainList, BorderLayout.CENTER);
		fenetre.add(mute,BorderLayout.SOUTH);
		fenetre.setVisible(true);
	}

	public void addPlayer(Player p){
		String name = p.getPlayerName();
		listePlayer.add(name);
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

	/*public void removePlayer(String name){
		ArrayList<String> l = new ArrayList<String>();
		mainList.removeAll();
		for(String s : listePlayer){
			if(s != name){
				addPlayer(s);
				l.add(s);
			}
		}
	}*/

}
