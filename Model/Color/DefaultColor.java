package Model.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

/**The {@code DefaultColor} is used to contain many colors used in {@code JLangit} project. Umm it just contain constant color using
 *class {@link Color}
 * @version     4/1/2024
 * @author      Julylun (Hoang Luan)
 */
public class DefaultColor {
//    Debugging Color
    public String RESET = "\u001B[0m";
    public String RED = "\u001B[31m";
    public String GREEN = "\u001B[32m";
    public String YELLOW = "\u001B[33m";

//    Componet Color
    private Color mainBackgroundColor = new Color(39,38,38);
    private Color subBackgroundColor = new Color(27,29,33);
    public Color FLASH_CARD = new Color(99, 128, 107);
    public Color LIST_COLOR = new Color(62, 61, 61);
    private Color gridColor = new Color(59, 44, 23);

//    Font Color
    private Color fontColor = new Color(255,255,255);
    public Color FONT_COLOR_2 = new Color(69, 126, 255);

//    UI Color
    private Color scrollBarColor = new Color(50,50,50);
    private Color searchBarColor = new Color(76, 76, 76);
    private Color noOnButton = new Color(77, 82, 77);
    private Color onButton = new Color(255,199,69);
    private Color pressedButton = new Color(124,164,255);
    final public Color TARGET_PANEL = new Color(36, 48, 64);
    final public Color TARGET_EMPTY_CIRCLE = new Color(30, 40, 54);
    final public Color HOME_LEARN_BUTTON_TRANS = new Color(67, 122, 135,99);
    final public Color HOME_MANAGE_BUTTON_TRANS = new Color(189, 121, 55, 99);
    final public Color HOME_SETTING_BUTTON_TRANS = new Color(141, 67, 41,99);
    final public Color HOME_EXIT_BUTTON_TRANS = new Color(99, 160, 97,99);

    final public Color HOME_LEARN_BUTTON = new Color(67, 122, 135);
    final public Color HOME_MANAGE_BUTTON = new Color(189, 121, 55);
    final public Color HOME_SETTING_BUTTON = new Color(141, 67, 41);
    final public Color HOME_EXIT_BUTTON = new Color(99, 160, 97);

//    Journey color
    final public Color JOURNEY_FONT_1 = new Color(0,0,0,50);
    final public Color JOURNEY_LOSER = new Color(36, 48, 64);
    final public Color JOURNEY_BADGUY = new Color(38, 73, 26);
    final public Color JOURNEY_LAZYGUY = new Color(62, 118, 42);
    final public Color JOURNEY_GOODBOY = new Color(102, 179, 76);
    final public Color JOURNEY_LEARNER = new Color(76, 179, 138);
    final public Color JOURNEY_SUPERLEARNER = new Color(76, 114, 179);
    final public Color JOURNEY_GODOFLANGUAGE = new Color(197, 201, 59);

//    final public Color

//    Custom Color
    public Color darkPurpleColor = new Color(57, 36, 103);
    public Color darkGreenPastelColor = new Color(79, 111, 82);
    public Color darkRedColor = new Color(125, 10, 10);

    public Color getSearchBarColor() {
        return searchBarColor;
    }

    public Color getNoOnButton() {
        return noOnButton;
    }

    public Color getOnButton() {
        return onButton;
    }

    public Color getPressedButton() {
        return pressedButton;
    }

    public Color getMainBackgroundColor() {
        return mainBackgroundColor;
    }

    public Color getSubBackgroundColor() {
        return subBackgroundColor;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getScrollBarColor() {
        return scrollBarColor;
    }
}
