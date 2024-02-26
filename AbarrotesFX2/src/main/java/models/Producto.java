package models;


public class Producto {
    protected String name;
    protected float priece;
    protected int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriece() {
        return priece;
    }

    public void setPriece(float priece) {
        this.priece = priece;
    }

    public void restarCantidad(int cantiadAVender){
        this.quantity = quantity - cantiadAVender;
    }

    @Override
    public String toString() {
        return  name +" "+
                "{ Precio=" + priece +
                ", Cantidad=" + quantity +
                " }";
    }

}

