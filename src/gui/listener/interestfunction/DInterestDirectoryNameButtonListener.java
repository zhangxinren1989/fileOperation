package gui.listener.interestfunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import gui.FMainFrame;
import gui.component.left.insterestfunction.FInterestDirectoryNamePanel;
import gui.constant.FileConstant;
import gui.constant.InterestConstant;
import gui.fileoperation.interest.InterestFunction;
import gui.util.FramePanelUtil;

public class DInterestDirectoryNameButtonListener implements ActionListener {
	private FInterestDirectoryNamePanel parentComponent;
	
	public DInterestDirectoryNameButtonListener(FInterestDirectoryNamePanel parentComponent) {
		this.parentComponent = parentComponent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = FramePanelUtil.getFilePath(parentComponent);
		int result = 0;

		if (null == filePath || filePath.length() == 0) {
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "没有选择文件夹", "警告",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		File file = new File(filePath);
		if(!file.exists() || file.isDirectory())
		{
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "请选择txt文件", "警告",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		result = InterestFunction.wordDirectoryInterest(file);
		
		if(result < 0)
		{
			if(result == -1)
			{
				JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件中含不可以做文件夹名的字符", "警告",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(result == -2)
			{
				JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "创建趣味文件夹失败", "警告",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(result == -3)
			{
				JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "读取文件异常", "警告",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(result == -4)
			{
				JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "文件内容为空", "警告",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		else
		{
			String generateDirectoryName = "";
			if(result == 0)
			{
				generateDirectoryName = file.getParent() + File.separator + InterestConstant.INTEREST_DIRECTORY_STRING;
			}
			else
			{
				generateDirectoryName = file.getParent() + File.separator + InterestConstant.INTEREST_DIRECTORY_STRING + (result);
			}
			
			FramePanelUtil.setFilePath(parentComponent, generateDirectoryName);
			FramePanelUtil.setFileShowPath(parentComponent, generateDirectoryName);
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "生成成功", "信息",
					JOptionPane.INFORMATION_MESSAGE);
			FramePanelUtil.refreshParentDirectory(parentComponent, generateDirectoryName);
			return;
		}
	}

}
