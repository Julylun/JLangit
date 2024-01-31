package View.CustomSwing;

import Model.Color.DefaultColor;
import View.MainMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ViewMenuButton extends JButton {
    private DefaultColor defaultColor = new DefaultColor();
    private Color noOnButton = defaultColor.getNoOnButton();
    private Color onButton = defaultColor.getOnButton();
    private Color pressedButton = defaultColor.getPressedButton();
    private int fontSize = 20;
    public ViewMenuButton(String name){
        super(name);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorder(new EmptyBorder(20,20,20,20));
        setForeground(noOnButton);
        setHorizontalAlignment(SwingConstants.LEFT);
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Opensans/static/OpenSans-ExtraBold.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFont(new Font("Open Sans ExtraBold", Font.PLAIN,fontSize));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ViewMenuButton.this.setForeground(onButton);
                ViewMenuButton.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
                setFont(new Font("Open Sans ExtraBold", Font.PLAIN,fontSize+1));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ViewMenuButton.this.setForeground(noOnButton);
                ViewMenuButton.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                setFont(new Font("Open Sans ExtraBold", Font.PLAIN,fontSize));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ViewMenuButton.this.setForeground(pressedButton);
                setFont(new Font("Open Sans ExtraBold", Font.PLAIN,fontSize-1));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ViewMenuButton.this.setForeground(onButton);
                setFont(new Font("Open Sans ExtraBold", Font.PLAIN,fontSize+1));
            }
        });
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[Button " + name + " Debugging] -> is pressed");
            }
        });
    }
}
