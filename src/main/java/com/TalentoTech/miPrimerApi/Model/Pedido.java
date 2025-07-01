package com.TalentoTech.miPrimerApi.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fecha;
    private String cliente;
    private double total;

    @ManyToMany
    @JoinTable(
        name = "pedido_articulo",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "articulo_id")
    )
    private List<Articulo> articulos;

    public Pedido() {}

    public Pedido(String fecha, String cliente, double total, List<Articulo> articulos) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
        this.articulos = articulos;
    }

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<Articulo> getArticulos() { return articulos; }
    public void setArticulos(List<Articulo> articulos) { this.articulos = articulos; }

    
    @Override
    public String toString() {
        return "Pedido{" +
               "id=" + id +
               ", fecha='" + fecha + '\'' +
               ", cliente='" + cliente + '\'' +
               ", total=" + total +
               ", articulos=" + articulos.stream()
                                         .map(Articulo::getNombre)
                                         .toList() +
               '}';
    }
}

