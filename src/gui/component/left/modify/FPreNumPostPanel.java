package gui.component.left.modify;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.modify.LPreNumPostModifyButtonListener;

public class FPreNumPostPanel extends JPanel {
	private static final long serialVersionUID = -1808500177322647121L;
	private JLabel fileNamePrefixLabel = new JLabel("文件前缀"); 
	private JTextField fileNamePrefixText = new JTextField(25);
	private JLabel startNumLabel = new JLabel("开始数字");
	private JTextField startNumField = new JTextField(10);
	private JLabel stepLabel = new JLabel("步长");
	private JTextField stepField = new JTextField(10);
	private JLabel fileNameSuffixLabel = new JLabel("文件后缀");
	private JTextField fileNameSuffixText = new JTextField(25);
	private JButton modifyButton = new JButton("修改文件名");
	
	public FPreNumPostPanel(JComponent parentComponent)
	{
		//setSize(400, 200);
		setBorder(BorderFactory.createTitledBorder("前缀+数字+后缀改文件名"));
		add(fileNamePrefixLabel);
		add(fileNamePrefixText);
		add(startNumLabel);
		add(startNumField);
		add(stepLabel);
		add(stepField);
		add(fileNameSuffixLabel);
		add(fileNameSuffixText);
		add(modifyButton);
		modifyButton.addActionListener(new LPreNumPostModifyButtonListener(this));
		
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
}
