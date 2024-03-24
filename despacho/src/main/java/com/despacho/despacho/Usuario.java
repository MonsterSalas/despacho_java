package com.despacho.despacho;
import java.util.List;
public class Usuario {
    private int id;
    private String nombre;
    private String rol;
    private List<Direccion> direcciones;
    // Constructor, getters y setters
    public Usuario(int id,String nombre,String rol,List<Direccion>direcciones){
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.direcciones = direcciones;
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getRol(){
        return rol;
    }
    public List<Direccion>getDirecciones(){
        return direcciones;
    }
}


