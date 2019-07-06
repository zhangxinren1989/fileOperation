package gui.component.left.practicefunction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.listener.practicefunction.PLongPathFileDeleteListener;
import gui.listener.practicefunction.PLongPathFileSelectListener;

public class LongPathFileDeletePanel extends JPanel {
	private static final long serialVersionUID = 6576616487192343121L;

	private JLabel tips = new JLabel("tips:删除一个文件夹及下面的所有内容，可用于过长路径的删除");
	private JButton deleteButton = new JButton("删除过长路径");
	private JButton longPathFileButton = new JButton("选择文件夹");
	private JFileChooser longPathFileChoosor = new JFileChooser();
	
	public LongPathFileDeletePanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("删除无法删除的过长路径"));
		add(tips);
		add(longPathFileButton);
		add(deleteButton);
		deleteButton.addActionListener(new PLongPathFileDeleteListener(this));
		longPathFileButton.addActionListener(new PLongPathFileSelectListener(longPathFileChoosor));
		parentComponent.add(this);
	}
}
