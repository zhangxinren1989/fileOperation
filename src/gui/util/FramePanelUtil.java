package gui.util;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import gui.FMainFrame;
import gui.FrameStatus;
import gui.component.custom.model.FBaseListModel;
import gui.component.left.insterestfunction.FInterestDirectoryNamePanel;
import gui.component.left.modify.FPreNumPostPanel;
import gui.component.left.page.FModifyPage;
import gui.component.right.FFileShowPanel;

public class FramePanelUtil {
	private FramePanelUtil(){}
	
	/**
	 * 获取选择的文件路径
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static String getFilePath(JComponent component)
	{
		return getFilePathSelect(component).getText();
	}
	
	/**
	 * 设置选择的文件路径
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @param path 设置的文件路径
	 */
	public static void setFilePath(JComponent component, String path)
	{
		getFilePathSelect(component).setText(path);
	}
	
	/**
	 * 获取显示选择的文件名的JTextField
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static JTextField getFilePathSelect(JComponent component)
	{
		return getMainFrame(component).getRightPanel().getMainPanel().getFilePathSelect();
	}
	
	/**
	 *  获取文件修改的前缀数字后缀的面板
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static FPreNumPostPanel getPreNumPanel(JComponent component)
	{
		return ((FModifyPage)(getMainFrame(component).getfMenuBar()).getfPage()).getfPreNumPostPanel();
	}
	
	/**
	 * 获取右下文件浏览面板
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static FFileShowPanel getFileShowPanel(JComponent component)
	{
		return getMainFrame(component).getRightPanel().getFileShowPanel();
	}
	
	/**
	 * 获取右上侧弹出的文件选择器
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static JFileChooser getFileChooser(JComponent component)
	{
		return getMainFrame(component).getRightPanel().getMainPanel().getFileChooser();
	}
	
	/**
	 * 获取右上文件扩展名JList
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static JList<String> getExtendNameList(JComponent component)
	{
		return getMainFrame(component).getRightPanel().getMainPanel().getExtendNameList();
	}
	
	/**
	 * 获取右上非文件扩展名JList
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static JList<String> getNonExtendNameList(JComponent component)
	{
		return getMainFrame(component).getRightPanel().getMainPanel().getNoneExtendNameList();
	}
	
	/**
	 * 获取左侧修改文件夹扩展名面板中的被替换为的扩展名
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	public static String getReplaceExtendName(JComponent component)
	{
		return ((FModifyPage)(getMainFrame(component).getfMenuBar()).getfPage()).getfExtendNamePanel().getReplaceAsField().getText();
	}
	
	public static FInterestDirectoryNamePanel getInterestDirectoryNamePanel(JComponent component)
	{
		return null;
	}
	
	/**
	 * 获取主面板，已有其它更简单的方式获取
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @return
	 */
	@Deprecated
	public static FMainFrame getMainFrame(Component component) {
		while (null != component && !FMainFrame.class.isInstance(component)) {
			component = component.getParent();
		}

		return component == null ? null : (FMainFrame) component;
	}

	/**
	 * 设置右下角文件浏览窗口显示目录
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @param path 要显示的目录
	 */
	public static void setFileShowPath(JComponent component, String path) {
		FFileShowPanel fileShowPanel = getFileShowPanel(component);
		JFileChooser fileShow = fileShowPanel.getFileShow();
		fileShow.setCurrentDirectory(new File(path));
	}

	/**
	 * 右下角文件浏览窗口刷新当前目录
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 */
	public static void refreshDirectory(JComponent component) {
		FFileShowPanel fileShowPanel = getFileShowPanel(component);
		JFileChooser fileShow = fileShowPanel.getFileShow();
		fileShow.rescanCurrentDirectory();
	}
	
	/**
	 *  右下角文件浏览窗口刷新上级目录
	 * @param component 程序的一个组件，通过一个组件可以找到其它组件
	 * @param parentPath 要刷新的上级目录
	 */
	public static void refreshParentDirectory(JComponent component, String parentPath)
	{
		FFileShowPanel fileShowPanel = getFileShowPanel(component);
		JFileChooser fileShow = fileShowPanel.getFileShow();
		fileShow.setCurrentDirectory(new File(parentPath));
		fileShow.rescanCurrentDirectory();
	}

	/**
	 * 设置右上显示扩展名组件的内容
	 * @param directory 文件扩展名来源目录
	 * @param parentComponent 程序的一个组件，通过一个组件可以找到其它组件
	 */
	public static void setExtendNameList(File directory, JComponent parentComponent)
	{
		Set<String> nonExtendNames = FileUtil.getExtendNames(directory);
		List<String> extendNames = new ArrayList<>(nonExtendNames);
		
		if(FileUtil.isContainNonExtendNameFile(directory))
		{
			extendNames.add(0, "无扩展名");
		}
		
		if(extendNames.size() > 0)
		{
			extendNames.add(0, "全部");
		}
		
		JList<String> extendNameList = getExtendNameList(parentComponent);
		JList<String> nonExtendNameList = getNonExtendNameList(parentComponent);
		
		FBaseListModel extendNameModel = new FBaseListModel(extendNames);
		FBaseListModel nonExtendNameModel = new FBaseListModel(new ArrayList<>(nonExtendNames));
		extendNameList.setModel(extendNameModel);
		extendNameList.setSelectedIndex(0);
		nonExtendNameList.setModel(nonExtendNameModel);
	}
	
	/**
	 * 将选择的文件置空，将选择的目录置为桌面，将扩展名非扩展名置空，设置是否可以选择文件
	 */
	public static void initFileSelect()
	{
		// 设置选择的文件路径为空
		FMainFrame.getInstance().getRightPanel().getMainPanel().getFilePathSelect().setText("");
		
		// 选择目录置为桌面
		JFileChooser fileShow = FMainFrame.getInstance().getRightPanel().getFileShowPanel().getFileShow();
		File currentDirectory = fileShow.getCurrentDirectory();
		FileSystemView fileSystemView = fileShow.getFileSystemView();
		File homeDirectory = fileSystemView.getHomeDirectory();
		
		try {
			if(!homeDirectory.getCanonicalPath().equals(currentDirectory.getCanonicalPath()))
			{
				fileShow.setCurrentDirectory(homeDirectory);
			}
		} catch (IOException e) {
			fileShow.setCurrentDirectory(homeDirectory);
			e.printStackTrace();
		}
		
		// 扩展名非扩展名置空
		JList<String> extendNameList = FMainFrame.getInstance().getRightPanel().getMainPanel().getExtendNameList();
		JList<String> noneExtendNameList = FMainFrame.getInstance().getRightPanel().getMainPanel().getNoneExtendNameList();
		extendNameList.setModel(new FBaseListModel(new ArrayList<>()));
		noneExtendNameList.setModel(new FBaseListModel(new ArrayList<>()));
		
		// 设置是否可以选择文件
		FMainFrame.getInstance().getRightPanel().getMainPanel().getSelectButton().setEnabled(FrameStatus.fileSelectEnable);
		FMainFrame.getInstance().getfMenuBar().getOpen().setEnabled(FrameStatus.fileSelectEnable);
	}
}
