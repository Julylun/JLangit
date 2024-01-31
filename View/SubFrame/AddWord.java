package View.SubFrame;

import Controller.Listener.AddWordButtonListener;
import Model.Color.DefaultColor;
import Model.Word;
import View.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.time.temporal.Temporal;
import java.util.Vector;

/**The {@code AddWord} is used to display a frame using for type the input for adding and editing word. This extends the class {@linkplain JFrame}
 *
 * @author JulyLun (Hoang Luan)
 */
public class AddWord extends JFrame {
    static final int TEXTFIEDSIZE = 30;
    private JTextField wordTF;
    private JTextField meaningTF;
    private JComboBox type;
    private JTextField phoneticTF;
    private Vector <JLabel> vLabel;
    private JPanel wordPanel, meaningPanel, typePanel, phoneticPanel, controlPanel, errorPanel;
    private DefaultColor defaultColor = new DefaultColor();
    private String[] labelName = {"Word", "Meaning", "Phonetic", "Type"};
    private String[] typeListName = {"Noun", "Verb", "Adjective", "Adverb","Preposition","Determiner","Pronoun","Conjunction","Interjection","Other"};
    private Component parentComponent;
    private JButton okButton, cancelButton;
    private int method;
    public AddWord(Component component, int method){
        this.parentComponent = component;
        this.method = method;
        init();
        setPanelItem();
        setUndecorated(true);
        setBackground(defaultColor.getSubBackgroundColor());
        setLayout(new GridLayout(5,1));
        add(wordPanel);
        add(meaningPanel);
        add(phoneticPanel);
        add(typePanel);
        add(controlPanel);

        addListener();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void setPanelItem(){
        //Set panel layout
        wordPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        meaningPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        phoneticPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        wordPanel.setBackground(defaultColor.getSubBackgroundColor());
        meaningPanel.setBackground(defaultColor.getSubBackgroundColor());
        typePanel.setBackground(defaultColor.getSubBackgroundColor());
        phoneticPanel.setBackground(defaultColor.getSubBackgroundColor());
        controlPanel.setBackground(defaultColor.getSubBackgroundColor());


        //
        wordTF.setColumns(TEXTFIEDSIZE);
        meaningTF.setColumns(TEXTFIEDSIZE);
        phoneticTF.setColumns(TEXTFIEDSIZE);


        //Add item into panel
        wordPanel.add(vLabel.get(0));
        wordPanel.add(wordTF);
        meaningPanel.add(vLabel.get(1));
        meaningPanel.add(meaningTF);
        phoneticPanel.add(vLabel.get(2));
        phoneticPanel.add(phoneticTF);
        typePanel.add(vLabel.get(3));
        typePanel.add(type);
        controlPanel.add(cancelButton);
        controlPanel.add(okButton);
    }
    private void init(){
        vLabel = new Vector<>();
        for (String name: labelName){
            vLabel.add(new JLabel(name));
            vLabel.lastElement().setForeground(defaultColor.getFontColor());
        }
        wordTF = new JTextField();
        meaningTF = new JTextField();
        phoneticTF = new JTextField();
        type = new JComboBox(typeListName);
        wordPanel = new JPanel();
        typePanel = new JPanel();
        meaningPanel = new JPanel();
        phoneticPanel = new JPanel();
        controlPanel = new JPanel();
        errorPanel = new JPanel();
        okButton = new JButton("Okay");
        cancelButton = new JButton("Cancel");

    }

    /**This take all input and cast it into a {@linkplain Word}
     *
     * @return {@linkplain Word}
     */
    public Word getWord(){
        Word tempWord = new Word(-1,wordTF.getText(),meaningTF.getText(),type.getSelectedIndex(),phoneticTF.getText());
        return tempWord;
    }
    private void addListener(){
        okButton.addActionListener(new AddWordButtonListener(this));
        cancelButton.addActionListener(new AddWordButtonListener(this));
    }

    /**Giving you a way to analyze word. We have two ways are editing word and add word.
     *
     *
     * @return int
     */
    public int getMethod() {
        return method;
    }

    /**
     *
     * @return
     */
    public Component getParentComponent() {
        return parentComponent;
    }
}
