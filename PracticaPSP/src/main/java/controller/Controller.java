package controller;

import models.Carrito;
import models.Pedido;
import models.Producto;
import models.ProductoFisico;
import models.ProductoDigital;
import models.Usuario;
import persistencia.Persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    ArrayList<Pedido> pedidosRealizados;
    ArrayList<Usuario> usuarios;
    ArrayList<Producto> productos;

    public Controller() {
        pedidosRealizados = Persistencia.cargaPedidos();
        usuarios = Persistencia.cargaUsuarios();
        productos = Persistencia.cargaProductos();
        inicializarDatosPrueba();
    }

    private void inicializarDatosPrueba() {
        // 1. PRODUCTOS DE PRUEBA (Físicos y Digitales)
        if (productos.isEmpty()) {
            Producto p1 = new ProductoFisico(1, "Camiseta Gaming", 19.99, 15, 250, 4.99);
            Producto p2 = new ProductoDigital(2, "Antivirus Pro 2026", 29.99, 100, 45.5, "LIC-ABC-999");
            Producto p3 = new ProductoFisico(3, "Teclado Mecánico RGB", 59.99, 8, 800, 7.50);
            Producto p4 = new ProductoDigital(4, "Curso de Java Avanzado", 49.99, 50, 1200.0, "EDU-JAVA-2026");

            productos.add(p1);
            productos.add(p2);
            productos.add(p3);
            productos.add(p4);

            Persistencia.guardarProducto(p1);
            Persistencia.guardarProducto(p2);
            Persistencia.guardarProducto(p3);
            Persistencia.guardarProducto(p4);
        }

        // 2. USUARIOS DE PRUEBA (Admin y Clientes)
        if (usuarios.isEmpty()) {
            Usuario u1 = new Usuario(1, "admin", "1234", "admin@tienda.com");
            u1.setActivo(true);
            u1.setCarrito(new Carrito(new HashMap<>()));

            Usuario u2 = new Usuario(2, "ana_gomez", "pass123", "ana@correo.com");
            u2.setActivo(true);
            u2.setCarrito(new Carrito(new HashMap<>()));

            Usuario u3 = new Usuario(3, "carlos99", "abc456", "carlos@correo.com");
            u3.setActivo(true);
            u3.setCarrito(new Carrito(new HashMap<>()));

            usuarios.add(u1);
            usuarios.add(u2);
            usuarios.add(u3);

            Persistencia.guardarUsuario(u1);
            Persistencia.guardarUsuario(u2);
            Persistencia.guardarUsuario(u3);
        }

        // 3. PEDIDOS REALIZADOS DE PRUEBA
        if (pedidosRealizados.isEmpty() && usuarios.size() >= 3 && productos.size() >= 4) {
            // Pedido 1 (Ana compra una Camiseta)
            Pedido ped1 = new Pedido();
            ped1.setIdPedido(1001);
            ped1.setPedido(productos.get(0));
            ped1.setFecha(new Date(System.currentTimeMillis() - 86400000L * 3)); // Hace 3 días
            ped1.setUsuario(usuarios.get(1)); // ana_gomez

            // Pedido 2 (Ana compra un Teclado)
            Pedido ped2 = new Pedido();
            ped2.setIdPedido(1002);
            ped2.setPedido(productos.get(2));
            ped2.setFecha(new Date(System.currentTimeMillis() - 86400000L * 2)); // Hace 2 días
            ped2.setUsuario(usuarios.get(1)); // ana_gomez

            // Pedido 3 (Carlos compra el Curso de Java)
            Pedido ped3 = new Pedido();
            ped3.setIdPedido(1003);
            ped3.setPedido(productos.get(3));
            ped3.setFecha(new Date(System.currentTimeMillis() - 86400000L)); // Ayer
            ped3.setUsuario(usuarios.get(2)); // carlos99

            // Pedido 4 (Admin compra el Antivirus)
            Pedido ped4 = new Pedido();
            ped4.setIdPedido(1004);
            ped4.setPedido(productos.get(1));
            ped4.setFecha(new Date()); // Hoy
            ped4.setUsuario(usuarios.get(0)); // admin

            pedidosRealizados.add(ped1);
            pedidosRealizados.add(ped2);
            pedidosRealizados.add(ped3);
            pedidosRealizados.add(ped4);

            Persistencia.guardarPedido(ped1);
            Persistencia.guardarPedido(ped2);
            Persistencia.guardarPedido(ped3);
            Persistencia.guardarPedido(ped4);
        }
    }

    public boolean iniciaSesion(String nombre, String contrasenia) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getContrasenia().equals(contrasenia)) {
                if (usuario.getCarrito() == null) {
                    usuario.setCarrito(new Carrito(new HashMap<>()));
                }
                return true;
            }
        }
        return false;
    }

    public void aniadeUsuario(Usuario usuario) {
        int id;
        boolean repetido;
        do {
            id = (int) (Math.random() * 1000);
            repetido = false;
            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    repetido = true;
                    break;
                }
            }
        } while (repetido);

        usuario.setId(id);
        usuario.setActivo(true);
        usuario.setCarrito(new Carrito(new HashMap<>()));
        usuarios.add(usuario);
        Persistencia.guardarUsuario(usuario);
    }

    public String pintaUsuariosActivos() {
        String respuesta = "";
        int cont = 1;
        for (Usuario u : usuarios) {
            if (u.isActivo()) {
                respuesta += "Usuario " + cont + " - Nombre: " + u.getNombre() + " | Email: " + u.getEmail() + "\n";
                cont++;
            }
        }
        return respuesta.isEmpty() ? "No hay usuarios activos." : respuesta;
    }

    public void aniadeProducto(Producto producto) {
        int id;
        boolean repetido;
        do {
            id = (int) (Math.random() * 1000);
            repetido = false;
            for (Producto p : productos) {
                if (p.getId() == id) {
                    repetido = true;
                    break;
                }
            }
        } while (repetido);

        producto.setId(id);
        productos.add(producto);
        Persistencia.guardarProducto(producto);
    }

    public boolean borraProducto(String nombreProductoBaja, int idProductoBaja) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProductoBaja) && producto.getId() == idProductoBaja) {
                productos.remove(producto);
                return true;
            }
        }
        return false;
    }

    public String pintaProductos() {
        String respuesta = "";
        int cont = 1;
        for (Producto producto : productos) {
            respuesta += "Producto " + cont + ":\n" + producto.toString() + "\n";
            cont++;
        }
        return respuesta.isEmpty() ? "No hay productos registrados." : respuesta;
    }

    public Producto buscaPorPrecio(double precioProducto) {
        for (Producto producto : productos) {
            // Usamos una tolerancia de 0.001 para evitar problemas de precisión con double
            if (Math.abs(producto.getPrecio() - precioProducto) < 0.001) {
                return producto;
            }
        }
        return null;
    }

    public boolean aniadeProductoCarrito(String nombreUsuario, String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                for (Usuario usuario : usuarios) {
                    if (usuario.getNombre().equals(nombreUsuario)) {
                        if (usuario.getCarrito() == null) {
                            usuario.setCarrito(new Carrito(new HashMap<>()));
                        }
                        HashMap<Producto, Integer> map = usuario.getCarrito().getCarrito();
                        map.put(producto, map.getOrDefault(producto, 0) + 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean quitaProductoCarrito(String nombreUsuario, String nombreProducto) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombreProducto)) {
                for (Usuario usuario : usuarios) {
                    if (usuario.getNombre().equals(nombreUsuario)) {
                        if (usuario.getCarrito() != null && usuario.getCarrito().getCarrito() != null) {
                            usuario.getCarrito().getCarrito().remove(producto);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String verTotalCarrito(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                Carrito carrito = usuario.getCarrito();
                if (carrito != null && carrito.getCarrito() != null) {
                    double total = 0;
                    for (Map.Entry<Producto, Integer> entry : carrito.getCarrito().entrySet()) {
                        total += entry.getKey().getPrecio() * entry.getValue();
                    }
                    return "Total del carrito: " + total + " €";
                }
            }
        }
        return "Usuario no encontrado o carrito vacío.";
    }

    public boolean cerrarPedido(String nombreUsuario) {
        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombreUsuario)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null || usuarioEncontrado.getCarrito() == null || usuarioEncontrado.getCarrito().getCarrito().isEmpty()) {
            return false;
        }

        for (Map.Entry<Producto, Integer> entry : usuarioEncontrado.getCarrito().getCarrito().entrySet()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido((int) (Math.random() * 90000) + 10000);
            pedido.setPedido(entry.getKey());
            pedido.setFecha(new Date());
            pedido.setUsuario(usuarioEncontrado);

            pedidosRealizados.add(pedido);
            Persistencia.guardarPedido(pedido);
        }

        usuarioEncontrado.getCarrito().getCarrito().clear();
        return true;
    }

    public String pintaPedidos() {
        String respuesta = "";
        int cont = 1;
        for (Pedido pedido : pedidosRealizados) {
            String usrNombre = (pedido.getUsuario() != null) ? pedido.getUsuario().getNombre() : "Desconocido";
            String detalleProducto = (pedido.getPedido() != null) ? pedido.getPedido().toString() : "Sin producto";

            respuesta += "----------------------------------------\n" +
                    "Pedido " + cont + " [ID: " + pedido.getIdPedido() + "]\n" +
                    "Usuario: " + usrNombre + "\n" +
                    "Detalles del producto:\n" + detalleProducto + "\n";
            cont++;
        }
        return respuesta.isEmpty() ? "No hay pedidos realizados." : respuesta + "----------------------------------------";
    }

    public String obtenerEstadisticasTienda() {
        if (pedidosRealizados.isEmpty()) {
            return "No hay suficientes datos de pedidos para calcular estadísticas.";
        }

        Producto masVendido = pedidosRealizados.stream()
                .filter(p -> p.getPedido() != null)
                .collect(Collectors.groupingBy(Pedido::getPedido, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        double ticketMedio = pedidosRealizados.stream()
                .filter(p -> p.getPedido() != null)
                .mapToDouble(p -> p.getPedido().getPrecio())
                .average()
                .orElse(0.0);

        Usuario usuarioMasPedidos = pedidosRealizados.stream()
                .filter(p -> p.getUsuario() != null)
                .collect(Collectors.groupingBy(Pedido::getUsuario, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        String nombreProdVendido = (masVendido != null) ? masVendido.getNombre() : "N/A";
        String nombreUsrPedidos = (usuarioMasPedidos != null) ? usuarioMasPedidos.getNombre() : "N/A";

        return "=== ESTADÍSTICAS DE LA TIENDA ===\n" +
                "- Producto más vendido: " + nombreProdVendido + "\n" +
                "- Ticket medio: " + String.format("%.2f", ticketMedio) + " €\n" +
                "- Usuario con más pedidos: " + nombreUsrPedidos + "\n";
    }
}