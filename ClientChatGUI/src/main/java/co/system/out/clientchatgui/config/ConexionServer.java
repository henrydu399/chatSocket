package co.system.out.clientchatgui.config;


import co.system.out.clientchatgui.main.ModelPrincipal;
import co.system.out.clientchatgui.main.ReadThread;
import co.system.out.clientchatgui.main.WriteThread;
import co.system.out.clientchatgui.utils.UtilServerChat;
import co.system.out.clientchatgui.utils.enums.PropertiesEnum;
import co.system.out.clientchatgui.utils.exceptions.ClientExceptions;
import co.system.out.clientchatgui.utils.exceptions.ClientExceptions.ClientExceptionsMensajes;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionServer {


    private Properties properties;
    private ModelPrincipal modelPrincipal;
    

    private Socket sckt;

    private ReadThread readThread;
    private WriteThread writeThread;
    
    

    public ConexionServer(ModelPrincipal _modelPrincipal,  Properties _properties) {
        this.modelPrincipal = _modelPrincipal;
        this.properties = _properties;
    }

    public void Conectar() throws  ClientExceptions{
        try {
            // Optenemos la ip del cliente 
            this.sckt = new Socket(
                    this.properties.getProperty(PropertiesEnum.serverIp.name()),
                    Integer.parseInt(this.properties.getProperty(PropertiesEnum.serverPuerto.name()))
            );

            if (sckt != null) {
                readThread = new ReadThread(sckt, this.modelPrincipal);
                readThread.start();

       
                writeThread = new WriteThread(sckt, this.modelPrincipal);
                writeThread.start();

            } else {
                throw  new ClientExceptions(ClientExceptionsMensajes.ERROR_CONECTANDO_SERVER, "0001", null);
            }

        } catch (IOException ex) {
            Logger.getLogger(ConexionServer.class.getName()).log(Level.SEVERE, "ERROR CONECTANDO CON EL SERVER");
            Logger.getLogger(ConexionServer.class.getName()).log(Level.SEVERE, null, ex);
            
            throw  new ClientExceptions(ClientExceptionsMensajes.ERROR_CONECTANDO_SERVER, "0001", null);

        }

    }

    public ReadThread getReadThread() {
        return readThread;
    }

    public WriteThread getWriteThread() {
        return writeThread;
    }
    
    
    
    
    

}
