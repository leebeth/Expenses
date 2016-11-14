package com.tienda.a_shop.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lorena on 10/10/2014.
 */
public class Producto
{
    private int id;
    private String nombre;
    private int totalGasto;
    private int posicion;
    private List<ItemGasto> items;

    public Producto (int id, String nombre, int posicion,int totalGasto)
    {
        this.id = id;
        this.nombre = nombre;
        this.totalGasto = totalGasto;
        this.posicion = posicion;
        items = new ArrayList<>();
    }

    public Producto (int id, String nombre, int posicion)
    {
        this.id = id;
        this.nombre = nombre;
        this.totalGasto = 0;
        this.posicion = posicion;
        items = new ArrayList<>();
    }

    public int getId(){ return id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getTotalGasto() {
        totalGasto = 0;
        for (ItemGasto item : items) {
            totalGasto += item.getValor();
        }
        return totalGasto;
    }

    public void setTotalGasto(int totalGasto) {
        this.totalGasto = totalGasto;
    }

    public List<ItemGasto> getItems() {
        return items;
    }

    public void setItems(List<ItemGasto> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return nombre + "   " + totalGasto;
    }
}
