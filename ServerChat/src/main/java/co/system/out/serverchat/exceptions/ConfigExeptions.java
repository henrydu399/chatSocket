
package co.system.out.serverchat.exceptions;


public class ConfigExeptions extends RuntimeException{
    
     
    private ConfigExeptionsMensajes error;

    private String codeError;

    public ConfigExeptions(ConfigExeptionsMensajes error, String codeError, String message) {
        super(message);
        this.error = error;
   
        this.codeError = codeError;
    }

    public ConfigExeptionsMensajes getError() {
        return error;
    }

    public void setError(ConfigExeptionsMensajes error) {
        this.error = error;
    }



 

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
    
    
    
        
    public static enum ConfigExeptionsMensajes{
             ERROR_CONFIGURACION_PROPERTIES("Error configuracion de properties"),
             ERROR_CONECTANDO_BASE_DE_DATOS("Error conectando a la base de datos"); 
             
              public  String value;

        private ConfigExeptionsMensajes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

              
              
    }
    
}
