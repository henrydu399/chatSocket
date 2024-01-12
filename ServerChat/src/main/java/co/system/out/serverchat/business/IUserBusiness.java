
package co.system.out.serverchat.business;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.entitys.Users;




public interface IUserBusiness {
    
     User login(String user, String password) throws UserExceptions;
     void create(String username, String password )throws UserExceptions;
     Users findByEmail(String email)throws UserExceptions;
     Users findById(Long id) throws UserExceptions;
    
    
}
