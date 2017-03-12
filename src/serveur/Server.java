package serveur;

import java.util.ArrayList;

public class Server {

	public static void main(String[] args) {
		
		//ArrayList<HandleClient> listeUser = new ArrayList<HandleClient>();
		ArrayList<HandleVoix> listeUser = new ArrayList<HandleVoix>();
		
		//port 5400
		//new ServerGestion(listeUser).start();
		
		
		//port 5300
		new ServerVoix(listeUser).start();
	}

}
