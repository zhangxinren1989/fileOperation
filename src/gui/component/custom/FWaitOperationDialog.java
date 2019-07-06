package gui.component.custom;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import gui.listener.custom.FProgressBarCompleteListener;
import gui.thread.ProgressBarThread;
import gui.util.FramePanelUtil;

public class FWaitOperationDialog extends JDialog {
	private static final long serialVersionUID = 5466294026812856321L;
	private static JLabel label;
	public static JProgressBar progressBar;
	public static JButton button;
	public static boolean completed = false;
	public static FWaitOperationDialog instance;
	
	public FWaitOperationDialog(JComponent parentComponent) {
		super(FramePanelUtil.getMainFrame(parentComponent), "操作中", true);
		label = new JLabel("请耐心等待操作完成。。。");
		progressBar = new JProgressBar();
		button = new JButton("完成");
		button.setEnabled(false);
		JPanel container = (JPanel) getContentPane();
		container.setLayout(new GridLayout(3, 1));
		JPanel panel1= new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel2= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel3= new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(label);
		panel2.add(progressBar);
		panel3.add(button);
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
		
		progressBar.setStringPainted(true);
		button.addActionListener(new FProgressBarCompleteListener());
//		setLocationRelativeTo(FramePanelUtil.getMainFrame(parentComponent));
		setSize(200, 200);
		setUndecorated(true);
		setResizable(false);
	}

	public static void showWaitDialog(JComponent parentComponent)
	{
		if(null == instance)
		{
			instance = new FWaitOperationDialog(parentComponent);
		}
		
		progressBar.setString("操作进行中...");
		progressBar.setIndeterminate(true);
		button.setEnabled(false);

		new Thread(new ProgressBarThread()).start();
		
		instance.setLocationRelativeTo(FramePanelUtil.getMainFrame(parentComponent));
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		instance.setVisible(true);
	}
	
	public static void hideWaitDialog()
	{
		instance.setVisible(false);
	}
}
