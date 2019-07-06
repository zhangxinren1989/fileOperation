package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.component.left.modify.DReplacePanel;
import gui.fileoperation.modify.OneLevelModify;
import gui.util.FileUtil;
import gui.util.FramePanelUtil;

public class DReplaceModifyButtonListener implements ActionListener{
	private DReplacePanel parentComponent;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = FramePanelUtil.getFilePath(parentComponent);
		if(null == filePath || filePath.trim().length() == 0)
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "没有选择文件夹", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String replace = parentComponent.getReplaceField().getText();
		if(null == replace || replace.length() == 0)
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "被替换的字符串不存在", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String replaceAs = parentComponent.getReplaceAsField().getText();
		
		int replaceIndex = 0;
		String place = parentComponent.getPlaceField().getText();
		
		try
		{
			replaceIndex = Integer.parseInt(place);
		}
		catch(NumberFormatException ex)
		{
			ex.printStackTrace();
		}
		
		File file = new File(filePath);
		
		if(!file.exists())
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件夹不存在", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(!FileUtil.checkLegalFileName(replaceAs))
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件名非法", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		OneLevelModify.replaceDirectoryModify(file, replace, replaceAs, replaceIndex); 
		
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public DReplaceModifyButtonListener(DReplacePanel parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
