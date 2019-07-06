package gui.listener.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.FMenuBar;
import gui.component.left.page.FInterestPage;
import gui.component.left.page.FModifyPage;
import gui.component.left.page.FPage;

public class FInterestPageListener implements ActionListener {

private FMenuBar menuBar;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FPage fpage = menuBar.getfPage();
		if(fpage instanceof FInterestPage)
		{
			return;
		}
		else
		{
			fpage = new FInterestPage(menuBar);
			menuBar.setfPage(fpage);
		}
	}
	
	public FInterestPageListener(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
	}
}
