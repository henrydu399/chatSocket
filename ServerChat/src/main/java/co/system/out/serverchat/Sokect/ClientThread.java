package co.system.out.serverchat.Sokect;

import co.system.out.serverchat.business.IContactosBusiness;
import co.system.out.serverchat.business.IUserBusiness;
import co.system.out.serverchat.exceptions.UserExceptions;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;


import co.system.out.clientchatgui.models.*;


import co.system.out.serverchat.util.MenssageUtil;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread {

    private Socket socket;
    private AppServer appServer;

    private Client cliente;

    DataInputStream dtinpt;
    DataOutputStream dtotpt;

    BufferedReader reader;

    ///Business Logic
    private IUserBusiness userBusiness;
    private IContactosBusiness contactosBusiness;

    //logic
    LogicClient logic;

    public ClientThread(Socket socket, AppServer _appServer, String ip, IUserBusiness _userBusiness, IContactosBusiness _contactosBusiness) {
        this.socket = socket;
        this.appServer = _appServer;
        this.userBusiness = _userBusiness;
        this.contactosBusiness = _contactosBusiness;

        logic = new LogicClient(this, this.userBusiness, this.contactosBusiness);

        cliente = new Client(ip, null, new Date());

        try {
            this.dtinpt = new DataInputStream(socket.getInputStream());
            //this.dtotpt = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void run() {
        try {

            String msgin = null;

            while (true) {

                if (!socket.isClosed()) {

                    //boolean valueExists = this.dtinpt.readBoolean();
                    try {
                        msgin = this.dtinpt.readUTF();
                        //reader = new BufferedReader(new InputStreamReader(dtinpt));				
                        if (msgin != null && !msgin.equals("")) {
                            //INTENTAMOS OBTENER EL MENSAJE COMO UN TYPO MENSSAGE 

                            Gson gson = new Gson();
                            Menssage msj = gson.fromJson(msgin, Menssage.class);
                            if (msj != null) {
                                executeComportamiento(msj);

                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        closeConexion();
                        break;
                    }

                } else {
                    closeConexion();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            closeConexion();
        } catch (Exception ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
            closeConexion();

        }
    }

    public void closeConexion() {
        try {
            if (this.dtinpt != null) {
                this.dtinpt.close();
            }
        } catch (IOException ex1) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex1);
        }
        try {
            if (this.dtotpt != null) {
                this.dtotpt.close();
            }
        } catch (IOException ex1) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex1);
        }
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException ex1) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex1);
        }

    }

    /*
	 * Metodo que ejecuta el comportamiento segun el tipo de mensaje enviado por el cliente 
     */
    public void executeComportamiento(Menssage msj) {
        String out = null;

        Gson gson = new Gson();
        String json = gson.toJson(msj);

        try {
            switch (msj.getType()) {
                case REGISTER:
                    Logger.getLogger(ClientThread.class.getName()).log(Level.INFO, "REGISTER :" + json);
                    break;
                case USERLOGIN:
                    Logger.getLogger(ClientThread.class.getName()).log(Level.INFO, "USERLOGIN :" + json);
                    this.logic.login(msj);
                    break;
                case USERMENSSAGE:  // MENSAJE ENVIADO A OTRO OSUARIO 
                    Logger.getLogger(ClientThread.class.getName()).log(Level.INFO, "USERMENSSAGE :" + json);
                    out = this.appServer.enviarMensaje(msj);
                    break;

                default:
                    Logger.getLogger(ClientThread.class.getName()).log(Level.INFO, "default :" + json);
                    break;
            }

        } catch (Exception e) {

        }

    }

    /*
	 * Metodo que devuelve el mensaje o respuesta  al cliente de un evento
     */
    public void enviarMensajeCliente(Menssage msj) {

        try {
            if (this.socket != null) {
                dtotpt = new DataOutputStream(this.socket.getOutputStream());
                dtotpt.writeUTF(new Gson().toJson(msj));
                //dtotpt.flush();

            } else {

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public AppServer getAppServer() {
        return appServer;
    }

}
