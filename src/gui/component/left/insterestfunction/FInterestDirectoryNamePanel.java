package gui.component.left.insterestfunction;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.listener.interestfunction.DInterestDirectoryNameButtonListener;
import gui.listener.interestfunction.FInterestWordFileButtonListener;

public class FInterestDirectoryNamePanel extends JPanel{
	private static final long serialVersionUID = 3213401650707308072L;
	
	private JLabel tips = new JLabel("tips:生成一个趣味+数字文件夹，不要用文件夹不支持字符");
	private JButton selectWordFileButton = new JButton("选择文字文件");
	private JFileChooser selectWordFileChoosor = new JFileChooser();
	private JButton generateButton = new JButton("生成文件夹");
	private File wordFile;
	
	public FInterestDirectoryNamePanel(JComponent parentComponent)
	{
		setBorder(BorderFactory.createTitledBorder("生成一组文件夹：使用指定文件的文字"));
		add(tips);
		add(selectWordFileButton);
		selectWordFileButton.addActionListener(new FInterestWordFileButtonListener(selectWordFileChoosor));
		add(generateButton);
		generateButton.addActionListener(new DInterestDirectoryNameButtonListener(this));
		
		
		parentComponent.add(this);
	}

	public JButton getSelectWordFileButton() {
		return selectWordFileButton;
	}

	public void setSelectWordFileButton(JButton selectWordFileButton) {
		this.selectWordFileButton = selectWordFileButton;
	}

	public JFileChooser getSelectWordFileChoosor() {
		return selectWordFileChoosor;
	}

	public void setSelectWordFileChoosor(JFileChooser selectWordFileChoosor) {
		this.selectWordFileChoosor = selectWordFileChoosor;
	}

	public JButton getGenerateButton() {
		return generateButton;
	}

	public void setGenerateButton(JButton generateButton) {
		this.generateButton = generateButton;
	}

	public File getWordFile() {
		return wordFile;
	}

	public void setWordFile(File wordFile) {
		this.wordFile = wordFile;
	}
}
