package Model.Color;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DefaultFont {
    public DefaultFont(){
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Opensans/static/OpenSans-ExtraBold.ttf"));
            Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Opensans/static/OpenSans-Italic.ttf"));
            Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/Opensans/static/OpenSans-Light.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Font getFont_OPENSANS_EXTRABOLD(int fontSize){
        return new Font("Open Sans ExtraBold", Font.PLAIN,fontSize);
    }

    public Font getFont_OPENSANS_ITALIC(int fontSize){
        return new Font("Open Sans Italic", Font.PLAIN,fontSize);
    }

    public Font getFont_OPENSANS_LIGHT(int fontSize){
        return new Font("Open Sans Light", Font.PLAIN,fontSize);
    }
}
