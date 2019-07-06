package gui.listener.interestfunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import gui.FMainFrame;
import gui.fileoperation.inherit.filter.TxtFileFilter;
import gui.fileoperation.interest.InterestFunction;
import gui.util.FramePanelUtil;

public class FInterestWordFileButtonListener implements ActionListener {
	private JFileChooser selectWordFileChoosor;
	
	public FInterestWordFileButtonListener(JFileChooser selectWordFileChoosor) {
		this.selectWordFileChoosor = selectWordFileChoosor;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//初始化当前路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        selectWordFileChoosor.setCurrentDirectory(homeFile);
        //初始化选择模式
        selectWordFileChoosor.setFileSelectionMode(JFileChooser.FILES_ONLY);
        selectWordFileChoosor.setMultiSelectionEnabled(false);
        selectWordFileChoosor.setFileFilter(new TxtFileFilter());
        
        int i = selectWordFileChoosor.showOpenDialog(FMainFrame.getInstance());
        
        if(i == JFileChooser.APPROVE_OPTION){
        	File selectFile = selectWordFileChoosor.getSelectedFile();
        	FramePanelUtil.setFilePath(FMainFrame.getInstance().getLeftPanel(), selectFile.getAbsolutePath());
        	FramePanelUtil.refreshParentDirectory(FMainFrame.getInstance().getLeftPanel(), selectFile.getAbsolutePath());
        }
	}

}
