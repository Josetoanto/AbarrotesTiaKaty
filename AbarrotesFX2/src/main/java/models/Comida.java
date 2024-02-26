package models;

public class Comida extends Producto {
    private String fechaCaducidad;
    private String tipoDeComida;

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTipoDeComida() {
        return tipoDeComida;
    }

    public void setTipoDeComida(String tipoDeComida) {
        this.tipoDeComida = tipoDeComida;
    }
    @Override
    public String toString() {
        return  name
                + "{ Cantidad=" + quantity +
                ", Precio=" + priece +
                ", Fecha de Caducidad='" + fechaCaducidad + '\'' +
                ", Tipo de comida='" + tipoDeComida + '\'' +
                '}';
    }
}
