package serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;



public class VoixSocket extends Thread{

	private InputStream voix;
	ArrayList<Socket> retour;
	private String ip;
	
	public VoixSocket(InputStream voix,String ip){
		this.voix = voix;
		retour = new ArrayList<Socket>();
		this.ip = ip;
	}

	public void run(){
		byte buffer[] = new byte[4096];
		int count;
		try {
			while ((count = voix.read(buffer)) != -1){
				for(Socket s : retour){
					OutputStream out = s.getOutputStream();
					out.write(buffer, 0, count);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addRetour(HandleVoix v){
		Socket s = null;
		try {
			s = new Socket(v.getIp(), 5500);
			System.out.println("VOIX :" + ip +" to "+s.getRemoteSocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retour.add(s);
	}
	
	public String getIp(){
		return ip;
	}
	
}