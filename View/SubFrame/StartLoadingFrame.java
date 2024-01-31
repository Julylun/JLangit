package View.SubFrame;

import Model.Color.DefaultColor;
import View.CustomSwing.Logo;

import javax.swing.*;
import java.awt.*;

public class StartLoadingFrame extends JFrame {
    private DefaultColor defaultColor = new DefaultColor();
    private JLabel loadingText;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private Logo loadingImage = new Logo("../../Resources/loadingImage.png");
    public StartLoadingFrame(){
        loadingImage.scale(180,120);
        loadingImage.setLogo();

        init();
        panelConfiguration();
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        setBackground(defaultColor.FONT_COLOR_2);
        setUndecorated(true);
        setSize(250,150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void init(){
        loadingText = new JLabel("I'm reading your cute brain ._ .!");
//        loadingImage
        topPanel = new JPanel();
        bottomPanel = new JPanel();
    }

    private void panelConfiguration(){
        bottomPanel.setBackground(defaultColor.FONT_COLOR_2);
        bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(loadingText);

        topPanel.setBackground(Color.white);
        topPanel.add(loadingImage);
    }
    public void setProgress(String str){
        loadingText.setText(str);
        loadingText.paintImmediately(loadingText.getVisibleRect());
    }
}
