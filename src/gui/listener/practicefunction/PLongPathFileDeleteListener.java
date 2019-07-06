package gui.listener.practicefunction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import gui.component.custom.FWaitOperationDialog;
import gui.component.left.practicefunction.LongPathFileDeletePanel;
import gui.util.FilePracticeUtil;
import gui.util.FramePanelUtil;

public class PLongPathFileDeleteListener implements ActionListener {
	private LongPathFileDeletePanel parentComponent;

	public PLongPathFileDeleteListener(LongPathFileDeletePanel parentComponent) {
		this.parentComponent = parentComponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String filePath = FramePanelUtil.getFilePath(parentComponent);

		if (null == filePath || filePath.length() == 0) {
			JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent), "没有选择文件夹", "警告",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String parentPath = new File(filePath).getParent();

		FWaitOperationDialog.completed = false;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				FWaitOperationDialog.showWaitDialog(parentComponent);
				FramePanelUtil.setFilePath(parentComponent, parentPath);
				FramePanelUtil.setExtendNameList(new File(parentPath), parentComponent);
				FramePanelUtil.refreshParentDirectory(parentComponent, parentPath);
			}
		}).start();;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				FilePracticeUtil.delFolder(filePath);
				FWaitOperationDialog.completed = true;
			}
		}).start();;

		// JOptionPane.showMessageDialog(FramePanelUtil.getMainFrame(parentComponent),
		// "修改完成", "成功", JOptionPane.INFORMATION_MESSAGE);
	}

}
