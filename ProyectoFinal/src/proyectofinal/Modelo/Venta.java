/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Modelo;

/**
 *
 * @author Xabi
 */
import java.time.LocalDate;

public class Venta {

    private int idVenta;
    private Cafe cafe;
    private int cantidad;
    private double total;
    private LocalDate fecha;

    public Venta() {
    }

    public Venta(int idVenta, Cafe cafe, int cantidad,
            double total, LocalDate fecha) {

        this.idVenta = idVenta;
        this.cafe = cafe;
        this.cantidad = cantidad;
        this.total = total;
        this.fecha = fecha;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cafe getCafe() {
        return cafe;
    }

    public void setCafe(Cafe cafe) {
        this.cafe = cafe;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    @Override
    public String toString() {
        return fecha + ";" +
               cafe.getCodigo() + ";" +
               cantidad + ";" +
               total;
    }
}