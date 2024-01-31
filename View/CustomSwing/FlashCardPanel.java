package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class FlashCardPanel extends JFrame {
    FlashCardPanel(){
        setBackground(new DefaultColor().FLASH_CARD);
    }
}
