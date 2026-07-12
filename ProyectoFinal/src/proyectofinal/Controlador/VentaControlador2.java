/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Controlador;

/**
 *
 * @author Xabi
 */

import java.time.LocalDate;
import java.util.Scanner;
import proyectofinal.Modelo.Cafe2;
import proyectofinal.Modelo.Venta2;
import proyectofinal.Util.Archivo2;

public class VentaControlador2 {

    private Venta2[] ventas;
    private Cafe2[] cafes;
    
    private int totalVentas;
    private int totalCafes;
    private Scanner sc;

    public VentaControlador2(Cafe2[] cafes, int totalCafes) {
        this.cafes = cafes;
        this.totalCafes = totalCafes;

        this.ventas = new Venta2[100];
        this.totalVentas = 0;
        this.sc = new Scanner(System.in);

        cargarVentas();
    }

    public Venta2[] getVentas() {
        return ventas;
    }

    public void menuVentas() {

        int opcion;

        do {

            System.out.println("\n====== VENTAS ======");
            System.out.println("1. Registrar venta");
            System.out.println("2. Consultar ventas");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    registrarVenta();
                    break;

                case 2:
                    consultarVentas();
                    break;

                case 3:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 3);
    }

    public void registrarVenta() {

        System.out.println("\n===== REGISTRAR VENTA =====");

        System.out.print("Código del café: ");
        String codigo = sc.nextLine();

        Cafe2 cafeSeleccionado = null;

            for (int i = 0; i < totalCafes; i++) {
                Cafe2 cafe = cafes[i];
                if (cafe.getCodigo().equalsIgnoreCase(codigo)) {
                    cafeSeleccionado = cafe;
                    break;
                }
            }

            if (cafeSeleccionado == null) {
                System.out.println("No existe un café con ese código.");
                return;
            }

        System.out.println("Café: " + cafeSeleccionado.getNombre());
        System.out.println("Precio: S/ " + cafeSeleccionado.getPrecio());
        System.out.println("Stock disponible: " + cafeSeleccionado.getStock());

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
                return;
            }

            if (cantidad > cafeSeleccionado.getStock()) {
                System.out.println("Stock insuficiente.");
                return;
            }

        double total = cantidad * cafeSeleccionado.getPrecio();

        // Descontar stock
        cafeSeleccionado.setStock(cafeSeleccionado.getStock() - cantidad);

        // Guardar el nuevo stock en cafes.txt
        Archivo2.sobrescribir("Datos/cafes.txt", obtenerDatosCafes());

        // Crear la venta
        int idVenta = totalVentas + 1;
        LocalDate fecha = LocalDate.now();

        Venta2 venta = new Venta2(
                idVenta,
                cafeSeleccionado,
                cantidad,
                total,
                fecha
        );

        // Guardar en memoria
        ventas[totalVentas] = venta;
        totalVentas++;

        // Guardar en ventas.txt
        guardarVenta(venta);
        generarBoleta(venta);
    }
    public void guardarVenta(Venta2 venta) {
        Archivo2.guardar("Datos/ventas.txt", venta.toString());
    }
    
    public void generarBoleta(Venta2 venta) {
        System.out.println("\n====================================");
        System.out.println("        STARBUCKS COFFEE");
        System.out.println("====================================");
        System.out.println("Boleta N°: " + venta.getIdVenta());
        System.out.println("Fecha: " + venta.getFecha());
        System.out.println();
        System.out.println("Producto : " + venta.getCafe().getNombre());
        System.out.println("Código   : " + venta.getCafe().getCodigo());
        System.out.println("Cantidad : " + venta.getCantidad());
        System.out.println("P. Unit. : S/ " + venta.getCafe().getPrecio());
        System.out.println("------------------------------------");
        System.out.println("TOTAL    : S/ " + venta.getTotal());
        System.out.println();
        System.out.println("¡Gracias por su compra!");
        System.out.println("====================================");
    }
    public void consultarVentas() {

    System.out.println("\n===== LISTA DE VENTAS =====");

        if (totalVentas == 0) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (int i = 0; i < totalVentas; i++) {
            Venta2 venta = ventas[i];

            System.out.println("---------------------------");
            System.out.println("ID Venta: " + venta.getIdVenta());
            System.out.println("Fecha: " + venta.getFecha());
            System.out.println("Código: " + venta.getCafe().getCodigo());
            System.out.println("Café: " + venta.getCafe().getNombre());
            System.out.println("Cantidad: " + venta.getCantidad());
            System.out.println("Total: S/ " + venta.getTotal());

        }
    }
    
    private String[] obtenerDatosCafes() {

        String[] datos = new String[100];

        for (int i = 0; i < totalCafes; i++) {
            datos[i] = cafes[i].toString();
        }

        return datos;
    }
       
    public void cargarVentas() {

    String[] datos = Archivo2.leer("Datos/ventas.txt");

        for (int i = 0; i < datos.length; i++) {

        if (datos[i] == null) continue;

        String linea = datos[i];

            String[] partes = linea.split(";");

            if (partes.length == 4) {

                String codigo = partes[1];

                Cafe2 cafeEncontrado = null;

                for (int j = 0; j < totalCafes; j++) {
                    Cafe2 cafe = cafes[j];
                    if (cafe.getCodigo().equalsIgnoreCase(codigo)) {
                        cafeEncontrado = cafe;
                        break;
                    }
                }

                if (cafeEncontrado != null) {

                    LocalDate fecha = LocalDate.parse(partes[0]);
                    int cantidad = Integer.parseInt(partes[2]);
                    double total = Double.parseDouble(partes[3]);

                    int id = totalVentas + 1;

                    Venta2 venta = new Venta2(
                            id,
                            cafeEncontrado,
                            cantidad,
                            total,
                            fecha
                    );

                    ventas[totalVentas] = venta;
                    totalVentas++;  
                }
            }
        }
    }   
}