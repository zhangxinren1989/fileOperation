package gui.listener.custom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.component.custom.FWaitOperationDialog;

public class FProgressBarCompleteListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FWaitOperationDialog.hideWaitDialog();
	}

}
