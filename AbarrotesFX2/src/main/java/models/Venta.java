package models;
import java.time.LocalDate;

public class Venta {
    private int total;
    private LocalDate fechaDeVenta;

    public LocalDate getFechaDeVenta() {
        return fechaDeVenta;
    }

    public void setFechaDeVenta(LocalDate fechaDeVenta) {
        this.fechaDeVenta = fechaDeVenta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta { " +
                "Total=" + total +
                ", Fecha de venta=" + fechaDeVenta +
                '}';
    }
}
