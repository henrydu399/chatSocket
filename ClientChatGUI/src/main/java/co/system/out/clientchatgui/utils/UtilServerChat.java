package co.system.out.clientchatgui.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.system.out.clientchatgui.main.MaincClient;

public class UtilServerChat {

    public static String getIP() {
        String publicIP = "";
        try {
           URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

            String ip = in.readLine(); //you get the IP as a String
            return ip;

        } catch (Exception ex) {
            publicIP
                    = "<No es posible resolver la direccion IP>";
            Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return publicIP;

    }

}
