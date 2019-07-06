package gui.component.right;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileSystemView;

import gui.listener.LSelectButtonListener;

public class FMainPanel extends JPanel {
	private static final long serialVersionUID = 8681475628430743033L;
	private JFileChooser fileChooser = new JFileChooser();;
	private JTextField filePathSelect = new JTextField(30);
	// TODO 
	private JLabel extendNameLabel = new JLabel("待处理文件扩展名");
	private JList<String> extendNameList;
	private JLabel noneExtendNameLabel = new JLabel("不处理文件扩展名");
	private JList<String> noneExtendNameList;
	private JButton selectButton = new JButton("选择文件夹");
	
	public FMainPanel(JComponent parentCompoent)
	{
		//add(fileChooser);
		add(selectButton);
		add(filePathSelect);
		filePathSelect.setEditable(false);
		selectButton.addActionListener(new LSelectButtonListener(this));
		
		// TODO test
		add(extendNameLabel);
		extendNameList = new JList<>();
		extendNameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane extendNameScrollPanel = new JScrollPane(extendNameList);
		extendNameScrollPanel.setPreferredSize(new Dimension(100, 130));
		// 设置list大小
		//extendNameList.setPreferredSize(new Dimension(80, 110));
		// 设置list行数
		extendNameList.setVisibleRowCount(5);
		add(extendNameScrollPanel);
		add(noneExtendNameLabel);
		noneExtendNameList = new JList<>();
		noneExtendNameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane noneExtendNameScrollPanel = new JScrollPane(noneExtendNameList);
		noneExtendNameScrollPanel.setPreferredSize(new Dimension(100, 130));
		// 设置list大小
		//noneExtendNameList.setPreferredSize(new Dimension(80, 110));
		// 设置list行数
		noneExtendNameList.setVisibleRowCount(5);
		add(noneExtendNameScrollPanel);
		
		//初始化当前路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File homeFile =fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
        fileChooser.setCurrentDirectory(homeFile);
        //初始化选择模式
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        
        parentCompoent.add(this);
	}

	public JFileChooser getFileChooser() {
		return fileChooser;
	}

	public void setFileChooser(JFileChooser fileChooser) {
		this.fileChooser = fileChooser;
	}

	public JTextField getFilePathSelect() {
		return filePathSelect;
	}

	public void setFilePathSelect(JTextField filePathSelect) {
		this.filePathSelect = filePathSelect;
	}

	public JList<String> getExtendNameList() {
		return extendNameList;
	}

	public void setExtendNameList(JList<String> extendNameList) {
		this.extendNameList = extendNameList;
	}

	public JList<String> getNoneExtendNameList() {
		return noneExtendNameList;
	}

	public void setNoneExtendNameList(JList<String> noneExtendNameList) {
		this.noneExtendNameList = noneExtendNameList;
	}

	public JButton getSelectButton() {
		return selectButton;
	}

	public void setSelectButton(JButton selectButton) {
		this.selectButton = selectButton;
	}
	
	
}
