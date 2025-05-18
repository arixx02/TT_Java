package articulo;
import java.util.ArrayList;
import java.util.Scanner;

public class ArticuloImpl implements ArticuloServices {
    public int crearArticulo(ArrayList<Articulo> Articulos, int siguienteIdArticulo, Scanner sc) {
        System.out.println("creando articulo...");
        System.out.print("ingresar titulo del articulo: ");
        String nombre = sc.nextLine();
        System.out.print("ingresar contenido del articulo: ");
        String descripcion = sc.nextLine();
        System.out.print("ingresar precio del articulo: ");
        double precio;
        do{
            precio = sc.nextDouble();
        }while(precio<=0);
        sc.nextLine(); 
        Articulo nuevoArticulo = new Articulo(siguienteIdArticulo++, nombre, descripcion, precio);
        Articulos.add(nuevoArticulo);
        System.out.println("Articulo created: " + nuevoArticulo);
        return siguienteIdArticulo;
    }

    public void consultarArticulo(ArrayList<Articulo> Articulos, Scanner sc) {
        System.out.println("consultando articulo...");
        System.out.print("ingresar ID del articulo a consultar: ");
        int idConsulta = sc.nextInt();
        sc.nextLine();
        boolean encontrado = false;
        for (Articulo Articulo : Articulos) {
            if (Articulo.getId() == idConsulta) {
                System.out.println("Articulo encontrado: " + Articulo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Articulo no encontrado.");
        }
    }

    public void listarArticulos(ArrayList<Articulo> Articulos) {
        System.out.println("listando articulos...");
        if (Articulos.isEmpty()) {
            System.out.println("No hay articulos disponibles.");
        } else {
            for (Articulo Articulo : Articulos) {
                System.out.println(Articulo);
            }
        }
    }

    public void modificarArticulo(ArrayList<Articulo> Articulos, Scanner sc) {
        System.out.println("Modificando articulo...");
        System.out.print("ingresar ID del articulo a modificar: ");
        int modificarId = sc.nextInt();
        sc.nextLine(); 
        boolean encontrado = false;
        for (Articulo Articulo : Articulos) {
            if (Articulo.getId() == modificarId) {
                System.out.print("Enter new titulo: ");
                String nuevoNombre = sc.nextLine();
                System.out.print("Enter new contenido: ");
                String nuevaDesc = sc.nextLine();
                System.out.print("Enter new precio: ");
                double nuevoPrecio ;
                do{
                    nuevoPrecio = sc.nextDouble();
                    sc.nextLine();
                }while(nuevoPrecio<=0);
                Articulo.setNombre(nuevoNombre);
                Articulo.setDescripcion(nuevaDesc); 
                Articulo.setPrecio(nuevoPrecio);
                System.out.println("Articulo modificado: " + Articulo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Articulo not encontrado.");
        }
    }

    public void suprimirArticulo(ArrayList<Articulo> Articulos, Scanner sc) {
        System.out.println("suprimiendo articulo...");
        System.out.print("ingresar ID del articulo a suprimir: ");
        int suprimirId = sc.nextInt();
        sc.nextLine(); 
        for (int i = 0; i < Articulos.size(); i++) {
            if (Articulos.get(i).getId() == suprimirId) {
                Articulos.remove(i);
                System.out.println("Articulo suprimido.");
                break;
            }
        }
    }
}
