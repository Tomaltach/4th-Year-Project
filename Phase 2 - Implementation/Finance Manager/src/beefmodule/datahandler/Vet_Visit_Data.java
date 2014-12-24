package beefmodule.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Vet_Visit_Data {
    String title;
    String cowtag;
    String info;
    String date;
    Double expense;

    public String getTitle() {
        return title;
    }
    public String getCowTag() {
        return cowtag;
    }
    public String getInfo() {
        return info;
    }
    public String getDate() {
        return date;
    }
    public Double getExpense() {
        return expense;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCowTag(String cowtag) {
        this.cowtag = cowtag;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setExpense(Double expense) {
        this.expense = expense;
    }
}