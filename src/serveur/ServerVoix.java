package serveur;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;


public class ServerVoix extends Thread{
	boolean stop = false;
	ArrayList<ClientHandler> listeClient;

	public ServerVoix() throws LineUnavailableException{
		this.listeClient = new ArrayList<ClientHandler>();
	}


	public void run(){
		System.out.println("SERVEUR : Lancement du server Voix port 5300");
		try (ServerSocket ss = new ServerSocket(5300)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();
					BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line = is.readLine();
					ClientHandler client = new ClientHandler(s,line,this);
					
					listeClient.add(client);
					System.out.println("SERVEUR : Nouveau client connecte au server Voix: "+ client.getIp());


					for(ClientHandler v : listeClient){
						if(v.getIp() != client.getIp()){
							client.getVoiceB().addRetour(v,client.getPseudo());
							v.getVoiceB().addRetour(client,v.getPseudo());
							
						}
					}

				} catch (SocketTimeoutException ex) {
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("SERVEUR : Could not bind port 5300");
		}
	}



	public synchronized void notifyClosedClient(ClientHandler ch) throws IOException{
		String name = ch.getPseudo();
		for(ClientHandler v : listeClient){
			if(v.getIp() != ch.getIp()){
				v.sendClosedClient(name);
			}
		}
	}

	public synchronized void finish() {
		stop = true;
	}
}
