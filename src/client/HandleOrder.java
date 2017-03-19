package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HandleOrder extends Thread{
	private ClientGUI gui;
	private InputStream is;
	public HandleOrder(ClientGUI gui, InputStream is){
		this.gui = gui;
		this.is= is;
	}

	public void run(){
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while (true) {
				line = bf.readLine();
				System.out.println("lecture"+line);
				switch (line) {
				case "USERLIST":
					gui.clearList();
					while((line = bf.readLine()).length()>0){
						System.out.println("lecture"+line);
						gui.addPlayer(line);
					}
					break;

				default:
					//	throw new ChatProtocolException("Invalid input");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
