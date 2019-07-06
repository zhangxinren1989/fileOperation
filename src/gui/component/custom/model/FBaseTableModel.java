
package gui.component.custom.model;

import javax.swing.table.DefaultTableModel;

public class FBaseTableModel extends DefaultTableModel
{
	public FBaseTableModel(Object[][] rows, String[] header)
	{
		super(rows, header);
	}
	/**
	 * 
	 * 使表格不可编辑.
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
	
	/**
	 * 返回列的真实类型，用于排序
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		Class returnValue;  
        if ((columnIndex >= 0) && (columnIndex < getColumnCount())) {  
            returnValue = getValueAt(0, columnIndex).getClass();  
        } else {  
            returnValue = Object.class;  
        }  
        return returnValue;  
	}
}

