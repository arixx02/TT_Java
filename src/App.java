import java.util.Scanner;
import articulo.ArticuloImpl;
import articulo.Articulo;
import java.util.ArrayList;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        int opcion = 0;
        ArticuloImpl service = new ArticuloImpl();  
        ArrayList<Articulo> articulos = new ArrayList<>();
        int siguienteIdArticulo = 1;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Crear articulo");
            System.out.println("2. Consultar articulo");
            System.out.println("3. Listar articulos");
            System.out.println("4. Modificar articulo");
            System.out.println("5. Suprimir articulo");
            System.out.println("6. Salir");
            System.out.print("Selecciona una opcion: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                System.out.println("Por favor ingrese un numero valido.");
                sc.next(); // descartar el input no válido
                continue;
            }
            sc.nextLine(); // consumir el salto de línea

            switch (opcion) {
                case 1:
                    siguienteIdArticulo = service.crearArticulo(articulos, siguienteIdArticulo, sc);
                    break;
                case 2:
                    service.consultarArticulo(articulos, sc);
                    break;
                case 3:
                    service.listarArticulos(articulos);
                    break;
                case 4:
                    service.modificarArticulo(articulos, sc);
                    break;
                case 5:
                    service.suprimirArticulo(articulos, sc);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no válida, por favor intente de nuevo.");
            }
        } while (opcion != 6);

        sc.close();
    }
}
