
package co.system.out.clientchatgui.eventsView;

import co.system.out.chatsocket.general.models.Menssage.typeMessages;
import co.system.out.chatsocket.general.models.Solicitud;
import co.system.out.clientchatgui.main.ModelPrincipal;
import co.system.out.clientchatgui.utils.JTableUtils;
import co.system.out.clientchatgui.utils.ViewUtil;
import co.system.out.clientchatgui.views.SolicitudesView;
import java.util.List;
import java.util.Objects;

/**
 * Clase que maneja los eventos de la vista y los componentes dentro de ella
 * @author henry
 */
public class SolicitudesEvent {
    
     private ModelPrincipal modelPrincipal;
     private SolicitudesView view;
     
     
     private   String[] columnNames = {  "Id","Apellido", "Nombre", "Email" };
     
     private Long idSolicitudSeleccionada = null;
     

    public SolicitudesEvent(ModelPrincipal modelPrincipal) {
        this.modelPrincipal = modelPrincipal;
        
        this.view = new SolicitudesView();
        
         this.view.getjTable_solcitudes().setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            this.columnNames
        ));
        
        
          this.view.getjButton_close().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close();
            }
        });
          
             this.view.getjButton_enviar_solicitud().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarSolicitud();
            }
        });
             
             this.view.getjButton_Aceptar_solicitud().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarSolicitud();
            }
        });
             
               this.view.getjTable_solcitudes().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarSolicitud(evt);
            }
        });
        
        
    }
    
    
    public void aceptarSolicitud(){
        
        if( Objects.nonNull(this.idSolicitudSeleccionada) ){
         String idSolicitud = String.valueOf( this.idSolicitudSeleccionada);
          this.modelPrincipal.getServer().getWriteThread().enviar(idSolicitud, typeMessages.ACEPTAR_CONTACTO);
        }else{
                ViewUtil.showBasic("Debe Seleccionar una solicitud para aceptarla", "ALERTA !", ViewUtil.INFORMATION_MESSAGE);
        }
    }
    
    
    public void seleccionarSolicitud(java.awt.event.MouseEvent evt){                  
           this.idSolicitudSeleccionada = Long.valueOf(JTableUtils.selectIdDataJtable(this.view.getjTable_solcitudes())) ;
                          
    }
    
    public void enviarSolicitud(){
         if( this.view.getjTextField_email_solicitud().getText() == null || this.view.getjTextField_email_solicitud().getText().isEmpty()){
            ViewUtil.showBasic("Debe escribir un email para enviar la solicitud", "ALERTA !", ViewUtil.INFORMATION_MESSAGE);
            return;
        }  
            String email = "";
            email = this.view.getjTextField_email_solicitud().getText();
            this.modelPrincipal.getServer().getWriteThread().enviar(email, typeMessages.SOLICITUD_CONTACTOS);
    }
    
    
    public void cargarSolicitudes (List<Solicitud> list){
        
        String [] [] data = JTableUtils.convertSolicitudes(list, columnNames.length);
        
          this.view.getjTable_solcitudes().setModel(new javax.swing.table.DefaultTableModel(
            data,
            this.columnNames
        ));
    }
    
    
    public void close(){
        this.view.setVisible(false);
    }

    public SolicitudesView getView() {
        return view;
    }
     
     
    
}
