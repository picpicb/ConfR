package serveur;


import javax.sound.sampled.LineUnavailableException;

public class Server {

	public static void main(String[] args) throws LineUnavailableException {
		
		//port 5300
		new ServerVoix().start();
	}

}
