package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;

public class SubColorPanel extends JPanel {
    public SubColorPanel(){
        setBackground(new DefaultColor().getSubBackgroundColor());
    }
}
