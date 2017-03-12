package serveur;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class HandleVoix extends Thread{
	private Socket s;
	private String ip;
	ArrayList<HandleVoix> listeUser;

	public HandleVoix(Socket s,ArrayList<HandleVoix> listeUser){
		this.s = s;
		this.ip = s.getRemoteSocketAddress().toString();
		this.listeUser = listeUser;
	}

	public void run(){
		System.out.println("Nouveau client connecte au server Voix: "+ip);
		for(HandleVoix c : listeUser){
			if(c.getIp() != ip){
				try {
					new VoixSocket(s.getInputStream(),c.getIp()).start();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getIp(){
		return ip;
	}
}
