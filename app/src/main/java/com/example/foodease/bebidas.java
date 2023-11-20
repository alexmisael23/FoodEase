package com.example.foodease;

public class bebidas {

    private String nombreBebida;
    private Integer precio;
    //private long fechaCreacion;
    private String tipoBebida;
    private Integer cantidad;

    public bebidas() {

    }

    public bebidas(String nombreBebida, Integer precio, long fechaCreacion, String tipoBebida, Integer cantidad) {
        this.nombreBebida = nombreBebida;
        this.precio = precio;
        //this.fechaCreacion = fechaCreacion;
        this.tipoBebida = tipoBebida;
        this.cantidad = cantidad;
    }

    public String getNombreBebida() {
        return nombreBebida;
    }

    public void setNombreBebida(String nombreBebida) {
        this.nombreBebida = nombreBebida;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    //public long getFechaCreacion() {
    //    return fechaCreacion;
    //}

    // public void setFechaCreacion(long fechaCreacion) {
    //    this.fechaCreacion = fechaCreacion;
    // }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
