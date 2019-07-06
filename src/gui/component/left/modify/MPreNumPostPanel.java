package gui.component.left.modify;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.listener.modify.MPreNumPostModifyButtonListener;
import gui.util.InitUtil;

public class MPreNumPostPanel extends JPanel {
	private static final long serialVersionUID = -4031371904607864900L;
	private JLabel fileNamePrefixLabel = new JLabel("文件前缀"); 
	private JTextField fileNamePrefixText = new JTextField(25);
	private JLabel startNumLabel = new JLabel("开始数字");
	private JTextField startNumField = new JTextField(10);
	private JLabel stepLabel = new JLabel("步长");
	private JTextField stepField = new JTextField(10);
	private JLabel fileNameSuffixLabel = new JLabel("文件后缀");
	private JTextField fileNameSuffixText = new JTextField(25);
	private JLabel modifyFileTypeLabel = new JLabel("修改文件（夹）类型");
	private ButtonGroup fileTypeGroup = new ButtonGroup();
	private JLabel renameRegularLabel = new JLabel("文件（夹）改名规则");
	private JCheckBox independentFileDirectory = new JCheckBox("文件（夹）独立命名");
	private JCheckBox independentDirectoryLevel = new JCheckBox("每一级独立命名");
	private JButton modifyButton = new JButton("修改多级文件（夹）名");
	
	public MPreNumPostPanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("前缀+数字+后缀改多级文件（夹）名"));
		add(fileNamePrefixLabel);
		add(fileNamePrefixText);
		add(startNumLabel);
		add(startNumField);
		add(stepLabel);
		add(stepField);
		add(fileNameSuffixLabel);
		add(fileNameSuffixText);
		add(modifyFileTypeLabel);
		JRadioButton onlyFile = new JRadioButton("仅文件");
		onlyFile.setSelected(true);
		JRadioButton onlyDirectory = new JRadioButton("仅文件夹");
		JRadioButton fileDirectory = new JRadioButton("文件和文件夹");
		onlyFile.setActionCommand("1");//仅处理文件
		onlyDirectory.setActionCommand("2");//仅处理文件夹
		fileDirectory.setActionCommand("3");//处理文件和文件夹
		fileTypeGroup.add(onlyFile);
		fileTypeGroup.add(onlyDirectory);
		fileTypeGroup.add(fileDirectory);
		add(onlyFile);
		add(onlyDirectory);
		add(fileDirectory);
		add(renameRegularLabel);
		add(independentFileDirectory);
		add(independentDirectoryLevel);
		add(modifyButton);
		modifyButton.addActionListener(new MPreNumPostModifyButtonListener(this));
		
		InitUtil.setDefaultFont(independentFileDirectory, independentDirectoryLevel, onlyDirectory, fileDirectory, onlyFile);
		parentComponent.add(this);
	}

	public JTextField getFileNamePrefixText() {
		return fileNamePrefixText;
	}

	public void setFileNamePrefixText(JTextField fileNamePrefixText) {
		this.fileNamePrefixText = fileNamePrefixText;
	}

	public JTextField getStartNumField() {
		return startNumField;
	}

	public void setStartNumField(JTextField startNumField) {
		this.startNumField = startNumField;
	}

	public JTextField getStepField() {
		return stepField;
	}

	public void setStepField(JTextField stepField) {
		this.stepField = stepField;
	}

	public JTextField getFileNameSuffixText() {
		return fileNameSuffixText;
	}

	public void setFileNameSuffixText(JTextField fileNameSuffixText) {
		this.fileNameSuffixText = fileNameSuffixText;
	}

	public ButtonGroup getFileTypeGroup() {
		return fileTypeGroup;
	}

	public void setFileTypeGroup(ButtonGroup fileTypeGroup) {
		this.fileTypeGroup = fileTypeGroup;
	}

	public JCheckBox getIndependentFileDirectory() {
		return independentFileDirectory;
	}

	public void setIndependentFileDirectory(JCheckBox independentFileDirectory) {
		this.independentFileDirectory = independentFileDirectory;
	}

	public JCheckBox getIndependentDirectoryLevel() {
		return independentDirectoryLevel;
	}

	public void setIndependentDirectoryLevel(JCheckBox independentDirectoryLevel) {
		this.independentDirectoryLevel = independentDirectoryLevel;
	}
}
