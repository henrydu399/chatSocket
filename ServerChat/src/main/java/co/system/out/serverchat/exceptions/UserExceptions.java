
package co.system.out.serverchat.exceptions;


public class UserExceptions extends RuntimeException{
    
    private UserExceptionsMensajes errorFinal;
    private String codeError;

    public UserExceptions(UserExceptionsMensajes error, String codeError, String message) {
        super(message);
        this.errorFinal = error;
        this.codeError = codeError;
    }

   

  

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }

    public UserExceptionsMensajes getErrorFinal() {
        return errorFinal;
    }

    public void setErrorFinal(UserExceptionsMensajes errorFinal) {
        this.errorFinal = errorFinal;
    }
    
    
    
    
    public static enum UserExceptionsMensajes{
             NO_EXISTE_EL_USUARIO("No existe el usuario"),
             ERROR_CONSULTADO_USUARIO("Error consultando el usuario"),
             ERROR_CONSULTANDO_CONTACTOS("Error consultado los contactos"),
             NO_EXISTEN_CONTACTOS_PARA_EL_USUARIO("No existen contacntos para el usuario"),
             ERROR_BUSCANDO_USUARIO("Error buscando Usuario")
             ; 
             
              public  String value;

        private UserExceptionsMensajes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

              
              
    }
 
}

     
