package View.LearnPanelPackage;

import Model.Color.DefaultColor;
import View.LearnPanel;
import View.MainMenu;

import javax.swing.*;
import java.awt.*;

public class MiniGame extends JPanel{
    private LearnPanel thisLearnPanel;
    private DefaultColor defaultColor = new DefaultColor();
    private MainMenu mainMenu;

    private int screenWidth, screenHeight;
    public MiniGame(LearnPanel learnPanel){
        thisLearnPanel = learnPanel;
        mainMenu = thisLearnPanel.getThisMainMenu();
        Dimension dimension = mainMenu.getViewPanel().getPreferredSize();
        screenHeight = dimension.height;
        screenWidth = dimension.width;

        setBounds(dimension.width/8,0,dimension.width/8*7, dimension.height);
        setBackground(defaultColor.darkRedColor);
    }
}
