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
import java.util.ArrayList;
import java.util.Scanner;
import proyectofinal.Modelo.Cafe;
import proyectofinal.Modelo.Venta;
import proyectofinal.Util.Archivo;

public class VentaControlador {

    private ArrayList<Venta> ventas;
    private ArrayList<Cafe> cafes;
    private Scanner sc;

    public VentaControlador(ArrayList<Cafe> cafes) {

        this.cafes = cafes; 
        this.ventas = new ArrayList<>();
        this.sc = new Scanner(System.in);
        
        cargarVentas();
    }

    public ArrayList<Venta> getVentas() {
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

        Cafe cafeSeleccionado = null;

            for (Cafe cafe : cafes) {
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
        Archivo.sobrescribir("Datos/cafes.txt", obtenerDatosCafes());

        // Crear la venta
        int idVenta = ventas.size() + 1;
        LocalDate fecha = LocalDate.now();

        Venta venta = new Venta(
                idVenta,
                cafeSeleccionado,
                cantidad,
                total,
                fecha
        );

        // Guardar en memoria
        ventas.add(venta);

        // Guardar en ventas.txt
        guardarVenta(venta);

        System.out.println("\n===== RESUMEN DE LA VENTA =====");
        System.out.println("ID Venta: " + idVenta);
        System.out.println("Fecha: " + fecha);
        System.out.println("Café: " + cafeSeleccionado.getNombre());
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Total: S/ " + total);
        System.out.println("Venta registrada correctamente.");
    }
    
    public void guardarVenta(Venta venta) {
        Archivo.guardar("Datos/ventas.txt", venta.toString());
    }

    public void consultarVentas() {

    System.out.println("\n===== LISTA DE VENTAS =====");

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta venta : ventas) {

            System.out.println("---------------------------");
            System.out.println("ID Venta: " + venta.getIdVenta());
            System.out.println("Fecha: " + venta.getFecha());
            System.out.println("Código: " + venta.getCafe().getCodigo());
            System.out.println("Café: " + venta.getCafe().getNombre());
            System.out.println("Cantidad: " + venta.getCantidad());
            System.out.println("Total: S/ " + venta.getTotal());

        }
    }
    
    private ArrayList<String> obtenerDatosCafes() {
        ArrayList<String> datos = new ArrayList<>();

        for (Cafe cafe : cafes) {
            datos.add(cafe.toString());
        }
        return datos;
    }
       
    public void cargarVentas() {

    ArrayList<String> datos = Archivo.leer("Datos/ventas.txt");

    for (String linea : datos) {

        String[] partes = linea.split(";");

        if (partes.length == 4) {

            String codigo = partes[1];

            Cafe cafeEncontrado = null;

            for (Cafe cafe : cafes) {
                if (cafe.getCodigo().equalsIgnoreCase(codigo)) {
                    cafeEncontrado = cafe;
                    break;
                }
            }

            if (cafeEncontrado != null) {

                LocalDate fecha = LocalDate.parse(partes[0]);
                int cantidad = Integer.parseInt(partes[2]);
                double total = Double.parseDouble(partes[3]);

                int id = ventas.size() + 1;

                Venta venta = new Venta(
                        id,
                        cafeEncontrado,
                        cantidad,
                        total,
                        fecha
                );

                ventas.add(venta);
            }
        }
    }
}
    
    
    
}
