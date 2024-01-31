package Controller.Listener;

import Model.Color.DefaultColor;
import Model.SimpleDate;
import View.CustomSwing.MainColorPanel;
import View.LearnPanelPackage.FlashCardLearningPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class FlashCardLearningPanelListener implements ActionListener {
    private DefaultColor defaultColor = new DefaultColor();
    private FlashCardLearningPanel thisFlashCardLearningPanel;
    private Calendar calendar = Calendar.getInstance();
    public FlashCardLearningPanelListener(FlashCardLearningPanel flashCardLearningPanel){
        thisFlashCardLearningPanel = flashCardLearningPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case "Unbox the card!":{
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: Unbox the card button is pressed"+ defaultColor.RESET);
                thisFlashCardLearningPanel.unboxCard();
                break;
            }
            case "Bad":{ //this decreases three learning levels
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: Bad button is pressed" + defaultColor.RESET);
                if(thisFlashCardLearningPanel.getLearningIndex() == thisFlashCardLearningPanel.getMaxWordNumber()-1){
                    thisFlashCardLearningPanel.getThisChoicePanel().getThisLearnPanel().getThisMainMenu().setView(new MainColorPanel());

                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 3,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );
                }
                else {
                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 3,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );

                    thisFlashCardLearningPanel.next();
                }
                break;
            }
            case "Not bad":{ // this decrease two learning level
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: Not bad button is pressed" + defaultColor.RESET);
                if(thisFlashCardLearningPanel.getLearningIndex() == thisFlashCardLearningPanel.getMaxWordNumber()-1){
                    thisFlashCardLearningPanel.getThisChoicePanel().getThisLearnPanel().getThisMainMenu().setView(new MainColorPanel());

                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 2,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );
                }
                else {
                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 2,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );

                    thisFlashCardLearningPanel.next();
                }
                break;
            }
            case "Fine":{ //this decrease one learning level
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: Fine button is pressed" + defaultColor.RESET);
                if(thisFlashCardLearningPanel.getLearningIndex() == thisFlashCardLearningPanel.getMaxWordNumber()-1){
                    thisFlashCardLearningPanel.getThisChoicePanel().getThisLearnPanel().getThisMainMenu().setView(new MainColorPanel());

                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 1,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );
                }
                else {
                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() - 1,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );

                    thisFlashCardLearningPanel.next();
                }
                break;
            }
            case "Good":{ //this keep learning level be stable
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: Good button is pressed" + defaultColor.RESET);
                if(thisFlashCardLearningPanel.getLearningIndex() == thisFlashCardLearningPanel.getMaxWordNumber()-1){
                    thisFlashCardLearningPanel.getThisChoicePanel().getThisLearnPanel().getThisMainMenu().setView(new MainColorPanel());

                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel(),
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );
                }
                else {
                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel(),
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );

                    thisFlashCardLearningPanel.next();
                }
                break;
            }
            case "My crush":{ //This increase one learning level
                System.out.println(defaultColor.YELLOW + "<FlashCardLearningPanelListener>[actionPerformed]: My crush button is pressed" + defaultColor.RESET);
                if(thisFlashCardLearningPanel.getLearningIndex() == thisFlashCardLearningPanel.getMaxWordNumber()-1){
                    thisFlashCardLearningPanel.getThisChoicePanel().getThisLearnPanel().getThisMainMenu().setView(new MainColorPanel());


                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() + 1,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );
                }
                else {

                    thisFlashCardLearningPanel.getDataAnalyzing().updateJourney(thisFlashCardLearningPanel.getDataAnalyzing().getNumberLearnedWordInDay(
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.MONTH) + 1,
                            calendar.get(Calendar.YEAR)
                    ) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));


                    thisFlashCardLearningPanel.getDataAnalyzing().editWordFromSQLDatabase(
                            thisFlashCardLearningPanel.getPresentWord(),
                            thisFlashCardLearningPanel.getPresentWord().getLearningLevel() + 1,
                            new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
                    );

                    thisFlashCardLearningPanel.next();
                }
                break;
            }
        }
    }
}
