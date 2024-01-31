package View.SubFrame;

import Model.Color.DefaultColor;
import View.CustomSwing.ViewMenuButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadingFrame extends JFrame {
    private JProgressBar progressBar;
    private JLabel countingLabel;
    private DefaultColor defaultColor = new DefaultColor();
    private int maxCounting;
    private JPanel textPanel;
    private JPanel progressBarPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JButton cancelButton;
    private boolean isStop = false;
    public LoadingFrame() {
        init();
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBackground(defaultColor.getSubBackgroundColor());
        textPanel.add(countingLabel);
        progressBarPanel.add(progressBar);

        buttonPanel.add(cancelButton);

        mainPanel.add(textPanel);
        mainPanel.add(progressBarPanel);
        mainPanel.add(buttonPanel);

        mainPanel.setBackground(defaultColor.getSubBackgroundColor());
        setBackground(defaultColor.getSubBackgroundColor());
        getContentPane().setBackground(defaultColor.getSubBackgroundColor());
        add(mainPanel);
        setUndecorated(true);
        setSize(200,100);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void init(){
        textPanel = new JPanel();
        progressBarPanel = new JPanel();
        buttonPanel = new JPanel();
        mainPanel = new JPanel();
        countingLabel = new JLabel("Adding word (0/0)");
        progressBar = new JProgressBar();
        cancelButton = new JButton("Cancel");

        //Set something relate to cancel button
        cancelButton.setContentAreaFilled(false);
        cancelButton.setOpaque(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 10));
        cancelButton.setBorder(new EmptyBorder(0,0,0,0));
        cancelButton.setForeground(defaultColor.getFontColor());
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStop = true;
            }
        });

        //

        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        countingLabel.setForeground(defaultColor.getFontColor());
        progressBar.setBackground(defaultColor.getMainBackgroundColor());
        progressBar.setForeground(defaultColor.getPressedButton());

        textPanel.setBackground(defaultColor.getSubBackgroundColor());
        progressBarPanel.setBackground(defaultColor.getSubBackgroundColor());
        buttonPanel.setBackground(defaultColor.getSubBackgroundColor());
        mainPanel.setBackground(defaultColor.getSubBackgroundColor());
        countingLabel.setBackground(defaultColor.getSubBackgroundColor());

    }
    public void setMax(int max){
        maxCounting = max;
        progressBar.setMinimum(0);
        progressBar.setMaximum(max);
    }
    public void setValue(int value){
        progressBar.setValue(value);
        countingLabel.setText("Adding word (" + value + "/" + maxCounting+ ")");
        countingLabel.paintImmediately(countingLabel.getVisibleRect());
        repaint();
       // revalidate();

    }

    public boolean isStop() {
        return isStop;
    }
}
