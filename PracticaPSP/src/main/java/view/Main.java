package view;

import controller.Controller;
import models.Producto;
import models.ProductoDigital;
import models.ProductoFisico;
import models.Usuario;
import utils.Utils;

import java.util.Scanner;

public class Main {
    public static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) {
        Controller controller = new Controller();
        iniciaSesion(controller);
    }

    private static void iniciaSesion(Controller controller) {
        int op = 0;
        do {
            System.out.print("""
                    1. Iniciar sesión
                    2. Registrarse
                    3. Salir
                    Introduce un valor:\s""");
            try {
                op = Integer.parseInt(S.nextLine());
                switch (op) {
                    case 1:
                        System.out.print("Dime el nombre de usuario: ");
                        String nombre = S.nextLine();
                        System.out.print("Dime la contraseña del usuario: ");
                        String contrasenia = S.nextLine();

                        if (controller.iniciaSesion(nombre, contrasenia)) {
                            System.out.println("Has iniciado sesión correctamente");
                            Utils.limpiaPantalla();
                            pintaMain(controller, nombre, contrasenia);
                        } else System.out.println("El usuario o la contraseña no son correctos...");
                        break;
                    case 2:
                        System.out.print("Dime tu nombre de usuario: ");
                        String nombre2 = S.nextLine();
                        System.out.print("Dime tu contraseña: ");
                        String contrasenia2 = S.nextLine();
                        System.out.print("Dime tu email: ");
                        String email = S.nextLine();

                        Usuario usuario = new Usuario(nombre2, contrasenia2, email);
                        controller.aniadeUsuario(usuario);
                        System.out.println("¡Usuario registrado con éxito!");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un valor que sea un número.");
            }
        } while (op != 3);
    }

    private static void pintaMain(Controller controller, String nombre, String contrasenia) {
        int opMenuPrincipal = 0;
        do {
            System.out.println("""
                    Menú principal:
                    =========================
                    1- Gestionar productos
                    2- Gestionar usuarios
                    3- Gestionar carrito
                    4- Cerrar pedido
                    5- Consultar historial de pedidos
                    6- Estadísticas de la tienda
                    7- Salir de la aplicación
                    Introduce un valor:\s""");
            try {
                opMenuPrincipal = Integer.parseInt(S.nextLine());
                switch (opMenuPrincipal) {
                    case 1:
                        System.out.print("""
                                Gestionar productos
                                =========================
                                1- Dar de alta un producto
                                2- Dar de baja un producto
                                3- Listado de productos
                                4- Busqueda por precio
                                5- Salir
                                Introduce un valor:\s""");
                        int productos = Integer.parseInt(S.nextLine());
                        switch (productos) {
                            case 1:
                                System.out.print("Dime el nombre del producto: ");
                                String nombreProducto = S.nextLine();
                                System.out.print("Dime el precio del producto: ");
                                double precioProducto = Double.parseDouble(S.nextLine());
                                System.out.print("Dime el stock del producto: ");
                                int stockProducto = Integer.parseInt(S.nextLine());

                                System.out.print("""
                                        ¿Que producto quiere meter?
                                        1- Producto físico
                                        2- Producto digital
                                        Introduce un valor:\s""");
                                int tipoProducto = Integer.parseInt(S.nextLine());

                                switch (tipoProducto) {
                                    case 1:
                                        System.out.print("Dime el peso del producto: ");
                                        int pesoProducto = Integer.parseInt(S.nextLine());
                                        System.out.print("Dime los gastos de envío del producto: ");
                                        double gastosProducto = Double.parseDouble(S.nextLine());

                                        ProductoFisico productoFisico = new ProductoFisico(nombreProducto, precioProducto, stockProducto, pesoProducto, gastosProducto);
                                        controller.aniadeProducto(productoFisico);
                                        break;
                                    case 2:
                                        System.out.print("Dime el tamaño de descarga del producto: ");
                                        double tamanioDescarga = Double.parseDouble(S.nextLine());
                                        System.out.print("Dime la licencia del producto: ");
                                        String licencia = S.nextLine();

                                        ProductoDigital productoDigital = new ProductoDigital(nombreProducto, precioProducto, stockProducto, tamanioDescarga, licencia);
                                        controller.aniadeProducto(productoDigital);
                                        break;
                                }
                                System.out.println("¡Producto añadido con éxito!");
                                break;
                            case 2:
                                System.out.print("Dime el nombre del producto que quieres dar de baja: ");
                                String nombreProductoBaja = S.nextLine();
                                System.out.print("Dime el id del producto que quieres dar de baja: ");
                                int idProductoBaja = Integer.parseInt(S.nextLine());

                                if (controller.borraProducto(nombreProductoBaja, idProductoBaja)) {
                                    System.out.println("Se ha borrado correctamente");
                                } else System.out.println("Ese nombre o id del producto no existe");
                                break;
                            case 3:
                                System.out.println(controller.pintaProductos());
                                break;
                            case 4:
                                System.out.print("Dime el precio del producto: ");
                                double precioProductoBuscar = Double.parseDouble(S.nextLine());
                                Producto encontrado = controller.buscaPorPrecio(precioProductoBuscar);
                                if (encontrado != null) {
                                    System.out.println(encontrado);
                                } else System.out.println("No existe ningún producto con ese precio");
                                break;
                        }
                        break;
                    case 2:
                        System.out.print("""
                                Gestionar usuarios
                                =====================
                                1- Dar de alta un usuario
                                2- Listar usuarios activos
                                Introduce un valor:\s""");
                        int opUsuario = Integer.parseInt(S.nextLine());
                        if (opUsuario == 1) {
                            System.out.print("Dime el nombre del usuario: ");
                            String nombreUsuario = S.nextLine();
                            System.out.print("Dime la contraseña del usuario: ");
                            String contraseniaUsuario = S.nextLine();
                            System.out.print("Dime el email del usuario: ");
                            String emailUsuario = S.nextLine();
                            Usuario uTemp = new Usuario(nombreUsuario, contraseniaUsuario, emailUsuario);
                            controller.aniadeUsuario(uTemp);
                            System.out.println("Usuario dado de alta correctamente.");
                        } else if (opUsuario == 2) {
                            System.out.println(controller.pintaUsuariosActivos());
                        }
                        break;
                    case 3:
                        System.out.print("""
                                Gestionar carrito
                                ======================
                                1- Añadir producto al carrito
                                2- Quitar producto del carrito
                                3- Ver total
                                Introduce un valor:\s""");
                        int opCarrito = Integer.parseInt(S.nextLine());
                        switch (opCarrito) {
                            case 1:
                                System.out.print("Dime el nombre del producto que quieres añadir: ");
                                String nombreAlta = S.nextLine();

                                if (controller.aniadeProductoCarrito(nombre, nombreAlta)) {
                                    System.out.println("Se ha añadido correctamente");
                                } else System.out.println("Ese nombre de producto no existe");
                                break;
                            case 2:
                                System.out.print("Dime el nombre del producto que quieres quitar: ");
                                String nombreBaja = S.nextLine();

                                if (controller.quitaProductoCarrito(nombre, nombreBaja)) {
                                    System.out.println("Se ha quitado correctamente");
                                } else System.out.println("Ese producto no estaba en el carrito");
                                break;
                            case 3:
                                System.out.println(controller.verTotalCarrito(nombre));
                                break;
                        }
                        break;
                    case 4:
                        if (controller.cerrarPedido(nombre)) {
                            System.out.println("¡Pedido cerrado y registrado con éxito!");
                        } else {
                            System.out.println("No se pudo cerrar el pedido (carrito vacío).");
                        }
                        break;
                    case 5:
                        System.out.println(controller.pintaPedidos());
                        break;
                    case 6:
                        System.out.println(controller.obtenerEstadisticasTienda());
                        break;
                    case 7:
                        System.out.println("Cerrando sesión...");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un valor que sea un número");
            }
        }
        while (opMenuPrincipal != 7);
    }
}