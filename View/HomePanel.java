package View;

import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import View.CustomSwing.Logo;
import View.CustomSwing.MainColorPanel;
import View.CustomSwing.SubColorPanel;
import View.HomePanelPackage.ButtonWidget;
import View.HomePanelPackage.JourneyWidget;
import View.HomePanelPackage.TargetWidget;

import javax.swing.*;
import java.awt.*;

/**The {@code HomePanel} extends the {@link JPanel}. This is used to contain all components in home page and this is home
 * page also. Moreover, this has a parameter which has class is {@link MainMenu}
 * @version 4/1/2024
 * @author Julylun (Hoang Luan)
 */
public class HomePanel extends JPanel {
    private DefaultColor defaultColor = new DefaultColor();
    private DefaultFont defaultFont = new DefaultFont();
    private TargetWidget targetWidget;
    private ButtonWidget buttonWidget;
    private JourneyWidget journeyWidget;
    private GridBagConstraints gbc;
    private MainColorPanel topPanel, botPanel;
    private MainMenu thisMainMenu;

    /** The constructor
     *
     * @param mainMenu
     */
    HomePanel(MainMenu mainMenu){
        thisMainMenu = mainMenu;
        init();
        defaultPanelConfiguration();
        setLayout(new GridLayout(2,1));
//        add(new JButton("1"));
//        add(new JButton("2"));
        repaint();
//        this.add(new JPanel());
        add(topPanel);
        add(botPanel);
        setBackground(defaultColor.getMainBackgroundColor());
        System.out.println("<HomePanel>[init]: targetWidget size = " + targetWidget.getPreferredSize());
        System.out.println("<HomePanel>[init]: topPanel size - " +  topPanel.getSize());
    }

    private void init(){

        gbc = new GridBagConstraints();
        topPanel = new MainColorPanel();
        botPanel = new MainColorPanel();
        targetWidget = new TargetWidget(400, this);
        buttonWidget = new ButtonWidget(this);
        journeyWidget = new JourneyWidget(this);
    }

    /**The {@code defaultPanelConfiguration} just is like an init function
     *
      */
    private void defaultPanelConfiguration(){
        //topPanel----------------------------------------
        topPanel.setLayout(new GridLayout(1,2));
//        JPanel jPanel = new JPanel();
//        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        jPanel.add(targetWidget);
        topPanel.add(targetWidget);
        topPanel.add(buttonWidget);

        //botPanel----------------------------------------
//        botPanel.setLayout(null);
        botPanel.setLayout(new BorderLayout());
        botPanel.add(journeyWidget, BorderLayout.CENTER);
        botPanel.add(new MainColorPanel(),BorderLayout.WEST);
        botPanel.add(new MainColorPanel(),BorderLayout.SOUTH);
        botPanel.add(new MainColorPanel(),BorderLayout.NORTH);
        botPanel.add(new MainColorPanel(),BorderLayout.EAST);

    }

    /**This is used to return a {@link MainMenu}
     *
     * @return {@link MainMenu}
     */
    public MainMenu getThisMainMenu() {
        return thisMainMenu;
    }
}
