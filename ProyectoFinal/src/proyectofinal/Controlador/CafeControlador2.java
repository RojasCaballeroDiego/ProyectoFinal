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
import proyectofinal.Modelo.Cafe2;
import proyectofinal.Util.Archivo2;

public class CafeControlador2 {

    private Cafe2[] cafes;
    private int totalCafes;
    private Scanner sc;

    public CafeControlador2() {
        cafes = new Cafe2[100];
        totalCafes = 0;
        sc = new Scanner(System.in);
        cargarCafes();
    }

    public Cafe2[] getCafes() {
        return cafes;
    }

    public int getTotalCafes() {
        return totalCafes;
    }

    public void cargarCafes() {

        String[] datos = Archivo2.leer("Datos/cafes.txt");

        for (int i = 0; i < datos.length; i++) {

            if (datos[i] != null) {

                String[] partes = datos[i].split(";");

                if (partes.length == 6) {

                    Cafe2 cafe = new Cafe2(
                            partes[0],
                            partes[1],
                            partes[2],
                            partes[3],
                            Double.parseDouble(partes[4]),
                            Integer.parseInt(partes[5])
                    );

                    cafes[totalCafes] = cafe;
                    totalCafes++;
                }
            }
        }
    }

    public void guardarCafe(Cafe2 cafe) {
        Archivo2.guardar("Datos/cafes.txt", cafe.toString());
    }

    public void menuProductos() {

        int opcion;

        do {

            System.out.println("\n====== GESTIÓN DE CAFÉS ======");
            System.out.println("1. Registrar café");
            System.out.println("2. Modificar café");
            System.out.println("3. Eliminar café");
            System.out.println("4. Consultar cafés");
            System.out.println("5. Volver");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1:
                    registrarCafe();
                    break;

                case 2:
                    modificarCafe();
                    break;

                case 3:
                    eliminarCafe();
                    break;

                case 4:
                    consultarCafes();
                    break;

                case 5:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    public void registrarCafe() {

        System.out.println("\n===== REGISTRAR CAFÉ =====");
        System.out.print("Código: ");
        String codigo = sc.nextLine();

        for (int i = 0; i < totalCafes; i++) {

            if (cafes[i].getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Ese código ya existe.");
                return;
            }
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Categoría (Caliente/Frío): ");
        String categoria = sc.nextLine();

        System.out.print("Tamaño: ");
        String tamaño = sc.nextLine();

        System.out.print("Precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Stock: ");
        int stock = Integer.parseInt(sc.nextLine());

        Cafe2 cafe = new Cafe2(codigo, nombre, categoria, tamaño, precio, stock);

        cafes[totalCafes] = cafe;
        totalCafes++;

        guardarCafe(cafe);

        System.out.println("Café registrado correctamente.");
    }

    public void modificarCafe() {

        System.out.println("\n===== MODIFICAR CAFÉ =====");
        System.out.print("Ingrese el código del café: ");

        String codigo = sc.nextLine();

        for (int i = 0; i < totalCafes; i++) {

            if (cafes[i].getCodigo().equalsIgnoreCase(codigo)) {

                System.out.print("Nuevo nombre: ");
                cafes[i].setNombre(sc.nextLine());

                System.out.print("Nueva categoría: ");
                cafes[i].setCategoria(sc.nextLine());

                System.out.print("Nuevo tamaño: ");
                cafes[i].setTamaño(sc.nextLine());

                System.out.print("Nuevo precio: ");
                cafes[i].setPrecio(Double.parseDouble(sc.nextLine()));

                System.out.print("Nuevo stock: ");
                cafes[i].setStock(Integer.parseInt(sc.nextLine()));

                guardarTodosLosCafes();

                System.out.println("Café modificado correctamente.");
                return;
            }
        }

        System.out.println("No existe un café con ese código.");
    }

    public void eliminarCafe() {

        System.out.println("\n===== ELIMINAR CAFÉ =====");
        System.out.print("Ingrese el código del café: ");

        String codigo = sc.nextLine();

        for (int i = 0; i < totalCafes; i++) {

            if (cafes[i].getCodigo().equalsIgnoreCase(codigo)) {

                for (int j = i; j < totalCafes - 1; j++) {
                    cafes[j] = cafes[j + 1];
                }

                cafes[totalCafes - 1] = null;
                totalCafes--;

                guardarTodosLosCafes();

                System.out.println("Café eliminado correctamente.");
                return;
            }
        }

        System.out.println("No existe un café con ese código.");
    }

    public void consultarCafes() {

        System.out.println("\n===== LISTA DE CAFÉS =====");

        if (totalCafes == 0) {
            System.out.println("No hay cafés registrados.");
            return;
        }

        for (int i = 0; i < totalCafes; i++) {

            Cafe2 c = cafes[i];

            System.out.println("----------------------------");
            System.out.println("Código: " + c.getCodigo());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Categoría: " + c.getCategoria());
            System.out.println("Tamaño: " + c.getTamaño());
            System.out.println("Precio: S/ " + c.getPrecio());
            System.out.println("Stock: " + c.getStock());
        }
    }

    public void guardarTodosLosCafes() {

        String[] datos = new String[100];

        for (int i = 0; i < totalCafes; i++) {
            datos[i] = cafes[i].toString();
        }

        Archivo2.sobrescribir("Datos/cafes.txt", datos);
    }
}