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
		System.out.println("Lancement du server Voix");
		try (ServerSocket ss = new ServerSocket(5300)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();
					BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line = is.readLine();
					ClientHandler client = new ClientHandler(s,line);
					listeClient.add(client);
					//client.sendClientList(listeClient);
					System.out.println("Nouveau client connecte au server Voix: "+ client.getIp());


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
			System.out.println("ServerGestion: Could not bind port 5300");
		}
	}




	public synchronized void finish() {
		stop = true;
	}
}
