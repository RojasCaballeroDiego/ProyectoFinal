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
import proyectofinal.Modelo.Usuario1;
import proyectofinal.Util.Archivo1;

public class LoginControlador1 {

    private Usuario1[] usuarios;
    private int totalUsuarios;
    private Scanner sc;

    public LoginControlador1() {
        
        usuarios = new Usuario1[100];
        totalUsuarios = 0;
        sc = new Scanner(System.in);
        cargarUsuarios();
    }

    public void registrarUsuario() {

        System.out.println("\n===== REGISTRO =====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        String correo;

        do {

            System.out.print("Correo: ");
            correo = sc.nextLine();

            if (!validarCorreo(correo)) {
                System.out.println("Correo inválido.");
            }

        } while (!validarCorreo(correo));

        if (correoExiste(correo)) {

            System.out.println("Ese correo ya está registrado.");
            return;

        }

        String contraseña;

        do {

            System.out.print("Contraseña (mínimo 4 caracteres): ");
            contraseña = sc.nextLine();

            if (!validarContraseña(contraseña)) {

                System.out.println("Contraseña inválida.");

            }

        } while (!validarContraseña(contraseña));

        Usuario1 usuario = new Usuario1(nombre, correo, contraseña);

        usuarios[totalUsuarios] = usuario;
        totalUsuarios++;

        Archivo1.guardar("Datos/usuarios.txt", usuario.toString());

        System.out.println("Usuario registrado correctamente.");

    }

    public boolean iniciarSesion() {

        System.out.println("\n===== INICIO DE SESIÓN =====");

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        for (int i = 0; i < totalUsuarios; i++) {
        Usuario1 u = usuarios[i];

        if (u.getCorreo().equals(correo)
                && u.getContraseña().equals(contraseña)) {
            System.out.println("\nBienvenido " + u.getNombre());
            return true;
        }
    }

        System.out.println("Correo o contraseña incorrectos.");

        return false;

    }

    public boolean validarCorreo(String correo) {

        return correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    }

    public boolean validarContraseña(String contraseña) {

        return contraseña.length() >= 4;

    }

    private boolean correoExiste(String correo) {

    for (int i = 0; i < totalUsuarios; i++) {

        if (usuarios[i].getCorreo().equalsIgnoreCase(correo)) {
            return true;
        }

    }

        return false;
    }

    public void cargarUsuarios() {

    String[] datos = Archivo1.leer("Datos/usuarios.txt");

    for (int i = 0; i < datos.length; i++) {

        if (datos[i] != null) {

            String[] partes = datos[i].split(";");

            if (partes.length == 3) {

                Usuario1 u = new Usuario1(
                        partes[0],
                        partes[1],
                        partes[2]);

                usuarios[totalUsuarios] = u;
                totalUsuarios++;

            }
        }
    }
}
}