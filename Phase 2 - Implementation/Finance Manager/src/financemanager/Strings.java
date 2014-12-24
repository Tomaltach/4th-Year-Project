package financemanager;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

public class Strings {
    public String about() {
        return "Developer:   Thomas Donegan\n" +
               "e-Mail:           Thomas.Donegan@mycit.ie\n" +
               "Version:        0.0.1";
    }
    public String welcome(){
        return "Welcome to the first Farm Finance Manager.";
    }
    public String[] status() {
        String[] status = {"",                      // 0
                           "Basics Module...",      // 1
                           "Beef Module...",        // 2
                           "Dairy Module...",       // 3
                           "Tilage Module...",      // 4
                           "Create Expense...",     // 5
                           "Loading...",            // 6
                           "Preferences...",        // 7
                           "Medical Expense...",    // 8
                           "Vet Visit...",          // 9
                           "Feed Expense...",       // 10
                           "Create Cow...",         // 11
                           "Sell Cow...",           // 12
                           "Manage Herd...",        // 13
                           "Show Herd...",          // 14
                           "Manage Cows...",        // 15
                           "Medical Overview...",   // 16
                           "Pie Chart...",          // 17
                           "Bar Chart...",          // 18
                           "PDF Report..."};        // 19
        return status;
    }
    public String[] colors() {
        String[] colors = {"Cyan",        // 0
                           "Gray",        // 1
                           "Green",       // 2
                           "Light grey",  // 3
                           "Magenta",     // 4
                           "Orange",      // 5
                           "Pink",        // 6
                           "Red",         // 7
                           "White",       // 8
                           "Yellow"};     // 9
        return colors;
    }
    public String[] months() {
        String[] months = {"January",       // 0
                           "February",      // 1
                           "March",         // 2
                           "April",         // 3
                           "May",           // 4
                           "June",          // 5
                           "July",          // 6
                           "August",        // 7
                           "September",     // 8
                           "October",       // 9
                           "November",      // 10
                           "December"};     // 11
        return months;
    }
    public String[] str_years() {
        String[] years = {"2013",       // 0
                          "2014",       // 1
                          "2015",       // 2
                          "2016",       // 3
                          "2017",       // 4
                          "2018",       // 5
                          "2019",       // 6
                          "2020"};      // 7
        return years;
    }
    public int[] int_years() {
        int[] years = {2013,       // 0
                       2014,       // 1
                       2015,       // 2
                       2016,       // 3
                       2017,       // 4
                       2018,       // 5
                       2019,       // 6
                       2020};      // 7
        return years;
    }
    public String[] showMonths() {
        String[] months = {"",              // 0
                           "January",       // 1
                           "February",      // 2
                           "March",         // 3
                           "April",         // 4
                           "May",           // 5
                           "June",          // 6
                           "July",          // 7
                           "August",        // 8
                           "September",     // 9
                           "October",       // 10
                           "November",      // 11
                           "December"};     // 12
        return months;
    }
    public String[] str_showYears() {
        String[] years = {"",           // 0
                          "2013",       // 1
                          "2014",       // 2
                          "2015",       // 3
                          "2016",       // 4
                          "2017",       // 5
                          "2018",       // 6
                          "2019",       // 7
                          "2020"};      // 8
        return years;
    }
    public String[] expense_types() {
        String[] e_type = {"",              // 0
                           "Fertilizer",    // 1
                           "Gas",           // 2
                           "Labour",        // 3
                           "Lighting",      // 4
                           "Machinery",     // 5
                           "Mortgaged",     // 6
                           "Rented",        // 7
                           "Slurry",        // 8
                           "Water"};        // 9
        return e_type;
    }
    public String[] beef_expense_types() {
        String[] e_type = {"",              // 0
                           "Fertilizer",    // 1
                           "Gas",           // 2
                           "Labour",        // 3
                           "Lighting",      // 4
                           "Machinery",     // 5
                           "Mortgaged",     // 6
                           "Rented",        // 7
                           "Slurry",        // 8
                           "Water"};        // 9
        return e_type;
    }
    public String[] days() {
        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08",
                         "09", "10", "11", "12", "13", "14", "15", "16",
                         "17", "18", "19", "20", "21", "22", "23", "24",
                         "25", "26", "27", "28", "29", "30", "31"};
        return days;
    }
    public String[] ma_types() {
        String[] ma_type = {"",              // 0
                           "Tyres",         // 1
                           "Fuel",          // 2
                           "Repairs",       // 3
                           "Service",       // 4
                           "Tax",           // 5
                           "Insurance"};    // 6
        return ma_type;
    }
    public String[] feed_type() {
        String[] feed_type = {"",           // 0
                              "Hay",        // 1
                              "Nuts",       // 2
                              "Silage"};    // 3
        return feed_type;
    }
}