package co.system.out.serverchat.dao;

import co.system.out.serverchat.business.ContactosBusinessImpl;
import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.clientchatgui.models.*;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactosDao {

    private Connection con;

    public ContactosDao(Connection con) {
        this.con = con;
    }

    public List<User> getContactosByUser(long idUser) {

        List<User> out = null;
        User[] array = null;

        User user = null;

        String SQL = "  SELECT c.CONTACTOS FROM CHATSOCKET.CONTACTOS c WHERE c.USER_ID = ?  ";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            if (this.con != null) {

                pstmt = this.con.prepareStatement(SQL);
                pstmt.setLong(1, idUser);

                rs = pstmt.executeQuery();
                // check the affected rows 
                while (rs.next()) {     
                    out =  new ArrayList<>();
                    Gson gson = new Gson();
                    array = gson.fromJson(rs.getString("CONTACTOS"), User[].class);
                    out = Arrays.asList(array);

                }
                
                if( out == null){
             Logger.getLogger(ContactosDao.class.getName()).log(Level.INFO, "NO SE ENCONTRARON CONTACTOS PARA EL USUARIO : " +idUser);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.NO_EXISTEN_CONTACTOS_PARA_EL_USUARIO, "0001",null);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", ex.getMessage());
        } catch (Exception e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptions.UserExceptionsMensajes.ERROR_CONSULTANDO_CONTACTOS, "0001", e.getMessage());
            //this.closeAll(conn, pstmt, null);
        }

        return out;

    }
    
    
    public void addContacto(){
        
    } 
    
    

}
