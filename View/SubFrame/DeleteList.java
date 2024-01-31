package View.SubFrame;

import Controller.Listener.DeleteListListener;
import Model.Color.DefaultColor;

import javax.swing.*;
import javax.swing.text.BoxView;
import javax.swing.text.View;
import View.ManagePanel;
import java.awt.*;

public class DeleteList extends JFrame {
    private DefaultColor defaultColor = new DefaultColor();
    private JLabel listNameLabel;
    private JComboBox<String> listNameCombobox;
    private JButton cancelButton, deleteButton, deleteAllButton;
    private JPanel bottomPanel, centerPanel;
    private Component parentComponent;
    public DeleteList(Component parentComponent){
        this.parentComponent = parentComponent;
        init();
        setPanelConfigurations();
        addComponentsIntoPanels();

        setLayout(new BorderLayout());
        add(centerPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);

        addListener();
        setUndecorated(true);

        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init(){
        listNameCombobox = new JComboBox<String>(((ManagePanel)(parentComponent)).getDataAnalyzing().getAllListInSQLDatabase());
        listNameLabel = new JLabel("List");
        cancelButton = new JButton("Cancel");
        deleteAllButton = new JButton("Delete all");
        deleteButton = new JButton("Delete");
        bottomPanel = new JPanel();
        centerPanel = new JPanel();
    }

    private void addComponentsIntoPanels(){
        centerPanel.add(listNameLabel);
        centerPanel.add(listNameCombobox);
        bottomPanel.add(cancelButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(deleteAllButton);
    }
    private void addListener(){
        DeleteListListener deleteListListener = new DeleteListListener(this);
        deleteButton.addActionListener(deleteListListener);
        cancelButton.addActionListener(deleteListListener);
        deleteAllButton.addActionListener(deleteListListener);
    }

    private void setPanelConfigurations(){
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public String getDeleteSelection(){
        return (String)listNameCombobox.getSelectedItem();
    }

    public Component getParentComponent() {
        return parentComponent;
    }
}
