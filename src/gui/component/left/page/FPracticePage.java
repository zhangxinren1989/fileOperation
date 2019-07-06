package gui.component.left.page;

import gui.FLeftPanel;
import gui.FMenuBar;
import gui.FrameStatus;
import gui.component.left.practicefunction.FileStatisticPanel;
import gui.component.left.practicefunction.LongPathFileDeletePanel;
import gui.constant.FileConstant;
import gui.util.FramePanelUtil;

public class FPracticePage extends FPage {
	FMenuBar menuBar;
	private FLeftPanel leftPanel;
	
	// 第一页
	private LongPathFileDeletePanel longPathFileDeletePanel;
	private FileStatisticPanel fileStatisticPanel;
	
	public FPracticePage(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
		leftPanel = FramePanelUtil.getMainFrame(menuBar).getLeftPanel();
		FMenuBar.totalPage = FileConstant.PRACTICE_TOTAL_PAGE;
		//this.menuBar.currMode = FileConstants.MODIFY_MODE;
		FMenuBar.currPage = 1;
		FrameStatus.fileSelectEnable = false;
		build();
	}

	@Override
	public void buildPage() {
		if(FMenuBar.currPage == 1)
		{
			longPathFileDeletePanel = new LongPathFileDeletePanel(leftPanel);
			longPathFileDeletePanel.setBounds(0, 0, 400, 200);
			
			fileStatisticPanel = new FileStatisticPanel(leftPanel);
			fileStatisticPanel.setBounds(0, 200, 400, 200);
			
			FrameStatus.multiLevel = true;
		}
		
	}

	public LongPathFileDeletePanel getLongPathFileDeletePanel() {
		return longPathFileDeletePanel;
	}

	public void setLongPathFileDeletePanel(LongPathFileDeletePanel longPathFileDeletePanel) {
		this.longPathFileDeletePanel = longPathFileDeletePanel;
	}

}
