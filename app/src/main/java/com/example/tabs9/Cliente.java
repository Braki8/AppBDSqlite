package com.example.tabs9;

public class Cliente {
    private int id;
    private String nombre;
    private String descripcion;
    private byte[] imagen;

    public Cliente(int id, String nombre, String descripcion, byte[] imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public byte[] getImagen() {
        return imagen;
    }
}
