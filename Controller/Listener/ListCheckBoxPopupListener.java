package Controller.Listener;

import View.LearnPanel;
import View.ManagePanel;
import View.Popup.ManageTablePopupMenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCheckBoxPopupListener implements ActionListener {
    private Component parentComponent;
    public ListCheckBoxPopupListener(Component component){
        this.parentComponent = component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ManageTablePopupMenu manageTablePopupMenu = (ManageTablePopupMenu) parentComponent;
        ManagePanel managePanel = (ManagePanel) manageTablePopupMenu.getParentComponent();
        if(managePanel.getDataAnalyzing().isInList(manageTablePopupMenu.getSelectedWord(),e.getActionCommand())){
            managePanel.getDataAnalyzing().deleteWordFromList(manageTablePopupMenu.getSelectedWord(), e.getActionCommand());
        }
        else {
            managePanel.getDataAnalyzing().addWordIntoList(manageTablePopupMenu.getSelectedWord(), e.getActionCommand());
        }
    }
}
