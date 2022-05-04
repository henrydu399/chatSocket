package co.system.out.clientchatgui.main;

import co.system.out.clientchatgui.config.ConexionServer;
import co.system.out.clientchatgui.eventsView.LoginEvent;
import co.system.out.clientchatgui.eventsView.MainEvent;
import co.system.out.clientchatgui.models.Client;
import co.system.out.clientchatgui.models.User;
import co.system.out.clientchatgui.utils.PropertiesUtil;
import co.system.out.clientchatgui.utils.UtilServerChat;
import co.system.out.clientchatgui.utils.enums.ViewEnum;
import co.system.out.clientchatgui.utils.exceptions.ClientExceptions;
import co.system.out.clientchatgui.utils.exceptions.ConfigExeptions;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelPrincipal {

    /*
    Listas
     */
    List<User> listContactos;

    // IP DEL CLIENTE 
    private String ip;
    // CLIENTE
    private Client client;

    // USER LOGGIN 
    private User user;

    //PROPERTIES DEL CLIENTE
    Properties properties;

    // CONEXION AL SERVER
    private ConexionServer server;

    // EVENT VIEW 
    private LoginEvent loginEvent;
    private MainEvent mainEvent;

    // VIEW ACTUAL
    javax.swing.JFrame viewCurrent;

    public void init() {

        try {
            this.ip = UtilServerChat.getIP();

            // CARGAMOS LOS PROPERTIES
            properties = PropertiesUtil.get();

            // CONECTAMOS CON EL SERVIDOR
            server = new ConexionServer(this, properties);
            server.Conectar();

            //view
            loginEvent = new LoginEvent(this);
            viewCurrent = loginEvent.getLoginview();

        } catch (ConfigExeptions e) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, "ERROR INICIANDO CLIENTE CHAT");
        } catch (Exception e) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.SEVERE, "ERROR INICIANDO CLIENTE CHAT");
        }

    }

    /*
    Metodo que controla las VIEWS DEL APP
     */
    public void switchView(ViewEnum viewName, javax.swing.JFrame viewPreviuw, Boolean HideCurrentView) {

        if (viewPreviuw != null) {
            viewPreviuw.setVisible(false);
        }

        if (HideCurrentView != null) {
            this.viewCurrent.setVisible(false);
        }

        switch (viewName) {
            case LOGIN:
                break;

            case MAIN:
                if (mainEvent == null) {
                    mainEvent = new MainEvent(this);
                }
                mainEvent.getMainView().setVisible(true);

                break;
        }
    }

    public javax.swing.JFrame getView(ViewEnum viewName) {
        javax.swing.JFrame view = null;
        switch (viewName) {
            case LOGIN:
                break;

            case MAIN:
                view = mainEvent.getMainView();
                break;
        }
        return view;
    }

    /*
    GETTERS
     */
    public List<User> getListContactos() {
        return listContactos;
    }

    public String getIp() {
        return ip;
    }

    public ConexionServer getServer() {
        return server;
    }

    //////////////////////////////////
    public User getUser() {
        return user;
    }

    public LoginEvent getLoginEvent() {
        return loginEvent;
    }

    public MainEvent getMainEvent() {
        return mainEvent;
    }

    public void setUser(User user) {
            this.user = user;
    }

}
