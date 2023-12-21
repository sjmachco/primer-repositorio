
package conexion_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TIVE
 */
public class Conexion {
    private final String url = "jdbc:mysql://localhost:3306/bd_comercial";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String user = "root";
    private final String pass = "admin";
    
    public Connection conexion(){
        try {
            Connection cn;
            Class.forName(driver);
            cn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa!");
            return cn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());           
            return null;
        } catch(ClassNotFoundException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
