package com.despacho.despacho.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "ID cannot be null")
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

    @Column(name = "direcciones")
    @NotBlank(message = "Direcciones no puede ser nulo")
    private String direcciones;

    @Column(name = "pass")
    @NotBlank(message = "Pass no puede ser nulo")
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
    public String getDirecciones(){
        return direcciones;
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
    public void setDirecciones(String direcciones) {
        this.direcciones = direcciones;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}


