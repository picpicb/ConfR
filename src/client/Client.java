package client;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	static Socket socket;
	static HandleOrder handleO;
	static Recorder microphone;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez l'adresse ip du serveur:");
		String ip = sc.nextLine();
		System.out.println("Entrez un pseudo:");
		String pseudo = sc.nextLine();
	
		
		microphone = null;
		ClientGUI gui = null;
		socket = null;
		handleO = null;
		HandleServer voix;
	
		
		/* Connexion au serveur avec un premier socket
		 * 
		 * SocketIN = microphone du client (passage du pseudo en premier)
		 * SocketOUT = retour txt du server (selon un protocole)
		 * 
		 */
		
		try {

			
			// création du thread qui enregistre et envoie
			OutputStream out = null;
			microphone = new Recorder(out);
			microphone.start();
			
			// création de l'interface graphique
			gui = new ClientGUI(new MuteListener(microphone));
			voix = new HandleServer(5500,gui);
			voix.start();
			

			socket = new Socket(ip, 5300);
			PrintStream printStream = new PrintStream(socket.getOutputStream());
			printStream.print(pseudo+"\n");
			
			microphone.setOutput(socket.getOutputStream());
			
			// création du thread qui écoute les commandes du serveur
			handleO = new HandleOrder(gui,socket.getInputStream());
			handleO.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	
		
		/* Ecoute du port 5500 pour les donnees voix
		 * 
		 * Il s'agit des voix que le serveur envoie. Chaque voix et intercepté dans un nouveau thread Player (qui joue le son)
		 * 
		 * SocketIN = voix venant du serveur 
		 * SocketOUT = vide
		 * 
		 */
		

		
		
		/*
		 * Debut de gestion de déconnexion à la fermeture de la fenetre.
		 */
		
		gui.getFrame().addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	        	System.out.println("fermeture de l'application");
	        	handleO.finish();
	        	microphone.finish();
	        	try {
		        	//handleO.join();
		        	//microphone.join();
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				//} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
	            System.exit(0);
	        }
	    });
		
	}
}
