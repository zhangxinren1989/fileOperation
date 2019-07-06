package gui.component.left.page;

import gui.FLeftPanel;
import gui.FMenuBar;
import gui.FrameStatus;
import gui.component.left.modify.DPreNumPostPanel;
import gui.component.left.modify.DReplacePanel;
import gui.component.left.modify.FExtendNamePanel;
import gui.component.left.modify.FPreNumPostPanel;
import gui.component.left.modify.FReplacePanel;
import gui.component.left.modify.MExtendNamePanel;
import gui.component.left.modify.MPreNumPostPanel;
import gui.component.left.modify.MReplacePanel;
import gui.constant.FileConstant;
import gui.util.FramePanelUtil;

public class FModifyPage extends FPage{
	FMenuBar menuBar;
	private FLeftPanel leftPanel;
	
	// 第一页
	private FPreNumPostPanel fPreNumPostPanel;
	private FReplacePanel fReplacePanel;
	private FExtendNamePanel fExtendNamePanel;
	
	// 第二页
	private DPreNumPostPanel dPreNumPostPanel;
	private DReplacePanel dReplacePanel;
	
	// 第三页
	private MPreNumPostPanel mPreNumPostPanel;
	private MReplacePanel mReplacePanel;
	private MExtendNamePanel mExtendNamePanel;
	
	public FModifyPage(FMenuBar menuBar)
	{
		this.menuBar = menuBar;
		leftPanel = FramePanelUtil.getMainFrame(menuBar).getLeftPanel();
		FMenuBar.totalPage = FileConstant.MODIFY_TOTAL_PAGE;
		//this.menuBar.currMode = FileConstants.MODIFY_MODE;
		FMenuBar.currPage = 1;
		FrameStatus.fileSelectEnable = true;
		build();
	}

	@Override
	public void buildPage() {
		if(FMenuBar.currPage == 1)
		{
			fPreNumPostPanel = new FPreNumPostPanel(leftPanel);
			fPreNumPostPanel.setBounds(0, 0, 400, 200);
			
			fReplacePanel = new FReplacePanel(leftPanel);
			fReplacePanel.setBounds(0, 200, 400, 200);
			
			fExtendNamePanel = new FExtendNamePanel(leftPanel);
			fExtendNamePanel.setBounds(0, 400, 400, 200);
			
			FrameStatus.multiLevel = false;
		}
		else if(FMenuBar.currPage == 2)
		{
			dPreNumPostPanel = new DPreNumPostPanel(leftPanel);
			dPreNumPostPanel.setBounds(0, 0, 400, 200);
			
			dReplacePanel = new DReplacePanel(leftPanel);
			dReplacePanel.setBounds(0, 200, 400, 200);
			
			FrameStatus.multiLevel = false;
		}
		else if(FMenuBar.currPage == 3)
		{
			mPreNumPostPanel = new MPreNumPostPanel(leftPanel);
			mPreNumPostPanel.setBounds(0, 0, 400, 250);
			
			mReplacePanel = new MReplacePanel(leftPanel);
			mReplacePanel.setBounds(0, 250, 400, 200);
			
			mExtendNamePanel = new MExtendNamePanel(leftPanel);
			mExtendNamePanel.setBounds(0, 450, 400, 150);
			
			FrameStatus.multiLevel = true;
		}
	}

	public FPreNumPostPanel getfPreNumPostPanel() {
		return fPreNumPostPanel;
	}

	public void setfPreNumPostPanel(FPreNumPostPanel fPreNumPostPanel) {
		this.fPreNumPostPanel = fPreNumPostPanel;
	}

	public FReplacePanel getfReplacePanel() {
		return fReplacePanel;
	}

	public void setfReplacePanel(FReplacePanel fReplacePanel) {
		this.fReplacePanel = fReplacePanel;
	}

	public FExtendNamePanel getfExtendNamePanel() {
		return fExtendNamePanel;
	}

	public void setfExtendNamePanel(FExtendNamePanel fExtendNamePanel) {
		this.fExtendNamePanel = fExtendNamePanel;
	}

	public DPreNumPostPanel getdPreNumPostPanel() {
		return dPreNumPostPanel;
	}

	public void setdPreNumPostPanel(DPreNumPostPanel dPreNumPostPanel) {
		this.dPreNumPostPanel = dPreNumPostPanel;
	}

	public DReplacePanel getdReplacePanel() {
		return dReplacePanel;
	}

	public void setdReplacePanel(DReplacePanel dReplacePanel) {
		this.dReplacePanel = dReplacePanel;
	}

	public MPreNumPostPanel getmPreNumPostPanel() {
		return mPreNumPostPanel;
	}

	public void setmPreNumPostPanel(MPreNumPostPanel mPreNumPostPanel) {
		this.mPreNumPostPanel = mPreNumPostPanel;
	}

	public MReplacePanel getmReplacePanel() {
		return mReplacePanel;
	}

	public void setmReplacePanel(MReplacePanel mReplacePanel) {
		this.mReplacePanel = mReplacePanel;
	}

	public MExtendNamePanel getmExtendNamePanel() {
		return mExtendNamePanel;
	}

	public void setmExtendNamePanel(MExtendNamePanel mExtendNamePanel) {
		this.mExtendNamePanel = mExtendNamePanel;
	}
	
	
}
