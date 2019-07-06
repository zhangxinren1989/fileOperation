
package gui.listener.practicefunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import gui.FMainFrame;
import gui.util.FramePanelUtil;

public class PStatisticFileSelectListener implements ActionListener
{
	private JFileChooser fileChooser;
	
	public PStatisticFileSelectListener(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//初始化当前路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        fileChooser.setCurrentDirectory(homeFile);
        //初始化选择模式
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        
        int i = fileChooser.showOpenDialog(FMainFrame.getInstance());
        
        if(i == JFileChooser.APPROVE_OPTION){
        	File selectFile = fileChooser.getSelectedFile();
        	FramePanelUtil.setFilePath(FMainFrame.getInstance().getLeftPanel(), selectFile.getAbsolutePath());
        	FramePanelUtil.refreshParentDirectory(FMainFrame.getInstance().getLeftPanel(), selectFile.getAbsolutePath());
        }
	}
	

}

