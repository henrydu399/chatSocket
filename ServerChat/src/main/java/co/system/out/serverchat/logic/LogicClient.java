
package co.system.out.serverchat.logic;

import co.system.out.serverchat.business.IContactosBusiness;
import co.system.out.serverchat.business.IUserBusiness;
import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.Sokect.ClientThread;
import co.system.out.serverchat.util.GSonUtils;
import co.system.out.serverchat.util.MenssageUtil;
import com.gosystem.commons.exceptions.BasicExeption;
import java.util.List;
import java.util.Objects;


public class LogicClient {
    
    private ClientThread clientThread;
    
    
    
 

    public LogicClient(ClientThread clientThread) {
        this.clientThread = clientThread;

    }
    
  
    
      /*
    Metodo para login
     */
    public void login(Menssage msj)  throws UserExceptions{

        User user = null;
        Menssage m = null;
        
        try {

            if (msj.getClientEmisor() != null && msj.getClientEmisor().getUser().getEmail() != null && msj.getClientEmisor().getUser().getPassword() != null) {

                user = this.clientThread.getUserBusiness().login(msj.getClientEmisor().getUser().getEmail(), msj.getClientEmisor().getUser().getPassword());
                
                if( this.clientThread.getAppServer().isOnlineUser( user.getUserId())){
                    throw new UserExceptions(UserExceptions.UserExceptionsMensajes.EL_USUARIO_YA_ESTA_ONLINE, "0002", null);
                }
                
                    this.clientThread.getCliente().setUser(user);
                    m = this.createMessajeLogin();
                   
                    this.clientThread.enviarMensajeCliente(m);
                    
                    //Cargando contactos 
                    this.enviarContactosActuales(user);
                    
                    //Cargando solicitudes
                    this.enviarSolicitudesActuales();

                     

            }else{
             m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), "ERROR CON LOS PARAMETROS", Menssage.typeMessages.USERLOGIN);
             m.setStatusCode(Menssage.StatusCode.ERRORPARAMETROS);
            this.clientThread.enviarMensajeCliente(m);
            }
        } catch (UserExceptions e) { 
             m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), e.getErrorFinal().value , Menssage.typeMessages.USERLOGIN);
             m.setStatusCode(Menssage.StatusCode.ERRORPARAMETROS);
            this.clientThread.enviarMensajeCliente(m);
        } catch (Exception e) {       
             m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), "ERROR CON EL SERVIDOR", Menssage.typeMessages.USERLOGIN);
            m.setStatusCode(Menssage.StatusCode.INTERNAL_ERROR);
            this.clientThread.enviarMensajeCliente(m);
        }

    }
    
    
    public  Menssage createMessajeLogin(){
            Menssage  m = MenssageUtil.create(
                             this.clientThread.getCliente(), // CLIENTE EMISOR 
                             this.clientThread.getCliente(),  // CLIENTE RECEPTOR 
                             "LOGIN EXITOSO", Menssage.typeMessages.USERLOGIN);
             m.setStatusCode(Menssage.StatusCode.OK);
              return m;
    }
    
    public void enviarContactosActuales( User user){
        List<User> contactos = this.cargarContactosUser(user.getUserId());
        Menssage m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), GSonUtils.serialize(contactos), Menssage.typeMessages.VER_CONTACTOS);
        m.setStatusCode(Menssage.StatusCode.OK);
        this.clientThread.enviarMensajeCliente(m);
    }
    
    public void enviarSolicitudesActuales() throws BasicExeption{
        List<Solicitud> listSolicitudes = this.clientThread.getSocilitudesBusiness().getAllByUserId(this.clientThread.getCliente().getUser().getUserId());
        Menssage m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), GSonUtils.serialize(listSolicitudes), Menssage.typeMessages.VER_SOLICITUDES);
        m.setStatusCode(Menssage.StatusCode.OK);
        this.clientThread.enviarMensajeCliente(m); 
    }
    
    
    private  List<User> cargarContactosUser(long idUser){
         Menssage m = null;
         List<User> listsUser = null;
        try{
            listsUser = this.clientThread.getContactosBusiness().getContactosByUser(idUser);
        }catch( UserExceptions e){
             m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), e.getErrorFinal().value , Menssage.typeMessages.USERLOGIN);
             m.setStatusCode(Menssage.StatusCode.ERROR_LOGICA);
            this.clientThread.enviarMensajeCliente(m);
        }
        return listsUser;
        
    }
        
    
    
    
}
