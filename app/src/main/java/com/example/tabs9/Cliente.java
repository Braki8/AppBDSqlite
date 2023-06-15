package com.example.tabs9;

import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {
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

    protected Cliente(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        descripcion = in.readString();
        imagen = in.createByteArray();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(descripcion);
        dest.writeByteArray(imagen);
    }
}
