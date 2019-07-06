package gui;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FSystemTray {
	public FSystemTray(FMainFrame mainFrame) throws IOException {
		/*
		 * 添加系统托盘
		 */
		if (SystemTray.isSupported()) {
			// 获取当前平台的系统托盘
			SystemTray tray = SystemTray.getSystemTray();
			// 加载一个图片用于托盘图标的显示
			Image image = ImageIO.read(FSystemTray.class.getResource("/resource/image/ico.jpg"));
			// 创建点击图标时的弹出菜单
			PopupMenu popupMenu = new PopupMenu();
			MenuItem openItem = new MenuItem("open");
			MenuItem exitItem = new MenuItem("exit");
			popupMenu.add(openItem);
			popupMenu.add(exitItem);

			openItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// 点击打开菜单时显示窗口
					if(mainFrame.getExtendedState() == Frame.ICONIFIED){
						mainFrame.setExtendedState(Frame.NORMAL);
					}
				}
			});

			exitItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { // 点击退出菜单时退出程序
					System.exit(0);
				}
			});

			// 创建一个托盘图标
			TrayIcon trayIcon = new TrayIcon(image, "文件操作", popupMenu);
			// 托盘图标自适应尺寸
			trayIcon.setImageAutoSize(true);

			trayIcon.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("托盘图标被右键点击");
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					switch (e.getButton()) {
					case MouseEvent.BUTTON1: {
						System.out.println("托盘图标被鼠标左键被点击");
						if(mainFrame.getExtendedState() == Frame.ICONIFIED){
							mainFrame.setExtendedState(Frame.NORMAL);
						}
						break;
					}
					case MouseEvent.BUTTON2: {
						System.out.println("托盘图标被鼠标中键被点击");
						break;
					}
					case MouseEvent.BUTTON3: {
						System.out.println("托盘图标被鼠标右键被点击");
						break;
					}
					default: {
						break;
					}
					}
				}
			});

			// 添加托盘图标到系统托盘
			try {
				tray.add(trayIcon);
			} catch (AWTException e) {
				e.printStackTrace();
			}
		} else {
	        System.out.println("当前系统不支持系统托盘");
	    }
	}
}
