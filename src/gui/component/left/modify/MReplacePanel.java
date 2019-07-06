package gui.component.left.modify;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gui.listener.modify.MReplaceModifyButtonListener;
import gui.util.InitUtil;

public class MReplacePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel replaceLabel = new JLabel("替换"); 
	private JTextField replaceField = new JTextField(27);
	private JLabel replaceAsLabel = new JLabel("替换为");
	private JTextField replaceAsField = new JTextField(25);
	private JLabel tips = new JLabel("tips:可以使用正则");
	private JLabel placeLabel = new JLabel("替换位置");
	private JTextField placeField = new JTextField("0", 5);
	private JLabel tips2 = new JLabel("tips:替换位置为大于0的数字,否则替换全部");
	private JLabel modifyFileTypeLabel = new JLabel("修改文件（夹）类型");
	private ButtonGroup fileTypeGroup = new ButtonGroup();
	private JButton modifyButton = new JButton("修改多级文件（夹）名");
	
	public MReplacePanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("字符串替换改文件（夹）名"));
		add(replaceLabel);
		add(replaceField);
		add(replaceAsLabel);
		add(replaceAsField);
		add(placeLabel);
		add(placeField);
		add(tips2);
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
		
		add(modifyButton);
		add(tips);
		
		modifyButton.addActionListener(new MReplaceModifyButtonListener(this));
		
		InitUtil.setDefaultFont(onlyDirectory, fileDirectory, onlyFile);
		
		parentComponent.add(this);
	}

	public JTextField getReplaceField() {
		return replaceField;
	}

	public void setReplaceField(JTextField replaceText) {
		this.replaceField = replaceText;
	}

	public JTextField getReplaceAsField() {
		return replaceAsField;
	}

	public void setReplaceAsField(JTextField replaceAsField) {
		this.replaceAsField = replaceAsField;
	}

	public JTextField getPlaceField() {
		return placeField;
	}

	public void setPlaceField(JTextField placeField) {
		this.placeField = placeField;
	}

	public ButtonGroup getFileTypeGroup() {
		return fileTypeGroup;
	}

	public void setFileTypeGroup(ButtonGroup fileTypeGroup) {
		this.fileTypeGroup = fileTypeGroup;
	}
}
