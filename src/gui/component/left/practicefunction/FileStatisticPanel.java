
package gui.component.left.practicefunction;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.listener.practicefunction.PStatisticFileListener;
import gui.listener.practicefunction.PStatisticFileSelectListener;

public class FileStatisticPanel extends JPanel
{
	private static final long serialVersionUID = 1750460266017351378L;

	private JLabel tips = new JLabel("tips:统计一个文件夹下面的所有文件及类型,总大小,平均大小等");
	private JButton statisticsButton = new JButton("统计");
	private JButton selectFileButton = new JButton("选择文件夹");
	private JFileChooser statisticFileChoosor = new JFileChooser();
	
	public FileStatisticPanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("统计文件类型，数量，大小等"));
		add(tips);
		add(selectFileButton);
		add(statisticsButton);
		statisticsButton.addActionListener(new PStatisticFileListener(this));
		selectFileButton.addActionListener(new PStatisticFileSelectListener(statisticFileChoosor));
		parentComponent.add(this);
	}
}

