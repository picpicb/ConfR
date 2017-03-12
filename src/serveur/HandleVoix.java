package serveur;


import java.net.Socket;

public class HandleVoix{
	private Socket s;
	private String ip;

	public HandleVoix(Socket s){
		this.s = s;
		String str = s.getRemoteSocketAddress().toString();
		String delims = "[/:]+";
		String[] tokens = str.split(delims);
		this.ip = tokens[1];
	}


	public String getIp(){
		return ip;
	}
	public Socket getSocket(){
		return s;
	}
}
