
package co.system.out.clientchatgui.utils.exceptions;




public class ClientExceptions  extends RuntimeException{
    
     
    private ClientExceptionsMensajes error;

    private String codeError;

    public ClientExceptions(ClientExceptionsMensajes error, String codeError, String message) {
        super(message);
        this.error = error;
   
        this.codeError = codeError;
    }

    public ClientExceptionsMensajes getError() {
        return error;
    }

    public void setError(ClientExceptionsMensajes error) {
        this.error = error;
    }



 

    public String getCodeError() {
        return codeError;
    }

    public void setCodeError(String codeError) {
        this.codeError = codeError;
    }
    
    
    
        
    public static enum ClientExceptionsMensajes{
             ERROR_CONECTANDO_SERVER("Error conectando con el server");
         
              public  String value;

        private ClientExceptionsMensajes(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

              
              
    }
    
}
