/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Util;

/**
 *
 * @author Xabi
 */

import java.io.*;

public class Archivo1 {

    // Guarda una línea en un archivo
    public static void guardar(String nombreArchivo, String dato) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, true))) {

            bw.write(dato);
            bw.newLine();

        } catch (IOException e) {

            System.out.println("Error al guardar en el archivo.");

        }

    }

    // Lee todas las líneas de un archivo
    public static String[] leer(String nombreArchivo) {

        String[] datos = new String[1000];
        int contador = 0;

        File archivo = new File(nombreArchivo);

        if (!archivo.exists()) {
            return datos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null && contador < datos.length) {

                datos[contador] = linea;
                contador++;

            }

        } catch (IOException e) {

            System.out.println("Error al leer el archivo.");

        }

        return datos;

    }

    // Sobrescribe completamente un archivo
    public static void sobrescribir(String nombreArchivo, String[] datos) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {

            for (int i = 0; i < datos.length; i++) {

                if (datos[i] != null) {
                    bw.write(datos[i]);
                    bw.newLine();
                }

            }

        } catch (IOException e) {

            System.out.println("Error al sobrescribir el archivo.");

        }

    }

}