/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Modelo;

/**
 *
 * @author Xabi
 */
public class Cafe1 {

    private String codigo;
    private String nombre;
    private String categoria;
    private String tamaño;
    private double precio;
    private int stock;

    public Cafe1() {
    }

    public Cafe1(String codigo, String nombre, String categoria,
            String tamaño, double precio, int stock) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.tamaño = tamaño;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        return codigo + ";" + nombre + ";" + categoria + ";" + tamaño + ";" + precio + ";" + stock;
    }
}
