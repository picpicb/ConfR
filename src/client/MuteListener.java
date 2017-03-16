package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MuteListener implements ActionListener{
	Recorder rec ;
	public MuteListener(Recorder rec){
		this.rec = rec;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton o = (JButton) arg0.getSource();
		if(o.getName() == "Mute"){
			System.out.println("MUTE");
			rec.mute(true);
		}
		
	}

}
