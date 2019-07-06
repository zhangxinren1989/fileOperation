
package gui.component.custom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import gui.FMainFrame;
import gui.FrameStatus;
import gui.listener.custom.AboutInfoCloseListener;
import gui.thread.MessageShowThread;

public class AboutInfoDialog extends JDialog{
	private MessageShowLabel aboutLabel = new MessageShowLabel();
	
	public AboutInfoDialog()
	{
		super(FMainFrame.getInstance(), "关于信息", true);
		setSize(765, 590);
		setLayout(null);
		aboutLabel.setBounds(25, 25, 700, 500);
		add(aboutLabel);
		FrameStatus.messageShow = true;
		final AboutInfoDialog fAboutInfoDialog = this;
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				 new MessageShowThread(aboutLabel, fAboutInfoDialog).start();
			}
		});
       
        
        addWindowListener(new AboutInfoCloseListener(this));
		setLocationRelativeTo(FMainFrame.getInstance());
		setVisible(true);
	}
	
	public static void showAboutInfo()
	{
		new AboutInfoDialog();
	}
}

