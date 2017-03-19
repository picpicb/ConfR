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
		if(o.getLabel() == "Couper le micro"){
			o.setLabel("Activer le micro");
			rec.mute(true);
		}else{
			o.setLabel("Couper le micro");
			rec.mute(false);
		}
	}

}
