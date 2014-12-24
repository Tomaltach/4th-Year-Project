package beefmodule.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;
import java.util.*;

import beefmodule.datahandler.*;
import financemanager.*;

public class SQL_Beef_Handler implements SQL_Beef_Interface{
    Resources resource = new Resources();

    String sql = "";
    String display = "";
    Connection conn = null;
    ResultSet result = null;
    Statement select = null;
    Statement insert = null;
    Statement update = null;
    Statement delete = null;
    
    // Holder variables
    Double expenseIn;
    String infoIn;
    String dateIn;
    String cowtagIn;
    String[] columnNames;
    Object[][] data;
    Double bPriceIn;
    String bDateIn;
    Double sPriceIn;
    String sDateIn;
    int herd_id;
    String herd_creation_date;
    String herd_termination_date;
    Double hayExpense;
    Double nutsExpense;
    Double silageExpense;
    String month;
    String year;

    String yearIn;
    String monthIn;
    int herdIdIn;

    // INSERT functions for SQL handling
    public void create_herd(int herdId, String herdCDate) throws SQLException {
        if(herdCDate != null) {
            sql = "INSERT INTO app.herd_info_list("
                + "herd_id, herd_creation_date) "
                + "VALUES(" + herdId + ", CAST('" + herdCDate + "' AS DATE))";
        
            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Herd has been created successfully";
                System.out.println(display);
            }
            catch(Exception e) {
                e.printStackTrace();
                display = "ERROR: Connection could not be made";
                System.out.println(display);
            }
            finally {
                if(conn != null) {
                    try {
                        conn.close();//close connection
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        display = "ERROR: Connection could not be closed";
                        System.out.println(display);
                    }
                }
            }
        }
        else if(herdCDate == null) {
            display = "ERROR! herdCDate CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_cow(String cowTag, double cBPrice, String cBDate) throws SQLException {
        if(cBDate != null) {
            sql = "INSERT INTO app.beef_info_list("
                + "beef_cow_tag, beef_bought_price, beef_bought_date) "
                + "VALUES('" + cowTag + "', " + cBPrice + ", CAST('" + cBDate + "' AS DATE))";

            try {
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Cow has been created successfully";
                System.out.println(display);
            }
            catch(Exception e) {
                e.printStackTrace();
                display = "ERROR: Connection could not be made";
                System.out.println(display);
            }
            finally {
                if(conn != null) {
                    try {
                        conn.close();//close connection
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        display = "ERROR: Connection could not be closed";
                        System.out.println(display);
                    }
                }
            }

            // add cow to default herd -> 0
            sql = "INSERT INTO app.herd_cow_linker("
                + "herd_id, beef_cow_tag) "
                + "VALUES(" + resource.DEFAULT_HERD + ", '" + cowTag + "')";

            try {
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Cow and herd have been successfully linked";
                System.out.println(display);
            }
            catch(Exception e) {
                e.printStackTrace();
                display = "ERROR: Connection could not be made";
                System.out.println(display);
            }
            finally {
                if(conn != null) {
                    try {
                        conn.close();//close connection
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        display = "ERROR: Connection could not be closed";
                        System.out.println(display);
                    }
                }
            }
        }
        else if(cBDate == null) {
            display = "ERROR! cBDate CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_feed_expenses(String sql) throws SQLException {
        this.sql = sql;
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            insert = conn.createStatement();
            insert.executeUpdate(sql);
            display = "Feed expense has been created successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }
    public void create_medical_expenses(String cowTag, String medInfo, String medDate, Double medPrice) throws SQLException {
        if(medPrice != null) {
            sql = "INSERT INTO app.medical_expenses("
                + "beef_cow_tag, med_info, med_date, med_expense) "
                + "VALUES('" + cowTag + "', '" + medInfo + "', CAST('" + medDate + "' AS DATE), " + medPrice + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Medical expense has been created successfully";
                System.out.println(display);
            }
            catch(Exception e) {
                e.printStackTrace();
                display = "ERROR: Connection could not be made";
                System.out.println(display);
            }
            finally {
                if(conn != null) {
                    try {
                        conn.close();//close connection
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        display = "ERROR: Connection could not be closed";
                        System.out.println(display);
                    }
                }
            }
        }
        else if(medPrice == null) {
            display = "ERROR! medPrice CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_vet_visits(String cowTag, String vvInfo, String vvDate, Double vvPrice) throws SQLException {
        if(vvPrice != null) {
            sql = "INSERT INTO app.vet_visits("
                + "beef_cow_tag, vv_info, vv_date, vv_expense) "
                + "VALUES('" + cowTag + "', '" + vvInfo + "', CAST('" + vvDate + "' AS DATE), " + vvPrice + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Vet visit has been created successfully";
                System.out.println(display);
            }
            catch(Exception e) {
                e.printStackTrace();
                display = "ERROR: Connection could not be made";
                System.out.println(display);
            }
            finally {
                if(conn != null) {
                    try {
                        conn.close();//close connection
                    }
                    catch(Exception e) {
                        e.printStackTrace();
                        display = "ERROR: Connection could not be closed";
                        System.out.println(display);
                    }
                }
            }
        }
        else if(vvPrice == null) {
            display = "ERROR! medPrice CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }

    // UPDATE functions for SQL handling
    public void edit_cow_info(String cowTag, double cSPrice, String cSDate) throws SQLException {
        // To update the sale price
        sql = "UPDATE app.beef_info_list "
            + "SET beef_sold_price = " + cSPrice
            + "WHERE beef_cow_tag = '" + cowTag + "'";
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Herd has been edited successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }

        // To update the sale date
        sql = "UPDATE app.beef_info_list "
            + "SET beef_sold_date = CAST('" + cSDate + "' AS DATE)"
            + "WHERE beef_cow_tag = '" + cowTag + "'";
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Herd has been edited successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }

        // To update the herd info
        sql = "UPDATE app.herd_cow_linker "
            + "SET herd_id = -1"
            + "WHERE beef_cow_tag = '" + cowTag + "'";
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Herd has been edited successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }
    public void edit_feed_expenses(String sql) throws SQLException {
        this.sql = sql;
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Feed expense has been edited successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }
    public void edit_herd(int herd_id, String herdTDate) throws SQLException {
        sql = "UPDATE app.herd_info_list "
            + "SET herd_termination_date = CAST('" + herdTDate + "' AS DATE)"
            + "WHERE herd_id = " + herd_id;
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Herd has been edited successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }
    public void add_cow_to_herd(int herdId, String cowTag) throws SQLException {
        sql = "UPDATE app.herd_cow_linker "
            + "SET herd_id = " + herdId
            + "WHERE beef_cow_tag = '" + cowTag + "'";

        try {
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Cow has been added to herd successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally{
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }
    public void remove_cow_from_herd(String cowTag) throws SQLException {
        sql = "UPDATE app.herd_cow_linker "
            + "SET herd_id = " + resource.DEFAULT_HERD
            + "WHERE beef_cow_tag = " + cowTag;

        try {
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Cow has been removed from herd successfully";
            System.out.println(display);
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
            System.out.println(display);
        }
        finally{
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                    System.out.println(display);
                }
            }
        }
    }

    // SELECT functions for SQL handling
    public List<Cow_Data> select_cow_data() throws SQLException {
        sql = "SELECT * FROM app.beef_info_list ORDER BY beef_sold_date DESC, beef_cow_tag";
        List<Cow_Data> CList = new ArrayList<Cow_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                cowtagIn = result.getString("beef_cow_tag");
                bPriceIn = result.getDouble("beef_bought_price");
                sPriceIn = result.getDouble("beef_sold_price");
                bDateIn = result.getString("beef_bought_date");
                sDateIn = result.getString("beef_sold_date");
                Cow_Data cData = new Cow_Data();
                cData.setCowTag(cowtagIn);
                cData.setBoughtPrice(bPriceIn);
                cData.setSoldPrice(sPriceIn);
                cData.setBoughtDate(bDateIn);
                cData.setSoldDate(sDateIn);
                CList.add(cData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return CList;
    }
    public List<Feed_Data> select_feed_data() throws SQLException {
        sql = "SELECT * FROM app.feed_expenses ORDER BY herd_id";
        List<Feed_Data> FList = new ArrayList<Feed_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                herd_id = result.getInt("herd_id");
                hayExpense = result.getDouble("feed_hay_expense");
                nutsExpense = result.getDouble("feed_nuts_expense");
                silageExpense = result.getDouble("feed_silage_expense");
                month = result.getString("feed_month");
                year = result.getString("feed_year");
                Feed_Data fData = new Feed_Data();
                fData.setHerd_id(herd_id);
                fData.setF_hay_expense(hayExpense);
                fData.setF_nuts_expense(nutsExpense);
                fData.setF_silage_expense(silageExpense);
                fData.setMonth(month);
                fData.setYear(year);
                FList.add(fData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return FList;
    }
    public List<Herd_Data> select_herd_data() throws SQLException {
        sql = "SELECT * FROM app.herd_info_list ORDER BY herd_termination_date DESC, herd_id";
        List<Herd_Data> HList = new ArrayList<Herd_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                herd_id = result.getInt("herd_id");
                herd_creation_date = result.getString("herd_creation_date");
                herd_termination_date = result.getString("herd_termination_date");
                Herd_Data hData = new Herd_Data();
                hData.setHerd_id(herd_id);
                hData.setHerd_creation_date(herd_creation_date);
                hData.setHerd_termination_date(herd_termination_date);
                HList.add(hData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return HList;
    }
    public List<Herd_Data> select_herd_data_active() throws SQLException {
        sql = "SELECT * FROM app.herd_info_list "
            + "WHERE herd_termination_date IS NULL "
            + "ORDER BY herd_termination_date DESC, herd_id";
        List<Herd_Data> HList = new ArrayList<Herd_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                herd_id = result.getInt("herd_id");
                herd_creation_date = result.getString("herd_creation_date");
                herd_termination_date = result.getString("herd_termination_date");
                Herd_Data hData = new Herd_Data();
                hData.setHerd_id(herd_id);
                hData.setHerd_creation_date(herd_creation_date);
                hData.setHerd_termination_date(herd_termination_date);
                HList.add(hData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return HList;
    }
    public List<Herd_Data> select_herd_list_data() throws SQLException {
        sql = "SELECT * FROM app.herd_cow_linker "
            + "WHERE herd_id != -1 "
            + "ORDER BY herd_id, beef_cow_tag";
        List<Herd_Data> HList = new ArrayList<Herd_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                herd_id = result.getInt("herd_id");
                cowtagIn = result.getString("beef_cow_tag");
                Herd_Data hData = new Herd_Data();
                hData.setHerd_id(herd_id);
                hData.setCowtag(cowtagIn);
                HList.add(hData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return HList;
    }
    public List<CowTag_Data> select_herd_linker_data() throws SQLException {
        sql = "SELECT * FROM app.herd_cow_linker "
            + "WHERE herd_id != -1 "
            + "ORDER BY herd_id, beef_cow_tag";
        List<CowTag_Data> CTList = new ArrayList<CowTag_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                herd_id = result.getInt("herd_id");
                cowtagIn = result.getString("beef_cow_tag");
                CowTag_Data ctData = new CowTag_Data();
                ctData.setHerd_id(herd_id);
                ctData.setCowTag(cowtagIn);
                CTList.add(ctData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return CTList;
    }
    public List<Medical_Data> select_medical_data() throws SQLException {
        sql = "SELECT * FROM app.medical_expenses ORDER BY beef_cow_tag, med_date";
        List<Medical_Data> MEList = new ArrayList<Medical_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                cowtagIn = result.getString("beef_cow_tag");
                infoIn = result.getString("med_info");
                dateIn = result.getString("med_date");
                expenseIn = result.getDouble("med_expense");
                Medical_Data meData = new Medical_Data();
                meData.setCowTag(cowtagIn);
                meData.setInfo(infoIn);
                meData.setDate(dateIn);
                meData.setExpense(expenseIn);
                MEList.add(meData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return MEList;
    }   
    public List<Vet_Visit_Data> select_vet_visit_data() throws SQLException {
        sql = "SELECT * FROM app.vet_visits ORDER BY beef_cow_tag";
        List<Vet_Visit_Data> VVList = new ArrayList<Vet_Visit_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                cowtagIn = result.getString("beef_cow_tag");
                infoIn = result.getString("vv_info");
                dateIn = result.getString("vv_date");
                expenseIn = result.getDouble("vv_expense");
                Vet_Visit_Data vvData = new Vet_Visit_Data();
                vvData.setCowTag(cowtagIn);
                vvData.setInfo(infoIn);
                vvData.setDate(dateIn);
                vvData.setExpense(expenseIn);
                VVList.add(vvData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return VVList;
    }

    public List<CowTag_Data> select_cowtag_data() throws SQLException {
        sql = "SELECT beef_cow_tag FROM app.beef_info_list WHERE beef_sold_date IS NULL";
        List<CowTag_Data> CTList = new ArrayList<CowTag_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                cowtagIn = result.getString("beef_cow_tag");
                CowTag_Data ctData = new CowTag_Data();
                ctData.setCowTag(cowtagIn);
                CTList.add(ctData);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally{
            if(conn != null){
                try{
                    conn.close();//close connection
                }
                catch(Exception e){
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
        return CTList;
    }

    // Checking methods

    public void checkMonthYear(String sql) {
        this.sql = sql;
        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            select = conn.createStatement();
            result = select.executeQuery(sql);
            display = "Check has been successful";
            while(result.next()) {
                yearIn = result.getString("feed_year");
                monthIn = result.getString("feed_month");
                herdIdIn = result.getInt("herd_id");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "ERROR: Connection could not be made";
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "ERROR: Connection could not be closed";
                }
            }
        }
    }
    // Holder methods
    public String getMonth() {
        return monthIn;
    }
    public String getYear() {
        return yearIn;
    }
    public int getHerdId() {
        return herdIdIn;
    }
}