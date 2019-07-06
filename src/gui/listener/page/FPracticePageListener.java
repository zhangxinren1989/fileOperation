package gui.listener.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gui.FMenuBar;
import gui.component.left.page.FModifyPage;
import gui.component.left.page.FPage;
import gui.component.left.page.FPracticePage;

public class FPracticePageListener implements ActionListener {
	private FMenuBar menuBar;

	@Override
	public void actionPerformed(ActionEvent e) {
		FPage fpage = menuBar.getfPage();
		if(fpage instanceof FPracticePage)
		{
			return;
		}
		else
		{
			fpage = new FPracticePage(menuBar);
			menuBar.setfPage(fpage);
		}
	}

	public FPracticePageListener(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
	}
}
