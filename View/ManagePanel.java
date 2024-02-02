package View;

import Controller.DataAnalyzing;
import Controller.Listener.ManageButtonListener;
import Controller.Listener.ManagePopupMenuListener;
import Controller.Listener.MenuButtonListener;
import Controller.Listener.searchBarListener;
import Model.Color.DefaultColor;
import Model.Word;
import View.CustomSwing.ManageButton;
import View.CustomSwing.RoundButton;
import View.CustomSwing.ScrollBarUI;
import View.CustomSwing.ViewTable;
import View.Popup.ManageTablePopupMenu;
import View.SubFrame.LoadingFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

/**The {@code ManagePanel} extends the {@link JPanel}. This is used to contain all components in Manage page and this is Manage
 * page also. Moreover, this has a parameter which has class is {@link MainMenu}
 * @version 4/1/2024
 * @author Julylun (Hoang Luan)
 */
public class ManagePanel extends JPanel {
    private ManageTablePopupMenu popupMenu;
    private DataAnalyzing dataAnalyzing;
    private DefaultColor defaultColor;

    private JScrollPane scrollPane;
    private DefaultTableModel model;
    private ViewTable viewTable;
    private JPanel bottomPanel;
    private JPanel topPanel;
    private JTextField searchBar;
    private String tempPath = "src/Temp/OfflineSync.tmp";
    private String[] colName = {"Word","Meaning","Type","Phonetic","Last learning date"};
    private String[] buttonName = {"New", "Sync", "Import", "Export"};
    private Vector vData;
    private Vector<ManageButton> vButton;
    private Component parentComponent;
    private boolean isSearchBarFocusing;
    public boolean isTyping;

    /**Constructor
     *
     * @param component this is a MainMenu
     */
    public ManagePanel(Component component){
        this.parentComponent = component;
        init();
        sync();
        setDefaultFunction();
        setLayout(new BorderLayout());
        add(scrollPane,BorderLayout.CENTER);
        addComponentIntoBottomPanel();
        add(bottomPanel,BorderLayout.SOUTH);
        addComponentIntoTopPanel();
        add(topPanel,BorderLayout.NORTH);
        searchBar.setText("Search bar");

        addListener();
    }
    private void setDefaultFunction(){
        //set UI for bottom Panel
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(defaultColor.getMainBackgroundColor());
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //set UI for top Panel
        searchBar.setColumns(30);
        searchBar.setBorder(new EmptyBorder(0,0,0,0));

        searchBar.setForeground(defaultColor.getFontColor());
        searchBar.setCaretColor(defaultColor.getFontColor());
        searchBar.setHorizontalAlignment(SwingConstants.CENTER);
        searchBar.setBackground(defaultColor.getSearchBarColor());
        isSearchBarFocusing = false;
        isTyping = false;


        topPanel.setOpaque(true);
        topPanel.setBackground(defaultColor.getMainBackgroundColor());
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Set UI for table
        scrollPane.setViewportView(viewTable);
        viewTable = new ViewTable(model);

        scrollPane.setViewportView(viewTable);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        scrollPane.setBackground(defaultColor.getMainBackgroundColor());
        scrollPane.getVerticalScrollBar().setBackground(defaultColor.getMainBackgroundColor());
        scrollPane.getHorizontalScrollBar().setBackground(defaultColor.getMainBackgroundColor());
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.getViewport().setBackground(defaultColor.getMainBackgroundColor());
    }
    private void addListener(){
        viewTable.addMouseListener(new ManagePopupMenuListener(this));
        for(ManageButton button: vButton){
            button.addActionListener(new ManageButtonListener(this));
        }

        searchBar.addKeyListener(new searchBarListener(this));
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Search bar")) searchBar.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().equals("")) searchBar.setText("Search bar");
            }
        });
    }
    private void init(){
        defaultColor = new DefaultColor();
        viewTable = new ViewTable();
        vData = new Vector();
        searchBar = new JTextField();
        model = new DefaultTableModel();
        bottomPanel = new JPanel();
        scrollPane = new JScrollPane();
        dataAnalyzing = new DataAnalyzing();
        vButton = new Vector<ManageButton>();
        topPanel = new JPanel();
        popupMenu = new ManageTablePopupMenu(this);

    }
    private void addComponentIntoTopPanel(){ topPanel.add(searchBar);
    }
    private void addComponentIntoBottomPanel(){
        for(String tempString: buttonName){
            vButton.add(new ManageButton(tempString));
            bottomPanel.add(vButton.getLast());
        }
    }

    /**The {@code refreshView()} is used to refresh table and its data
     *
     */
    public void refreshView(){
        System.out.println(new DefaultColor().YELLOW + "<Manage Panel> [RefreshView]: Refreshing" +  new DefaultColor().RESET);
        sync();
        viewTable = new ViewTable(model);
        setDefaultFunction();
        viewTable.addMouseListener(new ManagePopupMenuListener(this));
        System.out.println(new DefaultColor().GREEN + "<Manage Panel> [RefreshView]: Refreshed" + new DefaultColor().RESET);
    }

    /**The {@code refreshView()} is used to update data from sql database
     *
     */
    public void sync(){
        vData = dataAnalyzing.getDataFromSQLDatabase();
        model = dataAnalyzing.getTable(vData,colName);
        model.fireTableDataChanged();
    }

    public void sync(Vector vLocalData){
        model = dataAnalyzing.getTable(vLocalData,colName);
        model.fireTableDataChanged();
        viewTable = new ViewTable(model);
        setDefaultFunction();
        viewTable.addMouseListener(new ManagePopupMenuListener(this));
    }

    public JTextField getSearchBar() {
        return searchBar;
    }

    /** Renew popup for this class
     *
     */
    public void resetPopup(){
        popupMenu = new ManageTablePopupMenu(this);
    }

    public DataAnalyzing getDataAnalyzing() {
        return dataAnalyzing;
    }

    public ManageTablePopupMenu getPopupMenu() {
        return popupMenu;
    }

    public boolean isSearchBarFocusing() {
        return isSearchBarFocusing;
    }

    public void setFocusingBarStatus(boolean focus){
        isSearchBarFocusing = focus;
    }

    /**
     * Give you table of this
     * @return {@link JTable}
     */
    public JTable getViewTable() {
        return viewTable;
    }

    /**Give you a data vector of this class
     *
     * @return {@link Vector<String>}
     */
    public Vector getvData() {
        return vData;
    }
}
