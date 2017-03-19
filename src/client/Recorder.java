package client;


import java.io.BufferedOutputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class Recorder extends Thread {
	private AudioFormat format;
	private TargetDataLine microphone;
	private BufferedOutputStream out;
	private boolean conditionStop;
	private boolean conditionFinish;

	
	/*
	 *  Le Recorder est un thread qui enregistre la voix et la diffuse dans un outputStream.
	 *  Il s'agit de la sortie du socket qui relie le client et le serveur
	 * 
	 * 
	 */
	
	public Recorder(OutputStream out){
		this.out = new BufferedOutputStream(out);
		format = new AudioFormat(8000.0f, 16, 1, true, true);
		conditionStop = false;
		conditionFinish = false;
	}

	public void run(){
		try {
			microphone = AudioSystem.getTargetDataLine(format);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
			microphone = (TargetDataLine) AudioSystem.getLine(info);
			microphone.open(format);
			System.out.println("Mic open.");

			int numBytesRead;
			int CHUNK_SIZE = 1024;
			byte[] data = new byte[microphone.getBufferSize() / 5];
			microphone.start();
			int bytesRead = 0;
			try {
				while (!conditionFinish) { 
					while (conditionStop) {
						//si on veut couper son micro, le flux et vidé pour éviter le décallage.
						Thread.sleep(300);
						microphone.flush();
					}
					numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
					bytesRead = bytesRead + numBytesRead;
					//System.out.println(bytesRead);
					out.write(data, 0, numBytesRead);
				}
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mute(boolean b){
		conditionStop = b;
	}

	public void finish(){
		conditionFinish = true;
		
	}
}
