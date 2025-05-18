package pedido;

import java.util.ArrayList;
import java.util.Scanner;
import articulo.Articulo;


public class PedidoImpl implements PedidoServices{

    public int addPedido(ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos,Scanner sc,int siguienteIdPedido){
        System.out.println("ingresa el nombre del cliente: ");
        String nombre= sc.nextLine();
        System.out.println("ingrese fecha");
        String fecha= sc.nextLine();
        ArrayList<Articulo> listaPedido = new ArrayList<>();
        int opcion=0;
        double total=0;
        while(opcion>0){
            System.out.println("ingresa el id del producto a pedir (numero negativo para salir): ");
            int id = sc.nextInt();
            for(articulo.Articulo articulo : articulos){
                if(id==articulo.getId()){
                    System.out.println("articulo encontrado, ingrese cantidad: ");
                    int cantidad = sc.nextInt();
                    if(!listaPedido.contains(articulo)){
                        articulo.setDescripcion("la cantidad de este articulo es: "+cantidad);
                        listaPedido.add(articulo);
                    }
                    total+=cantidad*articulo.getPrecio();
                }
                else{
                    System.out.println("articulo no encontrado... ");
                }
            }
        }
        Pedido pedido = new Pedido(siguienteIdPedido,fecha,nombre,total);
        pedidos.add(pedido);
        return siguienteIdPedido++;
    }
    public void removePedido(ArrayList<Pedido> pedidos,Scanner sc) {
        int input;
        System.out.printf("ingresa el Id del pedido a eliminar: ");
        do{
            input=sc.nextInt();
        }while(input<=0);
        int id=input;
        if(pedidos.removeIf(pedido -> pedido.getId() == id)){
            System.out.printf("pedido con id %d eliminado...",id);
        }
        else{
            System.out.printf("pedido con id %d no encontrado...",id);
        }
    }
    public Pedido getPedido(ArrayList<Pedido> pedidos,Scanner sc){
        System.out.println("ingresa el id de pedido a buscar: ");
        int id = sc.nextInt();
        for(int i=0;i<pedidos.size();i++){
            if(id==pedidos.get(i).getId()){
                return pedidos.get(i);
            }
        }
        System.out.println("pedido ID no encontrado");
        return null;
    }
    public void updatePedido(Scanner sc,ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos){
        int idPedido,opcion=0;
        char decision;
        System.out.printf("ingresa el Id del pedido a actualizar: ");
        do{
            idPedido=sc.nextInt();
        }while(idPedido<=0);
        for(Pedido pedido : pedidos){
            if(idPedido==pedido.getId()){
                System.out.println("pedido encontrado, ingrese que quiere cambiar: (-1 para terminar)");
                System.out.println("1)nombre, 2)fecha, 3)cantidad de articulos");
                opcion=sc.nextInt();
                switch(opcion){
                    case 1:
                        System.out.println("ingresa el nombre del cliente actualizado: ");
                        pedido.setCliente(sc.nextLine());
                        break;
                    case 2:
                        System.out.println("ingrese fecha actualizada");
                        pedido.setFecha(sc.nextLine());
                        break;
                    case 3:
                        double total=pedido.getTotal();
                        while(opcion>=0){
                            System.out.println("ingresa el id del producto a pedir (numero negativo para salir): ");
                            int id = sc.nextInt();
                            for(articulo.Articulo articulo : articulos){
                                if(id==articulo.getId()){
                                    System.out.println("articulo encontrado, ingrese cantidad y si queres restar o sumar (R o S) primero cantidad: ");
                                    int cantidad = sc.nextInt();
                                    System.out.println("ahora si ingrese R o S");
                                    do{
                                    String input = sc.next();
                                    input.toUpperCase();
                                    decision = input.charAt(0);
                                    }while(decision!='R' && decision!='S');
                                    if(!pedido.getArticulos().contains(articulo)){
                                        articulo.setDescripcion("la cantidad de este articulo es: "+cantidad);
                                        pedido.getArticulos().add(articulo);
                                    }
                                    if(decision=='S'){
                                    total+=cantidad*articulo.getPrecio();
                                    }
                                    else {
                                        total-=cantidad*articulo.getPrecio();
                                    }
                                }
                                else{
                                    System.out.println("articulo no encontrado... ");
                                }
                            }
                            pedido.setTotal(total);
                        }
                        break;
                    default:
                        System.out.println("ERROR, opcion no disponible");
                }
            }
            else{
                System.out.println("pedido no encontrado... ");
            }
        }
    }
}