/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Principal;

/**
 *
 * @author Xabi
 */
import proyectofinal.Controlador.CafeControlador2;
import proyectofinal.Controlador.LoginControlador2;
import proyectofinal.Controlador.ReporteControlador2;
import proyectofinal.Controlador.VentaControlador2;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LoginControlador2 login = new LoginControlador2();
        CafeControlador2 cafeControlador = new CafeControlador2();
        VentaControlador2 ventaControlador = new VentaControlador2(
        cafeControlador.getCafes(),
        cafeControlador.getTotalCafes()
        );
        ReporteControlador2 reporteControlador =
        new ReporteControlador2(
                cafeControlador.getCafes(),
                ventaControlador.getVentas()
        );

        int opcionLogin;

        do {

            System.out.println("\n==============================");
            System.out.println(" STARBUCKS COFFEE SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcionLogin = Integer.parseInt(sc.nextLine());

            switch (opcionLogin) {

                case 1:
                    login.registrarUsuario();
                    break;

                case 2:

                    if (login.iniciarSesion()) {

                        int opcionMenu;

                        do {

                            System.out.println("========= MENÚ PRINCIPAL =========");
                            System.out.println("1. Gestión de Cafés");
                            System.out.println("2. Ventas");
                            System.out.println("3. Reportes");
                            System.out.println("4. Cerrar sesión");

                            opcionMenu = Integer.parseInt(sc.nextLine());

                            switch (opcionMenu) {

                                case 1:
                                    cafeControlador.menuProductos();
                                    break;

                                case 2:
                                    ventaControlador.menuVentas();
                                    break;

                                case 3:
                                    reporteControlador.menuReportes();
                                    break;

                                case 4:
                                    System.out.println("Sesión cerrada.");
                                    break;

                                default:
                                    System.out.println("Opción inválida.");

                            }

                        } while (opcionMenu != 4);

                    }

                    break;

                case 3:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opción inválida.");

            }

        } while (opcionLogin != 3);

        sc.close();
    }
}
