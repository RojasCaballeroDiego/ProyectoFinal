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
import proyectofinal.Modelo.Usuario;
import proyectofinal.Util.Archivo;

public class LoginControlador {

    private ArrayList<Usuario> usuarios;
    private Scanner sc;

    public LoginControlador() {

        usuarios = new ArrayList<>();
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

        Usuario usuario = new Usuario(nombre, correo, contraseña);

        usuarios.add(usuario);

        Archivo.guardar("Datos/usuarios.txt", usuario.toString());

        System.out.println("Usuario registrado correctamente.");

    }

    public boolean iniciarSesion() {

        System.out.println("\n===== INICIO DE SESIÓN =====");

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = sc.nextLine();

        for (Usuario u : usuarios) {

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

        for (Usuario u : usuarios) {

            if (u.getCorreo().equalsIgnoreCase(correo)) {

                return true;

            }

        }

        return false;

    }

    public void cargarUsuarios() {

    ArrayList<String> datos = Archivo.leer("Datos/usuarios.txt");

        for (String linea : datos) {

            String[] partes = linea.split(";");

            if (partes.length == 3) {

            Usuario u = new Usuario(partes[0], partes[1], partes[2]);

            usuarios.add(u);
            }
        }
    }

}
