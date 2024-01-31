package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;

public class WhiteLabel extends JLabel {
    public WhiteLabel(){
        setForeground(new DefaultColor().getFontColor());
    }

    public WhiteLabel(String text){
        this();
        setText(text);
    }
}
