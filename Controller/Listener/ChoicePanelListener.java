package Controller.Listener;

import Model.Color.DefaultColor;
import View.CustomSwing.CustomColorMessage;
import View.CustomSwing.SubColorPanel;
import View.LearnPanelPackage.ChoicePanel;
import View.LearnPanelPackage.FlashCardLearningPanel;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoicePanelListener implements ActionListener {
    private DefaultColor defaultColor = new DefaultColor();
    private ChoicePanel thisChoicePanel;
    public ChoicePanelListener(ChoicePanel choicePanel){
        thisChoicePanel = choicePanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ">":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed]: Button [>] is pressed" + defaultColor.RESET);
                int selectedIndex = thisChoicePanel.getNotSelectedListItem().getSelectedIndex();
                System.out.println("<ChoicePanelListener>[actionPerformed]: select "+ thisChoicePanel.getvNotSelectedListName().get(selectedIndex));
                thisChoicePanel.getvSelectedListName().add(thisChoicePanel.getvNotSelectedListName().get(selectedIndex));
                thisChoicePanel.getvNotSelectedListName().remove(selectedIndex);
                thisChoicePanel.refresh();
                break;
            }
            case ">>":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed] Button [>>] is pressed" + defaultColor.RESET);
                System.out.println("<ChoicePanelListener>[actionPerformed]: select all");
                thisChoicePanel.getvSelectedListName().addAll(thisChoicePanel.getvNotSelectedListName());
                thisChoicePanel.getvNotSelectedListName().removeAll(thisChoicePanel.getvNotSelectedListName());
                thisChoicePanel.refresh();
                break;
            }
            case "<":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed] Button [<] is pressed" + defaultColor.RESET);
                int selectedIndex = thisChoicePanel.getSelectedListItem().getSelectedIndex();
                System.out.println("<ChoicePanelListener>[actionPerformed]: Remove "+ thisChoicePanel.getvSelectedListName().get(selectedIndex));
                thisChoicePanel.getvNotSelectedListName().add(thisChoicePanel.getvSelectedListName().get(selectedIndex));
                thisChoicePanel.getvSelectedListName().remove(selectedIndex);
                thisChoicePanel.refresh();
                break;
            }case "<<":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed] Button [<<] is pressed" + defaultColor.RESET);
                System.out.println("<ChoicePanelListener>[actionPerformed]: Remove all");
                thisChoicePanel.getvNotSelectedListName().addAll(thisChoicePanel.getvSelectedListName());
                thisChoicePanel.getvSelectedListName().removeAll(thisChoicePanel.getvSelectedListName());
                thisChoicePanel.refresh();
                break;
            }
            case "Learn!":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed] Learn! is pressed" + defaultColor.RESET);
//                thisChoicePanel.getThisLearnPanel().getThisMainMenu().flashCardLearningPanel = new FlashCardLearningPanel(thisChoicePanel);

//                thisChoicePanel.getThisLearnPanel().getThisMainMenu().setView(thisChoicePanel.getThisLearnPanel().getThisMainMenu().flashCardLearningPanel);
                thisChoicePanel.getThisLearnPanel().getThisMainMenu().setView(new FlashCardLearningPanel(thisChoicePanel, FlashCardLearningPanel.NORMAL_LEARNING));
                break;
            }
            case "Learn by Spaced Repetition Method":{
                System.out.println(defaultColor.YELLOW + "<ChoicePanelListener>[actionPerformed] Learn by Spaced Repetition Method is pressed" + defaultColor.RESET);
                try {
                    thisChoicePanel.getThisLearnPanel().getThisMainMenu().setView(new FlashCardLearningPanel(thisChoicePanel, FlashCardLearningPanel.SPACED_REPETITION_LEARNING));
                }catch (NullPointerException npe){
                    new CustomColorMessage("Nothing to learn!", defaultColor.getSubBackgroundColor());
                    npe.printStackTrace();
                }

            }
        }
    }
}



