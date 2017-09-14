package HotelProject;

import java.sql.*;
import javax.swing.*;
public class SQLITE {
    Connection conn=null;
    public static Connection dbconnector()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:F:\\SagarProject\\src\\HotelProject\\HotelManagementSystem.sqlite");

            return conn;
        }catch(Exception e)
        {
            JOptionPane.showConfirmDialog(null, "not connected");
            return null;
        }
    }
    public static void main(String [] args )
    {
        Connection conn=dbconnector();
    }

}
