package models;

public class ProductoDigital extends Producto{
    //Atributos
    private double tamanioDescarga;
    private String licencia;

    //Contructor
    public ProductoDigital(int id, String nombre, double precio, int stock, double tamanioDescarga, String licencia) {
        super(id, nombre, precio, stock);
        this.tamanioDescarga = tamanioDescarga;
        this.licencia = licencia;
    }

    public ProductoDigital(String nombre, double precio, int stock, double tamanioDescarga, String licencia) {
        super(nombre, precio, stock);
        this.tamanioDescarga = tamanioDescarga;
        this.licencia = licencia;
    }

    //Getters y setters
    public double getTamanioDescarga() {
        return tamanioDescarga;
    }

    public void setTamanioDescarga(double tamanioDescarga) {
        this.tamanioDescarga = tamanioDescarga;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    //Pinta producto digital
    @Override
    public String toString() {
        return super.toString() +
                "Tamaño de descarga: " + tamanioDescarga + " MB\n" +
                "Licencia: " + licencia + "\n";
    }
}
