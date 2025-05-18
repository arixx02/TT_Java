package pedido;

import java.util.ArrayList;
import java.util.Scanner;
import articulo.Articulo;


public class PedidoImpl implements PedidoServices{

    public int addPedido(ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos,Scanner sc,int siguienteIdPedido){
        System.out.println("ingresa el nombre del cliente: ");
        String nombre,fecha;
        do{
        nombre= sc.nextLine();
        }while(nombre.length()>20 || nombre.length()<=0);
        System.out.println("ingrese fecha");
        do{
        fecha= sc.nextLine();
        }while(fecha.length()>20 || fecha.length()<=0);
        ArrayList<Articulo> listaPedido = new ArrayList<>();
        int id=0;
        double total=0;
        while(id>=0){
            System.out.println("ingresa el id del producto a pedir (numero negativo para salir): ");
            id = sc.nextInt();
            sc.nextLine();
            boolean encontrado = false;
            for(Articulo articulo : articulos){
                if(id==articulo.getId()){
                    System.out.println("articulo encontrado, ingrese cantidad: ");
                    int cantidad = sc.nextInt();
                    encontrado =true;
                    sc.nextLine();
                    if(!listaPedido.contains(articulo)){
                        articulo.setDescripcion("la cantidad de este articulo es: "+cantidad);
                        listaPedido.add(articulo);
                    }
                    total+=cantidad*articulo.getPrecio();
                }
            }
            if(!encontrado){
                System.out.println("Articulo no encontrado ...");
            }
        }
        Pedido pedido = new Pedido(siguienteIdPedido,fecha,nombre,total);
        pedido.setArticulos(listaPedido);
        pedidos.add(pedido);
        return siguienteIdPedido++;
    }

    public void removePedido(ArrayList<Pedido> pedidos,Scanner sc) {
        int input;
        System.out.printf("ingresa el Id del pedido a eliminar: ");
        do{
            input=sc.nextInt();
            sc.nextLine();
        }while(input<=0);
        int id=input;
        if(pedidos.removeIf(pedido -> pedido.getId() == id)){
            System.out.printf("pedido con id %d eliminado...",id);
        }
        else{
            System.out.printf("pedido con id %d no encontrado...",id);
        }
    }

    public String getPedido(ArrayList<Pedido> pedidos,Scanner sc){
        System.out.println("ingresa el id de pedido a buscar: ");
        int id;
        do{
            id = sc.nextInt();
            sc.nextLine();
        }while(id<=0);
        for(int i=0;i<pedidos.size();i++){
            if(id==pedidos.get(i).getId()){
                return toString1(pedidos.get(i));
            }
        }
        System.out.println("pedido ID no encontrado");
        return "no se encontro pedido con ID: "+id;
    }

    public void updatePedido(Scanner sc,ArrayList<Pedido> pedidos,ArrayList<Articulo> articulos){
        int idPedido,opcion=0;
        char decision;
        int id;
        System.out.printf("ingresa el Id del pedido a actualizar: ");
        do{
            idPedido=sc.nextInt();
            sc.nextLine();
        }while(idPedido<=0);
        for(Pedido pedido : pedidos){
            while(idPedido==pedido.getId() && opcion>=0){
                System.out.println("pedido encontrado, ingrese que quiere cambiar: (numero negativo para terminar)");
                System.out.println("1)nombre, 2)fecha, 3)cantidad de articulos");
                opcion=sc.nextInt();
                sc.nextLine();
                switch(opcion){
                    case 1:
                        System.out.println("ingresa el nombre del cliente actualizado: ");
                        String nombre;
                        do{
                            nombre= sc.nextLine();
                        }while(nombre.length()>20 || nombre.length()<=0);
                        pedido.setCliente(nombre);
                        break;
                    case 2:
                        System.out.println("ingrese fecha actualizada");
                        String fecha;
                        do{
                            fecha= sc.nextLine();
                        }while(fecha.length()>20 || fecha.length()<=0);
                        pedido.setFecha(fecha);
                        break;
                    case 3:
                        double total=pedido.getTotal();
                        ArrayList<Articulo> articulosPedido = pedido.getArticulos();
                        id=0;
                        while(id>=0){
                            System.out.println("ingresa el id del producto a pedir (numero negativo o 0 para salir): ");
                            id = sc.nextInt();
                            sc.nextLine();
                            for(Articulo articulo : articulos){
                                if(id==articulo.getId()){
                                    System.out.println("articulo encontrado, ingrese cantidad y si queres restar o sumar (R o S) primero cantidad: ");
                                    int cantidad;
                                    do{
                                        cantidad = sc.nextInt();
                                        sc.nextLine();
                                    }while(cantidad<0);
                                    System.out.println("ahora si ingrese R o S");
                                    do{
                                        String input = sc.nextLine();
                                        input=input.toUpperCase();
                                        decision = input.charAt(0);
                                    }while(decision!='R' && decision!='S');
                                    if(!articulosPedido.contains(articulo)){
                                        articulo.setDescripcion("la cantidad de este articulo es: "+cantidad);
                                        articulosPedido.add(articulo);
                                    }
                                    else{
                                        for(int j=0;j<articulosPedido.size();j++){
                                            if(articulosPedido.get(j).getId()==id)
                                                articulosPedido.get(j).setDescripcion("la cantidad de este articulo es: "+cantidad);
                                        }
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
            if(!(idPedido==pedido.getId())){
                System.out.println("pedido no encontrado... ");
            }
        }
    }

    String toString1(Pedido pedido){
        return  "ID: "+pedido.getId()+
                "\nnombre: "+pedido.getCliente()+
                "\nfecha: "+pedido.getFecha()+
                "\nimporte: "+pedido.getTotal()+
                "\nArticulos: "+pedido.getArticulos().toString();
    }
}