package View.CustomSwing;

import Model.Color.DefaultColor;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class JulyFileChooser extends JFileChooser {
    private DefaultColor defaultColor = new DefaultColor();
    public JulyFileChooser(){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            updateUI();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setBackground(defaultColor.getSubBackgroundColor());
    }
}
