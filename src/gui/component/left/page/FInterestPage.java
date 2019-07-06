package gui.component.left.page;

import gui.FLeftPanel;
import gui.FMenuBar;
import gui.FrameStatus;
import gui.component.left.insterestfunction.FInterestDirectoryNamePanel;
import gui.constant.FileConstant;
import gui.util.FramePanelUtil;

public class FInterestPage extends FPage {
	FMenuBar menuBar;
	private FLeftPanel leftPanel;
	
	// 第一页
	private FInterestDirectoryNamePanel interestDirectoryNamePanel;
	
	public FInterestPage(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
		leftPanel = FramePanelUtil.getMainFrame(menuBar).getLeftPanel();
		FMenuBar.totalPage = FileConstant.INTEREST_TOTAL_PAGE;
		//this.menuBar.currMode = FileConstants.MODIFY_MODE;
		FMenuBar.currPage = 1;
		FrameStatus.fileSelectEnable = false;
		build();
	}
	
	@Override
	public void buildPage() {
		
		if(FMenuBar.currPage == 1)
		{
			interestDirectoryNamePanel = new FInterestDirectoryNamePanel(leftPanel);
			interestDirectoryNamePanel.setBounds(0, 0, 400, 200);
			FrameStatus.multiLevel = false;
			
			//menuBar.getPrePage().setEnabled(false);
			//menuBar.getFirstPage().setEnabled(false);
			//menuBar.getNextPage().setEnabled(true);
			//menuBar.getLastPage().setEnabled(true);
		}
	}

}
