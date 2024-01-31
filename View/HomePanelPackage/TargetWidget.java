package View.HomePanelPackage;

import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import View.HomePanel;
import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Calendar;

public class TargetWidget extends JPanel implements MouseListener {
    private DefaultColor defaultColor = new DefaultColor();
    private int length;
    private int learnedWordNumber;
    private HomePanel thisHomePanel;
    private int aimNumber;
    public TargetWidget(int length, HomePanel homePanel){
        thisHomePanel = homePanel;
        aimNumber = thisHomePanel.getThisMainMenu().managePanel.getDataAnalyzing().getTarget();
        this.length = length;
        //setBackground(defaultColor.FONT_COLOR_2);

        Calendar calendar = Calendar.getInstance();
//        learnedWordNumber = (int) thisHomePanel.getThisMainMenu().managePanel.getDataAnalyzing().getNumberLearnedWordInMonth(calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.YEAR)).get(calendar.get(Calendar.DAY_OF_MONTH)-1);
        learnedWordNumber = thisHomePanel.getThisMainMenu().managePanel.getDataAnalyzing().getNumberLearnedWordInDay(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR));
        System.out.println("<TargetWidget>[Constructor]: learnWordNumber = " + learnedWordNumber);
        repaint();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("<TargetWidget>[painComponent]: painting..");
        Graphics2D graphics2D = (Graphics2D) g.create();
        TargetWidget.this.setPreferredSize(new Dimension(length,length));
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(new DefaultColor().TARGET_PANEL);
        System.out.println("<TargetWidget>[painComponent]: Size = " + getSize());
        Dimension panelSize = getSize();

//        Create Round rectangle background
        graphics2D.fill(new Area(new RoundRectangle2D.Double((panelSize.width - length)/2,(panelSize.height-length)/2 ,length,length,50,50)));
        graphics2D.setColor(new DefaultColor().TARGET_EMPTY_CIRCLE);

//        Create target journey
        int extentNumber = 0;
        extentNumber = (int)((((double)(360/(double)aimNumber))*(double) (learnedWordNumber)));
        if(extentNumber > 360) extentNumber = 360;
        System.out.println("<TargetWidget>[@Override - painComponent]: extentNumber = " + extentNumber);
        Arc2D arc2D = new Arc2D.Double((panelSize.width-length)/2+length/10,(panelSize.height-length)/2+length/10,length-length/10*2,length-length/10*2,0,360, Arc2D.OPEN);
        graphics2D.setStroke(new BasicStroke(10));
        graphics2D.draw(arc2D);
        arc2D = new Arc2D.Double((panelSize.width-length)/2+length/10,(panelSize.height-length)/2+length/10,length-length/10*2,length-length/10*2,90,-(extentNumber), Arc2D.OPEN);

//        Create target string
        graphics2D.setColor(new DefaultColor().getFontColor());
        graphics2D.setFont(new DefaultFont().getFont_OPENSANS_EXTRABOLD(15));
        String targetString = "You learned " + learnedWordNumber + "/" + aimNumber + " words today";
        FontMetrics fontMetrics = graphics2D.getFontMetrics(); //This use to measure text size
        graphics2D.drawString(targetString, (panelSize.width - fontMetrics.stringWidth(targetString))/2, (panelSize.height - fontMetrics.getHeight())/2);
        graphics2D.draw(arc2D);

        graphics2D.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
