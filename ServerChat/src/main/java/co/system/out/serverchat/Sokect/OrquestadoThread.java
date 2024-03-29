package co.system.out.serverchat.Sokect;

import co.system.out.serverchat.business.ContactosBusinessImpl;
import co.system.out.serverchat.business.IContactosBusiness;
import co.system.out.serverchat.business.ISocilitudesBusiness;
import co.system.out.serverchat.business.IUserBusiness;
import co.system.out.serverchat.business.SolicitudesBusinessImpl;
import co.system.out.serverchat.business.UserBusinessImpl;
import co.system.out.serverchat.config.ConexionCofig;
import co.system.out.serverchat.exceptions.ConfigExeptions;

import co.system.out.serverchat.util.PropertiesUtil;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



public class OrquestadoThread implements Runnable {

    private static AppServer app;
    private static BrocastClientMessage broadcast;
 
    private static Properties properties;

 
     

    private EntityManagerFactory factory;
    private static EntityManager em;

    @Override
    public void run() {

        try {
            /// Crear conexion a base de datos
            properties = PropertiesUtil.get();
          
            
            this.factory = Persistence.createEntityManagerFactory("ServerChat");
            this.em = factory.createEntityManager();


       
                    
            new Thread() {
                public void run() {
                    Run1();
                }
            }.start();

            new Thread() {
                public void run() {
                  //  Run2();
                }
            }.start();

        } catch (ConfigExeptions e) {
               Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE,"ERROR CARGANDO CONFIGURACION DEL SERVER",e  );
               Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE,"FIN SERVIDOR" );
        }

    }

    public static void Run1() {
        // init 
        app = new AppServer(em);
        app.start();

    }

    public static void Run2() {
        broadcast = new BrocastClientMessage(app);
        broadcast.start();
    }

}
