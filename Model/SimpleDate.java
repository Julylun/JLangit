package Model;


/**
 *  The {@code SimpleDate} model is used to contain three information including Day in month, Month, Year
 *
 * @author Julylun (Hoang Luan)
 * @version 18/01/2024
 */
public class SimpleDate {
    public int day, month, year;
    SimpleDate(){
        day = 15;
        month = 9;
        year = 2005;
    }

    public SimpleDate(int year, int month, int day){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getReverseString(){
        return year + "-" + month + "-" + day;
    }

    @Override
    public String toString() {
        return  day + "-" + month + "-" + year;
    }



}
