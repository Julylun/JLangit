package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private int shadowLength = 20;
    private DefaultColor defaultColor = new DefaultColor();
    public RoundedPanel(){
        setOpaque(false);
    }
    public RoundedPanel(Color backColor){
        setOpaque(false);
    }
    public RoundedPanel(int topLeft, int topRight, int botLeft, int botRight){
        this();
        this.roundTopRight = topRight;
        this.roundTopLeft = topLeft;
        this.roundBottomLeft = botLeft;
        this.roundBottomRight = botRight;
    }
    public RoundedPanel(int topLeft, int topRight, int botLeft, int botRight, Color color){
        this(topLeft,topRight,botLeft,botRight);
        setBackground(color);

    }
    public void setRound(int topLeft, int topRight, int botLeft, int botRight){
        this.roundTopRight = topRight;
        this.roundTopLeft = topLeft;
        this.roundBottomLeft = botLeft;
        this.roundBottomRight = botRight;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        //Create graphic object
        System.out.println("[RoundedPanel Debugging]: width:" + getWidth() + " - height: " + getHeight());
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setBackground(getBackground());
        //Create shadow
        if(shadowLength > 0) {
            int width = RoundedPanel.this.getWidth();
            int height = RoundedPanel.this.getHeight();
            RoundedPanel.this.setPreferredSize(new Dimension(width + shadowLength, height));
            //Area _are = new Area(new Rectangle2D.Double(0,0,width,))
            int roundX = Math.min(width,roundTopRight);
            int roundY = Math.min(height,roundTopRight);
            graphics2D.setColor(defaultColor.getMainBackgroundColor());
            graphics2D.fill(new Area(new Rectangle2D.Double(0,0,width+shadowLength,height)));
            Area _area = new Area(new RoundRectangle2D.Double(0,0,width+shadowLength,height,roundX,roundY));
            GradientPaint gp = new GradientPaint(0, 0, Color.BLACK, width, 0, defaultColor.getMainBackgroundColor());
            graphics2D.setPaint(gp);
            graphics2D.fill(_area);
            graphics2D.dispose();
        }
        RoundedPanel.this.setPreferredSize(new Dimension(RoundedPanel.this.getWidth(),RoundedPanel.this.getHeight()));
        graphics2D = (Graphics2D) g.create();
        graphics2D.setColor(defaultColor.getSubBackgroundColor());
        Area area = new Area(createRoundTopLeft());
        if(roundBottomRight > 0) area.intersect(new Area(createRoundBottomRight()));
        if(roundBottomLeft > 0) area.intersect(new Area(createRoundBottomLeft()));
        if(roundTopRight > 0) area.intersect(new Area(createRoundTopRight()));
        graphics2D.fill(area);
        graphics2D.dispose();
        super.paintComponent(g);
    }

    private Shape createRoundTopLeft(){
        int width = getWidth()-shadowLength;
        int height = getHeight();
        int roundX = Math.min(width,roundTopLeft);
        int roundY = Math.min(height,roundTopLeft);
        Area area = new Area(new RoundRectangle2D.Double(0,0,width,height,roundX,roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }
    private Shape createRoundTopRight() {
        int width = getWidth()-shadowLength;
        int height = getHeight();
        int roundX = Math.min(width, roundTopRight);
        int roundY = Math.min(height, roundTopRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, roundY / 2, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomLeft() {
        int width = getWidth()-shadowLength;
        int height = getHeight();
        int roundX = Math.min(width, roundBottomLeft);
        int roundY = Math.min(height, roundBottomLeft);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(roundX / 2, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    private Shape createRoundBottomRight() {
        int width = getWidth()-shadowLength;
        int height = getHeight();
        int roundX = Math.min(width, roundBottomRight);
        int roundY = Math.min(height, roundBottomRight);
        Area area = new Area(new RoundRectangle2D.Double(0, 0, width, height, roundX, roundY));
        area.add(new Area(new Rectangle2D.Double(0, 0, width - roundX / 2, height)));
        area.add(new Area(new Rectangle2D.Double(0, 0, width, height - roundY / 2)));
        return area;
    }

    public int getRoundTopRight() {
        return roundTopRight;
    }

    public int getRoundTopLeft() {
        return roundTopLeft;
    }

    public int getRoundBottomRight() {
        return roundBottomRight;
    }

    public int getRoundBottomLeft() {
        return roundBottomLeft;
    }

    public void setRoundTopRight(int roundTopRight) {
        this.roundTopRight = roundTopRight;
        repaint();
    }

    public void setRoundTopLeft(int roundTopLeft) {
        this.roundTopLeft = roundTopLeft;
        repaint();
    }

    public void setRoundBottomRight(int roundBottomRight) {
        this.roundBottomRight = roundBottomRight;
        repaint();
    }

    public void setRoundBottomLeft(int roundBottomLeft) {
        this.roundBottomLeft = roundBottomLeft;
        repaint();
    }
}
