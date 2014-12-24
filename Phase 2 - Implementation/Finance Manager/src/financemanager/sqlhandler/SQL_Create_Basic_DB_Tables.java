package financemanager.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

import financemanager.*;

public class SQL_Create_Basic_DB_Tables implements SQL_Create_Tables_Interface {
    Resources resource = new Resources();

    String sql = "";
    String display = "";
    Connection conn = null;
    ResultSet result = null;
    Statement create = null;

    public SQL_Create_Basic_DB_Tables() throws SQLException {
        display = "Creating tables:\n";
        create_labour_expenses();
        create_lighting_expenses();
        create_machinery_expenses();
        create_mortgaged_land();
        create_rented_land();
        System.out.println(display);
    }
    public void create_labour_expenses() throws SQLException {
        sql = "CREATE TABLE labour_expenses("
            + "l_id int NOT NULL UNIQUE, "
            + "l_expenses double, "
            + "l_month varchar(20))";
        try {
            conn = DriverManager.getConnection(resource.DATABASE_URL);//open connection
            create = conn.createStatement();
            create.executeUpdate(sql);
            display += "\tTABLE labour_expenses :: Created\n";
            //return display;
        }
        catch(Exception e) {
            e.printStackTrace();
            display = "\tTABLE labour_expenses :: ERROR: Connection could not be made";
        }
        finally {
            if(conn != null) {
                try {
                    conn.close();//close connection
                }
                catch(Exception e) {
                    e.printStackTrace();
                    display = "\tTABLE labour_expenses :: ERROR: Connection could not be closed";
                }
            }
        }
    }
    public void create_lighting_expenses() throws SQLException {
        sql = "CREATE TABLE lighting_expenses("
            + "li_id int NOT NULL UNIQUE, "
            + "li_expenses double, "
            + "li_month varchar(20))";
    }
    public void create_machinery_expenses() throws SQLException {
        sql = "CREATE TABLE machinery_expenses("
            + "ma_id int NOT NULL UNIQUE, "
            + "ma_expenses double, "
            + "ma_month varchar(20))";
    }
    public void create_mortgaged_land() throws SQLException {
        sql = "CREATE TABLE mortgaged_land("
            + "m_id int NOT NULL UNIQUE, "
            + "m_expenses double, "
            + "m_month varchar(20))";
    }
    public void create_rented_land() throws SQLException {
        sql = "CREATE TABLE rented_land("
            + "r_id int NOT NULL UNIQUE, "
            + "r_expenses double, "
            + "r_month varchar(20))";
    }
}