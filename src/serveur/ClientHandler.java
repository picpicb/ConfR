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

	public ClientHandler(Socket s,String pseudo) throws IOException{
		this.s = s;
		String str = s.getRemoteSocketAddress().toString();
		String delims = "[/:]+";
		String[] tokens = str.split(delims);
		this.ip = tokens[1]; 
		this.pseudo = pseudo;
		System.out.println(pseudo+" s'est connecte");
		emetteur = new VoiceBroadcaster(s.getInputStream(),this.ip);
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
	public void sendClientList(ArrayList<ClientHandler> listeClient) throws IOException{
		PrintStream printStream = new PrintStream(s.getOutputStream());
		printStream.print("USERLIST\n");
		for(ClientHandler c : listeClient){
			printStream.print(c.getPseudo()+"\n");
		}
	}
}
