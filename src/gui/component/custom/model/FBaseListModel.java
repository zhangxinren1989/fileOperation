package gui.component.custom.model;

import java.util.List;

import javax.swing.AbstractListModel;

public class FBaseListModel extends AbstractListModel<String> {
	private static final long serialVersionUID = 1230132809932082446L;
	private List<String> elements;
	
	public FBaseListModel(List<String> elements) {
		this.elements = elements;
	}
	
	@Override
	public String getElementAt(int index) {
		return elements.get(index++);
	}

	@Override
	public int getSize() {
		return elements.size();
	}

	public List<String> getElements() {
		return elements;
	}

	public void setElements(List<String> elements) {
		this.elements = elements;
	}

}
