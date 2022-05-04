package co.system.out.serverchat.dao;

import co.system.out.serverchat.exceptions.UserExceptions;
import co.system.out.serverchat.exceptions.UserExceptions.UserExceptionsMensajes;
import co.system.out.clientchatgui.models.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public User getUser(String userName, String password) throws UserExceptions {

        User user = null;

        String SQL = " SELECT u.ID ,  u.EMAIL  , u.USERNAME  ,u.ROL  , u.STATE , p.NOMBRE , p.APELLIDOS , p.NUMERO_IDENTIFICACION , p.TIPO_IDENTIFICACION  "
                + "  FROM CHATSOCKET.USERS u INNER JOIN CHATSOCKET.PERSONAS p ON ( u.NUMERO_IDENTIFICACION = p.NUMERO_IDENTIFICACION AND u.TIPO_IDENTIFICACION = p.TIPO_IDENTIFICACION) "
                + "  WHERE u.USERNAME  = ? AND u.PASSWORD = ?  ";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            if (this.con != null) {

                pstmt = this.con.prepareStatement(SQL);
                pstmt.setString(1, userName);
                pstmt.setString(2, password);

                rs = pstmt.executeQuery();
                // check the affected rows 
                while (rs.next()) {
                    //(String email, long userId,  String userName ,String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) 
                    user = getUser(rs);

                }

            }
        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", ex.getMessage());
        } catch (Exception e) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", e.getMessage());
            //this.closeAll(conn, pstmt, null);
        }
        return user;
    }

    public User FindUserGeneral(HashMap<String, String> parameters) throws UserExceptions {

        User user = null;

        String SQL = " SELECT u.ID ,  u.EMAIL  , u.USERNAME  ,u.ROL  , u.STATE , p.NOMBRE , p.APELLIDOS , p.NUMERO_IDENTIFICACION , p.TIPO_IDENTIFICACION  "
                + "  FROM CHATSOCKET.USERS u INNER JOIN CHATSOCKET.PERSONAS p ON ( u.NUMERO_IDENTIFICACION = p.NUMERO_IDENTIFICACION AND u.TIPO_IDENTIFICACION = p.TIPO_IDENTIFICACION) "
                + "  WHERE 1 = 1 ";

        for (String i : parameters.keySet()) {
            SQL = SQL + " " + i + " = ?";
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            if (this.con != null) {

                pstmt = this.con.prepareStatement(SQL);

                int cont = 1;
                for (String i : parameters.values()) {
                    pstmt.setString(1, i);
                    cont++;
                }

                rs = pstmt.executeQuery();
                // check the affected rows 
                while (rs.next()) {
                    user = getUser(rs);
                }

            }
        } catch (SQLException ex) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", ex.getMessage());
        } catch (Exception e) {

            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", e.getMessage());
            //this.closeAll(conn, pstmt, null);
        }
        return user;
    }

    private User getUser(ResultSet rs) throws UserExceptions {
        User u = null;
        if (rs != null) {
            try {
                u = new User(
                        rs.getString("EMAIL"),
                        rs.getLong("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("ROL"),
                        rs.getString("STATE"),
                        rs.getString("TIPO_IDENTIFICACION"),
                        rs.getString("NOMBRE"),
                        rs.getString("APELLIDOS"),
                        rs.getString("NUMERO_IDENTIFICACION")
                );
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                throw new UserExceptions(UserExceptionsMensajes.ERROR_CONSULTADO_USUARIO, "0001", ex.getMessage());
            }
        }
        return u;
    }
}
