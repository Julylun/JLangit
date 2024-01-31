package View.CustomSwing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class RoundButton extends JButton {
    private int radius;
    private int type;
    public RoundButton(String name,int radius, int type){
        this.radius = radius;
        this.type = type;

        //Clear button graphic
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        setBorder(new EmptyBorder(20,20,20,20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Area area = new Area(new Ellipse2D.Double(radius/2,radius/2,radius,radius));
        graphics2D.setStroke(new BasicStroke(3));
       // area.add(new Area(new Line2D.Double(radius/4,radius/4,radius/4,radius/4*3)));
        graphics2D.setColor(Color.white);
        graphics2D.draw(area);
//        graphics2D.fill(area);
        super.paintComponent(g);
    }
}
