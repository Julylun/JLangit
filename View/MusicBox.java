package View;

import View.CustomSwing.RoundButton;

import javax.swing.*;
import java.awt.*;

public class MusicBox extends JFrame {
    private JTable name;
    private String[] buttonName = {"Previous", "Pause/Continue","Next"};
    private JPanel buttonPanel;
    public MusicBox(){
        init();
        setLayout(new GridLayout(2,1));
        add(new JLabel("Example"));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(new RoundButton("1",20,0));
        buttonPanel.add(new RoundButton("2",20,0));
        buttonPanel.add(new RoundButton("3",20,0));
        add(buttonPanel);
        pack();
        setVisible(true);
    }
    private void init(){
        buttonPanel = new JPanel();
    }
}
