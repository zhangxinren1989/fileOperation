package gui.component.left.page;

import javax.swing.JPanel;

import gui.FMainFrame;
import gui.FMenuBar;
import gui.util.FramePanelUtil;

public abstract class FPage {
	public void prePage() {
		if(FMenuBar.currPage <= 1)
		{
			return;
		}
		else
		{
			FMenuBar.currPage -= 1;
			build();
		}
	}

	public void nextPage() {
		if(FMenuBar.currPage >= FMenuBar.totalPage)
		{
			return;
		}
		else
		{
			FMenuBar.currPage += 1;
			build();
		}
	}

	public void firstPage() {
		if(FMenuBar.currPage == 1)
		{
			return;
		}
		else
		{
			FMenuBar.currPage = 1;
			build();
		}
	}
	
	public void lastPage() {
		if(FMenuBar.currPage == FMenuBar.totalPage)
		{
			return;
		}
		else
		{
			FMenuBar.currPage = FMenuBar.totalPage;
			build();
		}
	}
	
	public void build()
	{
		// 清除左侧面板内容
		JPanel leftPanel = FMainFrame.getInstance().getLeftPanel();
		
		leftPanel.removeAll();
		leftPanel.repaint();
		
		// 重新设置左侧面板内容
		buildPage();
		// 设置菜单中的分页状态
		buildMenuPageStatus();
		
		leftPanel.revalidate();
		
		// 初始化右侧文件夹选择
		FramePanelUtil.initFileSelect();
	}
	
	public void buildMenuPageStatus()
	{
		FMenuBar menuBar = FMainFrame.getInstance().getfMenuBar();
		if(FMenuBar.currPage == 1)
		{
			menuBar.getFirstPage().setEnabled(false);
			menuBar.getPrePage().setEnabled(false);
		}
		else
		{
			menuBar.getFirstPage().setEnabled(true);
			menuBar.getPrePage().setEnabled(true);
		}
		
		if(FMenuBar.totalPage == FMenuBar.currPage)
		{
			menuBar.getLastPage().setEnabled(false);
			menuBar.getNextPage().setEnabled(false);
		}
		else
		{
			menuBar.getLastPage().setEnabled(true);
			menuBar.getNextPage().setEnabled(true);
		}
	}
	
	public abstract void buildPage();
}
