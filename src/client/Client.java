package client;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		//String ip = args[0];
		//String pseudo = args[1];
		
		//Création de l'interface graphique du client
		new ClientGUI();
		
		
		
		//Ecoute du port 5500 pour les données voix
		HandleServer voix;
		try {
			voix = new HandleServer(5500);
			voix.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		//Connexion au serveur pour la gestion		
		//Socket socketGestion = new Socket(ip, 5400);
		
		//Connexion au serveur pour le micro	
		Socket socketMicro = null;
		//socketMicro = new Socket(ip, 5300);
		try {
			Recorder microphone = new Recorder(socketMicro.getOutputStream());
			microphone.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
		InputStream targetStream = null;
		InputStream targetStream2 = null;
		try {
			targetStream = new BufferedInputStream(new FileInputStream("mission.wav"));
			targetStream2 = new BufferedInputStream(new FileInputStream("about_time.wav"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player p = new Player(targetStream);
		p.start();
		Player p2 = new Player(targetStream2);
		p2.start();
	}
}
