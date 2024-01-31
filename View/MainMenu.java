package View;

import Controller.DataAnalyzing;
import Controller.Listener.MenuButtonListener;
import Controller.ScreenDetails;
import Model.Color.DefaultColor;
import View.CustomSwing.CardPanelButton;
import View.CustomSwing.Logo;
import View.CustomSwing.RoundedPanel;
import View.CustomSwing.ViewMenuButton;
import View.LearnPanelPackage.FlashCardLearningPanel;
import View.SubFrame.LoadingFrame;
import View.SubFrame.StartLoadingFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/** The {@code MainMenu} is the first class created. This contains all components including
 * {@link ManagePanel}, {@link LearnPanel}, {@link HomePanel}, and other.
 *
 * @author JulyLun (Hoang Luan)
 */
public class MainMenu extends JFrame{
    public StartLoadingFrame startLoadingFrame = new StartLoadingFrame();
    private JPanel viewPanel;
    private RoundedPanel menuPanel;
    private String[] menuButtonString = {"Home","Learn","Manage","Setting", "Exit"};
    private GridBagConstraints gbc;
    private Rectangle currentFrameSize;
    private Vector<ViewMenuButton> vButton;
    private Vector<JPanel> vPanel;

    private DefaultColor defaultColor = new DefaultColor();
    public  ManagePanel managePanel = new ManagePanel(this);
    public LearnPanel learnPanel = new LearnPanel(this);


    private ScreenDetails screenDetails;

    /**The constructor. This have no parameter
     *
     */
    public MainMenu(){
        startLoadingFrame.setProgress("Setup UI");
        setUndecorated(true);
        init();
        try {
            startLoadingFrame.setProgress("I think you would like windows UI...");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        startLoadingFrame.setProgress("Buttons need some colors ._.");
        viewMenuButtonInit();
        //set Maximum frame and get screen details
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenDetails.setDataFromFrameDimension();
        screenDetails.debugClassInfo();

        startLoadingFrame.setProgress("Adding more space...");
        //Set panel size and set panel color
        panelInit();

        startLoadingFrame.setProgress("Decor time!");
        //Set layout for frame
        setLayout(new GridBagLayout());
        addComponentIntoMenu();
        //Add layout into frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(menuPanel,gbc);
        gbc.gridx = 1;
        add(viewPanel,gbc);

        startLoadingFrame.setProgress("This app needs a name :)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("JLangit");
        startLoadingFrame.setProgress("He is coming!");
        setVisible(true);
        addListener();
        startLoadingFrame.setVisible(false);
    }
    private void init(){
        startLoadingFrame.setProgress("Creating some cute buttons..");
        screenDetails = new ScreenDetails(View.MainMenu.this);
        //gbc init
        gbc = new GridBagConstraints();
        vButton = new Vector<ViewMenuButton>();
        vPanel = new Vector<JPanel>();
        //Panel init
        menuPanel = new RoundedPanel(defaultColor.getMainBackgroundColor());
        viewPanel= new JPanel();
        viewPanel.setLayout(new GridLayout());
        viewPanel.add(new HomePanel(this));


        //Demo
    }

    /**This give you a HomePanel to do many things
     *
     * @return {@link HomePanel}
     */
    public HomePanel getANewHome(){
        return new HomePanel(this);
    }

    /**The setView give you ability to set add other panel to viewPanel
     *
     * @param panel
     */
    public void setView(JPanel panel){
        System.out.println("[Set view function dbg]: set view");
        viewPanel.removeAll();
        viewPanel.add(panel);
//        viewPanel = panel;
        revalidate();
        repaint();
    }
    private void addComponentIntoMenu(){
        Logo logo = new Logo("../../Resources/JLangitLogo.png");
        logo.scale(150,150);
        logo.setLogo();
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        imagePanel.add(logo);
        imagePanel.setMaximumSize(imagePanel.getPreferredSize());
        imagePanel.setOpaque(false);

        menuPanel.add(imagePanel);
        for(JPanel panel: vPanel) {
            menuPanel.add(panel);
        }
    }
    private void viewMenuButtonInit(){
        for(int i = 0; i< menuButtonString.length; i++){
            vButton.add(new ViewMenuButton(menuButtonString[i]));
            vPanel.add(new JPanel());
            vPanel.getLast().setLayout(new FlowLayout(FlowLayout.LEFT));
            vPanel.getLast().add(vButton.getLast());
            vPanel.getLast().setOpaque(false);
            vPanel.getLast().setMaximumSize(vPanel.getLast().getPreferredSize());
        }
    }
    private void panelInit(){
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
        viewPanel.setLayout(new GridLayout(1,1));
        setPanelSize(screenDetails.getWidth(), screenDetails.getHeight()-(screenDetails.getDefaultMainFrameInsets().top+screenDetails.getDefaultMainFrameInsets().bottom));
        menuPanel.setBackground(defaultColor.getSubBackgroundColor());
        viewPanel.setBackground(defaultColor.getMainBackgroundColor());
        setBackground(defaultColor.getMainBackgroundColor());
    }
    private void setPanelSize(int width, int height){
       menuPanel.setPreferredSize(new Dimension(width/8,height));
       menuPanel.setRound(0,height/15,0,height/15);
       viewPanel.setPreferredSize(new Dimension(width/8*7, height));
    }

    private void addListener(){
        MenuButtonListener menuButtonListener = new MenuButtonListener(this);
        for(ViewMenuButton button: vButton){
            button.addActionListener(menuButtonListener);
        }
    }

    /** Give you a viewPanel which is we are using
     *
     * @return {@link JPanel}
     */
    public JPanel getViewPanel() {
        return viewPanel;
    }
}
