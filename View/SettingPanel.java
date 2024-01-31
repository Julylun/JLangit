package View;

import Controller.Listener.SettingPanelListener;
import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import View.CustomSwing.BorderButton;
import View.CustomSwing.MainColorPanel;
import View.CustomSwing.WhiteLabel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.beans.VetoableChangeListenerProxy;
import java.util.Set;
import java.util.Vector;

/**The {@code SettingPanel} is used to contain all components in Setting page. This is Setting page also :)
 *
 * @author Julylun (Hoang Luan)
 */
public class SettingPanel extends MainColorPanel {
    private MainMenu thisMainMenu;
    private DefaultColor defaultColor = new DefaultColor();
    private DefaultFont defaultFont = new DefaultFont();
    private String[] settingName = {"Target words","Delete all words","Delete all list","Yellow Duck assistant","About"};
    private String[] buttonName = {"Reset words","Reset lists","Open","About"};
    private Integer[] targetComboNumber = {10,30,40,50,70,100,150,200,300,400,500,700,900,1000,1200,1400,1500,1700,1900,2000,3000};
    private Vector<WhiteLabel> vLabel = new Vector<WhiteLabel>();
    private Vector<Component> vComponent = new Vector<Component>();
    private Vector<MainColorPanel> vPanel = new Vector<MainColorPanel>();
    private MainColorPanel mainPanel, titlePanel;
    private WhiteLabel title;
    private JScrollPane scrollPane;
    private BorderButton tempButton;
    private JComboBox tempComboBox;
    public SettingPanel(MainMenu mainMenu){
        thisMainMenu = mainMenu;
        init();
        defaultPanelConfiguration();
        setLayout(new BorderLayout());
        add(titlePanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        setBackground(defaultColor.getMainBackgroundColor());
    }

    private void init(){
        mainPanel = new MainColorPanel();
        titlePanel = new MainColorPanel();
        scrollPane = new JScrollPane();
        title = new WhiteLabel("SETTING");
        title.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(30));
        for(String name: settingName){
            vLabel.add(new WhiteLabel(name));
            vLabel.getLast().setFont(defaultFont.getFont_OPENSANS_LIGHT(20));
        }
        tempComboBox = new JComboBox<Integer>(targetComboNumber);
        tempComboBox.setBackground(defaultColor.getMainBackgroundColor());
        tempComboBox.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        tempComboBox.setOpaque(false);
        tempComboBox.addActionListener(new SettingPanelListener(this));
        tempComboBox.setSelectedItem(thisMainMenu.managePanel.getDataAnalyzing().getTarget());
        vComponent.add(tempComboBox);
        for(String name: buttonName){
            tempButton = new BorderButton(name, defaultColor.FLASH_CARD, defaultColor.darkGreenPastelColor);
            tempButton.addActionListener(new SettingPanelListener(this));
            vComponent.add(tempButton);
        }

    }

    private void defaultPanelConfiguration(){
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
//        mainPanel.setLayout(new GridLayout(2,1));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER       ));
        titlePanel.add(title);
        for(int i = 0; i < vLabel.size(); i++){
            vPanel.add(new MainColorPanel());
            vPanel.getLast().setSize(30,30);
            vPanel.getLast().setPreferredSize(new Dimension(30,30));
            vPanel.getLast().setLayout(new GridLayout(1,2));
            vPanel.getLast().add(vLabel.get(i));
            vPanel.getLast().add(vComponent.get(i));


        }


        for(MainColorPanel panel: vPanel){
            mainPanel.add(panel);
        }

//        mainPanel.add(new MainColorPanel());
        scrollPane.setViewportView(mainPanel);
        scrollPane.setOpaque(false);
        scrollPane.setForeground(defaultColor.getMainBackgroundColor());
        scrollPane.setBackground(defaultColor.getMainBackgroundColor());
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
    }

    public JComboBox getTargetNumber() {
        return tempComboBox;
    }

    public MainMenu getThisMainMenu() {
        return thisMainMenu;
    }
}
