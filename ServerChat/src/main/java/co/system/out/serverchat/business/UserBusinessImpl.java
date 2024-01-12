package co.system.out.serverchat.business;

import co.system.out.serverchat.dao.UserDAO;
import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.chatsocket.general.models.*;
import co.system.out.serverchat.entitys.Users;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class UserBusinessImpl implements IUserBusiness {

    private UserDAO dao;

    public UserBusinessImpl(EntityManager _em) {
        this.dao = new UserDAO(_em);

    }

    @Override
    public User login(String userName, String password) throws UserExceptions {
        try {

            User user = dao.getUser(userName, password);
            if (user != null) {
                return user;
            } else {
                throw new UserExceptions(UserExceptions.UserExceptionsMensajes.NO_EXISTE_EL_USUARIO, "0002", null);
            }
        }catch( UserExceptions ex){
                throw ex;
        } catch ( Exception e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
                throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0002", null);
        }

    }
    
    
       public User FindUserGeneral(HashMap<String,String> parameters ){
           User u ;
        try {
          u = dao.FindUserGeneral(parameters);   
        }catch( UserExceptions ex){
                throw ex;
        } catch ( Exception e) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
                throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_BUSCANDO_USUARIO, "0002", null);
        }
        return u;
    }

    @Override
    public void create(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Users findByEmail(String email) throws UserExceptions {
      return this.dao.findByEmail(email);
        
    }
    
     @Override
    public Users findById(Long id) throws UserExceptions {
        Users u =  new Users();
        u.setId(id);
      return this.dao.find(u);
        
    }

}
