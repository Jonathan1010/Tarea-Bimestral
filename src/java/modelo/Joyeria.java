/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "joyeria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Joyeria.findAll", query = "SELECT j FROM Joyeria j")
    , @NamedQuery(name = "Joyeria.findByIdjoyeria", query = "SELECT j FROM Joyeria j WHERE j.idjoyeria = :idjoyeria")
    , @NamedQuery(name = "Joyeria.findByNombre", query = "SELECT j FROM Joyeria j WHERE j.nombre = :nombre")
    , @NamedQuery(name = "Joyeria.findByUbicacion", query = "SELECT j FROM Joyeria j WHERE j.ubicacion = :ubicacion")
    , @NamedQuery(name = "Joyeria.findByDireccion", query = "SELECT j FROM Joyeria j WHERE j.direccion = :direccion")
    , @NamedQuery(name = "Joyeria.findByEncargado", query = "SELECT j FROM Joyeria j WHERE j.encargado = :encargado")
    , @NamedQuery(name = "Joyeria.findByDimLocal", query = "SELECT j FROM Joyeria j WHERE j.dimLocal = :dimLocal")
    , @NamedQuery(name = "Joyeria.findByCiudad", query = "SELECT j FROM Joyeria j WHERE j.ciudad = :ciudad")})
public class Joyeria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idjoyeria")
    private Integer idjoyeria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 45)
    @Column(name = "ubicacion")
    private String ubicacion;
    @Size(max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "encargado")
    private String encargado;
    @Size(max = 45)
    @Column(name = "dim_local")
    private String dimLocal;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;

    public Joyeria(Integer idjoyeria, String nombre, String ubicacion, String direccion, String encargado, String dimLocal, String ciudad) {
        this.idjoyeria = idjoyeria;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.direccion = direccion;
        this.encargado = encargado;
        this.dimLocal = dimLocal;
        this.ciudad = ciudad;
    }

    public Joyeria() {
    }
    
    public Joyeria(Integer idjoyeria) {
        this.idjoyeria = idjoyeria;
    }

    public Joyeria(Integer idjoyeria, String nombre) {
        this.idjoyeria = idjoyeria;
        this.nombre = nombre;
    }

   

    public Integer getIdjoyeria() {
        return idjoyeria;
    }

    public void setIdjoyeria(Integer idjoyeria) {
        this.idjoyeria = idjoyeria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDimLocal() {
        return dimLocal;
    }

    public void setDimLocal(String dimLocal) {
        this.dimLocal = dimLocal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idjoyeria != null ? idjoyeria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Joyeria)) {
            return false;
        }
        Joyeria other = (Joyeria) object;
        if ((this.idjoyeria == null && other.idjoyeria != null) || (this.idjoyeria != null && !this.idjoyeria.equals(other.idjoyeria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Joyeria[ idjoyeria=" + idjoyeria + " ]";
    }
    
}
