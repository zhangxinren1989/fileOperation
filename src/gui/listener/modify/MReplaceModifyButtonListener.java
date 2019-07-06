
package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import gui.component.left.modify.DReplacePanel;
import gui.component.left.modify.MReplacePanel;
import gui.fileoperation.modify.MultiLevelModify;
import gui.fileoperation.modify.OneLevelModify;
import gui.util.FileUtil;
import gui.util.FramePanelUtil;

public class MReplaceModifyButtonListener implements ActionListener {
private MReplacePanel parentComponent;
	
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
		
		int fileType = 0;
		String selectFileType = (String)(parentComponent.getFileTypeGroup().getSelection().getActionCommand());
		fileType = Integer.parseInt(selectFileType);
		
		List<String> extendNames = FramePanelUtil.getExtendNameList(parentComponent).getSelectedValuesList();
		List<String> nonExtendNames = FramePanelUtil.getNonExtendNameList(parentComponent).getSelectedValuesList();
		
		MultiLevelModify.replaceDirectoryFileModify(file, replace, replaceAs, replaceIndex, fileType, extendNames, nonExtendNames); 
		
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public MReplaceModifyButtonListener(MReplacePanel parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}

