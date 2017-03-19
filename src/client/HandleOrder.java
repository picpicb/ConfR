package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HandleOrder extends Thread{
	private ClientGUI gui;
	private InputStream is;
	private boolean conditionStop;
	public HandleOrder(ClientGUI gui, InputStream is){
		this.gui = gui;
		this.is= is;
		conditionStop = false;
	}

	public void run(){
		BufferedReader bf = new BufferedReader(new InputStreamReader(is));
		String line;
		try {
			while (!conditionStop) {
				line = bf.readLine();
				System.out.println("lecture"+line);
				switch (line) {
				
				// DELUSER NON TERMINE
				case "DELUSER":
					gui.clearList();
					line = bf.readLine();
					System.out.println("lecture"+line);
					gui.removePlayer(line);
					break;

				default:
					//	throw new ChatProtocolException("Invalid input");
				}
			}
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finish(){
		conditionStop = true;
		
	}
}
