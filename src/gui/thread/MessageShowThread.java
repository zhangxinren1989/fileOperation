
package gui.thread;

import gui.FrameStatus;
import gui.component.custom.AboutInfoDialog;
import gui.component.custom.MessageShowLabel;

public class MessageShowThread extends Thread {
	private MessageShowLabel messageShowLabel;
	private AboutInfoDialog aboutInfoDialog;
	
	@Override
	public void run()
	{
		while(FrameStatus.messageShow)
		{
			try {
				messageShowLabel.repaint();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		aboutInfoDialog.dispose();
	}
	
	public MessageShowThread(MessageShowLabel messageShowLabel, AboutInfoDialog aboutInfoDialog)
	{
		this.messageShowLabel = messageShowLabel;
		this.aboutInfoDialog = aboutInfoDialog;
	}
}

