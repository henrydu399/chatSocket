package co.system.out.clientchatgui.main;


import co.system.out.chatsocket.general.models.Menssage;
import co.system.out.chatsocket.general.models.Menssage.StatusCode;
import static co.system.out.chatsocket.general.models.Menssage.typeMessages.VER_CONTACTOS;
import co.system.out.chatsocket.general.models.Solicitud;
import co.system.out.chatsocket.general.models.User;
import co.system.out.clientchatgui.utils.GSonUtils;
import co.system.out.clientchatgui.utils.ViewUtil;
import co.system.out.clientchatgui.utils.enums.ViewEnum;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;

import java.util.Arrays;

import java.util.Objects;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ReadThread extends Thread {

    private BufferedReader reader;
    private Socket socket;

    private ModelPrincipal modelPrincipal;

    private String msgin;

    DataInputStream dtinpt;

    public ReadThread(Socket socket, ModelPrincipal _modelPrincipal) {
        this.socket = socket;
        this.modelPrincipal = _modelPrincipal;

        try {
            this.dtinpt = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, "ERROR CREANDO  LECTURA DEL SERVER ", ex);
            ex.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                msgin = this.dtinpt.readUTF();

                if (msgin != null && !msgin.equals("")) {
                    Gson gson = new Gson();
                    Menssage msj = gson.fromJson(msgin, Menssage.class);

                    if (msj != null) {

                        ComportamientoPorType(msj);
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, "ERROR LEYENDO MENSAJE DE SERVER", ex);       
                 ViewUtil.showBasic("Error del servidor  ","Mensaje Error" , ViewUtil.getTypeByServer(StatusCode.INTERNAL_ERROR) );
                ex.printStackTrace();
                //break;

            } catch (Exception e) {
                Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, "ERROR LEYENDO MENSAJE DE SERVER", e);
                ViewUtil.showBasic("Error del servidor 2 ","Mensaje Error" , ViewUtil.getTypeByServer(StatusCode.INTERNAL_ERROR) );
                e.printStackTrace();
                //break;
            }
        }
    }

    /*
     * Metodo que controla el mensaje recibido dependiento del tipo de mensaje 
     */
    private void ComportamientoPorType(Menssage msj) {
        // prints the username after displaying the server's message

        Gson gson = new Gson();
        String json = gson.toJson(msj);
        
        
            try{
                   System.out.println( "ENTRANDO MENSAJE DESDE EL SERVER | TIPO : " + msj.getType()
                        + "  CLIENTE ID EMISOR : " + msj.getClientEmisor().getUser().getUserId()==null?"":msj.getClientEmisor().getUser().getUserId()
                        + "  CLIENTE ID RECEPTOR : " + msj.getClienteReceptor().getUser().getUserId() == null ? "" : msj.getClienteReceptor().getUser().getUserId() 
                        + " MENSAJE : " + msj.getMesaje()
                );
                }catch(Exception e){
                    
                }

        switch (msj.getType()) {

            case USERLOGIN:

                if (msj.getStatusCode().equals(StatusCode.OK)) {
                    this.modelPrincipal.setUser(msj.getClientEmisor().getUser());
                    this.modelPrincipal.switchView(ViewEnum.MAIN, null, true);
                    
                } else {
                    ViewUtil.showBasic(msj.getMesaje(), "LOGIN", ViewUtil.getTypeByServer(msj.getStatusCode()));

                }

                break;

            case GLOBALMENSSAGE:
                 ViewUtil.showBasic(msj.getMesaje(), "MENSAJE SERVIDOR ", ViewUtil.getTypeByServer(msj.getStatusCode()));
                break;
            case USERMENSSAGE:

                this.modelPrincipal.getMainEvent().addConversacion(msj.getClientEmisor().getUser().getUserId(), msj.getMesaje(), false);

                if( this.modelPrincipal.getMainEvent().getUserSelect() != null &&   this.modelPrincipal.getMainEvent().getUserSelect().getUserId() == msj.getClientEmisor().getUser().getUserId() ){
                     this.modelPrincipal.getMainEvent().imprimirByUser(msj.getClientEmisor().getUser().getUserId());   
                }

                
                
                break;
            case SERVERMENSSAGE:
                System.out.println(" [ " + msj.getClientEmisor().getUser().getEmail() + " ] " + " [ " + msj.getMesaje() + " ]");
                break;
            case VER_CONTACTOS:
                if (msj.getStatusCode().equals(StatusCode.OK)) {
                    User[] listUserT = gson.fromJson(msj.getMesaje(), User[].class);
                     if( Objects.nonNull(listUserT))
                    this.modelPrincipal.getMainEvent().cargarContactos(Arrays.asList(listUserT));

                }
                break;
                
            case VER_SOLICITUDES:
                if (msj.getStatusCode().equals(StatusCode.OK)) {
                    Solicitud[] list = gson.fromJson(msj.getMesaje(), Solicitud[].class);
                    if( Objects.nonNull(list))
                    this.modelPrincipal.getMainEvent().getSolicitudesComponent().cargarSolicitudes(Arrays.asList(list));
                }
                break;

            default:
                break;
        }

    }
}
