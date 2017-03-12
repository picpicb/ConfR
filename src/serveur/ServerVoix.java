package serveur;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import client.Player;

public class ServerVoix extends Thread{
	boolean stop = false;
	ArrayList<HandleVoix> listeUser;

	public ServerVoix(ArrayList<HandleVoix> listeUser) throws LineUnavailableException{
		this.listeUser = listeUser;
		
	}
	
	
	public void run(){
		System.out.println("Lancement du server Voix");
		try (ServerSocket ss = new ServerSocket(5300)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();					
					HandleVoix client = new HandleVoix(s,listeUser);
					listeUser.add(client);
					
					//new Player(s.getInputStream()).start();
					//client.start();
				} catch (SocketTimeoutException ex) {
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("ServerGestion: Could not bind port 5400");
		}
	}



	
	public synchronized void finish() {
		stop = true;
	}
}
