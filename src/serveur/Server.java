package serveur;

import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

public class Server {

	public static void main(String[] args) throws LineUnavailableException {
		
		//ArrayList<HandleClient> listeUser = new ArrayList<HandleClient>();
		ArrayList<HandleVoix> listeUser = new ArrayList<HandleVoix>();
		
		//port 5400
		//new ServerGestion(listeUser).start();
		
		
		//port 5300
		new ServerVoix(listeUser).start();
	}

}
