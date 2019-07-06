package gui.component.right;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import gui.util.InitUtil;

public class FFileShowPanel extends JPanel {
	private static final long serialVersionUID = 405023984048397707L;
	private JFileChooser fileShow;
	
	public FFileShowPanel(JComponent parentComponent)
	{
		fileShow = new JFileChooser();
		InitUtil.disableFileChooserCreateFile(fileShow);
		
		add(fileShow);
		parentComponent.add(this);
	}

	public JFileChooser getFileShow() {
		return fileShow;
	}

	public void setFileShow(JFileChooser fileShow) {
		this.fileShow = fileShow;
	}
}
