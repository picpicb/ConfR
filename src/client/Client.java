package client;


import java.io.IOException;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		String ip = args[0];
		//String pseudo = args[1];
		
		//Creation de l'interface graphique du client
		//new ClientGUI();
		
		
		
		//Ecoute du port 5500 pour les donnees voix
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
		
		try {
			socketMicro = new Socket(ip, 5300);
			Recorder microphone = new Recorder(socketMicro.getOutputStream());
			microphone.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
