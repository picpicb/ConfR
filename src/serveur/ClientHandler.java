package serveur;


import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;


public class ClientHandler{
	private Socket s;
	private String ip;
	private String pseudo;
	private VoiceBroadcaster emetteur;
	ServerVoix server;

	public ClientHandler(Socket s,String pseudo, ServerVoix server) throws IOException{
		this.s = s;
		this.server = server;
		String str = s.getRemoteSocketAddress().toString();
		String delims = "[/:]+";
		String[] tokens = str.split(delims);
		this.ip = tokens[1]; 
		this.pseudo = pseudo;
		System.out.println(pseudo+" s'est connecte");
		emetteur = new VoiceBroadcaster(s.getInputStream(),this);
		emetteur.start();
	}


	public String getIp(){
		return ip;
	}
	public Socket getSocket(){
		return s;
	}
	public String getPseudo(){
		return pseudo;
	}
	public VoiceBroadcaster getVoiceB(){
		return emetteur;
	}
	public void stopClient() throws IOException{
		server.notifyClosedClient(this);
	}
	public void sendClosedClient(String name) throws IOException{
		PrintStream printStream = new PrintStream(s.getOutputStream());
		printStream.print("DELUSER\n");
		printStream.print(name+"\n");
		printStream.println("");
	}
	
}
