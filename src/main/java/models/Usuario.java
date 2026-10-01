package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
    //Atributos
    private int id;
    private String nombre;
    private String contrasenia;
    private String email;
    private boolean activo;
    private Carrito carrito;
    private ArrayList<Pedido> historialPedidos;

    //Contructor
    public Usuario(int id, String nombre, String contrasenia, String email) {
        this.id = id;
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
        this.activo = true;
    }

    public Usuario(String nombre, String contrasenia, String email) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
        this.activo = true;
    }

    public Usuario(String nombre, String contrasenia, String email, boolean activo) {
        this.nombre = nombre;
        this.contrasenia = contrasenia;
        this.email = email;
        this.activo = activo;
    }

    //Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public ArrayList<Pedido> getHistorialPedidos() {
        return historialPedidos;
    }

    public void setHistorialPedidos(ArrayList<Pedido> historialPedidos) {
        this.historialPedidos = historialPedidos;
    }
}
