/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Controlador;

/**
 *
 * @author Xabi
 */
import java.util.Scanner;
import proyectofinal.Modelo.Cafe1;
import proyectofinal.Modelo.Venta1;

public class ReporteControlador1 {

    private Cafe1[] cafes;
    private Venta1[] ventas;
    private Scanner sc;

    public ReporteControlador1(Cafe1[] cafes, Venta1[] ventas) {
        this.cafes = cafes;
        this.ventas = ventas;
        this.sc = new Scanner(System.in);
    }

    public void menuReportes() {
        int opcion;

        do {
            System.out.println("\n====== REPORTES ======");
            System.out.println("1. Total de ventas");
            System.out.println("2. Ingresos totales");
            System.out.println("3. Stock actual");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    reporteTotalVentas();
                    break;
                case 2:
                    reporteIngresos();
                    break;
                case 3:
                    reporteStock();
                    break;
                case 4:
                    System.out.println("Regresando...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    public void reporteTotalVentas() {

        int total = 0;

        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] != null) {
                total++;
            }
        }

        System.out.println("\nTotal de ventas realizadas: " + total);
    }

    public void reporteIngresos() {

        double total = 0;

        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] != null) {
                total += ventas[i].getTotal();
            }
        }

        System.out.println("\nIngresos totales: S/ " + total);
    }

    public void reporteStock() {

        System.out.println("\n===== STOCK ACTUAL =====");

        for (int i = 0; i < cafes.length; i++) {

            if (cafes[i] != null) {

                System.out.println("----------------------------");
                System.out.println("Código: " + cafes[i].getCodigo());
                System.out.println("Nombre: " + cafes[i].getNombre());
                System.out.println("Categoría: " + cafes[i].getCategoria());
                System.out.println("Tamaño: " + cafes[i].getTamaño());
                System.out.println("Precio: S/ " + cafes[i].getPrecio());
                System.out.println("Stock: " + cafes[i].getStock());
            }
        }
    }
}