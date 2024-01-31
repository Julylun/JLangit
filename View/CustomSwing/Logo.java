package View.CustomSwing;

import javax.swing.*;
import java.awt.*;

public class Logo extends JLabel {
    ImageIcon img;
    public Logo(String imgPath){
        img = new ImageIcon(this.getClass().getResource(imgPath));
    }
    public void scale(int width, int height){
        Image modifier = img.getImage();
        img = new ImageIcon(modifier.getScaledInstance(width,height,Image.SCALE_SMOOTH));
    }
    public void setLogo(){
        this.setIcon(img);
    }
}





