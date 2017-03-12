package client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Player extends Thread{

	AudioFormat format;// = new AudioFormat(8000.0f, 16, 1, true, true);
	AudioInputStream audioInputStream;
	SourceDataLine speaker;

	public Player(InputStream is){
		try {    	
			InputStream bufferedIn = new BufferedInputStream(is);
			audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			format = audioInputStream.getFormat(); 
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){
		System.out.println("nouvelle voix !");
		
		
		/*try{
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
			speaker = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

			speaker.open(format);
			speaker.start();

			int cnt = 0;
			byte tempBuffer[] = new byte[10000];
			try {
				while ((cnt = audioInputStream.read(tempBuffer, 0,tempBuffer.length)) != -1) {
					if (cnt > 0) {
						speaker.write(tempBuffer, 0, cnt);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			speaker.drain();
			speaker.close();
			speaker.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}*/
	}
}
