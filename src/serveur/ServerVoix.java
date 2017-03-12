package serveur;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;


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
					HandleVoix client = new HandleVoix(s);
					listeUser.add(client);
					System.out.println("Nouveau client connecte au server Voix: "+ client.getIp());
					for(HandleVoix c : listeUser){
						if(client.getIp() != c.getIp()){
							try {
								new VoixSocket(client.getSocket().getInputStream(),c.getIp()).start();
								new VoixSocket(c.getSocket().getInputStream(),client.getIp()).start();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}


					}
				} catch (SocketTimeoutException ex) {
				} catch (Exception e) {
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
