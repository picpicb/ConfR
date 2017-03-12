package client;


import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Player extends Thread{

	AudioFormat format;// = new AudioFormat(8000.0f, 16, 1, true, true);
	InputStream streamIn;
	SourceDataLine speaker;

	public Player(InputStream is) throws LineUnavailableException{
		format = new AudioFormat(8000.0f, 16, 1, true, true);
		DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, format);
		speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
        speaker.open(format);
        streamIn = is;
	}

	public void run(){
		System.out.println("nouvelle voix !");
		speaker.start();
		byte[] data = new byte[1024];
        System.out.println("Waiting for data...");
        while (true) {
            try {
				if (streamIn.available() <= 0)
				    continue;
				int readCount= streamIn.read(data, 0, data.length);
	            System.out.println(readCount);
	            if(readCount>0){
	                speaker.write(data, 0, readCount);
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}  

        }
		
		
		
		
		
		
		
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
