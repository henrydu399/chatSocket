package co.system.out.clientchatgui.eventsView;


import co.system.out.chatsocket.general.models.Client;
import co.system.out.chatsocket.general.models.Menssage;
import co.system.out.chatsocket.general.models.User;
import co.system.out.clientchatgui.main.ModelPrincipal;


import co.system.out.clientchatgui.utils.MenssageUtil;
import co.system.out.clientchatgui.views.LoginView;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginEvent {

    //VIEW LOGIN
    LoginView loginview;
    private ModelPrincipal modelPrincipal;

    public LoginEvent(ModelPrincipal modelPrincipal) {
        this.loginview = new LoginView();
        this.modelPrincipal = modelPrincipal;

        this.loginview.setVisible(true);

        /*
        EVENTOS 
         */
        loginview.getjButtonEntrar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login();
            }
        });

    }

    public void login() {
        try {

            User userLocal = new User(this.loginview.getjTextFieldUser().getText(), this.loginview.getjTextFieldPasssword().getText(), 0, "", "", "", "", "", ""); // usuario receptor

            Client clientEmisor = new Client(this.modelPrincipal.getIp(), userLocal, new Date());

            Menssage msj = MenssageUtil.create(clientEmisor, null, null, Menssage.typeMessages.USERLOGIN);

            this.modelPrincipal.getServer().getWriteThread().getDtotpt().writeUTF(new Gson().toJson(msj));
            this.modelPrincipal.getServer().getWriteThread().getDtotpt().flush();

        } catch (IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public LoginView getLoginview() {
        return loginview;
    }
    
    
    
    

}
