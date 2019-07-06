package gui.listener;

import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.component.right.FMainPanel;

public class LExtendNameListListener implements ListSelectionListener {
	FMainPanel parentComponent;
	
	public LExtendNameListListener(FMainPanel parentComponent) {
		this.parentComponent = parentComponent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<String> source = (JList<String>) e.getSource();
		if(!source.getValueIsAdjusting())
		{
			List<String> values = source.getSelectedValuesList();
			System.out.println(values);
		}
	}
	

}
