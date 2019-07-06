package gui;

import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import gui.component.right.FFileShowPanel;
import gui.component.right.FMainPanel;
import gui.util.FramePanelUtil;

public class FRightPanel extends JPanel {
	private static final long serialVersionUID = 6285756008334550120L;
	private FMainPanel mainPanel;
	private FFileShowPanel fileShowPanel;
	
	public FRightPanel()
	{
		setSize(500, 610);
		setLayout(null);
		
		mainPanel = new FMainPanel(this);
		mainPanel.setBorder(BorderFactory.createTitledBorder("基本操作"));
		mainPanel.setBounds(0, 0, 500, 410);
		
		fileShowPanel = new FFileShowPanel(this);
		fileShowPanel.setBounds(0, 400, 500, 300);
		
	}
	
	public void init()
	{
		FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
		try {
			FramePanelUtil.setFileShowPath(this, homeFile.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public FMainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(FMainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public FFileShowPanel getFileShowPanel() {
		return fileShowPanel;
	}
	public void setFileShowPanel(FFileShowPanel fileShowPanel) {
		this.fileShowPanel = fileShowPanel;
	}
	
	
}
