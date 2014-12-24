package financemanager.sqlhandler;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.util.*;
import java.sql.*;

import financemanager.datahandler.*;

public interface SQL_Interface {
    // INSERT functions for SQL handling
    public void create_fertilizer_expense(Double f_expense, int f_year, String f_month) throws SQLException;
    public void create_gas_expense(Double g_expense, int g_year, String g_month) throws SQLException;
    public void create_labour_expense(Double l_expense, int l_year, String l_month) throws SQLException;
    public void create_lighting_expense(Double li_expense, int li_year, String li_month) throws SQLException;
    public void create_machinery_expense(Double ma_expense, int ma_year, String ma_month) throws SQLException;
    public void create_mortgaged_land(Double m_expense, int m_year, String m_month) throws SQLException;
    public void create_rented_land(Double r_expense, int r_year, String r_month) throws SQLException;
    public void create_slurry_expense(Double s_expense, int s_year, String s_month) throws SQLException;
    public void create_water_expense(Double w_expense, int w_year, String w_month) throws SQLException;
    // UPDATE functions for SQL handling
    public void edit_fertilizer_expense(Double f_expense, int f_year, String f_month) throws SQLException;
    public void edit_gas_expense(Double g_expense, int g_year, String g_month) throws SQLException;
    public void edit_labour_expense(Double l_expense, int l_year, String l_month) throws SQLException;
    public void edit_lighting_expense(Double li_expense, int li_year, String li_month) throws SQLException;
    public void edit_machinery_expense(Double ma_expense, int ma_year, String ma_month) throws SQLException;
    public void edit_mortgaged_land(Double m_expense, int m_year, String m_month) throws SQLException;
    public void edit_rented_land(Double r_expense, int r_year, String r_month) throws SQLException;
    public void edit_slurry_expense(Double s_expense, int s_year, String s_month) throws SQLException;
    public void edit_water_expense(Double w_expense, int w_year, String w_month) throws SQLException;
    // SELECT functions for SQL handling
    public List<Fertilizer_Data> select_fertilizer_expense() throws SQLException;
    public List<Gas_Data> select_gas_expense() throws SQLException;
    public List<Labour_Data> select_labour_expense() throws SQLException;
    public List<Lighting_Data> select_lighting_expense() throws SQLException;
    public List<Machinery_Data> select_machinery_expense() throws SQLException;
    public List<Mortgaged_Data> select_mortgaged_land() throws SQLException;
    public List<Rented_Data> select_rented_land() throws SQLException;
    public List<Slurry_Data> select_slurry_expense() throws SQLException;
    public List<Water_Data> select_water_expense() throws SQLException;
}