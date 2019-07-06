package gui.listener.modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import gui.fileoperation.modify.OneLevelModify;
import gui.util.FramePanelUtil;

public class LExtendNameModifyButtonListener implements ActionListener {
	private JComponent parentComponent;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String replaceAs = FramePanelUtil.getReplaceExtendName(parentComponent);
		String filePath = FramePanelUtil.getFilePath(parentComponent);
		if(null == filePath || filePath.length() == 0)
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "没有选择文件夹", "警告", JOptionPane.WARNING_MESSAGE);
			return;
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
		
		OneLevelModify.extendNameModify(file, replaceAs, extendNames, nonExtendNames);
		FramePanelUtil.setExtendNameList(file, parentComponent);
		FramePanelUtil.refreshDirectory(parentComponent);
		JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

	public LExtendNameModifyButtonListener(JComponent parentComponent)
	{
		this.parentComponent = parentComponent;
	}
}
