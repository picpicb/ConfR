package serveur;


import java.net.Socket;

public class HandleVoix{
	private Socket s;
	private String ip;
	private String pseudo;

	public HandleVoix(Socket s,String pseudo){
		this.s = s;
		String str = s.getRemoteSocketAddress().toString();
		String delims = "[/:]+";
		String[] tokens = str.split(delims);
		this.ip = tokens[1]; 
		this.pseudo = pseudo;
		System.out.println(pseudo+" s'est connecte");
	}


	public String getIp(){
		return ip;
	}
	public Socket getSocket(){
		return s;
	}
}
