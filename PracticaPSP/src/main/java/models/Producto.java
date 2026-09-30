package models;

import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor vacío necesario para deserialización
    public Producto() {}

    // Constructor completo corregido para asignar los atributos
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Precio: " + precio + " €\n" +
                "Stock: " + stock + "\n";
    }
}