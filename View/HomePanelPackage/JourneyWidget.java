package View.HomePanelPackage;

import Controller.DataAnalyzing;
import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import View.CustomSwing.SubColorPanel;
import View.HomePanel;
import com.sun.java.swing.plaf.windows.WindowsMenuItemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.time.Instant;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Vector;

public class JourneyWidget extends JPanel {
    private DefaultColor defaultColor = new DefaultColor();
    private DefaultFont defaultFont = new DefaultFont();
    private HomePanel thisHomePanel;
    private Calendar calendar;
    private int numberOfDay;
    private int month;
    private int day;
    private DataAnalyzing dataAnalyzing;
    public JourneyWidget(HomePanel homePanel){
        thisHomePanel = homePanel;
        init();
    }

    private void init(){
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        numberOfDay = YearMonth.of(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1).lengthOfMonth();
        System.out.println("<JourneyWidget>[init]: numberOfDay=" + numberOfDay);
        System.out.println("<JourneyWidget>[init]: day=" + day);
        dataAnalyzing = thisHomePanel.getThisMainMenu().managePanel.getDataAnalyzing();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        Dimension size = getSize();
        System.out.println("<JourneyWidget>[@Override - painComponent]: size=" + size);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//        Create background
        graphics2D.setColor(new DefaultColor().getSubBackgroundColor());
        graphics2D.fill(new Area(new RoundRectangle2D.Double(0,0,size.width ,size.height,20,20)));



//        Draw a text "Your journey"
        graphics2D.setColor(defaultColor.getFontColor());
        graphics2D.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        String journeyText = "YOUR JOURNEY";
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        graphics2D.drawString(journeyText,(size.width-fontMetrics.stringWidth(journeyText))/2,size.height/15);



//         Journey days area Components can put in size.width/20 -> size.width-size.width/20 | size.height/15+ fontMetrics.stringHeight
        graphics2D.drawString(journeyText,(size.width-fontMetrics.stringWidth(journeyText))/2,size.height/15);

//        Draw journey days
//        size = (height-100)/5;
//        x space = (width-size*7/9);
        int penX, penY;
        penX = size.width/20;
        penY = size.height/15+ fontMetrics.getHeight();
        int daySize = (getHeight()-100)/5;
        int maxWidth = (int) (size.getWidth()-size.getWidth()/20);
        Vector<Integer> vNumberOfLearnedWord = dataAnalyzing.getNumberLearnedWordInMonth(month,calendar.get(Calendar.YEAR));
        String dateText;
        graphics2D.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        fontMetrics = graphics2D.getFontMetrics();
        for(int index = 1; index <= numberOfDay; index++){
            if(vNumberOfLearnedWord.get(index-1) < 10) graphics2D.setColor(defaultColor.JOURNEY_LOSER);
            else if(vNumberOfLearnedWord.get(index-1) < 20) graphics2D.setColor(defaultColor.JOURNEY_BADGUY);
            else if(vNumberOfLearnedWord.get(index-1) < 50) graphics2D.setColor(defaultColor.JOURNEY_LAZYGUY);
            else if(vNumberOfLearnedWord.get(index-1) < 150) graphics2D.setColor(defaultColor.JOURNEY_GOODBOY);
            else if(vNumberOfLearnedWord.get(index-1) < 300) graphics2D.setColor(defaultColor.JOURNEY_LEARNER);
            else if(vNumberOfLearnedWord.get(index-1) < 2000) graphics2D.setColor(defaultColor.JOURNEY_SUPERLEARNER);
            else if(vNumberOfLearnedWord.get(index-1) < 5000) graphics2D.setColor(defaultColor.JOURNEY_GODOFLANGUAGE);
            graphics2D.fillRoundRect(penX,penY,daySize,daySize,10,10);
            if(index == day) graphics2D.setColor(defaultColor.getFontColor());
            else graphics2D.setColor(defaultColor.JOURNEY_FONT_1);
            dateText = index + "/" + month;
            graphics2D.drawString(dateText, penX+ (daySize - fontMetrics.stringWidth(dateText))/2,penY+ (daySize - fontMetrics.getHeight())/2);
            penX+= ((size.getWidth()-(size.getWidth()/20)*2)-daySize*6)/6;
            if(penX + daySize > maxWidth){
                penX = size.width/20;
                penY+= daySize+20;
            }
        }


    }
}
