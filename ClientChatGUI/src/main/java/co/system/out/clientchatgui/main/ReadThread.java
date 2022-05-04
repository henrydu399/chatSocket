package co.system.out.clientchatgui.main;

import co.system.out.clientchatgui.models.*;
import co.system.out.clientchatgui.models.Menssage.StatusCode;
import co.system.out.clientchatgui.utils.GSonUtils;
import co.system.out.clientchatgui.utils.ViewUtil;
import co.system.out.clientchatgui.utils.enums.ViewEnum;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;

import java.util.Arrays;

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
                ex.printStackTrace();
                //break;

            } catch (Exception ex) {
                Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, "ERROR LEYENDO MENSAJE DE SERVER", ex);
                ex.printStackTrace();
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

        switch (msj.getType()) {

            case USERLOGIN:

                System.out.println( "ENTRANDO MENSAJE DESDE EL SERVER | USER LOGIN "
                        + "  CLIENTE ID EMISOR : " + msj.getClientEmisor().getUser().getUserId()
                        + "  CLIENTE ID RECEPTOR : " + msj.getClienteReceptor().getUser().getUserId()
                        + " MENSAJE : " + msj.getMesaje()
                );
                if (msj.getStatusCode().equals(StatusCode.OK)) {
                    this.modelPrincipal.setUser(msj.getClientEmisor().getUser());
                    this.modelPrincipal.switchView(ViewEnum.MAIN, null, true);
                    
                } else {
                    ViewUtil.showBasic(msj.getMesaje(), "LOGIN", ViewUtil.getTypeByServer(msj.getStatusCode()));

                }

                break;

            case GLOBALMENSSAGE:
                //this.mainClient.getMainView().getListMensajes().add( " [ SERVIDOR : ] " + " [ " +msj.getMesaje() + " ]"  );
                //this.mainClient.getMainView().Imprimir();
                break;
            case USERMENSSAGE:
                Logger.getLogger(ReadThread.class.getName()).log(Level.INFO, "ENTRANDO MENSAJE DESDE EL SERVER | USERMENSSAGE "
                        + "  CLIENTE  EMISOR : " + GSonUtils.serialize(msj.getClientEmisor())
                        + "  CLIENTE  RECEPTOR : " + GSonUtils.serialize(msj.getClienteReceptor())
                        + " MENSAJE : " + msj.getMesaje()
                );

//                 boolean existConvesation = false;
//                for (Conversacion c : this.modelPrincipal.getMainEvent().getListConversaciones()) {
//                    if (c.getUser().getUserId() ==  msj.getClientEmisor().getUser().getUserId() ) {
//                        c.addMenssage(new Date().toString(), msj.getMesaje());
//                        existConvesation = true;
//                    }
//                }
                this.modelPrincipal.getMainEvent().addConversacion(msj.getClientEmisor().getUser().getUserId(), msj.getMesaje(), false);

                if( this.modelPrincipal.getMainEvent().getUserSelect() != null &&   this.modelPrincipal.getMainEvent().getUserSelect().getUserId() == msj.getClientEmisor().getUser().getUserId() ){
                     this.modelPrincipal.getMainEvent().imprimirByUser(msj.getClientEmisor().getUser().getUserId());   
                }

                
                
                break;
            case SERVERMENSSAGE:
                System.out.println(" [ " + msj.getClientEmisor().getUser().getEmail() + " ] " + " [ " + msj.getMesaje() + " ]");
                // this.mainClient.getMainView().getListMensajes().add( " [ "+msj.getClientEmisor().getUser().getEmail()+" ] " + " [ " +msj.getMesaje() + " ]"  );
                //this.mainClient.getMainView().Imprimir();
                break;
            case CONTACTOS:
                if (msj.getStatusCode().equals(StatusCode.OK)) {
                    User[] listUserT = gson.fromJson(msj.getMesaje(), User[].class);
                    this.modelPrincipal.getMainEvent().cargarContactos(Arrays.asList(listUserT));

                }
                break;

            default:
                break;
        }

    }
}
