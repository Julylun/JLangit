package View.LearnPanelPackage;

import Controller.DataAnalyzing;
import Controller.Listener.FlashCardLearningPanelListener;
import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import Model.Word;
import View.CustomSwing.FlashCardPanel;
import View.CustomSwing.MainColorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.Vector;

public class FlashCardLearningPanel extends MainColorPanel {
    /**
     * By {@code NORMAL_LEARNING}, the FlashCardLearningPanel will take all words you choosing to learning
     */
    public static final int NORMAL_LEARNING = 0;
    /**
     * By {@code SPACE_REPETITION_LEARNING} the FlashCardLearningPanel will take all words you choosing then remove
     * words which can't response this method
     * Learn more about Spaced Repetition to know about this method
     */
    public static final int SPACED_REPETITION_LEARNING = 1;
    private DefaultColor defaultColor = new DefaultColor();
    private DefaultFont defaultFont = new DefaultFont();

    private JLabel titleLabel, wordLabel_wordPanel, wordLabel_meaningPanel, meaningLabel, phoneticLabel_wordPanel,
            phoneticLabel_meaningPanel, typeLabel_wordPanel, typeLabel_meaningPanel, lineLabel;
    private ChoicePanel thisChoicePanel;
    private Vector<Word> vWord;
    private MainColorPanel northPanel, southPanel, centerPanel, feedBackButtonPanel, unSecretButtonPanel, wordPanel,
            meaningPanel;
    private JButton unSecretButton;
    private String[] feedBackButtonName = {"Bad","Not bad","Fine","Good", "My crush"};
    private Vector<JButton> vFeedBackButton;
    private int learningIndex;
    private DataAnalyzing dataAnalyzing;
    private int learningType;
    public FlashCardLearningPanel(ChoicePanel choicePanel, int learningType){
        thisChoicePanel = choicePanel;
        this.learningType = learningType;
        init();
        setDefaultPanelConfiguration();

        setBackground(defaultColor.getMainBackgroundColor());
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        dataAnalyzing = thisChoicePanel.getThisLearnPanel().getThisMainMenu().managePanel.getDataAnalyzing();
        addListener();
        next();
    }

    private void init(){
        northPanel = new MainColorPanel();
        unSecretButtonPanel = new MainColorPanel();
        feedBackButtonPanel= new MainColorPanel();
        meaningPanel = new MainColorPanel();
        wordPanel = new MainColorPanel();
        centerPanel = new MainColorPanel();
        wordPanel = new MainColorPanel();
        meaningPanel = new MainColorPanel();
        southPanel = new MainColorPanel();
        vWord = thisChoicePanel.getThisLearnPanel().getThisMainMenu().managePanel.
                getDataAnalyzing().getWordFromList(thisChoicePanel.getvSelectedListName());
        for(Word tempWord: vWord){
            System.out.println("<FlashCardLearningPanel>[Init - Word dbg]: Loaded " + tempWord.debug());
        }
        if(learningType == SPACED_REPETITION_LEARNING)
        vWord = DataAnalyzing.wordsChoosing(vWord,1);

        wordLabel_wordPanel = new JLabel();
        wordLabel_meaningPanel = new JLabel();
        meaningLabel = new JLabel();
        phoneticLabel_meaningPanel = new JLabel();
        phoneticLabel_wordPanel = new JLabel();
        typeLabel_meaningPanel = new JLabel();
        typeLabel_wordPanel = new JLabel();
        vFeedBackButton = new Vector<JButton>();
        titleLabel = new JLabel("Flash card learning: 0/0");
        unSecretButton = new JButton("Unbox the card!");
        lineLabel = new JLabel("_________________________________");
        learningIndex = -1;



    }
    private void setDefaultPanelConfiguration(){
        //Set Font
        titleLabel.setForeground(defaultColor.getOnButton());
        titleLabel.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(25));
        wordLabel_wordPanel.setForeground(defaultColor.getFontColor());
        wordLabel_meaningPanel.setForeground(defaultColor.getFontColor());
        phoneticLabel_wordPanel.setForeground(defaultColor.getFontColor());
        phoneticLabel_meaningPanel.setForeground(defaultColor.getFontColor());
        typeLabel_wordPanel.setForeground(defaultColor.getFontColor());
        typeLabel_meaningPanel.setForeground(defaultColor.getFontColor());
        meaningLabel.setForeground(defaultColor.getFontColor());
        wordLabel_wordPanel.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        wordLabel_meaningPanel.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        typeLabel_wordPanel.setFont(defaultFont.getFont_OPENSANS_ITALIC(17));
        typeLabel_meaningPanel.setFont(defaultFont.getFont_OPENSANS_ITALIC(17));
        phoneticLabel_wordPanel.setFont(defaultFont.getFont_OPENSANS_LIGHT(19));
        phoneticLabel_meaningPanel.setFont(defaultFont.getFont_OPENSANS_LIGHT(19));
        meaningLabel.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(20));
        lineLabel.setForeground(defaultColor.getFontColor());
        lineLabel.setFont(defaultFont.getFont_OPENSANS_EXTRABOLD(17));

        MainColorPanel tempPanel;
        //northPanel-------------------------------------------
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        northPanel.add(titleLabel);

        //wordPanel--------------------------------------------
        wordPanel.setLayout(new BoxLayout(wordPanel,BoxLayout.Y_AXIS));
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(wordLabel_wordPanel);
        wordPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(typeLabel_wordPanel);
        wordPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(phoneticLabel_wordPanel);
        wordPanel.add(tempPanel);

        //meaningPanel-----------------------------------------
        meaningPanel.setLayout(new BoxLayout(meaningPanel,BoxLayout.Y_AXIS));
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(wordLabel_meaningPanel);
        meaningPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(typeLabel_meaningPanel);
        meaningPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(phoneticLabel_meaningPanel);
        meaningPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(lineLabel);
        meaningPanel.add(tempPanel);
        tempPanel = new MainColorPanel();
        tempPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        tempPanel.add(meaningLabel);
        meaningPanel.add(tempPanel);




        //feedBackButtonPanel----------------------------------
        feedBackButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(String buttonName: feedBackButtonName){
            vFeedBackButton.add(new JButton(buttonName));
            feedBackButtonPanel.add(vFeedBackButton.getLast());
        }

        //unSecretButtonPanel----------------------------------
        unSecretButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        unSecretButtonPanel.add(unSecretButton);
    }
    private void addListener(){
        FlashCardLearningPanelListener flashCardLearningPanelListener = new FlashCardLearningPanelListener(this);
        unSecretButton.addActionListener(flashCardLearningPanelListener);
        for(JButton button: vFeedBackButton){
            button.addActionListener(flashCardLearningPanelListener);
        }
    }
    public void unboxCard(){
        centerPanel.removeAll();
        centerPanel.add(meaningPanel);
        southPanel.removeAll();
        southPanel.add(feedBackButtonPanel);
        repaint();
        revalidate();

    }
    public void next(){
        if(learningIndex == vWord.size()){
            thisChoicePanel.getThisLearnPanel().getThisMainMenu().setView(thisChoicePanel.getThisLearnPanel());
        }
        learningIndex++;
        Word tempWord;
        if(learningIndex >= vWord.size()) tempWord = null;
        else tempWord = vWord.get(learningIndex);
        wordLabel_wordPanel.setText(tempWord.getWord());
        wordLabel_meaningPanel.setText(tempWord.getWord());
        meaningLabel.setText(tempWord.getMeaning());
        typeLabel_meaningPanel.setText(DataAnalyzing.getTypeString(tempWord.getType()));
        typeLabel_wordPanel.setText(DataAnalyzing.getTypeString(tempWord.getType()));
        phoneticLabel_meaningPanel.setText(tempWord.getPhonetic());
        phoneticLabel_wordPanel.setText(tempWord.getPhonetic());
        titleLabel.setText("Flash card learning: " + (learningIndex+1) + "/" + vWord.size());

        wordLabel_meaningPanel.paintImmediately(wordLabel_meaningPanel.getVisibleRect());
        wordLabel_wordPanel.paintImmediately(wordLabel_meaningPanel.getVisibleRect());
        meaningLabel.paintImmediately(meaningLabel.getVisibleRect());
        phoneticLabel_meaningPanel.paintImmediately(phoneticLabel_meaningPanel.getVisibleRect());
        phoneticLabel_wordPanel.paintImmediately(phoneticLabel_wordPanel.getVisibleRect());
        typeLabel_wordPanel.paintImmediately(typeLabel_wordPanel.getVisibleRect());
        typeLabel_meaningPanel.paintImmediately(typeLabel_meaningPanel.getVisibleRect());
        titleLabel.paintImmediately(titleLabel.getVisibleRect());
        centerPanel.removeAll();
        centerPanel.add(wordPanel);
        southPanel.removeAll();
        southPanel.add(unSecretButtonPanel);
        repaint();
        revalidate();
    }


    public Word getPresentWord(){
//        if(learningIndex >= vWord.size()) return null;
        return vWord.get(learningIndex);
    }

    public ChoicePanel getThisChoicePanel() {
        return thisChoicePanel;
    }

    public DataAnalyzing getDataAnalyzing() {
        return dataAnalyzing;
    }

    public int getLearningIndex() {
        return learningIndex;
    }

    public int getMaxWordNumber() {
        return vWord.size();
    }
}
