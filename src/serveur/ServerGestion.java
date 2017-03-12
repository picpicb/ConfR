package serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ServerGestion extends Thread{
	boolean stop = false;
	ArrayList<HandleClient> listeUser;

	public ServerGestion(ArrayList<HandleClient> listeUser){
		this.listeUser = listeUser;
	}
	
	
	public void run(){
		try (ServerSocket ss = new ServerSocket(5400)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();
					HandleClient client = new HandleClient(s,listeUser);
					listeUser.add(client);
					client.start();
				} catch (SocketTimeoutException ex) {
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
