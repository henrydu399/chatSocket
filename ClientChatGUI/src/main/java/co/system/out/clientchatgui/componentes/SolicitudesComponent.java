
package co.system.out.clientchatgui.componentes;

import co.system.out.chatsocket.general.models.Solicitud;
import co.system.out.chatsocket.general.models.User;
import co.system.out.clientchatgui.eventsView.SolicitudesEvent;
import co.system.out.clientchatgui.main.ModelPrincipal;
import java.util.List;

import javax.swing.JList;

public class SolicitudesComponent {
    
    private ModelPrincipal modelPrincipal;
    
    private List<Solicitud> listSolicitudes;
    private SolicitudesEvent solicitudesEvent;

    
    public SolicitudesComponent(ModelPrincipal modelPrincipal) {
        this.modelPrincipal = modelPrincipal;
        this.solicitudesEvent =  new SolicitudesEvent(modelPrincipal);
        
    }

    public SolicitudesEvent getSolicitudesEvent() {
        return solicitudesEvent;
    }
    
     
    public void cargarSolicitudes (List<Solicitud> list){
        this.listSolicitudes = list;
        this.solicitudesEvent.cargarSolicitudes(list);
    }
    
    
    
    
    
    
    
}
