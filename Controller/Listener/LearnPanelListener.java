package Controller.Listener;

import Model.Color.DefaultColor;
import View.LearnPanel;
import View.LearnPanelPackage.ChoicePanel;
import View.LearnPanelPackage.MiniGame;
import View.MainMenu;
import View.ManagePanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LearnPanelListener implements ActionListener {
    private LearnPanel thisLearnPanel;
    private DefaultColor defaultColor = new DefaultColor();
    public LearnPanelListener(LearnPanel component){
        this.thisLearnPanel = component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Flash card":{
                System.out.println(defaultColor.YELLOW + "<LearnPanelListener>[actionPerformed - Flash card]: Flash card is pressed" + defaultColor.RESET);
                MainMenu mainMenu = thisLearnPanel.getThisMainMenu();
                mainMenu.setView(new ChoicePanel(thisLearnPanel));
                break;
            }
            case "Mini game":{
                System.out.println(defaultColor.YELLOW + "<LearnPanelListener>[actionPerformed - Mini game]: Mini game is pressed" + defaultColor.RESET);
                thisLearnPanel.getThisMainMenu().setView(new MiniGame(thisLearnPanel));
                break;
            }
            case "Coming soon":{
                System.out.println(defaultColor.YELLOW + "<LearnPanelListener>[actionPerformed - Coming soon]: Coming soon is pressed" + defaultColor.RESET);
                break;
            }
        }
    }
}
