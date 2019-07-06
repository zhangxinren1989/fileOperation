package gui.listener.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.FMenuBar;
import gui.component.left.page.FModifyPage;
import gui.component.left.page.FPage;

public class FModifyPageListener implements ActionListener {
	private FMenuBar menuBar;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FPage fpage = menuBar.getfPage();
		if(fpage instanceof FModifyPage)
		{
			return;
		}
		else
		{
			fpage = new FModifyPage(menuBar);
			menuBar.setfPage(fpage);
		}
	}
	
	public FModifyPageListener(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
	}
}
