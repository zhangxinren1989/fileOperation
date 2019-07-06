package gui.util;

import java.awt.Font;
import java.awt.Image;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class InitUtil {
	public static final Font defaultFont = new Font("微软雅黑", Font.PLAIN, 12);
	
	private InitUtil() {
	}

	/**
	 * 设置全局字体
	 * @param font 要设置的字体
	 */
	public static void initGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	/**
	 * 获取程序图标
	 * @param path 图标路径
	 * @param width 图标宽度
	 * @param height 图标高度
	 * @return
	 */
	public static ImageIcon getImageIcon(String path, int width, int height) {
		if (width == 0 || height == 0) {
			return new ImageIcon(InitUtil.class.getResource(path));
		}
		ImageIcon icon = new ImageIcon(InitUtil.class.getResource(path));
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}
	
	/**
	 * 有些组件的字体不能通过设置全局字体改变，这里需要对这些组件手动设置一下字体
	 * @param components 要设置字体的组件
	 */
	public static void setDefaultFont(JComponent... components)
	{
		if(null != components)
		{
			for(JComponent component: components)
			{
				component.setFont(defaultFont);
			}
		}
	}
	
	/**
	 * 把右下角文件浏览器创建新文件夹图标给禁用掉
	 * @param fileChooser 右下角文件选择浏览器
	 */
	public static void disableFileChooserCreateFile(JFileChooser fileChooser)
	{
		JPanel panel1 = (JPanel)(fileChooser.getComponent(0));
		JPanel panel2 = (JPanel)(panel1.getComponent(0));
		JButton createFileButton = (JButton)panel2.getComponent(4);
		createFileButton.setEnabled(false);
	}
}
