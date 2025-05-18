package pedido;
import articulo.Articulo;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private String fecha;
    private String cliente;
    private double total;
    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(ArrayList<Articulo> articulos) {
        this.articulos=articulos;
    }

    public Pedido(int id, String fecha, String cliente, double total) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
