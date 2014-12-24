package beefmodule.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Herd_Data {
    int herd_id;
    String cowtag;
    String herd_creation_date;
    String herd_termination_date;

    public int getHerd_id() {
        return herd_id;
    }
    public void setHerd_id(int herd_id) {
        this.herd_id = herd_id;
    }
    public String getCowtag() {
        return cowtag;
    }
    public void setCowtag(String cowtag) {
        this.cowtag = cowtag;
    }
    public String getHerd_creation_date() {
        return herd_creation_date;
    }
    public void setHerd_creation_date(String herd_creation_date) {
        this.herd_creation_date = herd_creation_date;
    }
    public String getHerd_termination_date() {
        return herd_termination_date;
    }
    public void setHerd_termination_date(String herd_termination_date) {
        this.herd_termination_date = herd_termination_date;
    }
}