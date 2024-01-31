package View.CustomSwing;

import Model.Color.DefaultColor;
import Model.Color.DefaultFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;

public class BorderButton extends JButton implements MouseListener {
    private Color buttonColor;
    private Color onButtonColor;
    private Color colorSet;
    private int status = 0;
    public BorderButton(String name, Color onColor, Color color){
        super(name);
        buttonColor = color;
        onButtonColor = onColor;
        colorSet = buttonColor;
        setBackground(color);
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setText(name);
        setName(name);
        setFont(new DefaultFont().getFont_OPENSANS_EXTRABOLD(20));
        setForeground(new DefaultColor().getFontColor());
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        Dimension size = getSize();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(new DefaultColor().getMainBackgroundColor());
        graphics2D.fillRect(0,0,size.width,size.height);
//        System.out.println("<BorderButton>[@Override - painComponent]: Name= " + getName() + " || Size=" + size);
        if(status == 0)
        graphics2D.setColor(buttonColor);
        else if(status == 1) graphics2D.setColor(onButtonColor);
        else if(status == 2) graphics2D.setColor(new DefaultColor().getFontColor());
        graphics2D.setStroke(new BasicStroke(7));
        graphics2D.drawRoundRect(0,0,(int)size.getWidth(),(int)size.getHeight(),0,0);
        graphics2D.fillRect(0,0,(int)size.getWidth(),(int)size.getHeight());
        super.paintComponent(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        status = 1;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        status = 2;
        setForeground(onButtonColor);
        repaint();
        setSelected(true);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        status = 0;
        setForeground(new DefaultColor().getFontColor());
        repaint();
        setSelected(false);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        status = 1;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        status = 0;
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        repaint();
    }
}
