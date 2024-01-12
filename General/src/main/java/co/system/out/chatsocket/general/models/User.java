
package co.system.out.chatsocket.general.models;

public class User  extends Persona{
    
  private String email;
  private String password;
  private String userName;
  private long userId;
  private String rol;
  private String state;

   
  
  

    public User(String email, String password, long userId, String rol, String state, String tipoIdentificacion, String nombres, String apellidos, int edad, String numeroIdentificacion) {
        super(tipoIdentificacion, nombres, apellidos, edad, numeroIdentificacion);
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
    }

    public User(String email, String password, long userId, String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) {
        super(tipoIdentificacion, nombres, apellidos, numeroIdentificacion);
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
    }

    public User(String email, long userId, String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) {
        super(tipoIdentificacion, nombres, apellidos, numeroIdentificacion);
        this.email = email;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
    }
    
     public User(String email, long userId,  String userName ,String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) {
        super(tipoIdentificacion, nombres, apellidos, numeroIdentificacion);
        this.email = email;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
        this.userName = userName;
    }
     
     public User(String email,String password, long userId,  String userName ,String rol, String state, String tipoIdentificacion, String nombres, String apellidos, String numeroIdentificacion) {
        super(tipoIdentificacion, nombres, apellidos, numeroIdentificacion);
        this.email = email;
        this.userId = userId;
        this.rol = rol;
        this.state = state;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public String toString() {
        return  userName   ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    
    
    
    
     
     
     
     
    

	
    
    
    
    
    

  
      
  
  
  
  
    
}
