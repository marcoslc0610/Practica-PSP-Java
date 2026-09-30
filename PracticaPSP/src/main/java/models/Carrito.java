package models;

import java.io.Serializable;
import java.util.HashMap;

public class Carrito implements Serializable {
    //Atributos
    HashMap<Producto, Integer> carrito;

    //Contructor
    public Carrito(HashMap<Producto, Integer> carrito) {
        this.carrito = carrito;
    }

    //Getters y setters
    public HashMap<Producto, Integer> getCarrito() {
        return carrito;
    }

    public void setCarrito(HashMap<Producto, Integer> carrito) {
        this.carrito = carrito;
    }

    //Pinta carrito
    @Override
    public String toString() {
        return "Carrito{" +
                "carrito=" + carrito +
                '}';
    }
}
