/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.Sokect;



import co.system.out.chatsocket.general.models.Client;
import co.system.out.chatsocket.general.models.Menssage;
import co.system.out.chatsocket.general.models.Menssage.typeMessages;
import co.system.out.chatsocket.general.models.User;
import co.system.out.serverchat.business.ContactosBusinessImpl;
import co.system.out.serverchat.business.IContactosBusiness;
import co.system.out.serverchat.business.ISocilitudesBusiness;
import co.system.out.serverchat.business.IUserBusiness;
import co.system.out.serverchat.business.SolicitudesBusinessImpl;
import co.system.out.serverchat.business.UserBusinessImpl;




import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;



public class AppServer extends Thread {

    private final int puerto = 3000;
    private final String ip = "127.1.0.0";

    ServerSocket ssckt;

    List<ClientThread> listClientThread;
    
    
    private Client serverClient ;
    
    private EntityManager em;
    
     ///Business Logic
    private IUserBusiness userBusiness;
    private IContactosBusiness contactosBusiness;
    private ISocilitudesBusiness socilitudesBusiness;

        
    public AppServer(EntityManager _em) {
            this.em = _em;
            // Instanciando business
            userBusiness = new UserBusinessImpl( this.em);
            contactosBusiness = new ContactosBusinessImpl(this.em , userBusiness);
            socilitudesBusiness = new SolicitudesBusinessImpl(em, userBusiness, contactosBusiness);
    }
        
        

  
    
    
    /*
	 * Metodo que incia el servidor en el puerto parametrizado
     */
    public void run() {
      //String email, long userId, String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion
         serverClient = new Client("0.0.0.0",new User("server@gmail.com", 1l,"ADMIN","ACTIVO", "CC", "SERVER" , "CONECTADOS" , "00000001"), null);

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
            this.listClientThread = new ArrayList<>();
            System.out.println("      ESCUCHANDO CLIENTES .... ");

            try {
                while (true) {
                    try {
                        Socket sckt = ssckt.accept();
                        if (sckt != null) {

                            ClientThread clientRhread = new ClientThread(sckt, this,sckt.getInetAddress().getHostAddress() , this.userBusiness , this.contactosBusiness , this.socilitudesBusiness );
                            this.listClientThread.add(clientRhread);
                            // clientRhread.PreRun();
                            clientRhread.start();
                            System.out.println("    CLIENTE ACEPTADO  .... ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
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
    public String enviarMensaje(Menssage msj) {
        String out = null;
        // BUSCAMOS EL USUARIO RECEPTOR SI EXISTE
        if (msj.getClienteReceptor() != null && msj.getClienteReceptor().getUser() != null) {
            // BUSCAMOS SI ESTA ONLINE
            ClientThread temp = buscarClientexUsuario(msj.getClienteReceptor().getUser());
            if (temp != null) {
                temp.enviarMensajeCliente(msj);
            } else {
                out = "NO SE ENCONTRO USUARIO CONECTADO  :  " + msj.getClienteReceptor().getUser().getEmail();
                this.enviarRespuestaCliente(out, msj.getClientEmisor(),typeMessages.SERVERMENSSAGE);
            }

        } else {
            out = " NO SE ENVIO EL CLIENTE  RECEPTOR O USUARIO RECEPTOR : " + msj.getClienteReceptor().getUser().getEmail();
            this.enviarRespuestaCliente(out, msj.getClientEmisor(),typeMessages.SERVERMENSSAGE);
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
                if (temp.getCliente().getUser().getUserId()==(user.getUserId())) {
                    return temp;
                }
            }

        }
        return null;
    }
    
    /**
     * MEtodo que busca si el usuario ya esta online
     * @param msjRespuestaServer
     * @param clienteReceptor
     * @param typeMensage 
     */
 public boolean isOnlineUser ( Long idUSer){
     for (ClientThread temp : this.listClientThread) {
         
         if(  Objects.nonNull(temp.getCliente().getUser()) &&  temp.getCliente().getUser().getUserId() == idUSer){
             return true;
         }
     }
     return false;
 }
    
    
       public void enviarRespuestaCliente(String msjRespuestaServer, Client clienteReceptor, typeMessages typeMensage) {
           
        System.out.println("enviarRespuestaCliente :  "+ msjRespuestaServer);
        
        Menssage msj = new Menssage(this.serverClient, clienteReceptor, msjRespuestaServer, typeMensage);
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

    public Client getServerClient() {
        return serverClient;
    }

    public EntityManager getEm() {
        return em;
    }
    
    
    
    
    

}
