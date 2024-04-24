package com.despacho.despacho.model;

import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;
@Entity
@Table(name = "usuario")
public class Usuario extends RepresentationModel<Usuario>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "correo")
    @NotBlank(message = "Correo no puede ser nulo")
    private String correo;

    @Column(name = "nombre")
    @NotBlank(message = "Nombre no puede ser nulo")
    private String nombre;

    @Column(name = "rol")
    @NotBlank(message = "Rol no puede ser nulo")
    private String rol;
    
    @NotBlank(message = "Direccion 1 no puede ser nulo")
    @Column(name = "direccion1")
    private String direccion1;
    
    @Column(name = "direccion2")
    private String direccion2;
    
    @Column(name = "direccion3")
    private String direccion3;

    @Column(name = "pass")
    @NotBlank(message = "Contrasennia no puede ser nulo")
    private String pass;
    //GETTERS
    public Long getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public String getRol(){
        return rol;
    }
    public String getDireccion1(){
        return direccion1;
    }
    public String getDireccion2() {
        return direccion2;
    }

    public String getDireccion3() {
        return direccion3;
    }

    public String getPass(){
        return pass;
    }
    //SETTERS
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }
    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }
    public void setDireccion3(String direccion3) {
        this.direccion3 = direccion3;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}


