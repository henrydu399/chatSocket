
package co.system.out.serverchat.business;

import co.system.out.clientchatgui.models.*;

import co.system.out.serverchat.dao.ContactosDao;
import co.system.out.serverchat.dao.UserDAO;
import co.system.out.serverchat.exceptions.UserExceptions;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ContactosBusinessImpl  implements  IContactosBusiness{

    
      private ContactosDao dao;

    public ContactosBusinessImpl(Connection con) {
        this.dao = new ContactosDao(con);

    }
    
    @Override
    public List<User> getContactosByUser(Long idUser) throws UserExceptions {
         List<User> listUsers =  dao.getContactosByUser(idUser);
         return listUsers;
    }
    
 
    

    @Override
    public void addContacto(long idContacto, String email, String userName) throws UserExceptions {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
}
