package co.system.out.clientchat.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.system.out.clientchat.MaincClient;

public class UtilServerChat {

	public static String getIP() {
		String publicIP = "";
		/*
		 * try { URL tempURL = new URL("http://www.whatismyip.org/"); HttpURLConnection
		 * tempConn = (HttpURLConnection) tempURL.openConnection(); InputStream
		 * tempInStream = tempConn.getInputStream(); InputStreamReader tempIsr = new
		 * InputStreamReader(tempInStream); BufferedReader tempBr = new
		 * BufferedReader(tempIsr);
		 * 
		 * publicIP = tempBr.readLine();
		 * 
		 * tempBr.close(); tempInStream.close(); return publicIP;
		 * 
		 * } catch (Exception ex) { publicIP =
		 * "<No es posible resolver la direccion IP>";
		 * Logger.getLogger(MaincClient.class.getName()).log(Level.SEVERE, null, ex); }
		 */
		return publicIP;

	}
	
}
