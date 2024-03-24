package com.despacho.despacho;

public class Direccion {
    private String calle;
    private String ciudad;
    private String comuna;
    private String region;
    public Direccion(String calle,String ciudad,String comuna,String region) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.region = region;
    }
    public String getCalle(){
        return calle;
    }
    public String getCiudad(){
        return ciudad;
    }
    public String getComuna(){
        return comuna;
    }
    public String getRegion(){
        return region;
    }
}
