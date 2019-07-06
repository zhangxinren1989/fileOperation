package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import gui.component.left.modify.MPreNumPostPanel;
import gui.fileoperation.modify.MultiLevelModify;
import gui.util.FileUtil;
import gui.util.FramePanelUtil;

public class MPreNumPostModifyButtonListener implements ActionListener {
	private MPreNumPostPanel parentComponent;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = FramePanelUtil.getFilePath(parentComponent);
		if(null == filePath || filePath.length() == 0)
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "没有选择文件夹", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int step = 1;
		int start = 0;
		String pre = "";
		String suf = "";
		int fileType = 0;// 处理文件或文件夹或文件和文件夹
		boolean seperateFileDirectory = false;
		boolean seperateEveryLevel = false;
		
		try
		{
			step = Integer.parseInt(parentComponent.getStepField().getText());
			if(step == 0)
			{
				step = 1;
			}
		}
		catch(NumberFormatException ex)
		{
			ex.printStackTrace();
		}
		
		try
		{
			start = Integer.parseInt(parentComponent.getStartNumField().getText());
		}
		catch(NumberFormatException ex)
		{
			ex.printStackTrace();
		}
		
		pre = parentComponent.getFileNamePrefixText().getText();
		if(null == pre)
		{
			pre = "";
		}
		
		suf = parentComponent.getFileNameSuffixText().getText();
		if(null == suf)
		{
			suf = "";
		}
		
		if(!"".equals(pre + suf) && !FileUtil.checkLegalFileName(pre + suf))
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件名非法", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String selectFileType = (String)(parentComponent.getFileTypeGroup().getSelection().getActionCommand());
		fileType = Integer.parseInt(selectFileType);
		seperateFileDirectory = parentComponent.getIndependentFileDirectory().isSelected();
		seperateEveryLevel = parentComponent.getIndependentDirectoryLevel().isSelected();
		
		File file = new File(filePath);
		
		if(!file.exists())
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件夹不存在", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		List<String> extendNames = FramePanelUtil.getExtendNameList(parentComponent).getSelectedValuesList();
		List<String> nonExtendNames = FramePanelUtil.getNonExtendNameList(parentComponent).getSelectedValuesList();
		//if(extendNames.isEmpty())
		//{
		//	JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "请选择要处理的扩展名", "警告", JOptionPane.WARNING_MESSAGE);
		//	return;
		//}
		
		if(!FileUtil.checkLegalFileName(pre + suf))
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件名非法", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		MultiLevelModify.preNumPostModify(file, pre, suf, start, step, fileType, seperateFileDirectory, seperateEveryLevel, extendNames, nonExtendNames);
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public MPreNumPostModifyButtonListener(MPreNumPostPanel parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
