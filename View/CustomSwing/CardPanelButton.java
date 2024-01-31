package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardPanelButton extends JButton implements MouseListener {
    private DefaultColor defaultColor = new DefaultColor();
    private JLabel label;
    public Color backgroundColor;
    public CardPanelButton(String str){
        super(str);
        setBackground(defaultColor.darkPurpleColor);
        setPreferredSize(new Dimension(300,300));
        setFocusPainted(false);
        setForeground(defaultColor.getFontColor());
        setFont(new Font("Open Sans ExtraBold", Font.PLAIN,22));
        addMouseListener(this);
    }


    private void init(){
        backgroundColor = Color.white;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        setBackground(defaultColor.getPressedButton());
        setPreferredSize(new Dimension(400,400));
        repaint();
        revalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setBackground(defaultColor.getOnButton());
        setPreferredSize(new Dimension(300,300));
        repaint();
        revalidate();

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBackground(defaultColor.getOnButton());
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBackground(backgroundColor);
    }
}
