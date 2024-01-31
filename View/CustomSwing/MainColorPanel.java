package View.CustomSwing;

import Model.Color.DefaultColor;
import View.MainMenu;

import javax.swing.*;

public class MainColorPanel extends JPanel {
    public MainColorPanel(){
        this.setBackground(new DefaultColor().getMainBackgroundColor());
    }
}
