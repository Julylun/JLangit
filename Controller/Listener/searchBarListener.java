package Controller.Listener;

import View.ManagePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class searchBarListener implements KeyListener {
    private ManagePanel thisManagePanel;
    public searchBarListener(ManagePanel thisManagePanel){
        this.thisManagePanel = thisManagePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("<searchBarListener>[keyReleased]: key is released - - " + thisManagePanel.getSearchBar().getText());
        if(thisManagePanel.getSearchBar().getText().equals("")) {
            thisManagePanel.refreshView();
            return;
        }

        thisManagePanel.sync(thisManagePanel.getDataAnalyzing().getDataFromSQLDatabase(thisManagePanel.getSearchBar().getText()));
    }
}
