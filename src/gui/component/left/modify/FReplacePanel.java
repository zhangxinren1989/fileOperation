package gui.component.left.modify;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.modify.LReplaceModifyButtonListener;

public class FReplacePanel extends JPanel {
	private static final long serialVersionUID = 4249357593730943831L;
	private JLabel replaceLabel = new JLabel("替换");
	private JTextField replaceField = new JTextField(20);
	private JLabel tips = new JLabel("tips:支持正则");
	private JLabel replaceAsLabel = new JLabel("替换为");
	private JTextField replaceAsField = new JTextField(28);
	private JLabel placeLabel = new JLabel("替换位置");
	private JTextField placeField = new JTextField(5);
	private JLabel tips2 = new JLabel("tips:替换位置为大于0的数字,否则替换全部");
	private JButton modifyButton = new JButton("修改文件名");
	
	public FReplacePanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("字符串替换改文件名"));
		//setSize(400, 200);
		add(replaceLabel);
		add(replaceField);
		add(tips);
		add(replaceAsLabel);
		add(replaceAsField);
		add(placeLabel);
		placeField.setText("0");
		add(placeField);
		add(tips2);
		add(modifyButton);
		modifyButton.addActionListener(new LReplaceModifyButtonListener(this));
		
		parentComponent.add(this);
	}

	public JTextField getReplaceField() {
		return replaceField;
	}

	public void setReplaceField(JTextField replaceField) {
		this.replaceField = replaceField;
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
}
