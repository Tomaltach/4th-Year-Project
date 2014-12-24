package financemanager.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Gas_Data {
    String title;
    Double expense;
    String month;
    int year;

    public String getTitle() {
        return title;
    }
    public Double getExpense() {
        return expense;
    }
    public String getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setExpense(Double expense) {
        this.expense = expense;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
}