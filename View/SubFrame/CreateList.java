package View.SubFrame;

import Controller.Listener.CreateListListener;

import javax.swing.*;
import java.awt.*;

public class CreateList extends JFrame {
    private JLabel nameLabel, errorLabel;
    private JTextField nameTF;
    private JButton createButton, cancelButton;
    private JPanel mainPanel, buttonPanel;
    private Component parentComponent;
    public CreateList(Component component){
        this.parentComponent = component;
        init();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel,BorderLayout.CENTER);
        getContentPane().add(buttonPanel,BorderLayout.SOUTH);

        buttonPanel.add(cancelButton);
        buttonPanel.add(createButton);
        mainPanel.add(nameLabel);
        mainPanel.add(nameTF);

        addListener();
        setUndecorated(true);
        setSize(300,70);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void init(){
        nameLabel = new JLabel("List name: ");
        errorLabel = new JLabel("");
        nameTF = new JTextField();
        createButton = new JButton("Create");
        cancelButton = new JButton("Cancel");
        mainPanel = new JPanel();
        buttonPanel = new JPanel();


        nameTF.setColumns(15);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    private void addListener(){
        CreateListListener createListListener = new CreateListListener(this);
        cancelButton.addActionListener(createListListener);
        createButton.addActionListener(createListListener);
    }

    public String getText(){
        return nameTF.getText();
    }

    public Component getParentComponent() {
        return parentComponent;
    }
}
