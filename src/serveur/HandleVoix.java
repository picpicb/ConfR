package serveur;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HandleVoix extends Thread{
	private Socket s;
	private String ip;
	ArrayList<HandleVoix> listeUser;

	public HandleVoix(Socket s,ArrayList<HandleVoix> listeUser){
		this.s = s;
		
		String str = s.getRemoteSocketAddress().toString();
		String delims = "[/:]+";
		String[] tokens = str.split(delims);
		this.ip = tokens[1];
		this.listeUser = listeUser;
	}

	public void run(){
		System.out.println("Nouveau client connecte au server Voix: "+ip);
		for(HandleVoix c : listeUser){
			if(c.getIp() != ip){
				try {
					new VoixSocket(s.getInputStream(),c.getIp()).start();
					new VoixSocket(c.getVoix(),ip).start();
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
	public InputStream getVoix(){
		try {
			return s.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
