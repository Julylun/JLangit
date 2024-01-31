package View.CustomSwing;

import Model.Color.DefaultColor;
import View.MainMenu;
import View.ManagePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageButton extends JButton {

    public ManageButton(String name){
        super(name);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorder(new EmptyBorder(20,20,20,20));
        setForeground(new DefaultColor().getFontColor());
        setHorizontalAlignment(SwingConstants.LEFT);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ManageButton.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setForeground(new DefaultColor().getOnButton());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ManageButton.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setForeground(Color.white);
            }
            });
    }


}
