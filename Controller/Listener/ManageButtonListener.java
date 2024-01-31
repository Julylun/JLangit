package Controller.Listener;

import Controller.DataAnalyzing;
import Model.Color.DefaultColor;
import View.MainMenu;
import View.ManagePanel;
import View.SubFrame.AddWord;
import View.SubFrame.LoadingFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ManageButtonListener implements ActionListener {
    private Component parentComponent;
    public ManageButtonListener(Component component){
        this.parentComponent = component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "New":{
                System.out.println("<ManageButtonListiner> [actionPerformed - new]: New is pressed");
                new AddWord(parentComponent,0);
                break;
            }
            case "Sync":{
                ((ManagePanel)(parentComponent)).refreshView();
                break;
            }
            case "Import":{
                DataAnalyzing dataAnalyzing = ((ManagePanel)(parentComponent)).getDataAnalyzing();
                dataAnalyzing.addWordToSQLDatabase(dataAnalyzing.getDataFromFile(dataAnalyzing.getPath(0)));
                ((ManagePanel)(parentComponent)).refreshView();
                break;
            }
            case "Export":{
                DataAnalyzing dataAnalyzing = ((ManagePanel)(parentComponent)).getDataAnalyzing();
                if(dataAnalyzing.exportDataToFile(dataAnalyzing.getPath(1)) != 0){
                    System.out.println(new DefaultColor().RED + "<dataAnalyzing>[actionPerformed - Export]: Failed to export file!" + new DefaultColor().RESET);
                }
                break;
            }
        }
    }
}
