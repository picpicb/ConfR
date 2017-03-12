package serveur;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import client.Player;

public class ServerVoix extends Thread{
	boolean stop = false;
	ArrayList<HandleVoix> listeUser;

	public ServerVoix(ArrayList<HandleVoix> listeUser){
		this.listeUser = listeUser;
	}
	
	
	public void run(){
		System.out.println("Lancement du server Voix");
		try (ServerSocket ss = new ServerSocket(5300)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();
					//HandleVoix client = new HandleVoix(s,listeUser);
					//listeUser.add(client);
					if (s.isConnected()) {
	                    InputStream in = new BufferedInputStream(s.getInputStream());
	                    play(in);
	                }
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


	private static synchronized void play(final InputStream in) throws Exception {
        AudioInputStream ais = AudioSystem.getAudioInputStream(in);
        try (Clip clip = AudioSystem.getClip()) {
        	System.out.println("LECTURE");
            clip.open(ais);
            clip.start();
            Thread.sleep(100); // given clip.drain a chance to start
            clip.drain();
        }
    }
	
	public synchronized void finish() {
		stop = true;
	}
}
