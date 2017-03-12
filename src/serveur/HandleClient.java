package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class HandleClient extends Thread{
	private Socket s;
	private boolean stop = false;
	private String strName;
	private SocketAddress ip;
	ArrayList<HandleClient> listeUser;
	
	public HandleClient(Socket s,ArrayList<HandleClient> listeUser){
		this.s = s;
		this.ip = s.getRemoteSocketAddress();
		strName ="";
		this.listeUser = listeUser;
	}
	
	public void run(){
		try (BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()))) {
			while (!stop) {
				String line = is.readLine();
				System.out.println("lecture"+line);
				switch (line) {
				case "NAME":
					strName = is.readLine();
					newUser(strName);
					//handler.sendPrivateMessage(strName, strDest, strMsg);

				default:
					//	throw new ChatProtocolException("Invalid input");
				}


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void newUser(String strName2) {
		// TODO Auto-generated method stub
		
	}

	private void finish() {
		// TODO Auto-generated method stub
		
	}
	
}
