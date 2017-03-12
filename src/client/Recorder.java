package client;


import java.io.DataOutputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class Recorder extends Thread {
	private AudioFormat format;
	private TargetDataLine microphone;
	private DataOutputStream out;
	private boolean conditionStop;

	public Recorder(OutputStream out){
		this.out = new DataOutputStream(out);
		format = new AudioFormat(8000.0f, 16, 1, true, true);
		conditionStop = true;
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
				while (true) { 
					while (!conditionStop) {
						Thread.sleep(300);
					}
					numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
					bytesRead = bytesRead + numBytesRead;
					//System.out.println(bytesRead);
					out.write(data, 0, numBytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void manageRecording(boolean b){
		conditionStop = b;
	}

}
