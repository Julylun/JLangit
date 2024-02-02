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
        thisManagePanel.isTyping = true;

        if(thisManagePanel.getSearchBar().getText().equals("Search bar")) thisManagePanel.getSearchBar().setText("");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        thisManagePanel.isTyping = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        thisManagePanel.isTyping = true;
        System.out.println("<searchBarListener>[keyReleased]: key is released - - " + thisManagePanel.getSearchBar().getText());
        switch (e.getKeyCode()){
            case KeyEvent.VK_CONTROL:
            case KeyEvent.VK_SHIFT:
            case KeyEvent.VK_ALT:
                return;
        }
        if(thisManagePanel.getSearchBar().getText().equals("Search ba") && e.getKeyCode() == '\b') thisManagePanel.getSearchBar().setText("");
        else if(thisManagePanel.getSearchBar().getText().equals("")) {
            thisManagePanel.getSearchBar().setText("Search bar");
            thisManagePanel.refreshView();
            return;
        }

        thisManagePanel.sync(thisManagePanel.getDataAnalyzing().getDataFromSQLDatabase(thisManagePanel.getSearchBar().getText()));
    }
}
