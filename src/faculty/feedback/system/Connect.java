/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faculty.feedback.system;

/**
 *
 * @author rudolf
 */
import java.awt.Desktop;
import java.net.URI;
import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Connect {
    Connection conn =null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://mysql5.gear.host/collegedatabase";
   //static final String DB_URL = "jdbc:mysql://192.168.0.102:8889/College_Database_v0_1?zeroDateTimeBehavior=convertToNull";
    static final double vers= 1.03;
   static final String USER = "collegedatabase";
   static final String PASS = "Rs6pI5-NHXi!";   
   static int c=0;
    public static Connection ConnectDb()
    {
      
       try{
           Class.forName(JDBC_DRIVER);

            System.out.println("Please hold while we connect to the data base");
            System.out.println();
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            if(Updates()){
            if(c==0 )
            {
                
            JOptionPane.showMessageDialog(null, "Database Connection is Successful");
            }
            c++;
            }
            
            return conn;
            
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
           return null;
       }
       
       
    }
    public static boolean Updates()
    {
        Double v;
     try {
         Class.forName(JDBC_DRIVER);

            System.out.println("Please hold while we connect to the data base");
            System.out.println();
            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); 
            Statement stmt = conn.createStatement();
             String sql = "Select * FROM `Extras` WHERE `UP` = '1';";
             ResultSet rs = stmt.executeQuery(sql); 
             if(rs.absolute(1))
             {
                 v=rs.getDouble(1);
                 if(v==vers)
                 {
                   return true; 
                 }
                 else
                 {
                     JOptionPane.showMessageDialog(null, "Please update to the latest version of the application");
                     String u= rs.getString(3);
                     try {
                            Desktop desktop = java.awt.Desktop.getDesktop();
                            URI oURL = new URI(u);
                             desktop.browse(oURL);
                            } catch (Exception e) { 
                                     e.printStackTrace();
                               }
                     System.exit(1);
                 }
                 
             }
             else
             {
                  JOptionPane.showMessageDialog(null, "Servers are under maintainence right now. Sorry for the inconvenience");
                  System.exit(1);
             }
           
            
           
            
        
        }
     catch(Exception e)
     {
          e.printStackTrace();
     }
        
        return false;
    }
    
    
}
