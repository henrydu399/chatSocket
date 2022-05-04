
package co.system.out.serverchat.business;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.clientchatgui.models.*;
import java.util.List;


public interface IContactosBusiness {
    
    
    List<User> getContactosByUser(Long idUser) throws UserExceptions;
    
    void addContacto( long idContacto, String email, String userName)throws UserExceptions;
    
    
}
