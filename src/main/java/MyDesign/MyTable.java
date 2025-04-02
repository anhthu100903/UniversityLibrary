package MyDesign;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author QUANG DIEN
 */
public class MyTable extends JTable{

    public MyTable() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setRowHeight(36);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
                TableHeader header = new TableHeader(o + "");
//                header.setHorizontalAlignment(CENTER);
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);
                if(isSelected) {
                    com.setForeground(new Color(22,113,221));
                }else {
                    com.setForeground(new Color(102,102,102));
                }
                return com;
            }
        });
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
    public void setRowCount(int i) {
        // TODO Auto-generated method stub
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.setRowCount(i);
    }
}
