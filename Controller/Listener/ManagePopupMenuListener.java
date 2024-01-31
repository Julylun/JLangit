package Controller.Listener;

import Controller.DataAnalyzing;
import Model.Color.DefaultColor;
import Model.Word;
import View.ManagePanel;
import View.Popup.ManageTablePopupMenu;
import View.SubFrame.AddWord;
import View.SubFrame.CreateList;
import View.SubFrame.DeleteList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ManagePopupMenuListener implements MouseListener, ActionListener {
    private ManagePanel parentComponent;
    public ManagePopupMenuListener(Component parentComponent){
        this.parentComponent = (ManagePanel) parentComponent;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        parentComponent.getViewTable().setEnabled(false);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        parentComponent.resetPopup();
        int r = parentComponent.getViewTable().rowAtPoint(e.getPoint());
        if (r >= 0 && r < parentComponent.getViewTable().getRowCount()) {
            parentComponent.getViewTable().setRowSelectionInterval(r, r);
        } else {
            parentComponent.getViewTable().clearSelection();
        }
        parentComponent.getViewTable().setEnabled(true);
        if(e.getButton() == MouseEvent.BUTTON3){
            parentComponent.resetPopup();
            parentComponent.getViewTable().setEnabled(true);
            new ManageTablePopupMenu(parentComponent).show(parentComponent,e.getX(),e.getY());
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Add":{
                System.out.println(new DefaultColor().YELLOW + "<ManagePopupMenuListener> [actionPerformed - Add]: New is pressed" + new DefaultColor().RESET);
                new AddWord(parentComponent,0);
                break;
            }
            case "Delete":{
                System.out.println(new DefaultColor().YELLOW + "<ManagePopupMenuListener> [actionPerformed - Delete]: Delete is pressed" + new DefaultColor().RESET);
                SwingWorker swingWorker = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        DataAnalyzing dataAnalyzing = new DataAnalyzing();
                        parentComponent.getDataAnalyzing().deleteWordFromSQLDatabase((Word)parentComponent.getDataAnalyzing().getDataFromSQLDatabase().elementAt(parentComponent.getViewTable().getSelectedRow()));
                        parentComponent.refreshView();
                        return null;
                    }
                };
                swingWorker.execute();
                System.out.println(new DefaultColor().YELLOW + "<ManagePopupMenuListener> [actionPerformed - Delete]: Deleted" + new DefaultColor().RESET);
                break;
            }
            case "Edit":{
                new AddWord(parentComponent,1);
                break;
            }

            case "Create a new list":{
                new CreateList(parentComponent);
                parentComponent.resetPopup();
                break;
            }

            case "Delete list":{
                new DeleteList(parentComponent);
                break;
            }

        }
    }
}
