package Controller.Listener;

import View.ManagePanel;
import View.SubFrame.DeleteList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListListener implements ActionListener {
    private Component parentComponent;
    public DeleteListListener(Component component){
        this.parentComponent =  component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Delete":{
                System.out.println("<DeleteListListener>[actionPerformed]: Delete is pressed");
                ManagePanel managePanel = ((ManagePanel)(((DeleteList)(parentComponent)).getParentComponent()));
                managePanel.getDataAnalyzing().deleteListTable(((DeleteList)(parentComponent)).getDeleteSelection());
                new DeleteList(((DeleteList)(parentComponent)).getParentComponent());
                ((DeleteList)(parentComponent)).dispose();
                break;
            }
            case "Cancel":{
                System.out.println("<DeleteListListener>[actionPerformed]: Cancel is pressed");
                ((DeleteList)(parentComponent)).dispose();
                break;
            }
            case "Delete all":{
                System.out.println("<DeleteListListener>[actionPerformed]: Delete all is pressed");
                ManagePanel managePanel = ((ManagePanel)(((DeleteList)(parentComponent)).getParentComponent()));
                managePanel.getDataAnalyzing().deleteAllListTable();
                new DeleteList(((DeleteList)(parentComponent)).getParentComponent());
                ((DeleteList)(parentComponent)).dispose();
                break;
            }
        }
    }
}
