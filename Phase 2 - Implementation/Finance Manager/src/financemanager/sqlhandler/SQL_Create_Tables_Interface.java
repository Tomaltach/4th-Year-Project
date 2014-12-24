package financemanager.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

public interface SQL_Create_Tables_Interface {
    public void create_labour_expenses() throws SQLException;
    public void create_lighting_expenses() throws SQLException;
    public void create_machinery_expenses() throws SQLException;
    public void create_mortgaged_land() throws SQLException;
    public void create_rented_land() throws SQLException;
}
