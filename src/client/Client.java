package client;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		String ip = "192.168.43.138";
		String pseudo = "toto";
		
		//Creation de l'interface graphique du client
		//new ClientGUI();
		
		
		//Connexion au serveur pour le micro/data
		Socket socket = null;
		try {
			socket = new Socket(ip, 5300);
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			printStream.print(pseudo+"\n");
			Recorder microphone = new Recorder(socket.getOutputStream());
			microphone.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Ecoute du port 5500 pour les donnees voix
		HandleServer voix;
		try {
			voix = new HandleServer(5500);
			voix.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
