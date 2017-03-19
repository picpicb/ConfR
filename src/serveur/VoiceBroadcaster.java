package serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;



public class VoiceBroadcaster extends Thread{

	private InputStream voix;
	ArrayList<Socket> retour;
	private String ip;
	ClientHandler ch;
	
	public VoiceBroadcaster(InputStream voix,ClientHandler c){
		this.voix = voix;
		retour = new ArrayList<Socket>();
		ch = c;
		this.ip = c.getIp();
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
			ch.stopClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void addRetour(ClientHandler v, String pseudo){
		Socket s = null;
		try {
			s = new Socket(v.getIp(), 5500);
			PrintStream printStream = new PrintStream(s.getOutputStream());
			printStream.print(pseudo+"\n");
			System.out.println("VOIX :" + ip +" to "+s.getRemoteSocketAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		retour.add(s);
	}
	
	public String getIp(){
		return ip;
	}
	
}