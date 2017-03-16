package client;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		
		String ip = "10.0.0.119";
		String pseudo = "obama";
		Recorder microphone = null;

		
		
		
		//Connexion au serveur pour le micro/data
		Socket socket = null;
		try {
			socket = new Socket(ip, 5300);
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			printStream.print(pseudo+"\n");
			microphone = new Recorder(socket.getOutputStream());
			microphone.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Creation de l'interface graphique du client
		
		
		ClientGUI gui = new ClientGUI(new MuteListener(microphone));
		
		//Ecoute du port 5500 pour les donnees voix
		HandleServer voix;
		try {
			voix = new HandleServer(5500,gui);
			voix.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
