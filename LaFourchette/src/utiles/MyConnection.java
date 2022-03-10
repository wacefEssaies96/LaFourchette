package utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author user
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/lafourchette";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance; 
   private MyConnection(){
        try {
           cnx= DriverManager.getConnection(url, login, pwd);
           System.out.println("Connexion etablie!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   }
   
   public Connection getCnx()
   {
       return cnx;
   }
   
   public static MyConnection getInstance(){
       if (instance == null)
       { 
           instance = new MyConnection(); 
       }
       return instance; 
   }
}

