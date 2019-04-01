package da3_java;


import java.sql.*;
import javax.swing.*;
public class Connection_Util {
    Connection conn = null;
    public static Connection connectdb()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/da3","root","");
            return conn;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
