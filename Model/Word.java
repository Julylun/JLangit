package Model;

import org.w3c.dom.css.CSSImportRule;

import java.util.Calendar;
import java.util.Vector;


/**
 * The model {@code Word} includes many attributes relating to a word, this is used to save temporary data.
 * @author Julylun (Hoang Luan)
 * @version 18/01/2024
 */
public class Word {
    private String word;
    private String meaning;
    private int type;
    private String phonetic;
    private int id;
    private SimpleDate lastLearningDate;
    private int learningLevel;
    public Word( int id ,String word, String meaning, int type, String phonetic){
        init();
        this.learningLevel = 0;
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.type = type;
        this.phonetic = phonetic;
    }

    public Word( int id ,String word, String meaning, int type, String phonetic, SimpleDate lastLearningDate, int learningLevel){
        this(id, word, meaning, type, phonetic);
        this.lastLearningDate = lastLearningDate;
        this.learningLevel = learningLevel;
    }

    public Word(){
        this(-1,"", "",-1,"");
    }

    private void init(){
        lastLearningDate = new SimpleDate();
    }
    public Vector exportToVector(){
        Vector vt = new Vector();
        vt.add(word);
        vt.add(meaning);
        vt.add(type);
        vt.add(phonetic);
        return vt;
    }

    public String debug(){
        return "[Word debugging: ]: " + word + " - " + meaning + " - " + type + " - " + phonetic + " - " + lastLearningDate.toString();
    }




    public void setLastLearningDate(int day, int month, int year) {
        this.lastLearningDate = new SimpleDate(day,month,year);
    }

    public void setLastLearningDate(SimpleDate lastLearningDate) {
        this.lastLearningDate = lastLearningDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLearningLevel(int learningLevel) {
        this.learningLevel = learningLevel;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setPhonetic(String phonetic) {
        this.phonetic = phonetic;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public SimpleDate getLastLearningDate() {
        return lastLearningDate;
    }

    public int getId() {
        return id;
    }

    public int getLearningLevel() {
        return learningLevel;
    }

    public int getType() {
        return type;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public String getWord() {
        return word;
    }
}
