package View;

import Controller.Listener.LearnPanelListener;
import Model.Color.DefaultColor;
import View.CustomSwing.CardPanelButton;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.lang.management.BufferPoolMXBean;

/**The {@code LearnPanel} is used to contain all components in the Learn page.
 *
 * @author JulyLun (Hoang Luan)
 */
public class LearnPanel extends JPanel {
    private CardPanelButton flashCard, gameCard, orderCard;
    private DefaultColor defaultColor = new DefaultColor();

    private MainMenu thisMainMenu;
    private JPanel mainPanel;
    private JPanel trashPanel_1, trashPanel_2;
    LearnPanel(MainMenu component){
        thisMainMenu = component;

        init();
        defaultConfig();
        addListener();
        setBackground(defaultColor.getMainBackgroundColor());
        setLayout(new GridLayout(3,1));
        add(trashPanel_1);
        add(mainPanel);
        add(trashPanel_2);

    }
    private void init(){
        mainPanel = new JPanel();
        flashCard = new CardPanelButton("Flash card");
        gameCard = new CardPanelButton("Mini game");
        orderCard = new CardPanelButton("Coming soon");
        trashPanel_1 = new JPanel();
        trashPanel_2 = new JPanel();
    }
    private void addListener(){
        LearnPanelListener learnPanelListener = new LearnPanelListener(this);
        flashCard.addActionListener(learnPanelListener);
        gameCard.addActionListener(learnPanelListener);
        orderCard.addActionListener(learnPanelListener);
    }
    private void defaultConfig(){
        //Set layout to main Panel
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        //set Background color
        trashPanel_1.setBackground(defaultColor.getMainBackgroundColor());
        trashPanel_2.setBackground(defaultColor.getMainBackgroundColor());
        mainPanel.setBackground(defaultColor.getMainBackgroundColor());
        flashCard.setBackground(defaultColor.darkPurpleColor);
        flashCard.backgroundColor = flashCard.getBackground();
        gameCard.setBackground(defaultColor.darkGreenPastelColor);
        gameCard.backgroundColor = gameCard.getBackground();
        orderCard.setBackground(defaultColor.darkRedColor);
        orderCard.backgroundColor = orderCard.getBackground();


        //Add components
        mainPanel.add(flashCard);
        mainPanel.add(gameCard);
        mainPanel.add(orderCard);
    }

    /** Giving you a MainMenu pointer
     *
     *
     * @return {@linkplain MainMenu}
     */
    public MainMenu getThisMainMenu() {
        return thisMainMenu;
    }
}
