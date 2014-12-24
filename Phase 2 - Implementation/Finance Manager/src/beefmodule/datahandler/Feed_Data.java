package beefmodule.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Feed_Data {
    int herd_id;
    Double f_silage_expense;
    Double f_nuts_expense;
    Double f_hay_expense;
    String month;
    String year;
    
    public void setHerd_id(int herd_id) {
        this.herd_id = herd_id;
    }
    public int getHerd_id() {
        return herd_id;
    }
    public void setF_silage_expense(Double f_silage_expense) {
        this.f_silage_expense = f_silage_expense;
    }
    public Double getF_silage_expense() {
        return f_silage_expense;
    }
    public void setF_nuts_expense(Double f_nuts_expense) {
        this.f_nuts_expense = f_nuts_expense;
    }
    public Double getF_nuts_expense() {
        return f_nuts_expense;
    }
    public void setF_hay_expense(Double f_hay_expense) {
        this.f_hay_expense = f_hay_expense;
    }
    public Double getF_hay_expense() {
        return f_hay_expense;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
}