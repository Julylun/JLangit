package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomColorMessage extends JFrame {
    WhiteLabel messageLabel;
    ManageButton button;
    String message;
    JPanel jPanel;
    public CustomColorMessage(String message, Color color){
        this.message = message;
        init();
        setUndecorated(true);
        getContentPane().setBackground(color);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.setBackground(color);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(messageLabel);
        jPanel.add(button);
        add(jPanel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomColorMessage.this.dispose();
            }
        });
        setSize(200,60);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void init(){
        messageLabel = new WhiteLabel(message);
         button = new ManageButton("Okay");
        jPanel = new JPanel();
    }
}
