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
	ArrayList<HandleVoix> listeVoix;
	ArrayList<VoixSocket> listeRetour;

	public ServerVoix(ArrayList<HandleVoix> listeUser) throws LineUnavailableException{
		this.listeVoix = listeUser;
		listeRetour = new ArrayList<VoixSocket>();
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
					
					
					HandleVoix client = new HandleVoix(s,line);
					System.out.println("Nouveau client connecte au server  Voix: "+ client.getIp());
					VoixSocket vs = new VoixSocket(client.getSocket().getInputStream(),client.getIp());
					listeRetour.add(vs);


					for(HandleVoix v : listeVoix){
						if(v.getIp() != client.getIp()){
							vs.addRetour(v);
						}
					}
					for(VoixSocket r : listeRetour){
						if(r.getIp() != client.getIp()){
							r.addRetour(client);
						}
					}
					listeVoix.add(client);
					vs.start();

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
