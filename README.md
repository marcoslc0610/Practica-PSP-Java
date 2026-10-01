# Practica-PSP-Java
## 1. Descripción del Proyecto
Aplicación de consola en Java desarrollada bajo el paradigma de la Programación Orientada a Objetos (POO). El sistema permite gestionar una tienda online con control de usuarios, catálogo de productos (clasificados mediante herencia en productos físicos y digitales), gestión de carritos de compra, procesamiento de pedidos y persistencia de datos.

## 2. Decisiones de Diseño y Arquitectura
El proyecto se ha estructurado siguiendo una arquitectura limpia y modular dividida en paquetes:
* **`models`**: Contiene las clases del dominio (`Producto`, `ProductoFisico`, `ProductoDigital`, `Usuario`, `Carrito`, `Pedido`). Todas implementan `Serializable` para permitir su almacenamiento persistente. Se hace uso de herencia y polimorfismo (por ejemplo, en el método `toString()` de los productos).
* **`controller`**: Contiene la clase `Controller`, que centraliza toda la lógica de negocio, validaciones, gestión de colecciones en memoria (`ArrayList`) y la inicialización de datos de prueba (mocks).
* **`persistencia`**: Gestiona la capa de almacenamiento en ficheros utilizando serialización de objetos de Java (`ObjectInputStream` / `ObjectOutputStream`) organizados en directorios específicos (`data/productos/`, `data/usuarios/`, `data/pedidosRealizados/`).
* **`view`**: Contiene la clase `Main` y la interfaz por consola basada en bucles `do-while` y menús interactivos.
* **`utils`**: Utilidades auxiliares como la limpieza de pantalla.

## 3. Extra Elegido e Implementado
Para mejorar la aplicación y optar a la máxima nota, se ha seleccionado la siguiente ampliación:
* **Estadísticas de tienda:** Se ha implementado un módulo de análisis de datos utilizando la API de **Java Streams** y `Collectors`. Mediante consultas funcionales y declarativas, el sistema calcula de forma eficiente:
  * El **producto más vendido** (agrupando por producto y contando ocurrencias).
  * El **ticket medio** de las ventas realizadas (mapeando precios y calculando la media).
  * El **usuario con más pedidos** del sistema.

Esta opción se ha integrado de forma totalmente coherente con el menú principal, permitiendo consultar las métricas analíticas en tiempo real a partir del historial de pedidos cargado en memoria.

## 4. Manejo de Excepciones
Para garantizar la robustez de la aplicación frente a errores del usuario en la consola, se han implementado bloques `try-catch` específicos para la captura de excepciones como `NumberFormatException` (evitando que el programa falle si se introducen letras en lugar de números en los menús) y controles de referencias nulas (`NullPointerException`).

## 5. Capturas de Ejecución

### Menú Principal y Listado de Productos
<img width="279" height="231" alt="Menu Principal" src="https://github.com/user-attachments/assets/f06c7c02-6d90-423b-a8bb-90913e9828d1" />

### Manejo de Excepciones
<img width="377" height="490" alt="Manejo de Excepciones" src="https://github.com/user-attachments/assets/73ee5c13-ee76-40b8-b248-feb0c64a200b" />

### Estadísticas de la Tienda
<img width="377" height="348" alt="Estadísticas de la Tienda" src="https://github.com/user-attachments/assets/089815bf-b948-4949-9168-3e2e13f91097" />
