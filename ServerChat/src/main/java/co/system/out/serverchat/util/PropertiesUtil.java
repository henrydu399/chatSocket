package co.system.out.serverchat.util;

import co.system.out.serverchat.Sokect.ClientThread;
import co.system.out.serverchat.business.enums.PropertiesEnum;
import co.system.out.serverchat.exceptions.ConfigExeptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PropertiesUtil {
    
    private static final String path = "D:\\HOME SYSTEM\\chatSocket\\serverChat\\configuracion.properties";


    public static Properties get() throws ConfigExeptions {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            System.out.println(properties.get( PropertiesEnum.driverNameDb.name()));
     
        } catch (FileNotFoundException e) {
             Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE,"Connection get()",e  );      
            throw  new ConfigExeptions(ConfigExeptions.ConfigExeptionsMensajes.ERROR_CONECTANDO_BASE_DE_DATOS,"0003",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }

}
