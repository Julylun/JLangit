package View.LearnPanelPackage;

import Controller.Listener.ChoicePanelListener;
import Controller.Listener.LearnPanelListener;
import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import View.CustomSwing.CustomList;
import View.CustomSwing.MainColorPanel;
import View.CustomSwing.ManageButton;
import View.CustomSwing.WhiteLabel;
import View.LearnPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ChoicePanel extends MainColorPanel {
    private DefaultFont defaultFont = new DefaultFont();
    private DefaultColor defaultColor = new DefaultColor();
    private CustomList selectedListItem, notSelectedListItem;
    private WhiteLabel leftText, rightText, mainTitle;
    private MainColorPanel centerPanel, southPanel, buttonPanel, leftPanel, rightPanel, titlePanel;
    private JScrollPane leftScrollPane, rightScrollPane;
    private String[] testList = {"item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3","item1","item2","item3",};
    private String[] listButtonName = {">",">>","<","<<"};
    private ManageButton learnButton, learnBySpacedRepetitionButton;
    private Vector<String> vSelectedListName, vNotSelectedListName;
    private Vector<ManageButton> vListButton = new Vector<ManageButton>();

    private LearnPanel thisLearnPanel;
    public ChoicePanel(Component component){
        this.thisLearnPanel = (LearnPanel) component;
        init();
        setDefaultPanelConfigurations();
        setBackground(defaultColor.getMainBackgroundColor());
        setLayout(new BorderLayout());
        add(centerPanel,BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);
        addListener();
    }

    private void init(){
        for(String name: listButtonName){
            vListButton.add(new ManageButton(name));
        }
        vNotSelectedListName = thisLearnPanel.getThisMainMenu().managePanel.getDataAnalyzing().getAllListInSQLDatabase();
        vSelectedListName = new Vector<String>();

        notSelectedListItem = new CustomList(vNotSelectedListName);
        selectedListItem = new CustomList(vSelectedListName);

        centerPanel = new MainColorPanel();
        leftScrollPane = new JScrollPane();
        rightScrollPane = new JScrollPane();
        leftPanel = new MainColorPanel();
        rightPanel = new MainColorPanel();
        buttonPanel = new MainColorPanel();
        southPanel = new MainColorPanel();
        titlePanel = new MainColorPanel();
        leftText = new WhiteLabel("Not select");
        rightText = new WhiteLabel("Selected");
        mainTitle = new WhiteLabel("Select your word lists");
        learnButton = new ManageButton("Learn!");
        learnBySpacedRepetitionButton = new ManageButton("Learn by Spaced Repetition Method");
    }

    private void setDefaultPanelConfigurations(){
        //centerPanel configuration
        centerPanel.setLayout(new GridLayout(1,3));
        centerPanel.add(leftPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(rightPanel);

        //southPanel configuration
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(learnBySpacedRepetitionButton);
        southPanel.add(learnButton);


        //mainTitlePanel configuration
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainTitle.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(13));
        titlePanel.add(mainTitle);

        //left-right panel configuration
        leftPanel.setLayout(new BorderLayout());
        rightPanel.setLayout(new BorderLayout());
        leftPanel.add(leftScrollPane,BorderLayout.CENTER);
        leftPanel.add(leftText,BorderLayout.NORTH);
        rightPanel.add(rightScrollPane,BorderLayout.CENTER);
        rightPanel.add(rightText,BorderLayout.NORTH);


        //ScrollPane configuration
        leftScrollPane.setViewportView(notSelectedListItem);
        rightScrollPane.setViewportView(selectedListItem);

        //buttonPanel configuration
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
        MainColorPanel tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(ManageButton button: vListButton){
            button.setFont(new Font("Open Sans ExtraBold", Font.PLAIN,22));
            tempPanel.add(button);
        }
        buttonPanel.add(tempPanel);
    }

    private void addListener(){
        ChoicePanelListener choicePanelListener = new ChoicePanelListener(this);
        for(ManageButton button: vListButton){
            button.addActionListener(choicePanelListener);
        }
        learnButton.addActionListener(choicePanelListener);
        learnBySpacedRepetitionButton.addActionListener(choicePanelListener);
    }


    public void refresh(){
        rightScrollPane.setViewportView(selectedListItem);
        leftScrollPane.setViewportView(notSelectedListItem);
        repaint();
        revalidate();
    }

    public Vector<String> getvSelectedListName() {
        return vSelectedListName;
    }

    public Vector<String> getvNotSelectedListName() {
        return vNotSelectedListName;
    }

    public CustomList getNotSelectedListItem() {
        return notSelectedListItem;
    }

    public CustomList getSelectedListItem() {
        return selectedListItem;
    }

    public LearnPanel getThisLearnPanel() {
        return thisLearnPanel;
    }
}
