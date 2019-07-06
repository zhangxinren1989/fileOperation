package gui;

import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.constant.CommonConstant;
import gui.util.InitUtil;

/**
 * TODO:一键修改文件名 一键创建指定名称文件
 */
public class FMainFrame extends JFrame {
	private static final long serialVersionUID = 8819277520034994594L;
	private FLeftPanel leftPanel;
	private FRightPanel rightPanel;
	private FMenuBar fMenuBar;
	private FSystemTray systemTray;
	private static FMainFrame instance;
	
	public FMainFrame() {
		ImageIcon ico = InitUtil.getImageIcon(CommonConstant.ICON_IMAGE, 128, 128);
		setIconImage(ico.getImage());

		setSize(910, 655);
		setLayout(null);
		
		leftPanel = new FLeftPanel();
		add(leftPanel);
		leftPanel.setBounds(0, 0, 400, 610);
		
		rightPanel = new FRightPanel();
		add(rightPanel);
		rightPanel.setBounds(400, 0, 500, 610);
		
		fMenuBar = new FMenuBar();
		setJMenuBar(fMenuBar);
		
		try {
			systemTray = new FSystemTray(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		instance = this;
		
		init();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void init()
	{
		rightPanel.init();
		fMenuBar.init();
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		InitUtil.initGlobalFont(InitUtil.defaultFont); // 统一设置字体
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		// SwingUtilities.updateComponentTreeUI(new FMainFrame());
		new FMainFrame();
	}

	public FLeftPanel getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(FLeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public FRightPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(FRightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public FMenuBar getfMenuBar() {
		return fMenuBar;
	}

	public void setfMenuBar(FMenuBar fMenuBar) {
		this.fMenuBar = fMenuBar;
	}

	public static FMainFrame getInstance() {
		return instance;
	}

	public FSystemTray getSystemTray() {
		return systemTray;
	}

	public void setSystemTray(FSystemTray systemTray) {
		this.systemTray = systemTray;
	}

}
