package Controller.Listener;

import Model.Color.DefaultColor;
import View.MainMenu;
import View.ManagePanel;
import View.SettingPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener {
    MainMenu mainMenu;
    public MenuButtonListener(MainMenu mainMenu){
        this.mainMenu = mainMenu;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(new DefaultColor().RED + e.getActionCommand() + new DefaultColor().RESET);
        switch (e.getActionCommand()){
            case "Home":{
                mainMenu.setView(mainMenu.getANewHome());
                System.out.println(new DefaultColor().YELLOW + "<MenuButtonListener> [Action debugging]: Home button is pressed" + new DefaultColor().RESET);
                break;
            }
            case "LEARN":{}
            case "Learn":{
                mainMenu.setView(mainMenu.learnPanel); //mainMenu.viewPanel = learnPenl;
                System.out.println(new DefaultColor().YELLOW + "<MenuButtonListener> [Action debugging]: Learn button is pressed" + new DefaultColor().RESET);
                break;
            }
            case "MANAGE":{}
            case "Manage":{
                mainMenu.setView(mainMenu.managePanel); //mainMenu.viewPanel = managePanel;
                mainMenu.managePanel.refreshView();
                System.out.println(new DefaultColor().YELLOW + "<MenuButtonListener> [Action debugging]: Manage button is pressed" + new DefaultColor().RESET);
                break;
            }
            case "SETTING":{}
            case "Setting":{
                mainMenu.setView(new SettingPanel(mainMenu));
                System.out.println(new DefaultColor().YELLOW + "<MenuButtonListener> [Action debugging]: Manage button is pressed" + new DefaultColor().RESET);
                break;
            }
            case "EXIT":{}
            case "Exit":{

                System.out.println(new DefaultColor().YELLOW + "<MenuButtonListener> [Action debugging]: Exit button is pressed" + new DefaultColor().RESET);
                System.exit(0);
                break;
            }
        }
    }
}
