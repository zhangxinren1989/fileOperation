
package gui.listener.custom;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import gui.component.custom.AboutInfoDialog;
import gui.util.UIUtil;

public class AboutInfoCloseListener extends WindowAdapter {
	private AboutInfoDialog aboutInfoDialog;
	@Override
	public void windowClosing(WindowEvent arg0) {
		UIUtil.stopMessageShow();
	}
	
	public AboutInfoCloseListener(AboutInfoDialog aboutInfoDialog)
	{
		this.aboutInfoDialog = aboutInfoDialog;
	}

	public AboutInfoDialog getAboutInfoDialog() {
		return aboutInfoDialog;
	}

	public void setAboutInfoDialog(AboutInfoDialog aboutInfoDialog) {
		this.aboutInfoDialog = aboutInfoDialog;
	}
}

