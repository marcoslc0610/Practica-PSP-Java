package models;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {
    //Atributos
    private int idPedido;
    private Producto pedido;
    private Date fecha;
    private Usuario usuario;

    //Getters y setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getPedido() {
        return pedido;
    }

    public void setPedido(Producto pedido) {
        this.pedido = pedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
