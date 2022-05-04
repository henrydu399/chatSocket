package co.system.out.clientchatgui.utils;

import co.system.out.clientchatgui.utils.exceptions.ConfigExeptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesUtil {

    private static final String path = "C:\\HOME SYSTEM\\chatSocket\\ClientChatGUI\\configuracion.properties";

    public static Properties get() throws ConfigExeptions {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, "SE CARGARON LAS CONFIGURACIONES CORRECTAMENTE");

        } catch (FileNotFoundException e) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, "Connection get()", e);
            throw new ConfigExeptions(ConfigExeptions.ConfigExeptionsMensajes.ERROR_CONECTANDO_BASE_DE_DATOS, "0003", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }

}
