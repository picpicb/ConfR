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
	private boolean conditionStop;
	private String name;
	private boolean conditionFinish;

	public Player(InputStream is, String name) throws LineUnavailableException{
		format = new AudioFormat(8000.0f, 16, 1, true, true);
		DataLine.Info speakerInfo = new DataLine.Info(SourceDataLine.class, format);
		speaker = (SourceDataLine) AudioSystem.getLine(speakerInfo);
        speaker.open(format);
        streamIn = is;
        conditionStop = false;
        conditionFinish = false;
        this.name = name;
	}

	public void run(){
		System.out.println("nouvelle voix !");
		speaker.start();
		byte[] data = new byte[1024];
        while (!conditionFinish) {
            try {
				if (streamIn.available() <= 0)
				    continue;
				int readCount= streamIn.read(data, 0, data.length);
	            if(readCount>0 && !conditionStop){
	                speaker.write(data, 0, readCount);
	            }
			} catch (IOException e) {
				e.printStackTrace();
			}  

        }
	}
    public void mute(boolean b){
		conditionStop = b;
	}
    
    public String getPlayerName(){
    	return name;
    }
    
	public void finish(){
		conditionFinish = true;
		
	}
    
}
