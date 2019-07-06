package gui.component.custom;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class FFileShowChooser extends JFileChooser {
	private static final long serialVersionUID = -664257592165832455L;

	@Override
	protected JDialog createDialog(Component parent) throws HeadlessException {
		JDialog dialog =  super.createDialog(parent);
		dialog.setSize(400, 300);
		return dialog;
	}
}
