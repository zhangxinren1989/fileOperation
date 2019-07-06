
package gui.listener.practicefunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import gui.component.custom.FileStatisticDialog;

public class PStatisticFileListener implements ActionListener
{
	private JComponent component;
	public PStatisticFileListener(JComponent component)
	{
		this.component = component;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		FileStatisticDialog.showFileStaticDialog();
	}

	public JComponent getComponent()
	{
		return component;
	}

	public void setComponent(JComponent component)
	{
		this.component = component;
	}

}

