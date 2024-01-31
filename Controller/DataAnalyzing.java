package Controller;


import Model.Color.DefaultColor;
import Model.Color.DefaultFont;
import Model.JulyException.WrongExtensionException;
import Model.SimpleDate;
import Model.Word;
import View.CustomSwing.JulyFileChooser;
import View.CustomSwing.Logo;
import View.MainMenu;
import View.ManagePanel;
import View.SubFrame.LoadingFrame;
import com.sun.jdi.VoidValue;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.State;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Style;
import java.awt.*;
import java.io.*;
import java.lang.invoke.VolatileCallSite;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.Timer;
import java.util.stream.Stream;



/**The DataAnalyzing is like the back-end of JLangit project. This is used to communicate with database, file..., get
 * and send data to database or JLangit file
 *
 * @author Julylun (Hoang Luan)
 */
public class DataAnalyzing {
    public static final int MILLIS_PER_DAY = 86400000;
    public final int JULYW_FILE_EXTENSION = 0;
    private File file;
    private Word word;
    private DefaultColor defaultColor = new DefaultColor();
    private Connection sqlServerConnection;
    FileNameExtensionFilter fileFilter;
    public DataAnalyzing(){
        init();



        //Use JDBC to connect to SQL Database
        try {
            System.out.println(defaultColor.YELLOW + "[SQL Server]: Connecting to SQL Server database" + defaultColor.RESET);
            sqlServerConnection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;;databaseName=JLangitDB;instance=MSSQLSERVER;encrypt=true;trustServerCertificate=true; integratedSecurity=true");
            System.out.println(defaultColor.GREEN + "[SQL Server]: Connected to SQL Server database" + defaultColor.RESET);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.print("[SQL Server]:" ); System.err.print("Failed to connect to SQL Server databse");
        }
    }
    private void init(){
        fileFilter = new FileNameExtensionFilter("July Language IT Words (*.julyw)", "julyw");
        word = new Word();
    }

    //DATA SECTOR
    public static int learningLevelsToSpaceDays(int learningLevels){
        switch (learningLevels){
            case 0:
            case 1:{
                return 1;
            }
            case 2:{
                return 2;
            }
            case 3:{
                return 4;
            }
            case 4:{
                return 6;
            }
            case 5:{
                return 10;
            }
            case 6:{
                return 16;
            }
            case 7:{
                return 20;
            }
            case 8:{
                return 30;
            }
            case 9:{
                return 60;
            }
            case 10:{
                return 90;
            }
            default: return 90;
        }
    }

    /**
     * The {@WordsChoosing} is used to remove words which user don't want to learn depending on their chosen method
     * @param vData
     * @param choosingType
     * @return Vector
     */
    public static Vector<Word> wordsChoosing(Vector<Word> vData, int choosingType){
        switch (choosingType){
            case 1:{
                System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[WordsChoosing]: Depending on Spaced Repetition method" + new DefaultColor().RESET);
                Vector<Integer> vDeletingWord = new Vector<>();
                Calendar firstCalendar, secondCalendar;
                Date date;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
                int wordIndex = -1;

                secondCalendar = Calendar.getInstance();
                firstCalendar = Calendar.getInstance();

                for(Word word: vData){
                    wordIndex++;
                    SimpleDate simpleDate = word.getLastLearningDate();
                    String stringDate = simpleDate.year + "/" + simpleDate.month + "/" + simpleDate.day;
                    try {
                        firstCalendar.setTime(sdf.parse(stringDate));
                        int differenceDay = ((int)((secondCalendar.getTimeInMillis() - firstCalendar.getTimeInMillis())/MILLIS_PER_DAY)) - learningLevelsToSpaceDays(word.getLearningLevel());
                        System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[WordsChoosing]: Difference days of " + word.getWord() + " = " + differenceDay + new DefaultColor().RESET);
                        if(differenceDay < 0) {
                            System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[WordsChoosing]: Removing" + word.debug() + "; Index = " + wordIndex + new DefaultColor().RESET);
                            vDeletingWord.add(wordIndex);
                            wordIndex--;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                for(int index: vDeletingWord){
                    vData.remove(index);
                }
                System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[WordsChoosing]: Returning a vector - size of vector is " + vData.size() + new DefaultColor().RESET);
                return vData;
            }
        }
        return null;
    }


    //OTHER SECTOR

    /** Give this a type number (from Word model) and it will return u type string back
     *
     * @param typeNumber
     * @return {@linkplain String}
     */
    public static String getTypeString(int typeNumber){
        switch (typeNumber){
            case 0: return "Noun";
            case 1: return "Verb";
            case 2: return "Adjective";
            case 3: return "Adverb";
            case 4: return "Preposition";
            case 5: return "Determiner";
            case 7: return "Pronoun";
            case 8: return "Conjunction";
            case 9: return "Interjection";
            case 10: return "Other";
            default: return null;
        }
    }

    //TABLE SECTOR ---------------------------------------------------------------------
    //
    //
    //

    /**This will take your Vector<Word> and title then analyze them and return you back a {@linkplain DefaultTableModel}
     *
     * @param data
     * @param title
     * @return {@linkplain DefaultTableModel}
     */
    public DefaultTableModel getTable(Vector data, String[] title){
        Vector<Vector> tempVector = new Vector<Vector>();
        Vector<String> tempTitle = new Vector<String>();
        for(Object obj: data){
            Word word = (Word)obj;
            String tempString = word.getLastLearningDate().toString();

            tempVector.add(new Vector());
            tempVector.getLast().add(word.getWord());
            tempVector.getLast().add(word.getMeaning());
            tempVector.getLast().add(getTypeString(word.getType()));
            tempVector.getLast().add(word.getPhonetic());

            if(!tempString.equals("15-9-2005")) tempVector.getLast().add(word.getLastLearningDate().toString());
            else tempVector.getLast().add("Haven't studied yet!");

        }
        for(String temp: title){
            tempTitle.add(temp);
        }
        return new DefaultTableModel(tempVector,tempTitle);
    }

    //SQL DATABASE SECTOR --------------------------------------------------------------
    //
    //
    //


    /** The {@code getTarget} method is used to get the number of target which is words user want to learn in a day
     *
     * @return int
     */
    public int getTarget(){
        try {
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("select targetWord from SysConfig");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /** The {@code setTarget} method is used to set number of target which is words user want to learn in a day
     *
     * @param targetNumber
     */
    public void setTarget(int targetNumber){
        try {
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("UPDATE SysConfig SET targetWord = " + targetNumber + " where id = 1");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**The {@code updateJourney} is used to change learned words in a specific Journey day. This uses three parameters to
     * detect Journey day including day, month, year and uses "target" parameter to make the changing number
     *
     * @param target
     * @param day
     * @param month
     * @param year
     */
    public void updateJourney(int target ,int day, int month, int year){
        String date = "'" + year + "-" + month + "-" + day + "'";
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[updateJourney]: Update beginning..." + defaultColor.RESET);
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[updateJourney]: Checking Journey Appearance..." + defaultColor.RESET);
        createJourneyDay(day,month,year);
        System.out.println(defaultColor.GREEN + "<DataAnalyzing>[updateJourney]: Checked Journey Appearance" + defaultColor.RESET);
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[updateJourney]: Updating target..." + defaultColor.RESET);
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[updateJourney]: Update target to " + target + defaultColor.RESET);
        try {
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("Update Journey SET learnedWord =" + target + "where id =" + date);
            preparedStatement.execute();
            System.out.println(defaultColor.GREEN + "<DataAnalyzing>[updateJourney]: Updated target..." + defaultColor.RESET);
            System.out.println(defaultColor.GREEN + "<DataAnalyzing>[updateJourney]: Updated Journey END" + defaultColor.RESET);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** The {@code createJourneyDay} method depend on date of computer and uses it to create id of journey in Journey table
     * in SQL Database. Before creating, this method will use {@code isAppearedJourney} to check appearance of date in Journey
     *
     */
    public void createJourneyDay(int day, int month, int year){
        String date = "'" + year + "-" + month + "-" + day + "'";
        if(isAppearedJourney(day,month,year)){
            System.out.println(defaultColor.RED + "<DataAnalyzing>[createJourneyDay]: " + date + " appeared in Database. Stop creating" + defaultColor.RESET);
            return;
        }
        try{
            System.out.println("<DataAnalyzing>[createJourneyDay]: creating a journay on " + date);
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("Insert Into Journey values ("+date+",0)");
            preparedStatement.execute();
            System.out.println("<DataAnalyzing>[createJourneyDay]: created a journay on " + date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** The {@code isAppearedJourney} uses three parameter including day, month, year (All integer) to retrieve data from database
     * then check it, if database has date such as input then return true else return false
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public boolean isAppearedJourney(int day, int month, int year){
        String date = "'" + year + "-" + month + "-" + day + "'";
        try{
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("Select * from Journey where id = " + date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println("<DataAnalyzing>[isAppearedJourney]:" + date + " appears in Journey");
                return true;
            }
            System.out.println("<DataAnalyzing>[isAppearedJourney]:" + date + " doesn't appear in Journey");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<DataAnalyzing>[isAppearedJourney]:" + date + " doesn't appear in Journey");
            return false;
        }
    }

    public int getNumberLearnedWordInDay(int day, int month, int year){
        String date = "'" + year + "-" + month + "-" + day + "'";
        if(!isAppearedJourney(day,month,year)){
            System.err.println("<DataAnalyzing>[getNumberLearnedWordInDay]: " + date + " doesn't appear in Database.");
            return 0;
        }
        try {
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("select learnedWord from Journey where id = " + date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /** Return a vector having many number of learned words in a specific month
     *
     * @param month
     * @param year
     * @return {@linkplain Vector}
     */
    public Vector getNumberLearnedWordInMonth(int month, int year){
        try {
            Vector<Integer> returnVector = new Vector<Integer>();
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.GetNumberLearnedWordInMonth(?,?)}");
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2,year);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int day = resultSet.getInt(3);
                while (returnVector.size()+1 != day){
                    returnVector.add(0);
                }
                returnVector.add(resultSet.getInt(2));
            }
            System.out.println("<DataAnalyzing>[getNumberLearnedWordInMonth]: returnVector size= " + returnVector.size());
            while (returnVector.size() < 32){
                returnVector.add(0);
            }
            return returnVector;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**This take your lists and return you back all words in those lists
     *
     * @param vListName
     * @return {@linkplain Vector<Word>}
     */
    public Vector<Word> getWordFromList(Vector<String> vListName){ //Get all words in some lists to learn
        Vector<Word> returnVector = new Vector<Word>();
        try {
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[getWordFromList]: Getting words form list..." +defaultColor.RESET);
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.GetWordFromList(?)}");

            //BEGIN: Create sql command to exec
            String innerJoinString = "";
            for (String listName : vListName) {
                innerJoinString += (" left join " + listName + " on Word.id = " + listName + ".wordId");
            }
            if(vListName.size() != 0)
            innerJoinString+= " where";
            for (String listName : vListName) {
                innerJoinString += (" " + listName + ".wordId IS NOT NULL");
                if(!listName.equals(vListName.getLast())) innerJoinString+= " OR";
            }
            //End

            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[getWordFromList]:  innerJoinString =" + innerJoinString +defaultColor.RESET);
            preparedStatement.setString(1,innerJoinString);
            ResultSet resultSet = preparedStatement.executeQuery();
            Word tempWord;
            while (resultSet.next()){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(resultSet.getTimestamp("lastLearn").getTime()));

                tempWord = new Word(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)),
                        resultSet.getInt("learningLevel")
                );
                returnVector.add(tempWord);
            }
            System.out.println(defaultColor.GREEN + "<DataAnalyzing>[getWordFromList]: Getting words complete" +defaultColor.RESET);
            return returnVector;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**Delete all lists in SQL Database
     *
     */
    public void deleteAllListTable(){ //This use to delete all list in sql
        Vector <String> vList = getAllListInSQLDatabase();
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[deleteAllListTable]: Deleting all list " +defaultColor.RESET);
        for (String listName: vList){
            deleteListTable(listName);
        }
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[deleteAllListTable]: Deleted all list " +defaultColor.RESET);
    }

    /** Delete a specific list in SQL Database
     *
     * @param listName
     */
    public void deleteListTable(String listName){ //Delete one list in sql
        try(PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.DeleteListTable(?)}")){
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[deleteListTable]: Deleting " + listName + defaultColor.RESET);
                preparedStatement.setString(1,listName);
                preparedStatement.execute();
            System.out.println(defaultColor.GREEN + "<DataAnalyzing>[deleteListTable]: Deleted" + listName + defaultColor.RESET);
        } catch (SQLException e){
            System.out.println(defaultColor.RED + "<DataAnalyzing>[deleteListTable]: Some errors occur " + defaultColor.RESET);
            e.printStackTrace();
        }
    }



    /**
     * Delete a specific word in a specific list in SQL Database
     * @param word
     * @param listName
     * @return
     */
    public boolean deleteWordFromList(Word word, String listName){ //Remove a word from a list
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[deleteWordIntoList]: Deleting " + word.getWord() + " from " + listName + defaultColor.RESET);
        try (PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.DeleteWordFromList(?,?)}")) {
            preparedStatement.setInt(1,word.getId());
            preparedStatement.setString(2,listName);

            preparedStatement.execute();
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[deleteWordIntoList]: Deleted " + word.getWord() + " from " + listName + defaultColor.RESET);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(defaultColor.GREEN + "<DataAnalyzing>[addWordIntoList]: Deleted " + word.getWord() + " From " + listName + defaultColor.RESET);
        return true;
    }

    /** Add a word into a specific list in SQL Database
     *
     * @param word
     * @param listName
     * @return
     */
    public boolean addWordIntoList(Word word, String listName){ //Add a word into a list
        System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[addWordIntoList]: Adding " + word.getWord() + " into " + listName + defaultColor.RESET);
        try (PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.AddWordIntoList(?,?)}")) {
            preparedStatement.setInt(1,word.getId());
            preparedStatement.setString(2,listName);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println(defaultColor.GREEN + "<DataAnalyzing>[addWordIntoList]: Added " + word.getWord() + " into " + listName + defaultColor.RESET);
        return true;
    }

    /**Check is a specific word in a list you choose
     *
     * @param word
     * @param listName
     * @return
     */
    public boolean isInList(Word word, String listName){ //Check is a word in a list
        try (PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.CheckIsWordInList(?,?)}")){
            preparedStatement.setInt(1, word.getId());
            preparedStatement.setString(2,listName);
            ResultSet resultSet  = preparedStatement.executeQuery();
            int countValue = 0;
            while (resultSet.next()){
                countValue++;
                break;
            }
            System.out.println("<DataAnalyzing>[isInList]: countValue = " + countValue);
            if(countValue == 0) return false;
            else return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**Return a vector containing all list names in SQL Database
     *
     * @return {@linkplain Vector}
     */
    public Vector<String> getAllListInSQLDatabase(){ //return all name of lists
        try(PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.GetListTableName()}")){
            Vector <String> returnVector = new Vector<String>();
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[getAllListInSQLDatabase]: Getting " +defaultColor.RESET );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                 returnVector.add(resultSet.getString(1));
            }
            return returnVector;
        } catch (SQLException e){
            e.printStackTrace();
            return new Vector<String>();
        }
    }

    /** Create a list in SQL Database
     *
     * @param listName
     * @return
     */
    public int createListInSQLDatabase(String listName){ //Create a list in db
        try(PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("{call dbo.CreateListTable(?)}")) {
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[createListInSQLDatabase]: creating " + listName );
            preparedStatement.setString(1,listName);
            preparedStatement.execute();
            System.out.println(defaultColor.YELLOW + "<DataAnalyzing>[createListInSQLDatabase]: created " + defaultColor.RESET);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return 0;
    }

    public void deleteAllWordsFromDatabase(){
        try {
            System.out.println(defaultColor.YELLOW + "[deleteAllWordsFromSQLDatabase]: Deleting..." + word.getWord());
//            JOptionPane.showMessageDialog(null, "I confirmed this is Hoang Luan's app hehe");
            sqlServerConnection.createStatement().execute("Delete from Word");
            System.out.println(defaultColor.GREEN + "[deleteAllWordsFromSQLDatabase]: Deleted" + word.getWord());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Delete a specific word from SQL Database and all lists
     *
     * @param word
     * @return
     */
    public int deleteWordFromSQLDatabase(Word word){ //Delete word from db
        try {
            System.out.println(defaultColor.YELLOW + "[deleteWordFromSQLDatabase]: Deleting - " + word.getWord() );
            sqlServerConnection.createStatement().execute("Delete from Word where id = " + word.getId());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /** Return a {@code Vector<Word>} containing all words in SQL Database
     *
     * @return
     */
    public Vector getDataFromSQLDatabase(){
        Vector returnVector = new Vector();
        try {
            System.out.println(defaultColor.YELLOW + "<Data Analyzing> [getDataFromSQLDatabase]: Loading data from SQL Database" + defaultColor.RESET);
            Statement sqlStatement = sqlServerConnection.createStatement();
            ResultSet sqlResultSet = sqlStatement.executeQuery("Select * from Word order by word");

            while (sqlResultSet.next()){
                word = new Word();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(sqlResultSet.getDate("lastLearn").getTime()));

                word.setId(sqlResultSet.getInt("id"));
                word.setWord(sqlResultSet.getString("word"));
                word.setMeaning(sqlResultSet.getString("meaning"));
                word.setType(sqlResultSet.getInt("type"));
                word.setPhonetic(sqlResultSet.getString("phonetic"));
                word.setLastLearningDate(new SimpleDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));
                word.setLearningLevel(sqlResultSet.getInt("learningLevel"));
                System.out.println("<Data Analyzing> [ONLINE IMPORT] <-- " + word.debug());
                returnVector.add(word);
            }
            System.out.println(defaultColor.GREEN + "<Data Analyzing> [getDataFromSQLDatabase]: Loaded data from SQL Database, total number of data: " + returnVector.size() + " words" + defaultColor.RESET);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return returnVector;
    }

    /** Edit word in SQL Database
     *
     * @param word
     */
    public void editWordFromSQLDatabase(Word word){
        try {
            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("UPDATE Word SET word = ?, meaning = ?, type = ?, phonetic = ? where id = ? ");

            preparedStatement.setString(1,word.getWord());
            preparedStatement.setString(2,word.getMeaning());
            preparedStatement.setInt(3,word.getType());
            preparedStatement.setString(4,word.getPhonetic());
            preparedStatement.setInt(5,word.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(new DefaultColor().GREEN + "<Data Analyzing> [editWordFromSQLDatabase]: Edited data to database" + defaultColor.RESET);
    }

    public void editWordFromSQLDatabase(Word word, int learningLevel ,SimpleDate date){
        try {
            System.out.println(new DefaultColor().YELLOW + "<Data Analyzing> [editWordFromSQLDatabase]: Editing lastLearn data to database.." + defaultColor.RESET);
            if(learningLevel < 0) learningLevel = 0;

            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("UPDATE Word SET lastLearn = " + "'" + date.getReverseString() + "', learningLevel = ? where id = ?");
            System.out.println(date.getReverseString());
            preparedStatement.setInt(2,word.getId());
            preparedStatement.setInt(1,learningLevel);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(new DefaultColor().GREEN + "<Data Analyzing> [editWordFromSQLDatabase]: Edited lastLearn data to database" + defaultColor.RESET);
    }

    /**Add a word to SQL Database
     *
     * @param word
     */
    public void addWordToSQLDatabase(Word word) {
        try {
            SimpleDate simpleDate = word.getLastLearningDate();
            Calendar calendar = Calendar.getInstance();
            calendar.set(simpleDate.year, simpleDate.month-1, simpleDate.day);
            Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());


            PreparedStatement preparedStatement = sqlServerConnection.prepareStatement("insert into Word (word,meaning,type,phonetic,lastLearn,learningLevel) values(?,?,?,?,?,?)");
            preparedStatement.setString(1,word.getWord());
            preparedStatement.setString(2,word.getMeaning());
            preparedStatement.setInt(3,word.getType());
            preparedStatement.setString(4,word.getPhonetic());
            preparedStatement.setTimestamp(5,timestamp);
            preparedStatement.setInt(6,word.getLearningLevel());

            preparedStatement.execute();

        }
        catch (SQLException e){
            e.printStackTrace();
            //System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[addWordToSQLDatabase]: "+ e.getMessage() + new DefaultColor().RESET);
        }
        System.out.println(new DefaultColor().GREEN + "<Data Analyzing> [addWordToSQLDatabase]: Exported data to database.." + defaultColor.RESET);
    }

    /**Add many words from a vector to SQL Database
     *
     * @param vData
     */
    public void addWordToSQLDatabase(Vector vData){
        int totalNumberData = vData.size();
        LoadingFrame loadingFrame = new LoadingFrame();
        loadingFrame.setMax(totalNumberData);
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                int i = 0;
                for(Object obj: vData){
                    if(loadingFrame.isStop()) break;
                    loadingFrame.setValue(i++);
                    //  loadingFrame.setValue(i);
                    System.out.println(new DefaultColor().YELLOW + "<Data Analyzing> [addWordToSQLDatabase]: Adding " + ((Word)obj).debug() + "(" + i + "/" + totalNumberData + ")" + defaultColor.RESET);
                    addWordToSQLDatabase((Word) obj);
                    System.out.println(new DefaultColor().GREEN+ "<Data Analyzing> [addWordToSQLDatabase]: Added " + ((Word)obj).debug() + "(" + i + "/" + totalNumberData + ")" + defaultColor.RESET);
                }
                loadingFrame.setVisible(false);
                return null;
            }
        };
        worker.execute();
        return;
        //loadingFrame.setMax(totalNumberData);

    }
    /*
    public void addWordToSQLDatabase(Vector vData){
        int debugVectorSize = vData.capacity();
        int numOfImported = 0;
        String sqlComand = "insert into Word (word,meaning,type,phonetic) values ";
        for(Object obj: vData){
            sqlComand += "('" + word.getWord() + "','" + word.getMeaning() + "'," + word.getType() + ",'" + word.getPhonetic() + "'),";
            System.out.println("<Data Analyzing> [addWordToSQLDatabase]: Imported " + word.debug() + "(" + ++numOfImported +"/" + debugVectorSize + ")");
        }
        try {
            sqlServerConnection.createStatement().executeQuery(sqlComand.substring(0,sqlComand.length()-2)+";");
        } catch (SQLException e){
            System.out.println(new DefaultColor().YELLOW + "<DataAnalyzing>[addWordToSQLDatabase]: "+ e.getMessage() + new DefaultColor().RESET);
        }
        System.out.println(new DefaultColor().GREEN + "<Data Analyzing> [addWordToSQLDatabase]: Exported Vector data to database");

    }

     */

    //FILE SECTOR--------------------------------------------------------------------------------
    //
    //
    //

   // public int checkFileNumberLoading(File path){
//  }

    /** Open a File Chooser to get path from a file and return it back
     *
     * @param type
     * @return {@linkplain File}
     */
    public File getPath(int type){
        JulyFileChooser fileChooser = new JulyFileChooser();
        int returnValue = -1;
        fileChooser.addChoosableFileFilter(fileFilter);
        fileChooser.setFileFilter(fileFilter);
        if(type == 0){ //This condition detect which way Chooser must choose.
            //Used for Import function | Import ".julyw" type (July Language IT Words)
            returnValue = fileChooser.showOpenDialog(null);
        }
        if(type == 1){
            //Used for Export function | Export ".julyw" type (July Language IT Words)
            returnValue = fileChooser.showSaveDialog(null);
        }

        if(returnValue == 0){
            return  fileChooser.getSelectedFile();
        }
        else {
        return null;
        }
    }

    /**Check if file extension is wrong then throws a {@code WrongExtensionException}, else do nothing
     *
     * @param fileValue
     * @param extensionType
     * @throws WrongExtensionException
     */
    public void checkFileExtention(File fileValue, int extensionType) throws WrongExtensionException {
        if(extensionType == 0){
            String fileExtension = fileValue.getName().substring(fileValue.getName().lastIndexOf('.')+1);
            System.out.println("<Data Analyzing - getDataFromTemp> [Data importing]: File extension: \"." + fileExtension +"\"");
            if(!fileExtension.toLowerCase().equals("julyw")){
                throw new WrongExtensionException("Wrong file extension type! File extension must be \"*.julyw\".");
            }
        }
    }

    /** Create and export all words from SQL Database to a file having *.julyw extension
     *
     * @param path
     * @return
     */
    public int exportDataToFile(File path){ //Used by manageButtonListener - actionPerformed
        try {
            try {
                checkFileExtention(path,JULYW_FILE_EXTENSION);
            }
            catch (WrongExtensionException wee){
                path = new File(path + ".julyw");
            }
            FileWriter fileWriter = new FileWriter(path);
            System.out.println(defaultColor.YELLOW + "<Data Analyzing - exportDataToFile> [Data exporting]: Exporting data at :" + path);

            Vector vData = getDataFromSQLDatabase();
            Word word;
            for (Object obj: vData){
                word = (Word) obj;
                fileWriter.write(word.getWord()+"\n");
                fileWriter.write(word.getMeaning()+"\n");
                fileWriter.write(word.getType()+"\n");
                fileWriter.write(word.getPhonetic()+"\n");
                fileWriter.write(word.getLastLearningDate().year+"\n");
                fileWriter.write(word.getLastLearningDate().month+"\n");
                fileWriter.write(word.getLastLearningDate().day+"\n");
                fileWriter.write(word.getLearningLevel()+"\n");
                System.out.println("<Export> ---> Word: " + word.debug());
            }
            fileWriter.close();
            System.out.println(defaultColor.GREEN + "<Data Analyzing - exportDataToFile> [Data exporting]: Exported data at :" + path + defaultColor.RESET);
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    /**Read file from a path and put all words to a vector then return it back
     *
     * @param path
     * @return {@linkplain Word}
     */
    public Vector getDataFromFile(File path){ //Used by manageButtonListener - actionPerformed
        try{
            file = path;
            System.out.println(defaultColor.YELLOW + "<Data Analyzing - getDataFromTemp> [Data importing]: Get data from path: \"" + path + "\"" + defaultColor.RESET);

            //BEGIN: Detect and fill file extension in case users using all types choosing choice (Only .julyw)
            //If the file extension is wrong then throw WrongExtensionException and break my life ;-;
            checkFileExtention(file,JULYW_FILE_EXTENSION);
            //END [DETECT AND FILL FILE EXTENSION]-------------------------------------------------

            Scanner scanner = new Scanner(file);
            Vector returnVector = new Vector();
            System.out.println(defaultColor.YELLOW + "<Data Analyzing - getDataFromTemp> [Data importing]: Loading data from offline sync" + defaultColor.RESET);
            while (scanner.hasNextLine()){
                word = new Word();
                word.setWord(scanner.nextLine());
                word.setMeaning(scanner.nextLine());
                word.setType(Integer.valueOf(scanner.nextLine()));
                word.setPhonetic(scanner.nextLine());
                word.setLastLearningDate(new SimpleDate(Integer.valueOf(scanner.nextLine()), Integer.valueOf(scanner.nextLine()), Integer.valueOf(scanner.nextLine())));
                word.setLearningLevel(Integer.valueOf(scanner.nextLine()));
                System.out.println("<Import> <-- " + word.debug());
                returnVector.add(word);
            }
            System.out.println(defaultColor.GREEN + "<Data Analyzing - getDataFromTemp> [Data importing]: Loaded" + defaultColor.RESET);
            return returnVector;
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("<Data Analyzing - getDataFromTemp> [Data importing]: Failed to load data from offline sync file");
            return null;
        }
    }


    //GET - SET SECTOR-----------------------------------------------------------------------------------
    //
    //
    //

    public String getTime(){
        Calendar calendar = Calendar.getInstance();
        return "'" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) +"'";
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;

    }
}
