package beefmodule.datahandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Cow_Data {
    String cowTag;
    Double boughtPrice;
    Double soldPrice;
    String boughtDate;
    String soldDate;

    public String getBoughtDate() {
        return boughtDate;
    }
    public void setBoughtDate(String boughtDate) {
        this.boughtDate = boughtDate;
    }
    public Double getBoughtPrice() {
        return boughtPrice;
    }
    public void setBoughtPrice(Double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
    public String getCowTag() {
        return cowTag;
    }
    public void setCowTag(String cowTag) {
        this.cowTag = cowTag;
    }
    public String getSoldDate() {
        return soldDate;
    }
    public void setSoldDate(String soldDate) {
        this.soldDate = soldDate;
    }
    public Double getSoldPrice() {
        return soldPrice;
    }
    public void setSoldPrice(Double soldPrice) {
        this.soldPrice = soldPrice;
    }
}