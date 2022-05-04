package co.system.out.serverchat.business;

import co.system.out.serverchat.dao.UserDAO;
import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.clientchatgui.models.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserBusinessImpl implements IUserBusiness {

    private UserDAO dao;

    public UserBusinessImpl(Connection con) {
        this.dao = new UserDAO(con);

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

}
