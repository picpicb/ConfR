package client;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
	static Socket socket;
	
	public static void main(String[] args) {
		
		String ip = "192.168.0.30";
		String pseudo = "JM";
		Recorder microphone = null;
		ClientGUI gui = null;
		socket = null;
	
		
		//Connexion au serveur pour le micro/data
		
		try {
			socket = new Socket(ip, 5300);
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			printStream.print(pseudo+"\n");
			microphone = new Recorder(socket.getOutputStream());
			microphone.start();
			gui = new ClientGUI(new MuteListener(microphone));
			new HandleOrder(gui,socket.getInputStream()).start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//Ecoute du port 5500 pour les donnees voix
		HandleServer voix;
		try {
			voix = new HandleServer(5500,gui);
			voix.start();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		
		gui.getFrame().addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            System.exit(0);
	        }
	    });
		
	}
}
