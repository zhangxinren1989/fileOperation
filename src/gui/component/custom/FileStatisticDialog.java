
package gui.component.custom;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TrayIcon.MessageType;
import java.io.File;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import gui.FMainFrame;
import gui.component.custom.model.FBaseTableModel;
import gui.fileoperation.practice.PracticeFunction;
import gui.util.FramePanelUtil;
import gui.util.UIUtil;

public class FileStatisticDialog extends JDialog
{
	private JScrollPane scrollPane;
	private JTable statisticTable;
	private JLabel statisticInfo;
	private File statisticDir;
	
	public FileStatisticDialog()
	{
		super(FMainFrame.getInstance(), "文件夹统计信息", true);
		setSize(600, 450);
		
		String filePath = FramePanelUtil.getFilePath(FMainFrame.getInstance().getRightPanel());
		
		if(null == filePath || "".equals(filePath))
		{
			JOptionPane.showMessageDialog(FMainFrame.getInstance(), "请先选择待操作的文件夹", "警告", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		this.statisticDir = new File(filePath);
		Map<String, Object[][]> statisticInfoMap = PracticeFunction.statisticFileInfo(statisticDir);
		String[] tableHeader = {"序号", "类型", "数量", "总大小(b)", "最大(b)", "最小(b)", "平均(b)", ""};
		
		setLayout(new BorderLayout());
		
		statisticTable = new JTable();
		statisticTable.setRowHeight(30);
		FBaseTableModel model = new FBaseTableModel(statisticInfoMap.get("rows"), tableHeader);
		statisticTable.setModel(model);
		RowSorter<FBaseTableModel> sorter = new TableRowSorter<FBaseTableModel>(model);
		statisticTable.setRowSorter(sorter);
		UIUtil.FitTableColumns(statisticTable);
		scrollPane = new JScrollPane(statisticTable);
		add(scrollPane, BorderLayout.CENTER);
		
		Object[][] totalInfo = statisticInfoMap.get("totalInfo");
		statisticInfo = new JLabel("<html>总文件个数：" + totalInfo[0][1] + ", 总文件夹个数：" + totalInfo[0][0] + ", 文件总大小：" + totalInfo[0][2] + "b, <br>"
				+ "文件平均大小：" + totalInfo[0][3] + "b, 最大文件大小：" + totalInfo[0][4] + "b, 最小文件大小：" + totalInfo[0][5] + "b</html>");
		add(statisticInfo, BorderLayout.SOUTH);
		
		setLocationRelativeTo(FMainFrame.getInstance());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static void showFileStaticDialog()
	{
		new FileStatisticDialog();
	}

	public JScrollPane getScrollPane()
	{
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane)
	{
		this.scrollPane = scrollPane;
	}

	public JLabel getStatisticInfo()
	{
		return statisticInfo;
	}

	public void setStatisticInfo(JLabel statisticInfo)
	{
		this.statisticInfo = statisticInfo;
	}

	public File getStatisticDir()
	{
		return statisticDir;
	}

	public void setStatisticDir(File statisticDir)
	{
		this.statisticDir = statisticDir;
	}
	
}

