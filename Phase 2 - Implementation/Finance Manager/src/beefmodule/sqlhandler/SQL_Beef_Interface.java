package beefmodule.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

public interface SQL_Beef_Interface {
    // INSERT functions for SQL handling
    public void create_herd(int herdId, String herdCDate) throws SQLException;
    public void create_cow(String cowTag, double cBPrice, String cBDate) throws SQLException;
    public void create_feed_expenses(String sql) throws SQLException;
    public void create_medical_expenses(String cowTag, String medInfo, String medDate, Double medPrice) throws SQLException;
    public void create_vet_visits(String cowTag, String vvInfo, String vvDate, Double vvPrice) throws SQLException;
    // UPDATE functions for SQL handling
    public void edit_cow_info(String cowTag, double cSPrice, String cSDate) throws SQLException;
    public void edit_herd(int herd_id, String herdTDate) throws SQLException;
    public void edit_feed_expenses(String sql) throws SQLException;
    public void add_cow_to_herd(int herdId, String cowTag) throws SQLException;
    public void remove_cow_from_herd(String cowTag) throws SQLException;
    // SELECT functions for SQL handling
    
}
