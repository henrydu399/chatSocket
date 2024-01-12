package co.system.out.clientchatgui.componentes;



import co.system.out.chatsocket.general.models.User;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;


public class ContactosComponent {

    private JList<User> listContactos;
   
    

    public ContactosComponent(List<User> listContactos, JList<User> _jListView) {
        
        this.listContactos = _jListView;
        
        
        if (listContactos != null) {
            this.listContactos.setModel(getJlist(listContactos));
        } 

     
        this.init();

    }

    private void init() {
        //panel = new JPanel(new BorderLayout());
        // create list book and set to scrollpane and add to panel
        //panel = new JPanel(new BorderLayout());
       // panel.setBorder(new EmptyBorder(10, 10, 10, 10));
       // panel.add(new JScrollPane(this.listContactos), BorderLayout.CENTER);


    }

    private DefaultListModel<User> getJlist(List<User> listContactos) {
        // create List model
        DefaultListModel<User> model = new DefaultListModel<>();
        // add item to model
        for (User u : listContactos) {
            model.addElement(u);
        }

        // create JList with model
        //JList<User> list = new JList<User>(model);
        return model;
    }

    public static void main(String[] args) {
        // new JListContactosCustomRenderer();
    }

}
