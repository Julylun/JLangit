package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ScrollBarUI extends BasicScrollBarUI {
    private DefaultColor defaultColor = new DefaultColor();
    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = defaultColor.getScrollBarColor();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(defaultColor.getScrollBarColor());
        return button;
    }
    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(defaultColor.getScrollBarColor());
        button.setForeground(Color.white);
        return button;
    }
}
