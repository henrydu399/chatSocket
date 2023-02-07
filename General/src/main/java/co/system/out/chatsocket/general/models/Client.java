/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.chatsocket.general.models;


import java.net.Socket;
import java.util.Date;

/**
 *
 * @author henry
 */
public class Client {
    
    
    private String ip;
    private User user;
    private Date iniConexion;
    private boolean stateConnect;
    static Socket sckt;

    public static Socket getSckt() {
		return sckt;
	}

	public static void setSckt(Socket sckt) {
		Client.sckt = sckt;
	}

	public Client(String ip, User user, Date iniConexion) {
        this.ip = ip;
        this.user = user;
        this.iniConexion = iniConexion;
        this.stateConnect = true;
    }

    public boolean isStateConnect() {
        return stateConnect;
    }

    public void setStateConnect(boolean stateConnect) {
        this.stateConnect = stateConnect;
    }
    
    

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getIniConexion() {
        return iniConexion;
    }

    public void setIniConexion(Date iniConexion) {
        this.iniConexion = iniConexion;
    }
    
    
    
    
}
