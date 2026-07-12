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

public class Venta1 {

    private int idVenta;
    private Cafe1 cafe;
    private int cantidad;
    private double total;
    private LocalDate fecha;

    public Venta1() {
    }

    public Venta1(int idVenta, Cafe1 cafe, int cantidad,
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

    public Cafe1 getCafe() {
        return cafe;
    }

    public void setCafe(Cafe1 cafe) {
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
