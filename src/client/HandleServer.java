package client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class HandleServer extends Thread {

		private int port;
		ServerSocket ss;
		private boolean stop = false;
		public HandleServer(int port) throws IOException {
			this.port = port;
			System.out.println("HandleServer : attente de nouvelles voix");
			this.start();
		}

		public void run() {
			try (ServerSocket ss = new ServerSocket(port)) {
				ss.setSoTimeout(1000);
				while (!stop) {
					try {
						Socket s = ss.accept();
						new Player(s.getInputStream()).start();
					} catch (SocketTimeoutException ex) {
					}
				}
			} catch (IOException e) {
				System.out.println("Could not bind port " + port);
			}
		}
		public synchronized void finish() {
			stop = true;
		}
	}

