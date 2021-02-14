package Controller;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WordWrapCellRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		int tableRowHeight = table.getRowHeight();

		setText(value.toString());
		setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

		if (table.getRowHeight(row) != getPreferredSize().height) {
			if (getPreferredSize().height > tableRowHeight) {
				table.setRowHeight(row, getPreferredSize().height);
			} else {
				table.setRowHeight(row, tableRowHeight);
			}
		}

		setFont(table.getFont());

		return this;
	}
}
