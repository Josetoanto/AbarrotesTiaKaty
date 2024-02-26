package models;
import java.util.ArrayList;
import java.util.LinkedList;

public class Abarrote {

    private int totalSemanal = 0;
    private LinkedList<Venta> listaVentas = new LinkedList<>();
    private ArrayList<Producto> inventario = new ArrayList<>();

    public void agregarProducto(Producto newProducto){
        inventario.add(newProducto);
    }
    public void agregarVenta(Venta venta){listaVentas.add(venta);}
    public String devolverInventario(){
        String inventario = "";
        for (Producto productoIterator: this.inventario){
            inventario = inventario + productoIterator.toString()+"\n";
        }
        return inventario;
    }
    public String devolverVentas(){
        String ventas = "";
        for (Venta ventaIterator: listaVentas){
            ventas = ventas + ventaIterator.toString()+"\n";
        }
        return ventas;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }
    public void borrarInventario(){
        inventario.clear();
    }
    public int getTotalSemanal() {
        return totalSemanal;
    }
    public void borrarListaVentas(){
        listaVentas.clear();
    }

    public void borrarTotalSemanal(){
        totalSemanal = 0;
    }

    public void setTotalSemanal(int total) {
        this.totalSemanal = this.totalSemanal + total;
    }
}
