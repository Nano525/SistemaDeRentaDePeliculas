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

    private static void agregarPelicula() {
        System.out.println("\n--- AGREGAR PELÍCULA ---");
        System.out.print("Código de la película: ");
        String codigo = scanner.nextLine();

        if (sistema.existePelicula(codigo)) {
            System.out.println("Error: Ya existe una película con ese código.");
            return;
        }

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Duración (en minutos): ");
        double duracion = Double.parseDouble(scanner.nextLine());

        System.out.print("Cantidad disponible: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        sistema.agregarPelicula(codigo, titulo, duracion, cantidad, genero);
    }

    private static void eliminarPelicula() {
        System.out.println("\n--- ELIMINAR PELÍCULA ---");
        System.out.print("Código de la película a eliminar: ");
        String codigo = scanner.nextLine();

        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        Peliculas pelicula = sistema.obtenerPelicula(codigo);
        System.out.println("Película a eliminar: " + pelicula.getTitulo());
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            sistema.eliminarPelicula(codigo);
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static void rentarPelicula() {
        System.out.println("\n--- RENTAR PELÍCULA ---");
        System.out.print("Código de la película a rentar: ");
        String codigo = scanner.nextLine();

        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        sistema.rentarPelicula(codigo);
    }

    private static void devolverPelicula() {
        System.out.println("\n--- DEVOLVER PELÍCULA ---");
        System.out.print("Código de la película a devolver: ");
        String codigo = scanner.nextLine();

        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        sistema.devolverPelicula(codigo);
    }

    private static void agregarDatosPrueba() {
        System.out.println("Agregando películas de prueba...");
        sistema.agregarPelicula("P001", "El Padrino", 175, 3, "Drama");
        sistema.agregarPelicula("P002", "Titanic", 194, 2, "Romance");
        sistema.agregarPelicula("P003", "Avatar", 162, 4, "Ciencia Ficción");
        sistema.agregarPelicula("P004", "Jurassic Park", 127, 2, "Aventura");
        sistema.agregarPelicula("P005", "Toy Story", 81, 3, "Animación");
        sistema.agregarPelicula("P006", "The Dark Knight", 152, 2, "Acción");
        sistema.agregarPelicula("P007", "Forrest Gump", 142, 1, "Drama");
        sistema.agregarPelicula("P008", "Inception", 148, 3, "Ciencia Ficción");
        System.out.println("Películas de prueba agregadas exitosamente.\n");
    }
}
