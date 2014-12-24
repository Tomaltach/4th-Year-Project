package financemanager.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Rented_Data {
    Double expense;
    String month;
    int year;

    public Double getExpense() {
        return expense;
    }
    public String getMonth() {
        return month;
    }
    public int getYear() {
        return year;
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