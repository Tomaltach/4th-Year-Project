package financemanager.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.sql.*;

public class Connect_To_DB extends SQL_Handler {
    private static String dbURL = "jdbc:derby://localhost:1527/Farm Finance Manager;create=true;user=admin;password=hello123";
    private static Connection conn = null;
    private static Statement stmt = null;

    private void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);
        }
        catch(Exception except) {
            except.printStackTrace();
        }
    }
    private void shutdown() {
        try {
            if(stmt != null) {
                stmt.close();
            }
            if(conn != null) {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }
        }
        catch(SQLException sqlExcept) {}
    }
}