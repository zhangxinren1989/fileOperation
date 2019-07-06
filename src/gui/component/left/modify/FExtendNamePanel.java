package gui.component.left.modify;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.listener.modify.LExtendNameModifyButtonListener;

public class FExtendNamePanel extends JPanel {
	private static final long serialVersionUID = -7036394553443384132L;
	private JLabel replaceAsLabel = new JLabel("替换为");
	private JTextField replaceAsField = new JTextField(10);
	private JButton modifyButton = new JButton("修改扩展名");
	
	public FExtendNamePanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("文件扩展名修改"));
		add(replaceAsLabel);
		add(replaceAsField);
		add(modifyButton);
		modifyButton.addActionListener(new LExtendNameModifyButtonListener(this));
		parentComponent.add(this);
	}

	public JTextField getReplaceAsField() {
		return replaceAsField;
	}

	public void setReplaceAsField(JTextField replaceAsField) {
		this.replaceAsField = replaceAsField;
	}
}
