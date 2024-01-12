package co.system.out.clientchatgui.eventsView;

import co.system.out.chatsocket.general.models.Conversacion;
import co.system.out.chatsocket.general.models.User;
import co.system.out.clientchatgui.componentes.ContactosComponent;
import co.system.out.clientchatgui.componentes.SolicitudesComponent;
import co.system.out.clientchatgui.main.ModelPrincipal;


import co.system.out.clientchatgui.utils.DateUtils;
import co.system.out.clientchatgui.utils.PropertiesUtil;
import co.system.out.clientchatgui.utils.ViewUtil;
import co.system.out.clientchatgui.views.MainView;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

public class MainEvent {

    public final String YO = "|YO|";
    
    //VIEW LOGIN
    MainView mainView;
    

    //COMPONENTES
    ContactosComponent contactosComponent;
    SolicitudesComponent solicitudesComponent;
    
    

    private ModelPrincipal modelPrincipal;

    String MensajesText;
    private List<Conversacion> listConversaciones;
    private List<User> listContactos;

    // USUARIO SELECIONADO 
    private User userSelect;

    public MainEvent(ModelPrincipal modelPrincipal) {
        this.mainView = new MainView();
        this.modelPrincipal = modelPrincipal;

        this.listConversaciones = new ArrayList<>();
        this.listContactos = new ArrayList<>();

        this.contactosComponent = new ContactosComponent(listContactos, this.mainView.getjListContactos());
        this.solicitudesComponent =  new SolicitudesComponent(modelPrincipal);

        this.mainView.setVisible(true);
        
        ///PERFIL
        this.mainView.getjLabelUserName().setText( this.modelPrincipal.getUser().getUserName());
        this.mainView.getjLabelRolUSerName().setText(this.modelPrincipal.getUser().getRol());
        /*
        EVENTOS 
         */
        this.mainView.getjButtonEnviar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar(evt);
            }
        });

        this.mainView.getjListContactos().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                selectContacto(evt);
            }
        });
        
          this.mainView.getjButton_solicitudes().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verSolicitudes();
            }
        });
        
        

    }
    
    
    public void verSolicitudes(){
        this.solicitudesComponent.getSolicitudesEvent().getView().setVisible(true);
    }

    public void cargarContactos(List<User> _listContactos) {
        this.listContactos = _listContactos;
        contactosComponent = new ContactosComponent(listContactos, this.mainView.getjListContactos());
        //this.mainView.revalidate();
        //this.mainView.repaint();
    }

    private void selectContacto(javax.swing.event.ListSelectionEvent evt) {
        JList<User> edgeList = (JList<User>) evt.getSource();
        this.userSelect = (User) edgeList.getSelectedValue();
        this.mainView.getjTextFieldUserRecived().setText(this.userSelect.getUserName());
        imprimirByUser(this.userSelect.getUserId());

    }

    public void imprimirByUser(long userId) {
        this.mainView.getjTextAreaConversation().setText("");
        String temp = "";

        for (Conversacion c : this.listConversaciones) {
            if (c.getUser().getUserId() == userId) {
                for (Conversacion.MenssageConversacion m : c.getMenssages()) {
                    if( m.getMsj().contains(this.YO)){
                         String msjTemp = m.getMsj().replace(this.YO, "");
                         temp = temp + "                             " + m.getFecha() + " | " + msjTemp+ "" + "\n";
                    }else{
                         temp = temp + " " + m.getFecha() + " | " + m.getMsj()+ "" + "\n";
                    }
                   
                   
                }
            }
        }

        this.mainView.getjTextAreaConversation().setText(temp);
    }

    private void enviar(java.awt.event.ActionEvent evt) {
        if( this.userSelect == null){
            ViewUtil.showBasic("No se ha seleccionado un contacto", "ALERTA !", ViewUtil.INFORMATION_MESSAGE);
            return;
        }
        
        String msjTemp = "";
        msjTemp = this.mainView.getjTextFieldMensaje().getText();
        
        String userTemp = "";
        userTemp = this.mainView.getjTextFieldUserRecived().getText();

        this.modelPrincipal.getServer().getWriteThread().enviarMessage(this.userSelect.getUserId(), msjTemp);
        
        addConversacion(this.userSelect.getUserId(), this.YO + msjTemp, true);
        this.imprimirByUser(this.userSelect.getUserId());

        this.mainView.getjTextFieldMensaje().setText("");
    }

    /*
    Adiciona o crea una conversacion
    long id -> id del usuario 
     */
    public void addConversacion(long userId, String msj,boolean isLocal) {
        
        Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "GUARDANDO CONVERSACION ...  CON EL USER :"+userId+" ES LOCAL : " + isLocal);
        boolean existConversaction = false;

        for (Conversacion c : this.listConversaciones) {
            if (c.getUser().getUserId() == userId) {
                 Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "CONVERSACION ENCONTRADA ...  PARA EL USUARIO :"+userId+" ES LOCAL : " + isLocal);
                existConversaction = true;
                c.addMenssage(DateUtils.getDateNow(DateUtils.Formateryyyymmdd_HHMMSS), msj);
                 Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "MENSAJE GUARDADO...  PARA EL USUARIO :"+userId+" ES LOCAL : " + isLocal);
            }
        }

        if (existConversaction == false) {
            Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "CONVERSACION NO ENCONTRADA...  PARA EL USUARIO :"+userId+" ES LOCAL : " + isLocal);
            User temp = getUserByContactos(userId);
           
            if (temp != null) {
                 Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "SE ENCONTRO EL USUARIO  PÁRA CREAR NUEVA CONVERSACION...  PARA EL USUARIO :"+userId+" ES LOCAL : " + isLocal);
                Conversacion c = new Conversacion(temp);
                c.addMenssage(DateUtils.getDateNow(DateUtils.Formateryyyymmdd_HHMMSS), msj);
                this.listConversaciones.add(c);
            }else{
                  Logger.getLogger(PropertiesUtil.class.getName()).log(Level.INFO, "NO EXISTE EL USUARIO "+userId+" EN LOS CONTACTOS");
            }

        }
    }

    public User getUserByContactos(long userId) {
        User temp = null;
        for (User u : this.listContactos) {
            if (u.getUserId() == userId) {
                temp = u;
            }
        }
        return temp;
    }

    public MainView getMainView() {
        return mainView;
    }

    public List<Conversacion> getListConversaciones() {
        return listConversaciones;
    }

    public User getUserSelect() {
        return userSelect;
    }

    public void setUserSelect(User userSelect) {
        this.userSelect = userSelect;
    }

    public SolicitudesComponent getSolicitudesComponent() {
        return solicitudesComponent;
    }
    
    
    

}
