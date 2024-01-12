package co.system.out.clientchatgui.utils;


import co.system.out.chatsocket.general.models.Menssage.StatusCode;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ViewUtil {

    /**
     * Used for error messages.
     */
    public static final int ERROR_MESSAGE = 0;
    /**
     * Used for information messages.
     */
    public static final int INFORMATION_MESSAGE = 1;
    /**
     * Used for warning messages.
     */
    public static final int WARNING_MESSAGE = 2;
    /**
     * Used for questions.
     */
    public static final int QUESTION_MESSAGE = 3;
    /**
     * No icon is used.
     */
    public static final int PLAIN_MESSAGE = -1;

    public static void showBasic(String msj, String title, int type) {
        JFrame jFrame = new JFrame();
        JOptionPane.showMessageDialog(jFrame, msj, title, type);

    }

    public static int getTypeByServer(StatusCode statusCode) {

        switch (statusCode) {
            case ERRORPARAMETROS:
                return WARNING_MESSAGE;
               
            case INTERNAL_ERROR:
                return ERROR_MESSAGE;
               
            case OK:
                return INFORMATION_MESSAGE;
                
            default:
                return  ERROR_MESSAGE; 
        }

    }

}
