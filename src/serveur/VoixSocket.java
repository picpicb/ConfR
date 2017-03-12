package serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;



public class VoixSocket extends Thread{

	private InputStream voix;
	private String ip;
	private Socket s;
	
	public VoixSocket(InputStream voix, String ip){
		this.voix = voix;
		this.ip = ip;
	}
	
	public void run(){
		System.out.println("VoixSocket -- Connexion à :"+ip);
		try {
			s = new Socket(ip, 5500);
			OutputStream out = s.getOutputStream();
			 byte buffer[] = new byte[1024];
             int count;
             while ((count = voix.read(buffer)) != -1)
                 out.write(buffer, 0, count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
