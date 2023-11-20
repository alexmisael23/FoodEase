package com.example.foodease;

public class comidas {

    private String nombreComida;
    private Integer precio;
    //private long fechaCreacion;
    private String tipoComida;
    private Integer cantidad;
    private boolean eliminado;

    public comidas() {

    }

    public comidas(String nombreComida, Integer precio, long fechaCreacion, String tipoComida, Integer cantidad) {
        this.nombreComida = nombreComida;
        this.precio = precio;
        //this.fechaCreacion = fechaCreacion;
        this.tipoComida = tipoComida;
        this.cantidad = cantidad;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
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

    public String getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(String tipoComida) {
        this.tipoComida = tipoComida;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }
}
