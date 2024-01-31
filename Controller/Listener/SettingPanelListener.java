package Controller.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import View.SettingPanel;
import View.SubFrame.About;


public class SettingPanelListener implements ActionListener {
    private View.SettingPanel thisSettingPanel;
    public SettingPanelListener(SettingPanel settingPanel){
        thisSettingPanel = settingPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("comboBoxChanged")){
            System.out.println("<SettingPanelListener>[@Override - actionPerformed]: Target will be changed");
            thisSettingPanel.getThisMainMenu().managePanel.getDataAnalyzing().setTarget((Integer) thisSettingPanel.getTargetNumber().getSelectedItem());
        }
        else {
            switch (e.getActionCommand()){
                case "Reset words":{
                    thisSettingPanel.getThisMainMenu().managePanel.getDataAnalyzing().deleteAllWordsFromDatabase();
                    thisSettingPanel.getThisMainMenu().managePanel.refreshView();
                    break;
                }

                case "Reset lists":{
                    thisSettingPanel.getThisMainMenu().managePanel.getDataAnalyzing().deleteAllListTable();
                    thisSettingPanel.getThisMainMenu().managePanel.refreshView();
                    break;
                }

                case "About":{
                    new About();
                    break;
                }
            }
        }
    }
}
