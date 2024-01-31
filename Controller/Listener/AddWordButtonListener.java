package Controller.Listener;

import Controller.DataAnalyzing;
import Model.Color.DefaultColor;
import Model.Word;
import View.ManagePanel;
import View.SubFrame.AddWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddWordButtonListener implements ActionListener {
    private AddWord parentComponent;
    public AddWordButtonListener(Component component){
        this.parentComponent = (AddWord) component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Okay": {
                SwingWorker swingWorker = new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        System.out.println(new DefaultColor().YELLOW + "<Word> [Okay]: Okay is pressed" + new DefaultColor().RESET);
                        ManagePanel temp = (ManagePanel) parentComponent.getParentComponent();
                        if(parentComponent.getMethod() == 0) {
                            new DataAnalyzing().addWordToSQLDatabase(parentComponent.getWord());
                            new AddWord(parentComponent.getParentComponent(), 0);
                        }
                        else {
                            Word word = parentComponent.getWord();
                            Word tempWord = (Word)( new DataAnalyzing().getDataFromSQLDatabase().get(((ManagePanel) parentComponent.getParentComponent()).getViewTable().getSelectedRow()));
                            word.setId(tempWord.getId());
                            new DataAnalyzing().editWordFromSQLDatabase(word);
                        }
                        temp.refreshView();
                        parentComponent.dispose();
                        return null;
                    }
                };
                swingWorker.execute();
                break;
            }
            case "Cancel": {
                System.out.println(new DefaultColor().YELLOW + "<Word> [Cancel]: Cancel is pressed" + new DefaultColor().RESET);
                JFrame tempFrame = (JFrame) parentComponent;
                tempFrame.dispose();
                break;
            }
        }
    }
}
