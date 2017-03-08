package client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Client {
	public static void main(String[] args) {
		InputStream targetStream = null;
		try {
			targetStream = new BufferedInputStream(new FileInputStream("mission.wav"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player p = new Player(targetStream);
		p.start();
	}
}
