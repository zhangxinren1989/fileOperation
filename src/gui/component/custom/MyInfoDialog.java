package gui.component.custom;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gui.FMainFrame;

public class MyInfoDialog extends JDialog {
	private static MyInfoDialog instance;
	JLabel zfb = new JLabel();
	JLabel programer = new JLabel("作者：zhangxinren QQ：2589071709 QQ群：699041094");
    JLabel info = new JLabel("如果有要求,意见或bug请联系我，欢迎打赏");
    ImageIcon background =new ImageIcon(MyInfoDialog.class.getResource("/resource/image/zfb.jpg"));
	
	public MyInfoDialog()
	{
		super(FMainFrame.getInstance(), "我的信息", true);
		setSize(360, 420);
		
		zfb.setSize(340, 340);
        background.setImage(
      		  background.getImage().
      		  getScaledInstance(background.getIconWidth(),background.getIconHeight(), Image.SCALE_DEFAULT)); 
        zfb.setIcon(background);
        
        add(programer, BorderLayout.NORTH);
        add(zfb, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
        programer.setHorizontalAlignment(SwingConstants.CENTER);
        zfb.setHorizontalAlignment(SwingConstants.CENTER);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        programer.setVerticalAlignment(SwingConstants.CENTER);
        zfb.setVerticalAlignment(SwingConstants.CENTER);
        info.setVerticalAlignment(SwingConstants.CENTER);
        
        setLocationRelativeTo(FMainFrame.getInstance());
	}
	
	public static void showMyInfo()
	{
		if(instance == null)
		{
			instance = new MyInfoDialog();
		}
		
		if(!instance.isVisible())
		{
			instance.setVisible(true);
		}
	}
}
