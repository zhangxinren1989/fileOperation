package gui.util;

import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import gui.FrameStatus;

public class UIUtil {
	private UIUtil(){}
	
	public static void stopMessageShow()
	{
		FrameStatus.messageShow = false;
	}
	
	public static void FitTableColumns(JTable myTable){
		  JTableHeader header = myTable.getTableHeader();
	     int rowCount = myTable.getRowCount();
	
	     Enumeration columns = myTable.getColumnModel().getColumns();
	     while(columns.hasMoreElements()){
	         TableColumn column = (TableColumn)columns.nextElement();
	         int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
	         int width = (int)myTable.getTableHeader().getDefaultRenderer()
	                 .getTableCellRendererComponent(myTable, column.getIdentifier()
	                         , false, false, -1, col).getPreferredSize().getWidth();
	         for(int row = 0; row<rowCount; row++){
	             int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
	               myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
	             width = Math.max(width, preferedWidth);
	         }
	         header.setResizingColumn(column); // 此行很重要
	         column.setWidth(width+myTable.getIntercellSpacing().width);
	     }
	}
}
