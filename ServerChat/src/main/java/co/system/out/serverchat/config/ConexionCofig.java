
package co.system.out.serverchat.config;

import co.system.out.serverchat.Sokect.ClientThread;
import co.system.out.serverchat.business.enums.PropertiesEnum;
import co.system.out.serverchat.exceptions.ConfigExeptions;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionCofig {
    
    private static String host;
    private static String port;
    private static String databaseName;
    private static String user;
    private static String password;
    private static String driverNameDb;

    
    public ConexionCofig(Properties p) {
        driverNameDb = p.getProperty(PropertiesEnum.driverNameDb.name());
        host = p.getProperty(PropertiesEnum.hostnameDb.name());
        port = p.getProperty(PropertiesEnum.portDb.name());
        databaseName = p.getProperty(PropertiesEnum.databaseName.name());
        user = p.getProperty(PropertiesEnum.userDb.name());
        password = p.getProperty(PropertiesEnum.PasswordDb.name());
    }
    
   
    
    public static Connection get() throws ConfigExeptions{
        Connection conn = null;
            try {
                //jdbc:mariadb://{host}[:{port}]/[{database}]
            	//ORACLE
                //conn = DriverManager.getConnection(   driverNameDb+ host +":"+port+":"+databaseName, user,password );
                //MARIADB 
                conn = DriverManager.getConnection(driverNameDb+ host +":"+port+"/"+databaseName, user,password );

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE,"Connection get()",e  );
            
            throw  new ConfigExeptions(ConfigExeptions.ConfigExeptionsMensajes.ERROR_CONECTANDO_BASE_DE_DATOS,"0003",e.getMessage());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
}
