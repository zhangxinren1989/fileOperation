package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import gui.component.custom.AboutInfoDialog;
import gui.component.custom.MyInfoDialog;
import gui.component.left.page.FModifyPage;
import gui.component.left.page.FPage;
import gui.listener.LSelectButtonListener;
import gui.listener.page.FInterestPageListener;
import gui.listener.page.FModifyPageListener;
import gui.listener.page.FPracticePageListener;
import gui.util.InitUtil;
import gui.util.ListenerUtil;

public class FMenuBar extends JMenuBar{
	private static final long serialVersionUID = -1680617400645054299L;
	private JMenu about = new JMenu("关于");
	private JMenu start = new JMenu("开始");
	private JMenu page = new JMenu("操作");
	private JRadioButtonMenuItem modifyView = new JRadioButtonMenuItem("修改文件");
	private JRadioButtonMenuItem createView = new JRadioButtonMenuItem("创建文件");
	private JRadioButtonMenuItem deleteView = new JRadioButtonMenuItem("删除文件");
	private JRadioButtonMenuItem practiceView = new JRadioButtonMenuItem("实用功能");
	private JRadioButtonMenuItem interestView = new JRadioButtonMenuItem("趣味功能");
	private ButtonGroup viewGroup = new ButtonGroup();
	private JMenuItem tips = new JMenuItem("说明");
	private JMenuItem info = new JMenuItem("关于作者");
	private JMenuItem open = new JMenuItem("打开");
	private JMenuItem exit = new JMenuItem("退出");
	private JMenuItem prePage = new JMenuItem("上一页");
	private JMenuItem nextPage = new JMenuItem("下一页");
	private JMenuItem firstPage = new JMenuItem("首页");
	private JMenuItem lastPage = new JMenuItem("尾页");
	private FPage fPage;
	
	public static int currPage;
	public static int totalPage;
	/** 1:新增，2：删除，3：修改 */
	//public static int currMode;
	
	public FMenuBar()
	{
		add(start);
		add(page);
		add(about);
		
		start.add(open);
		start.addSeparator();
		start.add(exit);
		
		page.add(prePage);
		page.add(nextPage);
		page.add(firstPage);
		page.add(lastPage);
		page.addSeparator();
		viewGroup.add(modifyView);
		viewGroup.add(createView);
		createView.setEnabled(false);
		viewGroup.add(deleteView);
		viewGroup.add(practiceView);
		viewGroup.add(interestView);
		deleteView.setEnabled(false);
		modifyView.setSelected(true);
		modifyView.addActionListener(new FModifyPageListener(this));
		practiceView.addActionListener(new FPracticePageListener(this));
		interestView.addActionListener(new FInterestPageListener(this));
		
		page.add(modifyView);
		page.add(createView);
		page.add(deleteView);
		page.add(practiceView);
		page.add(interestView);
		
		about.add(tips);
		about.add(info);
		
		open.addActionListener(new LSelectButtonListener(this));
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// FIXME 在操作文件时不允许退出
				ListenerUtil.exit();
			}
		});
		
		prePage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fPage.prePage();
			}
		});
		
		nextPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fPage.nextPage();
			}
		});
		
		firstPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fPage.firstPage();
			}
		});
		
		lastPage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fPage.lastPage();
			}
		});
		
		info.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MyInfoDialog.showMyInfo();
			}
		});
		
		tips.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AboutInfoDialog.showAboutInfo();
			}
		});
		
		// 这几个默认不是微软雅黑，处理一下
		InitUtil.setDefaultFont(start, page, about, modifyView, createView, deleteView, 
				practiceView, interestView);
	}
	
	public void init()
	{
		fPage = new FModifyPage(this);
	}
	
	public JMenuItem getPrePage() {
		return prePage;
	}
	public void setPrePage(JMenuItem prePage) {
		this.prePage = prePage;
	}
	public JMenuItem getNextPage() {
		return nextPage;
	}
	public void setNextPage(JMenuItem nextPage) {
		this.nextPage = nextPage;
	}
	public JMenuItem getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(JMenuItem firstPage) {
		this.firstPage = firstPage;
	}
	public JMenuItem getLastPage() {
		return lastPage;
	}
	public void setLastPage(JMenuItem lastPage) {
		this.lastPage = lastPage;
	}
	public FPage getfPage() {
		return fPage;
	}
	public void setfPage(FPage fPage) {
		this.fPage = fPage;
	}

	public JMenuItem getOpen() {
		return open;
	}

	public void setOpen(JMenuItem open) {
		this.open = open;
	}
}
