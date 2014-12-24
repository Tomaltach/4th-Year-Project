package beefmodule.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

public class SQL_Create_Beef_DB_Tables implements SQL_Create_Tables_Interface {
    final String DATABASE_URL = "jdbc:derby://localhost:1527/Farm Finance Manager";
    String sql = "";
    String display;
    Connection conn = null;
    ResultSet result = null;
    Statement select = null;
    Statement insert = null;
    Statement delete = null;

    public SQL_Create_Beef_DB_Tables() throws SQLException {
        display = "";
        create_beef_info_list();
        create_feed_expenses();
        create_herd_cow_linker();
        create_herd_info_list();
        create_medical();
        create_vet_visits();
        System.out.println(display);
    }
    public void create_beef_info_list() throws SQLException {
        sql = "CREATE TABLE beef_info_list("
            + "beef_cow_tag int NOT NULL UNIQUE, "
            + "beef_bought_price double, "
            + "beef_sold_price double, "
            + "beef_bought_date date, "
            + "beef_sold_date date)";
    }
    public void create_feed_expenses() throws SQLException {
        sql = "CREATE TABLE feed_expenses("
            + "herd_id int NOT NULL UNIQUE, "
            + "f_nuts double, "
            + "f_silage double, "
            + "f_hay double)";
    }
    public void create_herd_cow_linker() throws SQLException {
        sql = "CREATE TABLE beef_info_list("
            + "herd_id int NOT NULL UNIQUE, "
            + "beef_cow_tag int NOT NULL UNIQUE)";
    }
    public void create_herd_info_list() throws SQLException {
        sql = "CREATE TABLE herd_info_list("
            + "herd_id int NOT NULL UNIQUE, "
            + "herd_creation_date date, "
            + "herd_termination_date date)";
    }
    public void create_medical() throws SQLException {
        sql = "CREATE TABLE medical("
            + "med_id int NOT NULL UNIQUE, "
            + "beef_cow_tag int NOT NULL UNIQUE, "
            + "med_info varchar(50), "
            + "med_date date, "
            + "med_price double)";
    }
    public void create_vet_visits() throws SQLException {
        sql = "CREATE TABLE vet_visits("
            + "vv_id int NOT NULL UNIQUE, "
            + "beef_cow_tag int NOT NULL UNIQUE, "
            + "vv_info varchar(50), "
            + "vv_date date, "
            + "vv_price double)";
    }
}