package financemanager.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.util.*;
import java.sql.*;

import financemanager.*;
import financemanager.datahandler.*;

public class SQL_Handler implements SQL_Interface {
    Resources resource = new Resources();

    String sql = "";
    String display = "";
    Connection conn = null;
    ResultSet result = null;
    Statement select = null;
    Statement insert = null;
    Statement update = null;

    // Holder variables
    Double expenseIn;
    String monthIn;
    int yearIn;
    String[] columnNames;
    Object[][] data;

    // INSERT functions for SQL handling
    public void create_fertilizer_expense(Double f_expense, int f_year, String f_month) throws SQLException {
        if(f_expense != null) {
            sql = "INSERT INTO app.fertilizer_expenses("
                + "f_expense, f_month, f_year) "
                + "VALUES(" + f_expense + ", '" + f_month + "', " + f_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Fertilizer expense has been created successfully";
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
        else if(f_expense == null) {
            display = "ERROR! f_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_gas_expense(Double g_expense, int g_year, String g_month) throws SQLException {
        if(g_expense != null) {
            sql = "INSERT INTO app.gas_expenses("
                + "g_expense, g_month, g_year) "
                + "VALUES(" + g_expense + ", '" + g_month + "', " + g_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Gas expense has been created successfully";
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
        else if(g_expense == null) {
            display = "ERROR! g_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_labour_expense(Double l_expense, int l_year, String l_month) throws SQLException {
        if(l_expense != null) {
            sql = "INSERT INTO app.labour_expenses("
                + "l_expense, l_month, l_year) "
                + "VALUES(" + l_expense + ", '" + l_month + "', " + l_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Labour expense has been created successfully";
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
        else if(l_expense == null) {
            display = "ERROR! l_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_lighting_expense(Double li_expense, int li_year, String li_month) throws SQLException {
        if(li_expense != null) {
            sql = "INSERT INTO app.lighting_expenses("
                + "li_expense, li_month, li_year) "
                + "VALUES(" + li_expense + ", '" + li_month + "', " + li_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Lighting expense has been created successfully";
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
        else if(li_expense == null) {
            display = "ERROR! li_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_machinery_expense(Double ma_expense, int ma_year, String ma_month) throws SQLException {
        if(ma_expense != null) {
            sql = "INSERT INTO app.machinery_expenses("
                + "ma_expense, ma_month, ma_year) "
                + "VALUES(" + ma_expense + ", '" + ma_month + "', " + ma_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Machinery expense has been created successfully";
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
        else if(ma_expense == null) {
            display = "ERROR! ma_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_mortgaged_land(Double m_expense, int m_year, String m_month) throws SQLException {
        if(m_expense != null) {
            sql = "INSERT INTO app.mortgaged_land("
                + "m_expense, m_month, m_year) "
                + "VALUES(" + m_expense + ", '" + m_month + "', " + m_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Mortgaged expense has been created successfully";
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
        else if(m_expense == null) {
            display = "ERROR! m_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_rented_land(Double r_expense, int r_year, String r_month) throws SQLException {
        if(r_expense != null) {
            sql = "INSERT INTO app.rented_land("
                + "r_expense, r_month, r_year) "
                + "VALUES(" + r_expense + ", '" + r_month + "', " + r_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Rented expense has been created successfully";
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
        else if(r_expense == null) {
            display = "ERROR! r_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_slurry_expense(Double s_expense, int s_year, String s_month) throws SQLException {
        if(s_expense != null) {
            sql = "INSERT INTO app.slurry_expenses("
                + "s_expense, s_month, s_year) "
                + "VALUES(" + s_expense + ", '" + s_month + "', " + s_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Slurry expense has been created successfully";
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
        else if(s_expense == null) {
            display = "ERROR! s_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }
    public void create_water_expense(Double w_expense, int w_year, String w_month) throws SQLException {
        if(w_expense != null) {
            sql = "INSERT INTO app.water_expenses("
                + "w_expense, w_month, w_year) "
                + "VALUES(" + w_expense + ", '" + w_month + "', " + w_year + ")";

            try{
                conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
                insert = conn.createStatement();
                insert.executeUpdate(sql);
                display = "Water expense has been created successfully";
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
        else if(w_expense == null) {
            display = "ERROR! w_expense CANNOT BE null";
            System.out.println(display);
        }
        else {
            display = "ERROR! Not all args[] were filled";
            System.out.println(display);
        }
    }

    // UPDATE functions for SQL handling
    public void edit_fertilizer_expense(Double f_expense, int f_year, String f_month) throws SQLException {
        sql = "UPDATE app.fertilizer_expenses "
            + "SET f_expense = f_expense + " + f_expense
            + "WHERE f_month = '" + f_month + "' AND f_year = " + f_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Fertilizer expense has successfully been updated";
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
    public void edit_gas_expense(Double g_expense, int g_year, String g_month) throws SQLException {
        sql = "UPDATE app.gas_expenses "
            + "SET g_expense = g_expense + " + g_expense
            + "WHERE g_month = '" + g_month + "' AND g_year = " + g_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Gas expense has successfully been updated";
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
    public void edit_labour_expense(Double l_expense, int l_year, String l_month) throws SQLException {
        sql = "UPDATE app.labour_expenses "
            + "SET l_expense = l_expense + " + l_expense
            + "WHERE l_month = '" + l_month + "' AND l_year = " + l_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Labour expense has successfully been updated";
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
    public void edit_lighting_expense(Double li_expense, int li_year, String li_month) throws SQLException {
        sql = "UPDATE app.lighting_expenses "
            + "SET li_expense = li_expense + " + li_expense
            + "WHERE li_month = '" + li_month + "' AND li_year = " + li_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Lighting expense has successfully been updated";
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
    public void edit_machinery_expense(Double ma_expense, int ma_year, String ma_month) throws SQLException {
        sql = "UPDATE app.machinery_expenses "
            + "SET ma_expense = ma_expense + " + ma_expense
            + "WHERE ma_month = '" + ma_month + "' AND ma_year = " + ma_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Machinery expense has successfully been updated";
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
    public void edit_mortgaged_land(Double m_expense, int m_year, String m_month) throws SQLException {
        sql = "UPDATE app.mortgaged_land "
            + "SET m_expense = m_expense + " + m_expense
            + "WHERE m_month = '" + m_month + "' AND m_year = " + m_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Mortgaged land has successfully been updated";
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
    public void edit_rented_land(Double r_expense, int r_year, String r_month) throws SQLException {
        sql = "UPDATE app.rented_land "
            + "SET r_expense = r_expense + " + r_expense
            + "WHERE r_month = '" + r_month + "' AND r_year = " + r_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Rented land has successfully been updated";
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
    public void edit_slurry_expense(Double s_expense, int s_year, String s_month) throws SQLException {
        sql = "UPDATE app.slurry_expenses "
            + "SET s_expense = s_expense + " + s_expense
            + "WHERE s_month = '" + s_month + "' AND s_year = " + s_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Slurry expense has successfully been updated";
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
    public void edit_water_expense(Double w_expense, int w_year, String w_month) throws SQLException {
        sql = "UPDATE app.water_expenses "
            + "SET w_expense = w_expense + " + w_expense
            + "WHERE w_month = '" + w_month + "' AND w_year = " + w_year;

        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            update = conn.createStatement();
            update.executeUpdate(sql);
            display = "Water expense has successfully been updated";
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

    // SELECT functions for SQL handling
    public List<Fertilizer_Data> select_fertilizer_expense() throws SQLException {
        sql = "SELECT * FROM app.fertilizer_expenses";
        List<Fertilizer_Data> FDList = new ArrayList<Fertilizer_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("f_expense");
                monthIn = result.getString("f_month");
                yearIn = result.getInt("f_year");
                //data = {expenseIn, monthIn, yearIn};
                Fertilizer_Data fData = new Fertilizer_Data();
                fData.setExpense(expenseIn);
                fData.setMonth(monthIn);
                fData.setYear(yearIn);
                FDList.add(fData);
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
        return FDList;
    }
    public List<Gas_Data> select_gas_expense() throws SQLException {
        sql = "SELECT * FROM app.gas_expenses";
        List<Gas_Data> GDList = new ArrayList<Gas_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("g_expense");
                monthIn = result.getString("g_month");
                yearIn = result.getInt("g_year");
                //data = {expenseIn, monthIn, yearIn};
                Gas_Data gData = new Gas_Data();
                gData.setExpense(expenseIn);
                gData.setMonth(monthIn);
                gData.setYear(yearIn);
                GDList.add(gData);
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
        return GDList;
    }
    public List<Labour_Data> select_labour_expense() throws SQLException {
        sql = "SELECT * FROM app.labour_expenses";
        List<Labour_Data> LDList = new ArrayList<Labour_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("l_expense");
                monthIn = result.getString("l_month");
                yearIn = result.getInt("l_year");
                //data = {expenseIn, monthIn, yearIn};
                Labour_Data lData = new Labour_Data();
                lData.setExpense(expenseIn);
                lData.setMonth(monthIn);
                lData.setYear(yearIn);
                LDList.add(lData);
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
        return LDList;
    }
    public List<Lighting_Data> select_lighting_expense() throws SQLException {
        sql = "SELECT * FROM app.lighting_expenses";
        List<Lighting_Data> LiDList = new ArrayList<Lighting_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("li_expense");
                monthIn = result.getString("li_month");
                yearIn = result.getInt("li_year");
                //data = {expenseIn, monthIn, yearIn};
                Lighting_Data liData = new Lighting_Data();
                liData.setExpense(expenseIn);
                liData.setMonth(monthIn);
                liData.setYear(yearIn);
                LiDList.add(liData);
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
        return LiDList;
    }
    public List<Machinery_Data> select_machinery_expense() throws SQLException {
        sql = "SELECT * FROM app.machinery_expenses";
        List<Machinery_Data> MaDList = new ArrayList<Machinery_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("ma_expense");
                monthIn = result.getString("ma_month");
                yearIn = result.getInt("ma_year");
                //data = {expenseIn, monthIn, yearIn};
                Machinery_Data maData = new Machinery_Data();
                maData.setExpense(expenseIn);
                maData.setMonth(monthIn);
                maData.setYear(yearIn);
                MaDList.add(maData);
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
        return MaDList;
    }
    public List<Mortgaged_Data> select_mortgaged_land() throws SQLException {
        sql = "SELECT * FROM app.mortgaged_land";
        List<Mortgaged_Data> MDList = new ArrayList<Mortgaged_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("m_expense");
                monthIn = result.getString("m_month");
                yearIn = result.getInt("m_year");
                //data = {expenseIn, monthIn, yearIn};
                Mortgaged_Data mData = new Mortgaged_Data();
                mData.setExpense(expenseIn);
                mData.setMonth(monthIn);
                mData.setYear(yearIn);
                MDList.add(mData);
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
        return MDList;
    }
    public List<Rented_Data> select_rented_land() throws SQLException {
        sql = "SELECT * FROM app.rented_land";
        List<Rented_Data> RDList = new ArrayList<Rented_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("r_expense");
                monthIn = result.getString("r_month");
                yearIn = result.getInt("r_year");
                //data = {expenseIn, monthIn, yearIn};
                Rented_Data rData = new Rented_Data();
                rData.setExpense(expenseIn);
                rData.setMonth(monthIn);
                rData.setYear(yearIn);
                RDList.add(rData);
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
        return RDList;
    }
    public List<Slurry_Data> select_slurry_expense() throws SQLException {
        sql = "SELECT * FROM app.slurry_expenses";
        List<Slurry_Data> SDList = new ArrayList<Slurry_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("s_expense");
                monthIn = result.getString("s_month");
                yearIn = result.getInt("s_year");
                //data = {expenseIn, monthIn, yearIn};
                Slurry_Data sData = new Slurry_Data();
                sData.setExpense(expenseIn);
                sData.setMonth(monthIn);
                sData.setYear(yearIn);
                SDList.add(sData);
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
        return SDList;
    }
    public List<Water_Data> select_water_expense() throws SQLException {
        sql = "SELECT * FROM app.water_expenses";
        List<Water_Data> WDList = new ArrayList<Water_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            //columnNames = {"Expense", "Month", "Year"};
            while(result.next()){// process results one row at a time
                expenseIn = result.getDouble("w_expense");
                monthIn = result.getString("w_month");
                yearIn = result.getInt("w_year");
                //data = {expenseIn, monthIn, yearIn};
                Water_Data wData = new Water_Data();
                wData.setExpense(expenseIn);
                wData.setMonth(monthIn);
                wData.setYear(yearIn);
                WDList.add(wData);
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
        return WDList;
    }

    public List<Basics_Data> select_basics(String sqlStat1, String sqlStat2, String sqlStat3, String sqlStat4, String sqlStat5, String sqlStat6, String sqlStat7, String sqlStat8, String sqlStat9) throws SQLException {
        List<Basics_Data> BDList = new ArrayList<Basics_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sqlStat1);
            //columnNames = {"Expense", "Month", "Year"};
            Basics_Data bData1 = new Basics_Data();
            bData1.setTitle("Fertilizer Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("f_expense");
                yearIn = result.getInt("f_year");
                monthIn = result.getString("f_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat2);
            //columnNames = {"Expense", "Month", "Year"};
            bData1 = new Basics_Data();
            bData1.setTitle("Gas Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("g_expense");
                yearIn = result.getInt("g_year");
                monthIn = result.getString("g_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat3);
            //columnNames = {"Expense", "Month", "Year"};
            bData1 = new Basics_Data();
            bData1.setTitle("Labour Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("l_expense");
                yearIn = result.getInt("l_year");
                monthIn = result.getString("l_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat4);
            bData1 = new Basics_Data();
            bData1.setTitle("Lighting Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("li_expense");
                yearIn = result.getInt("li_year");
                monthIn = result.getString("li_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat5);
            bData1 = new Basics_Data();
            bData1.setTitle("Machinery Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("ma_expense");
                yearIn = result.getInt("ma_year");
                monthIn = result.getString("ma_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat6);
            bData1 = new Basics_Data();
            bData1.setTitle("Mortgage Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("m_expense");
                yearIn = result.getInt("m_year");
                monthIn = result.getString("m_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat7);
            bData1 = new Basics_Data();
            bData1.setTitle("Rent Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("r_expense");
                yearIn = result.getInt("r_year");
                monthIn = result.getString("r_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat8);
            //columnNames = {"Expense", "Month", "Year"};
            bData1 = new Basics_Data();
            bData1.setTitle("Slurry Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("s_expense");
                yearIn = result.getInt("s_year");
                monthIn = result.getString("s_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
            }
            select = conn.createStatement();
            result = select.executeQuery(sqlStat9);
            //columnNames = {"Expense", "Month", "Year"};
            bData1 = new Basics_Data();
            bData1.setTitle("Water Info");
            BDList.add(bData1);
            while(result.next()) {
                expenseIn = result.getDouble("w_expense");
                yearIn = result.getInt("w_year");
                monthIn = result.getString("w_month");
                Basics_Data bData = new Basics_Data();
                bData.setTitle("");
                bData.setExpense(expenseIn);
                bData.setMonth(monthIn);
                bData.setYear(yearIn);
                BDList.add(bData);
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
        return BDList;
    }
    public List<Basics_Data> select_basics(String sql, int type) throws SQLException {
        this.sql = sql;
        List<Basics_Data> BDList = new ArrayList<Basics_Data>();
        try{
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection?
            select = conn.createStatement();
            result = select.executeQuery(sql);
            Basics_Data bData1;
            //columnNames = {"Expense", "Month", "Year"};
            switch(type) {
                case 1:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Fertilizer Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("f_expense");
                        yearIn = result.getInt("f_year");
                        monthIn = result.getString("f_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 2:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Gas Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("g_expense");
                        yearIn = result.getInt("g_year");
                        monthIn = result.getString("g_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 3:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Labour Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("l_expense");
                        yearIn = result.getInt("l_year");
                        monthIn = result.getString("l_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 4:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Lighting Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("li_expense");
                        yearIn = result.getInt("li_year");
                        monthIn = result.getString("li_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 5:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Machinery Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("ma_expense");
                        yearIn = result.getInt("ma_year");
                        monthIn = result.getString("ma_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 6:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Mortgage Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("m_expense");
                        yearIn = result.getInt("m_year");
                        monthIn = result.getString("m_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 7:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Rent Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("r_expense");
                        yearIn = result.getInt("r_year");
                        monthIn = result.getString("r_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 8:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Slurry Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("s_expense");
                        yearIn = result.getInt("s_year");
                        monthIn = result.getString("s_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                case 9:
                    bData1 = new Basics_Data();
                    bData1.setTitle("Water Info");
                    BDList.add(bData1);
                    while(result.next()) {
                        expenseIn = result.getDouble("w_expense");
                        yearIn = result.getInt("w_year");
                        monthIn = result.getString("w_month");
                        Basics_Data bData = new Basics_Data();
                        bData.setTitle("");
                        bData.setExpense(expenseIn);
                        bData.setMonth(monthIn);
                        bData.setYear(yearIn);
                        BDList.add(bData);
                    }
                    break;
                default:
                    System.out.println("ERROR! checkMonthYear");
                    break;
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
        return BDList;
    }

    // Checking methods
    public void checkMonthYear(String sql, int type) {
        this.sql = sql;
        try {
            //Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            select = conn.createStatement();
            result = select.executeQuery(sql);
            display = "Check has been successful";
            switch(type) {
                case 1:
                    while(result.next()) {
                        yearIn = result.getInt("f_year");
                        monthIn = result.getString("f_month");
                    }
                    break;
                case 2:
                    while(result.next()) {
                        yearIn = result.getInt("g_year");
                        monthIn = result.getString("g_month");
                    }
                    break;
                case 3:
                    while(result.next()) {
                        yearIn = result.getInt("l_year");
                        monthIn = result.getString("l_month");
                    }
                    break;
                case 4:
                    while(result.next()) {
                        yearIn = result.getInt("li_year");
                        monthIn = result.getString("li_month");
                    }
                    break;
                case 5:
                    while(result.next()) {
                        yearIn = result.getInt("ma_year");
                        monthIn = result.getString("ma_month");
                    }
                    break;
                case 6:
                    while(result.next()) {
                        yearIn = result.getInt("m_year");
                        monthIn = result.getString("m_month");
                    }
                    break;
                case 7:
                    while(result.next()) {
                        yearIn = result.getInt("r_year");
                        monthIn = result.getString("r_month");
                    }
                    break;
                case 8:
                    while(result.next()) {
                        yearIn = result.getInt("s_year");
                        monthIn = result.getString("s_month");
                    }
                    break;
                case 9:
                    while(result.next()) {
                        yearIn = result.getInt("w_year");
                        monthIn = result.getString("w_month");
                    }
                    break;
                default:
                    System.out.println("ERROR! checkMonthYear");
                    break;
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
    public int getYear() {
        return yearIn;
    }
}