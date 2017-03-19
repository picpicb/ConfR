package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;

public class HandleServer extends Thread {
	ClientGUI gui;
	private int port;
	ServerSocket ss;
	private boolean stop = false;
	ArrayList<Socket> listePlayer;
	public HandleServer(int port, ClientGUI gui) throws IOException {
		this.port = port;
		this.gui = gui;
		listePlayer = new ArrayList<Socket>();
		System.out.println("HandleServer : attente de nouvelles voix");
	}

	public void run() {
		try (ServerSocket ss = new ServerSocket(port)) {
			ss.setSoTimeout(1000);
			while (!stop) {
				try {
					Socket s = ss.accept();
					listePlayer.add(s);
					BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
					String line = is.readLine();
					Player player = new Player(s.getInputStream(),line);
					
					player.start();
					gui.addPlayer(player);
				} catch (SocketTimeoutException ex) {
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("Could not bind port " + port);
		}
	}
	
	
	public synchronized void finish() {
		stop = true;
		for(Socket s : listePlayer){
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

