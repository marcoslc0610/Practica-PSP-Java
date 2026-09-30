package models;

public class ProductoFisico extends Producto {
    //Atributos
    private int peso;
    private double gastosEnvio;

    //Constructor
    public ProductoFisico(int id, String nombre, double precio, int stock, int peso, double gastosEnvio) {
        super(id, nombre, precio, stock);
        this.peso = peso;
        this.gastosEnvio = gastosEnvio;
    }

    public ProductoFisico(String nombre, double precio, int stock, int peso, double gastosEnvio) {
        super(nombre, precio, stock);
        this.peso = peso;
        this.gastosEnvio = gastosEnvio;
    }

    //Getters y setters
    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(double gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    //Pinta producto físico
    @Override
    public String toString() {
        return super.toString() +
                "Peso: " + peso + " g\n" +
                "Gastos de envío: " + gastosEnvio + " €\n";
    }
}