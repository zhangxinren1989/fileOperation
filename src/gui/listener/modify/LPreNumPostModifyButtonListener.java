package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

import gui.component.left.modify.FPreNumPostPanel;
import gui.fileoperation.modify.OneLevelModify;
import gui.util.FileUtil;
import gui.util.FramePanelUtil;

public class LPreNumPostModifyButtonListener implements ActionListener {
	private FPreNumPostPanel parentComponent;
	
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
		
		List<String> extendNames = FramePanelUtil.getExtendNameList(parentComponent).getSelectedValuesList();
		List<String> nonExtendNames = FramePanelUtil.getNonExtendNameList(parentComponent).getSelectedValuesList();
		if(extendNames.isEmpty())
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "请选择要处理的扩展名", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(!FileUtil.checkLegalFileName(pre + suf))
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件名非法", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		OneLevelModify.preNumPostModify(file, pre, suf, start, step, extendNames, nonExtendNames);
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public LPreNumPostModifyButtonListener(FPreNumPostPanel parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
