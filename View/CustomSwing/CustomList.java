package View.CustomSwing;

import Model.Color.DefaultColor;
import Model.Color.DefaultFont;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CustomList extends JList {
    private DefaultColor defaultColor = new DefaultColor();
    private DefaultFont defaultFont = new DefaultFont();
    public CustomList(Vector vector){
        setListData(vector);
        setForeground(defaultColor.getFontColor());
        setBackground(defaultColor.LIST_COLOR);
        setSelectionBackground(defaultColor.getOnButton());
        setSelectionForeground(defaultColor.FONT_COLOR_2);
        setFocusable(false);
        setFont(defaultFont.getFont_OPENSANS_LIGHT(14));
    }
}
