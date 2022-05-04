
package co.system.out.serverchat.business;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.clientchatgui.models.*;




public interface IUserBusiness {
    
    public User login(String user, String password) throws UserExceptions;
    public void create(String username, String password )throws UserExceptions;
    
    
}
