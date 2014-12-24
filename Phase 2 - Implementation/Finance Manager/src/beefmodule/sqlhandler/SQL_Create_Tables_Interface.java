package beefmodule.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

public interface SQL_Create_Tables_Interface {
    public void create_beef_info_list() throws SQLException;
    public void create_feed_expenses() throws SQLException;
    public void create_herd_cow_linker() throws SQLException;
    public void create_herd_info_list() throws SQLException;
    public void create_medical() throws SQLException;
    public void create_vet_visits() throws SQLException;
}