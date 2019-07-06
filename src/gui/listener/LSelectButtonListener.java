package gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import gui.util.FramePanelUtil;

public class LSelectButtonListener implements ActionListener {
	JComponent parentComponent;
	@Override
	public void actionPerformed(ActionEvent e) {
		 int i = FramePanelUtil.getFileChooser(parentComponent).showOpenDialog(FramePanelUtil.getMainFrame(parentComponent));
	        if(i == JFileChooser.APPROVE_OPTION){
	        	File file = FramePanelUtil.getFileChooser(parentComponent).getSelectedFile();
	        	try {
	        		FramePanelUtil.setFilePath(parentComponent, file.getCanonicalPath());
	        		FramePanelUtil.setFileShowPath(parentComponent, file.getCanonicalPath());
	        		FramePanelUtil.setExtendNameList(file, parentComponent);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
	}

	public LSelectButtonListener(JComponent parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
