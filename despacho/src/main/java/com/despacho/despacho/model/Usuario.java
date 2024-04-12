package com.despacho.despacho.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name= "name")
    private String name;
    @Column(name= "rol")
    private String rol;
    @Column(name = "direcciones")
    private String direcciones;
    @Column(name = "pass")
    private String pass;
    public Long getId(){
        return id;
    }
    public String getNombre(){
        return name;
    }
    public String getRol(){
        return rol;
    }
    public String getDirecciones(){
        return direcciones;
    }
    public String getPass(){
        return pass;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.name = nombre;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setDirecciones(String direcciones) {
        this.direcciones = direcciones;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
}


