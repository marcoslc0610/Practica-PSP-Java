package persistencia;

import models.Pedido;
import models.Producto;
import models.Usuario;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    public static ArrayList<Pedido> cargaPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        File carpeta = new File("data/pedidosRealizados/");

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            return listaPedidos;
        }

        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                        Pedido pedido = (Pedido) ois.readObject();
                        listaPedidos.add(pedido);
                    } catch (Exception e) {
                        System.err.println("Error al cargar el pedido del fichero: " + archivo.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
        return listaPedidos;
    }

    public static ArrayList<Usuario> cargaUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        File carpeta = new File("data/usuarios/");

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            return listaUsuarios;
        }

        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                        Usuario usuario = (Usuario) ois.readObject();
                        listaUsuarios.add(usuario);
                    } catch (Exception e) {
                        System.err.println("Error al cargar el usuario del fichero: " + archivo.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
        return listaUsuarios;
    }

    public static ArrayList<Producto> cargaProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        File carpeta = new File("data/productos/");

        if (!carpeta.exists() || !carpeta.isDirectory()) {
            return listaProductos;
        }

        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                        Producto producto = (Producto) ois.readObject();
                        listaProductos.add(producto);
                    } catch (Exception e) {
                        System.err.println("Error al cargar el producto del fichero: " + archivo.getName());
                        e.printStackTrace();
                    }
                }
            }
        }
        return listaProductos;
    }

    public static void guardarPedido(Pedido pedido) {
        if (pedido == null) return;

        File carpeta = new File("data/pedidosRealizados/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String rutaFichero = "data/pedidosRealizados/pedido_" + pedido.getIdPedido() + ".ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
            oos.writeObject(pedido);
        } catch (Exception e) {
            System.err.println("Error al guardar el pedido ID " + pedido.getIdPedido() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void guardarUsuario(Usuario usuario) {
        if (usuario == null) return;

        File carpeta = new File("data/usuarios/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String nombreArchivo = String.valueOf(usuario.getId());
        String rutaFichero = "data/usuarios/usuario_" + nombreArchivo + ".ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
            oos.writeObject(usuario);
        } catch (Exception e) {
            System.err.println("Error al guardar el usuario " + usuario.getEmail() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void guardarProducto(Producto producto) {
        if (producto == null) return;

        File carpeta = new File("data/productos/");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        String rutaFichero = "data/productos/producto_" + producto.getId() + ".ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFichero))) {
            oos.writeObject(producto);
        } catch (Exception e) {
            System.err.println("Error al guardar el producto ID " + producto.getId() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
