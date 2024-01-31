package View.HomePanelPackage;

import Controller.Listener.MenuButtonListener;
import Model.Color.DefaultColor;
import View.CustomSwing.BorderButton;
import View.CustomSwing.MainColorPanel;
import View.CustomSwing.ViewMenuButton;
import View.HomePanel;

import javax.swing.*;
import java.awt.*;

public class ButtonWidget extends JPanel {
    private DefaultColor defaultColor = new DefaultColor();
    private HomePanel thisHomePanel;
    private JPanel contentPanel;
    private MainColorPanel firstPanel, secondPanel, thirdPanel;
    private BorderButton learnButton, manageButton, settingButton, exitButton;
    public ButtonWidget(HomePanel homePanel){
        thisHomePanel = homePanel;
        init();
        setDefaultPanelConfiguration();
        setBackground(defaultColor.getMainBackgroundColor());
        setLayout(new BorderLayout());
        add(new MainColorPanel(),BorderLayout.NORTH);
        add(new MainColorPanel(),BorderLayout.EAST);
        add(new MainColorPanel(),BorderLayout.SOUTH);
        add(new MainColorPanel(),BorderLayout.WEST);
        add(contentPanel,BorderLayout.CENTER);

        addListener();
    }

    private void init(){
        contentPanel = new JPanel();
        contentPanel.setBackground(defaultColor.getMainBackgroundColor());
        firstPanel = new MainColorPanel();
        secondPanel = new MainColorPanel();
        thirdPanel = new MainColorPanel();

        learnButton = new BorderButton("LEARN", defaultColor.HOME_LEARN_BUTTON, defaultColor.HOME_LEARN_BUTTON_TRANS);
        manageButton = new BorderButton("MANAGE", defaultColor.HOME_MANAGE_BUTTON, defaultColor.HOME_MANAGE_BUTTON_TRANS);
        settingButton = new BorderButton("SETTING", defaultColor.HOME_SETTING_BUTTON, defaultColor.HOME_SETTING_BUTTON_TRANS);
        exitButton = new BorderButton("EXIT", defaultColor.HOME_EXIT_BUTTON, defaultColor.HOME_EXIT_BUTTON_TRANS);
    }

    private void addListener(){
        MenuButtonListener menuButtonListener = new MenuButtonListener(thisHomePanel.getThisMainMenu());
        learnButton.addActionListener(menuButtonListener);
        manageButton.addActionListener(menuButtonListener);
        settingButton.addActionListener(menuButtonListener);
        exitButton.addActionListener(menuButtonListener);
    }
    private void setDefaultPanelConfiguration(){
        contentPanel.setLayout(new GridLayout(3,1,10,10));
        contentPanel.add(firstPanel);
        contentPanel.add(secondPanel);
        contentPanel.add(thirdPanel);

        firstPanel.setLayout(new GridLayout(1,1));
        secondPanel.setLayout(new GridLayout(1,2,10,0));
        thirdPanel.setLayout(new GridLayout(1,1));

        firstPanel.add(learnButton);
        secondPanel.add(manageButton);
        secondPanel.add(settingButton);
        thirdPanel.add(exitButton);

    }
}
