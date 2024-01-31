package Controller.Listener;

import View.ManagePanel;
import View.Popup.ManageTablePopupMenu;
import View.SubFrame.CreateList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateListListener implements ActionListener {
    private CreateList parentComponent;
    public CreateListListener(Component component){
        this.parentComponent = (CreateList) component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Cancel":{
                parentComponent.dispose();
                break;
            }
            case "Create":{
                //ManagePanel -> ManageTablePopupMenu -> ManagePopupMenuListener -> CreateList -> CreateListListener (here)
               // ((ManagePanel)(((ManageTablePopupMenu)(parentComponent.getParentComponent())).getParentComponent())).getDataAnalyzing().createListInSQLDatabase();
                ((ManagePanel)(parentComponent.getParentComponent())).getDataAnalyzing().createListInSQLDatabase(parentComponent.getText());
                parentComponent.dispose();
                break;
            }
        }
    }
}
