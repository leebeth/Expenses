package com.tienda.a_shop.domain;

/**
 * Created by Lorena on 14/11/2016.
 */
public class ItemGasto {
    private int id;
    private String nombre;
    private int valor;

    public ItemGasto(int valor, String nombre, int id) {
        this.valor = valor;
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
