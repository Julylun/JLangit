package View.Popup;

import Controller.Listener.ListCheckBoxPopupListener;
import Controller.Listener.ManagePopupMenuListener;
import Model.Color.DefaultColor;
import Model.Word;
import View.ManagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.SplittableRandom;
import java.util.Vector;

public class ManageTablePopupMenu extends JPopupMenu {
    private Vector<JMenuItem> vMenuItem;
    private String[] menuName = {"Add","Edit","Delete"};
    private Vector <String> vListName;
    private Vector<JCheckBoxMenuItem> vListCheckBoxMenuItem;
    private JMenu listMenu;
    private JMenuItem newListMenuItem, deleteListMenuItem;
    private int selectedRow;

    private Word selectedWord;
    Component parentComponent;
    public ManageTablePopupMenu(Component parentComponent){
        this.parentComponent = parentComponent;
        init();
        setBackground(new DefaultColor().getMainBackgroundColor());

        //Add item into popup
        for(JMenuItem jMenuItem: vMenuItem){
            add(jMenuItem);
        }
        add(listMenu);
        addListener();
    }
    private void init(){
        //Get all list in sql database
        vListName = ((ManagePanel)(parentComponent)).getDataAnalyzing().getAllListInSQLDatabase();

        vListCheckBoxMenuItem  = new Vector<JCheckBoxMenuItem>();
        vMenuItem = new Vector<JMenuItem>();
        listMenu = new JMenu("List");
        newListMenuItem = new JMenuItem("Create a new list");
        deleteListMenuItem = new JMenuItem("Delete list");

        //Add menu item into vMenuItem
        for(String name: menuName){
            vMenuItem.add(new JMenuItem(name));
        }

        //Add components to List Menu
        //If a word belong to a list, it will have a tick symbol next list name
        listMenu.add(newListMenuItem);
        listMenu.add(deleteListMenuItem);
        listMenu.addSeparator();


        selectedRow = ((ManagePanel)(parentComponent)).getViewTable().getSelectedRow(); //Get selected row
        System.out.println("<ManageTablePopupMenu>[init - Add components to list menu - get selected row]: Selected row is " + selectedRow);
        //First start selectedRow will be -1 so if we don't have this condition then program will be error
        if(selectedRow != -1) selectedWord = ((Word)(((ManagePanel)(parentComponent)).getvData().get(selectedRow)));

        for(String name: vListName){
            vListCheckBoxMenuItem.add(new JCheckBoxMenuItem(name));
            if(selectedRow != -1){
                System.out.println("<ManageTablePopupMenu>[init - Add components to list menu - get selected row]: Selected row is " + selectedRow  + " " +  ((ManagePanel)(parentComponent)).getDataAnalyzing().isInList(selectedWord,name));
                if(((ManagePanel)(parentComponent)).getDataAnalyzing().isInList(selectedWord,name)){
                    vListCheckBoxMenuItem.lastElement().setSelected(true);
                }
                else vListCheckBoxMenuItem.lastElement().setSelected(false);
            }
            listMenu.add(vListCheckBoxMenuItem.lastElement());
        }

    }
    private void addListener(){
        ManagePopupMenuListener managePopupMenuListener = new ManagePopupMenuListener(parentComponent);
        ListCheckBoxPopupListener listCheckBoxPopupListener = new ListCheckBoxPopupListener(this);
        for(JMenuItem menuItem: vMenuItem){
            menuItem.addActionListener(managePopupMenuListener);
        }
        newListMenuItem.addActionListener(managePopupMenuListener);
        deleteListMenuItem.addActionListener(managePopupMenuListener);
        for(JCheckBoxMenuItem checkBoxMenuItem: vListCheckBoxMenuItem){
            checkBoxMenuItem.addActionListener(listCheckBoxPopupListener);
        }

    }

    public Word getSelectedWord() {
        return selectedWord;
    }

    public Component getParentComponent() {
        return parentComponent;
    }
}
