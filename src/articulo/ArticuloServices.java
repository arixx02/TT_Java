package articulo;

import java.util.ArrayList;
import java.util.Scanner;

public interface ArticuloServices {
    int crearArticulo(ArrayList<Articulo> articulos, int siguienteIdArticulo, Scanner sc);
    void consultarArticulo(ArrayList<Articulo> articulos, Scanner sc);
    void listarArticulos(ArrayList<Articulo> articulos);
    void modificarArticulo(ArrayList<Articulo> articulos, Scanner sc);
    void suprimirArticulo(ArrayList<Articulo> articulos, Scanner sc);
}