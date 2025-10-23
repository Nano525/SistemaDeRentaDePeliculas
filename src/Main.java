import java.util.Scanner;

public class Main {
    private static SistemaRentaPeliculas sistema;
    private static Scanner scanner;

    public static void main(String[] args) {
        sistema = new SistemaRentaPeliculas();
        scanner = new Scanner(System.in);

        // Agregar algunas películas de prueba
        agregarDatosPrueba();

        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
        }

        System.out.println("¡Gracias por usar el Sistema de Renta de Películas!");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n=== SISTEMA DE RENTA DE PELÍCULAS ===");
        System.out.println("1. Agregar película al catálogo");
        System.out.println("2. Eliminar película del catálogo");
        System.out.println("3. Rentar película");
        System.out.println("4. Devolver película");
        System.out.println("5. Mostrar catálogo completo");
        System.out.println("6. Reporte: Películas disponibles");
        System.out.println("7. Reporte: Películas rentadas");
        System.out.println("8. Reporte: Películas más rentadas");
        System.out.println("9. Reporte: Películas menos rentadas");
        System.out.println("10. Reporte: Películas por género");
        System.out.println("0. Salir del sistema");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarPelicula();
                break;
            case 2:
                eliminarPelicula();
                break;
            case 3:
                rentarPelicula();
                break;
            case 4:
                devolverPelicula();
                break;
            case 5:
                sistema.mostrarCatalogo();
                break;
            case 6:
                sistema.reportePeliculasDisponibles();
                break;
            case 7:
                sistema.reportePeliculasRentadas();
                break;
            case 8:
                sistema.reportePeliculasMasRentadas();
                break;
            case 9:
                sistema.reportePeliculasMenosRentadas();
                break;
            case 10:
                sistema.reportePeliculasPorGenero();
                break;
            case 0:
                return false;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
        return true;
    }