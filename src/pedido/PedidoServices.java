package pedido;

import java.util.ArrayList;
import java.util.Scanner;
import articulo.Articulo;

public interface PedidoServices {
    int addPedido(ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos,Scanner sc,int siguienteIdPedido);
    void removePedido(ArrayList<Pedido> pedidos,Scanner sc);
    Pedido getPedido(ArrayList<Pedido> pedidos,Scanner sc);
    void updatePedido(Scanner sc,ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos);
}