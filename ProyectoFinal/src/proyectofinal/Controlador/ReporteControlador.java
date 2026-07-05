/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Controlador;

/**
 *
 * @author Xabi
 */
import java.util.ArrayList;
import java.util.Scanner;
import proyectofinal.Modelo.Cafe;
import proyectofinal.Modelo.Venta;

public class ReporteControlador {

    private ArrayList<Cafe> cafes;
    private ArrayList<Venta> ventas;
    private Scanner sc;

    public ReporteControlador(ArrayList<Cafe> cafes, ArrayList<Venta> ventas) {
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
        System.out.println("\nTotal de ventas realizadas: " + ventas.size());
    }

    public void reporteIngresos() {

        double total = 0;

        for (Venta venta : ventas) {
            total += venta.getTotal();
        }

        System.out.println("\nIngresos totales: S/ " + total);
    }

    public void reporteStock() {

    StringBuilder sb = new StringBuilder();

    sb.append("\n===== STOCK ACTUAL =====\n\n");

        for (Cafe cafe : cafes) {

            sb.append("Código: ").append(cafe.getCodigo()).append("\n");
            sb.append("Nombre: ").append(cafe.getNombre()).append("\n");
            sb.append("Categoría: ").append(cafe.getCategoria()).append("\n");
            sb.append("Tamaño: ").append(cafe.getTamaño()).append("\n");
            sb.append("Precio: S/ ").append(cafe.getPrecio()).append("\n");
            sb.append("Stock: ").append(cafe.getStock()).append("\n");
            sb.append("----------------------------\n");
        }

        System.out.println(sb.toString());
    }
}
