
package co.system.out.serverchat.Sokect;

import co.system.out.serverchat.business.IContactosBusiness;
import co.system.out.serverchat.business.IUserBusiness;
import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.clientchatgui.models.*;
import co.system.out.serverchat.util.GSonUtils;
import co.system.out.serverchat.util.MenssageUtil;
import java.util.List;


public class LogicClient {
    
    private ClientThread clientThread;
    
    
    
        ///Business Logic
    private IUserBusiness userBusiness;
    private IContactosBusiness contactosBusiness;

    public LogicClient(ClientThread clientThread, IUserBusiness userBusiness, IContactosBusiness contactosBusiness) {
        this.clientThread = clientThread;
        this.userBusiness = userBusiness;
        this.contactosBusiness = contactosBusiness;
    }
    
  
    
      /*
    Metodo para login
     */
    public void login(Menssage msj) {

        User user = null;
        Menssage m = null;
        
        try {

            if (msj.getClientEmisor() != null && msj.getClientEmisor().getUser().getEmail() != null && msj.getClientEmisor().getUser().getPassword() != null) {

                user = this.userBusiness.login(msj.getClientEmisor().getUser().getEmail(), msj.getClientEmisor().getUser().getPassword());

                    this.clientThread.getCliente().setUser(user);
                    
                     m = MenssageUtil.create(
                             this.clientThread.getCliente(), // CLIENTE EMISOR 
                             this.clientThread.getCliente(),  // CLIENTE RECEPTOR 
                             "LOGIN EXITOSO", Menssage.typeMessages.USERLOGIN);
                     
                    m.setStatusCode(Menssage.StatusCode.OK);
                    this.clientThread.enviarMensajeCliente(m);
                    
                    //Cargando contactos 
                    List<User> contactos = this.cargarContactosUser(user.getUserId());
                    m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), GSonUtils.serialize(contactos), Menssage.typeMessages.CONTACTOS);
                    m.setStatusCode(Menssage.StatusCode.OK);
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
    
    
    private  List<User> cargarContactosUser(long idUser){
         Menssage m = null;
         List<User> listsUser = null;
        try{
            listsUser = this.contactosBusiness.getContactosByUser(idUser);
        }catch( UserExceptions e){
             m = MenssageUtil.create(this.clientThread.getAppServer().getServerClient(), this.clientThread.getCliente(), e.getErrorFinal().value , Menssage.typeMessages.USERLOGIN);
             m.setStatusCode(Menssage.StatusCode.ERROR_LOGICA);
            this.clientThread.enviarMensajeCliente(m);
        }
        return listsUser;
        
    }
        
    
    
    
}
