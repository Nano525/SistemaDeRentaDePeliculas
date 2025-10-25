import java.util.Scanner;

/**
 * Clase principal que maneja la interfaz de usuario del Sistema de Renta de Películas
 */
public class Main {
    private static SistemaRentaPeliculas sistema; // Instancia del sistema de rentas
    private static Scanner scanner; // Scanner para entrada de datos

    /**
     * Método principal que inicia la aplicación
     */
    public static void main(String[] args) {
        sistema = new SistemaRentaPeliculas();
        scanner = new Scanner(System.in);

        // Agregar algunas películas de prueba
        agregarDatosPrueba();

        // Bucle principal del menú
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
        }

        System.out.println("¡Gracias por usar el Sistema de Renta de Películas!");
        scanner.close();
    }

    /**
     * Muestra el menú principal con todas las opciones disponibles
     */
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

    /**
     * Lee la opción seleccionada por el usuario con manejo de errores
     */
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna -1 si la entrada no es un número válido
        }
    }

    /**
     * Procesa la opción seleccionada por el usuario
     */
    private static boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                agregarPelicula(); // Agregar nueva película al catálogo
                break;
            case 2:
                eliminarPelicula(); // Eliminar película del catálogo
                break;
            case 3:
                rentarPelicula(); // Rentar una película disponible
                break;
            case 4:
                devolverPelicula(); // Devolver una película rentada
                break;
            case 5:
                sistema.mostrarCatalogo(); // Mostrar todo el catálogo
                break;
            case 6:
                sistema.reportePeliculasDisponibles(); // Reporte de películas disponibles
                break;
            case 7:
                sistema.reportePeliculasRentadas(); // Reporte de películas rentadas
                break;
            case 8:
                sistema.reportePeliculasMasRentadas(); // Reporte de películas más populares
                break;
            case 9:
                sistema.reportePeliculasMenosRentadas(); // Reporte de películas menos populares
                break;
            case 10:
                sistema.reportePeliculasPorGenero(); // Reporte clasificado por género
                break;
            case 0:
                return false; // Salir del sistema
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
        return true; // Continuar con el menú
    }

    /**
     * Permite al usuario agregar una nueva película al catálogo
     */
    private static void agregarPelicula() {
        System.out.println("\n--- AGREGAR PELÍCULA ---");
        System.out.print("Código de la película: ");
        String codigo = scanner.nextLine();

        // Verificar si ya existe una película con ese código
        if (sistema.existePelicula(codigo)) {
            System.out.println("Error: Ya existe una película con ese código.");
            return;
        }

        // Solicitar datos de la película
        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Duración (en minutos): ");
        double duracion = Double.parseDouble(scanner.nextLine());

        System.out.print("Cantidad disponible: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        // Agregar la película al sistema
        sistema.agregarPelicula(codigo, titulo, duracion, cantidad, genero);
    }

    /**
     * Permite al usuario eliminar una película del catálogo con confirmación
     */
    private static void eliminarPelicula() {
        System.out.println("\n--- ELIMINAR PELÍCULA ---");
        System.out.print("Código de la película a eliminar: ");
        String codigo = scanner.nextLine();

        // Verificar si existe la película
        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        // Mostrar información de la película y solicitar confirmación
        Peliculas pelicula = sistema.obtenerPelicula(codigo);
        System.out.println("Película a eliminar: " + pelicula.getTitulo());
        System.out.print("¿Está seguro? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();

        // Proceder con la eliminación si el usuario confirma
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            sistema.eliminarPelicula(codigo);
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    /**
     * Permite al usuario rentar una película disponible
     */
    private static void rentarPelicula() {
        System.out.println("\n--- RENTAR PELÍCULA ---");
        System.out.print("Código de la película a rentar: ");
        String codigo = scanner.nextLine();

        // Verificar si existe la película
        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        // Intentar rentar la película
        sistema.rentarPelicula(codigo);
    }

    /**
     * Permite al usuario devolver una película rentada
     */
    private static void devolverPelicula() {
        System.out.println("\n--- DEVOLVER PELÍCULA ---");
        System.out.print("Código de la película a devolver: ");
        String codigo = scanner.nextLine();

        // Verificar si existe la película
        if (!sistema.existePelicula(codigo)) {
            System.out.println("Error: No existe una película con ese código.");
            return;
        }

        // Devolver la película
        sistema.devolverPelicula(codigo);
    }

    /**
     * Agrega películas de prueba al sistema para demostración
     */
    private static void agregarDatosPrueba() {
        System.out.println("Agregando películas de prueba...");
        // Agregar películas de diferentes géneros para pruebas
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
