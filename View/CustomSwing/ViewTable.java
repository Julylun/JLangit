package View.CustomSwing;

import Controller.Listener.ManagePopupMenuListener;
import Model.Color.DefaultColor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;

public class ViewTable extends JTable {
    private static final long serialVersionUID = 1L;
    DefaultColor defaultColor = new DefaultColor();
    public ViewTable(){
        setOpaque(false);
        setBackground(defaultColor.getMainBackgroundColor());
        setGridColor(defaultColor.getGridColor());
        setForeground(defaultColor.getFontColor());
        getTableHeader().setOpaque(false);
        getTableHeader().setBackground(defaultColor.getSubBackgroundColor());
        getTableHeader().setForeground(defaultColor.getFontColor());
        getTableHeader().setBorder(new LineBorder(defaultColor.getGridColor(),0,true));
    }
    public ViewTable(DefaultTableModel model){
        this();
        setModel(model);
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
