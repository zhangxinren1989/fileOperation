package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.component.left.modify.DPreNumPostPanel;
import gui.fileoperation.modify.OneLevelModify;
import gui.util.FileUtil;
import gui.util.FramePanelUtil;

public class DPreNumPostModifyButtonListener implements ActionListener {
	private DPreNumPostPanel parentComponent;
	
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
		
		File file = new File(filePath);
		
		if(!file.exists())
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件夹不存在", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(!FileUtil.checkLegalFileName(pre + suf))
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件名非法", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		OneLevelModify.preNumPostDirectoryModify(file, pre, suf, start, step);
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public DPreNumPostModifyButtonListener(DPreNumPostPanel parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
