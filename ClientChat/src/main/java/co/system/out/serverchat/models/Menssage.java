/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.system.out.serverchat.models;

import java.io.Serializable;

/**
 *
 * @author henry
 */
public class Menssage implements Serializable{
    
    private Client clientEmisor;
    private Client clienteReceptor;
    private String Mesaje;
    private boolean isLogin;

    public Menssage(Client clientEmisor, Client clienteReceptor, String Mesaje, boolean isLogin) {
        this.clientEmisor = clientEmisor;
        this.clienteReceptor = clienteReceptor;
        this.Mesaje = Mesaje;
        this.isLogin = isLogin;
    }

    public Client getClientEmisor() {
        return clientEmisor;
    }

    public void setClientEmisor(Client clientEmisor) {
        this.clientEmisor = clientEmisor;
    }

    public Client getClienteReceptor() {
        return clienteReceptor;
    }

    public void setClienteReceptor(Client clienteReceptor) {
        this.clienteReceptor = clienteReceptor;
    }

    public String getMesaje() {
        return Mesaje;
    }

    public void setMesaje(String Mesaje) {
        this.Mesaje = Mesaje;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    
    
    
    
    
    
    
}
