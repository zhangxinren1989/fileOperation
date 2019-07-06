package gui.thread;

import javax.swing.JButton;
import javax.swing.JProgressBar;

import gui.component.custom.FWaitOperationDialog;

public class ProgressBarThread implements Runnable {
	
	@Override
	public void run() {
		JProgressBar progressBar = FWaitOperationDialog.progressBar;
		JButton button = FWaitOperationDialog.button;
		try {
			while (true) {
				if(FWaitOperationDialog.completed)
				{
					break;
				}
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		progressBar.setIndeterminate(false);
		progressBar.setString("操作完成！");
		button.setEnabled(true);
	}

}
