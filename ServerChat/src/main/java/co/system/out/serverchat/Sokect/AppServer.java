/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.Sokect;

import co.system.out.serverchat.models.Client;
import co.system.out.serverchat.models.Menssage;
import co.system.out.serverchat.models.Menssage.typeMessages;
import co.system.out.serverchat.models.User;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author henry
 */
public class AppServer extends Thread {

    private final int puerto = 3000;
    private final String ip = "192.168.56.1";

    ServerSocket ssckt;

    List<ClientThread> listClientThread;

    /*
	 * Metodo que incia el servidor en el puerto parametrizado
     */
    public void run() {

        try {
            ssckt = new ServerSocket(puerto);
            if (ssckt != null) {
                execute();
            } else {
                System.out.println("ERROR:  NO SE PUDO INCIIAR EL SERVER ");
            }

        } catch (IOException ex) {
            System.out.println("ERROR: INICIANDO SERVER " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public void execute() {

        try {
            this.listClientThread = new ArrayList<ClientThread>();
            System.out.println("      ESCUCHANDO CLIENTES .... ");

            try {
                while (true) {
                    try {
                        Socket sckt = ssckt.accept();
                        if (sckt != null) {

                            ClientThread clientRhread = new ClientThread(sckt, this);
                            this.listClientThread.add(clientRhread);
                            // clientRhread.PreRun();
                            clientRhread.start();
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }

            } catch (Exception e) {
                System.out.println("Error in the server: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
	 * Metodo que RECIBE LOS MENSAJE DE LOS USUARIOS Y LOS REDIJIRE CORRECTAMENTE AL
	 * USUARIO RECEPTOR
	 * 
	 * @IN Menssage
     */
    public String enviarMensajeUserxUser(Menssage msj) {
        String out = null;
        // BUSCAMOS EL USUARIO RECEPTOR SI EXISTE
        if (msj.getClienteReceptor() != null && msj.getClienteReceptor().getUser() != null) {
            ClientThread temp = buscarClientexUsuario(msj.getClienteReceptor().getUser());
            if (temp != null) {
                temp.enviarMensajeCliente(msj);
            } else {
                out = "NO SE ENCONTRO CLIENTE  RECEPTOR   O USUARIO RECEPTOR :  " + msj.getClienteReceptor().getUser().getEmail();
                this.enviarRespuestaCliente(out, msj.getClientEmisor());
            }

        } else {
            out = " NO SE ENVIO EL CLIENTE  RECEPTOR O USUARIO RECEPTOR : " + msj.getClienteReceptor().getUser().getEmail();
            this.enviarRespuestaCliente(out, msj.getClientEmisor());
        }
        return out;
    }

    /*
	 * Metodo que busca en todos los clientes conectados el usuario receptor
     */
    private ClientThread buscarClientexUsuario(User user) {
        for (ClientThread temp : this.listClientThread) {
            if (temp.getSocket().isClosed()) {
                this.listClientThread.remove(temp);
            } else {
                if (temp.getCliente().getUser().getEmail().equals(user.getEmail())) {
                    return temp;
                }
            }

        }
        return null;
    }

    private void enviarRespuestaCliente(String msjRespuestaServer, Client clienteReceptor) {
        // Server response
        Client serverClient = new Client("0.0.0.0",
                new User("server@gmail.com", null, "SERVER NAME", "SERVER LAST NAME", 0, "0"), null);
        // Msj create
        Menssage msj = new Menssage(serverClient, clienteReceptor, msjRespuestaServer, null);

        msj.setType(typeMessages.SERVERMENSSAGE);

        ClientThread temp = buscarClientexUsuario(clienteReceptor.getUser());

        if (temp != null) {
            temp.enviarMensajeCliente(msj);
        }

    }

    public List<ClientThread> getListClientThread() {
        if (listClientThread == null) {
            listClientThread = new ArrayList<ClientThread>();
        }
        return listClientThread;
    }

    public void setListClientThread(List<ClientThread> listClientThread) {
        this.listClientThread = listClientThread;
    }

}
