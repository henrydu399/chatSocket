/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.system.out.serverchat.entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author henry
 */
@Entity
@Table(name = "SOLICITUDES")
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s"),
    @NamedQuery(name = "Solicitudes.findById", query = "SELECT s FROM Solicitudes s WHERE s.id = :id"),
    @NamedQuery(name = "Solicitudes.findByNombre", query = "SELECT s FROM Solicitudes s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "Solicitudes.findByApellido", query = "SELECT s FROM Solicitudes s WHERE s.apellido = :apellido")})
public class Solicitudes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    
    @Column(name = "EMAIL")
    private String email;
    
    @JoinColumn(name = "ID_USER_SOLICITUD", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUserSolicitud;
    @JoinColumn(name = "ID_USER", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users idUser;

    public Solicitudes() {
    }

    public Solicitudes(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Users getIdUserSolicitud() {
        return idUserSolicitud;
    }

    public void setIdUserSolicitud(Users idUserSolicitud) {
        this.idUserSolicitud = idUserSolicitud;
    }

    public Users getIdUser() {
        return idUser;
    }

    public void setIdUser(Users idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.system.out.serverchat.entitys.Solicitudes[ id=" + id + " ]";
    }
    
}
