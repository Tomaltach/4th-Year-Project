package beefmodule.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class CowTag_Data {
    String cowtag;
    int herd_id;

    public String getCowTag() {
        return cowtag;
    }
    public void setCowTag(String cowtag) {
        this.cowtag = cowtag;
    }
    public int getHerd_id() {
        return herd_id;
    }
    public void setHerd_id(int herd_id) {
        this.herd_id = herd_id;
    }
}