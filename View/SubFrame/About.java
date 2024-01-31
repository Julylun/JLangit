package View.SubFrame;

import Model.Color.DefaultFont;
import View.CustomSwing.Logo;
import View.CustomSwing.SubColorPanel;
import View.CustomSwing.ManageButton;
import View.CustomSwing.WhiteLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame {
    private SubColorPanel panelMain, panelBottom;
    private ManageButton buttonOkay;
    private WhiteLabel title, content;
    private DefaultFont defaultFont = new DefaultFont();
    private Logo logo = new Logo("../../Resources/poorteamLogo.png");
    public About(){
        init();
        setLayout(new BorderLayout());
        add(panelMain, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);


        panelBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelBottom.add(buttonOkay);

        title.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        content.setFont(defaultFont.getFont_OPENSANS_ITALIC(15));
        panelMain.setLayout(null);
        title.setBounds(30,5,400,100);
        content.setBounds(30,40, 400,100);
        logo.setBounds(340,10,40,40);
        logo.scale(40,40);
        logo.setLogo();

        panelMain.add(title);
        panelMain.add(content);
        panelMain.add(logo);

        buttonOkay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                About.this.dispose();
            }
        });
        setUndecorated(true);
        setSize(400,250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init(){
        panelMain = new SubColorPanel();
        panelBottom = new SubColorPanel();
        buttonOkay = new ManageButton("Okay");
        title = new WhiteLabel("About");
        content = new WhiteLabel("<HTML>JLangit is buit in 23/1/2024 by Hoang Lun :D. </br>@poorteam2024</HTML>");
    }
}
