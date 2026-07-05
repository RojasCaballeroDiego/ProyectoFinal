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
import proyectofinal.Util.Archivo;

public class CafeControlador {

    private ArrayList<Cafe> cafes;
    private Scanner sc;

    public CafeControlador() {
        cafes = new ArrayList<>();
        sc = new Scanner(System.in);
        cargarCafes();
    }

    public ArrayList<Cafe> getCafes() {
        return cafes;
    }

    public void cargarCafes() {

        ArrayList<String> datos = Archivo.leer("Datos/cafes.txt");

        for (String linea : datos) {

            String[] partes = linea.split(";");

            if (partes.length == 6) {

                Cafe cafe = new Cafe(
                        partes[0],
                        partes[1],
                        partes[2],
                        partes[3],
                        Double.parseDouble(partes[4]),
                        Integer.parseInt(partes[5])
                );

                cafes.add(cafe);
            }
        }
    }

    public void guardarCafe(Cafe cafe) {
        Archivo.guardar("Datos/cafes.txt", cafe.toString());
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

        for (Cafe c : cafes) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) {
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

        Cafe cafe = new Cafe(codigo, nombre, categoria, tamaño, precio, stock);

        cafes.add(cafe);

        guardarCafe(cafe);

        System.out.println("Café registrado correctamente.");
    }

    public void modificarCafe() {

    System.out.println("\n===== MODIFICAR CAFÉ =====");

    System.out.print("Ingrese el código del café: ");
    String codigo = sc.nextLine();

        for (Cafe cafe : cafes) {

            if (cafe.getCodigo().equalsIgnoreCase(codigo)) {

                System.out.print("Nuevo nombre: ");
                cafe.setNombre(sc.nextLine());

                System.out.print("Nueva categoría: ");
                cafe.setCategoria(sc.nextLine());

                System.out.print("Nuevo tamaño: ");
                cafe.setTamaño(sc.nextLine());

                System.out.print("Nuevo precio: ");
                cafe.setPrecio(Double.parseDouble(sc.nextLine()));

                System.out.print("Nuevo stock: ");
                cafe.setStock(Integer.parseInt(sc.nextLine()));

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

        for (int i = 0; i < cafes.size(); i++) {

            if (cafes.get(i).getCodigo().equalsIgnoreCase(codigo)) {

                cafes.remove(i);

                guardarTodosLosCafes();

                System.out.println("Café eliminado correctamente.");
                return;
            }
        }

        System.out.println("No existe un café con ese código.");
    }

    public void consultarCafes() {

    System.out.println("\n===== LISTA DE CAFÉS =====");

        if (cafes.isEmpty()) {
            System.out.println("No hay cafés registrados.");
            return;
        }

        for (Cafe c : cafes) {

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

        ArrayList<String> datos = new ArrayList<>();

        for (Cafe cafe : cafes) {
            datos.add(cafe.toString());
        }

        Archivo.sobrescribir("Datos/cafes.txt", datos);
    }
    
}