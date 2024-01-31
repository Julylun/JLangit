package Controller;

import View.MainMenu;
import com.sun.tools.javac.Main;

import java.awt.*;

public class ScreenDetails {
    private int height, width, scaleFactor;
    private Dimension frameDimension;
    private MainMenu mainMenu;
    private Dimension screenSize;
    private Rectangle windowsSize;
    private int taskBarHeight;
    private Insets defaultMainFrameInsets;
    public ScreenDetails(){}
    public ScreenDetails(MainMenu mainMenu){
        this.mainMenu = mainMenu;
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();;
        this.windowsSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        taskBarHeight = (int) (screenSize.getHeight() - windowsSize.getHeight());
    }

    public void debugClassInfo(){
        System.out.println("[ScreenDetails Class Debugging] -> " + this.toString());
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    @Override
    public String toString() {
        return "ScreenDetails{" +
                "height=" + height +
                ", width=" + width +
                ", scaleFactor=" + scaleFactor +
                ", frameDimension=" + frameDimension +
                ", taskBarHeight=" + taskBarHeight +
                '}';
    }

    public void setDataFromFrameDimension(){
        //Set MainMenu frame to visible to get screen size then set false and use that screen size to set attributes for this class
        mainMenu.setVisible(true);
        this.frameDimension = mainMenu.getSize();
        defaultMainFrameInsets = mainMenu.getInsets();
        mainMenu.setVisible(false);
        setHeight((int) frameDimension.getHeight());
        setWidth((int) GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().getWidth());
        //Use class ScaleAnalyzing to detect screen dpi and set it to attribute scaleFactor
        setScaleFactor(new ScaleAnalyzing().dpiToScaleFactor());

    }
    public void setDataFromFrameDimension(Dimension frameDimension) {
        this.frameDimension = frameDimension;
    }

    public void setFrameDimension(Dimension frameDimension) {
        this.frameDimension = frameDimension;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setScaleFactor(int scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Dimension getFrameDimension() {
        return frameDimension;
    }

    public int getHeight() {
        return height;
    }

    public int getScaleFactor() {
        return scaleFactor;
    }

    public int getWidth() {
        return width;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public int getTaskBarHeight() {
        return taskBarHeight;
    }

    public Insets getDefaultMainFrameInsets() {
        return defaultMainFrameInsets;
    }
}
