import java.util.*;

/**
 * Clase principal que maneja la lógica del sistema de renta de películas
 * Contiene el catálogo y todas las operaciones del negocio
 */
public class SistemaRentaPeliculas {
    private Map<String, Peliculas> catalogo; // Catálogo de películas (código -> película)
    private Map<String, Integer> peliculasRentadas; // Contador de películas rentadas
    private Scanner scanner; // Scanner para entrada de datos

    /**
     * Constructor que inicializa las estructuras de datos del sistema
     */
    public SistemaRentaPeliculas() {
        this.catalogo = new HashMap<>(); // Inicializar catálogo vacío
        this.peliculasRentadas = new HashMap<>(); // Inicializar contador de rentas
        this.scanner = new Scanner(System.in);
    }

    /**
     * Agrega una nueva película al catálogo del sistema
     */
    public boolean agregarPelicula(String codigo, String titulo, double duracion, 
                                 int cantidadDisponible, String genero) {
        // Verificar si ya existe una película con ese código
        if (catalogo.containsKey(codigo)) {
            System.out.println("Error: Ya existe una película con el código " + codigo);
            return false;
        }
        
        // Crear nueva película y agregarla al catálogo
        Peliculas pelicula = new Peliculas(codigo, titulo, duracion, cantidadDisponible, 0, genero);
        catalogo.put(codigo, pelicula);
        System.out.println("Película agregada exitosamente al catálogo.");
        return true;
    }

    /**
     * Elimina una película del catálogo del sistema
     */
    public boolean eliminarPelicula(String codigo) {
        // Verificar si existe la película
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }
        
        // Verificar si hay copias rentadas (no se puede eliminar si hay rentas activas)
        if (peliculasRentadas.containsKey(codigo) && peliculasRentadas.get(codigo) > 0) {
            System.out.println("Error: No se puede eliminar la película porque tiene copias rentadas.");
            return false;
        }
        
        // Eliminar película del catálogo y contador de rentas
        catalogo.remove(codigo);
        peliculasRentadas.remove(codigo);
        System.out.println("Película eliminada exitosamente del catálogo.");
        return true;
    }

    /**
     * Renta una película del catálogo
     */
    public boolean rentarPelicula(String codigo) {
        // Verificar si existe la película
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }

        Peliculas pelicula = catalogo.get(codigo);
        // Intentar rentar la película
        if (pelicula.rentar()) {
            // Actualizar contador de rentas en el sistema
            peliculasRentadas.put(codigo, peliculasRentadas.getOrDefault(codigo, 0) + 1);
            System.out.println("Película rentada exitosamente: " + pelicula.getTitulo());
            return true;
        } else {
            System.out.println("Error: No hay copias disponibles de la película " + pelicula.getTitulo());
            return false;
        }
    }

    /**
     * Devuelve una película rentada al catálogo
     */
    public boolean devolverPelicula(String codigo) {
        // Verificar si existe la película
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }

        // Verificar si hay copias rentadas de esta película
        if (!peliculasRentadas.containsKey(codigo) || peliculasRentadas.get(codigo) <= 0) {
            System.out.println("Error: No hay copias rentadas de esta película.");
            return false;
        }

        // Devolver la película y actualizar contadores
        Peliculas pelicula = catalogo.get(codigo);
        pelicula.devolver(); // Aumentar copias disponibles
        peliculasRentadas.put(codigo, peliculasRentadas.get(codigo) - 1); // Disminuir contador de rentas

        // Si no hay más copias rentadas, eliminar de la lista de rentadas
        if (peliculasRentadas.get(codigo) == 0) {
            peliculasRentadas.remove(codigo);
        }

        System.out.println("Película devuelta exitosamente: " + pelicula.getTitulo());
        return true;
    }

    /**
     * Genera reporte de películas disponibles para renta
     */
    public void reportePeliculasDisponibles() {
        System.out.println("\n=== PELÍCULAS DISPONIBLES PARA RENTA ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        boolean hayDisponibles = false;
        // Recorrer todas las películas y mostrar solo las disponibles
        for (Peliculas pelicula : catalogo.values()) {
            if (pelicula.getCantidadDisponible() > 0) {
                System.out.println(pelicula);
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay películas disponibles para renta en este momento.");
        }
    }

    /**
     * Genera reporte de películas actualmente rentadas
     */
    public void reportePeliculasRentadas() {
        System.out.println("\n=== PELÍCULAS ACTUALMENTE RENTADAS ===");
        if (peliculasRentadas.isEmpty()) {
            System.out.println("No hay películas rentadas en este momento.");
            return;
        }

        // Mostrar películas con copias rentadas
        for (Map.Entry<String, Integer> entry : peliculasRentadas.entrySet()) {
            String codigo = entry.getKey();
            int cantidadRentada = entry.getValue();
            Peliculas pelicula = catalogo.get(codigo);
            System.out.println(pelicula.getTitulo() + " - Copias rentadas: " + cantidadRentada);
        }
    }

    /**
     * Genera reporte de películas más rentadas (ordenadas por popularidad)
     */
    public void reportePeliculasMasRentadas() {
        System.out.println("\n=== PELÍCULAS MÁS RENTADAS ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        // Crear lista y ordenar por número de rentas (descendente)
        List<Peliculas> peliculas = new ArrayList<>(catalogo.values());
        peliculas.sort((p1, p2) -> Integer.compare(p2.getVecesRentada(), p1.getVecesRentada()));

        // Mostrar solo películas que han sido rentadas al menos una vez
        for (Peliculas pelicula : peliculas) {
            if (pelicula.getVecesRentada() > 0) {
                System.out.println(pelicula.getTitulo() + " - Veces rentada: " + pelicula.getVecesRentada());
            }
        }
    }

    /**
     * Genera reporte de películas menos rentadas (ordenadas por menor popularidad)
     */
    public void reportePeliculasMenosRentadas() {
        System.out.println("\n=== PELÍCULAS MENOS RENTADAS ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        // Crear lista y ordenar por número de rentas (ascendente)
        List<Peliculas> peliculas = new ArrayList<>(catalogo.values());
        peliculas.sort((p1, p2) -> Integer.compare(p1.getVecesRentada(), p2.getVecesRentada()));

        // Mostrar todas las películas ordenadas por menor número de rentas
        for (Peliculas pelicula : peliculas) {
            System.out.println(pelicula.getTitulo() + " - Veces rentada: " + pelicula.getVecesRentada());
        }
    }

    /**
     * Genera reporte de películas clasificadas por género
     */
    public void reportePeliculasPorGenero() {
        System.out.println("\n=== PELÍCULAS CLASIFICADAS POR GÉNERO ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        // Mapa para agrupar películas por género
        Map<String, List<Peliculas>> peliculasPorGenero = new HashMap<>();

        // Agrupar películas por género
        for (Peliculas pelicula : catalogo.values()) {
            String genero = pelicula.getGenero();
            peliculasPorGenero.computeIfAbsent(genero, generoKey -> new ArrayList<>()).add(pelicula);
        }

        // Mostrar películas agrupadas por género
        for (Map.Entry<String, List<Peliculas>> entry : peliculasPorGenero.entrySet()) {
            System.out.println("\n--- Género: " + entry.getKey() + " ---");
            for (Peliculas pelicula : entry.getValue()) {
                System.out.println("  " + pelicula.getTitulo() + " (" + pelicula.getCodigo() + ")");
            }
        }
    }

    /**
     * Muestra el catálogo completo de películas
     */
    public void mostrarCatalogo() {
        System.out.println("\n=== CATÁLOGO COMPLETO ===");
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }

        // Mostrar todas las películas del catálogo
        for (Peliculas pelicula : catalogo.values()) {
            System.out.println(pelicula);
        }
    }

    /**
     * Verifica si existe una película con el código dado
     */
    public boolean existePelicula(String codigo) {
        return catalogo.containsKey(codigo);
    }

    /**
     * Obtiene una película por su código
     */
    public Peliculas obtenerPelicula(String codigo) {
        return catalogo.get(codigo);
    }

    /**
     * Cierra el scanner del sistema
     */
    public void cerrarSistema() {
        scanner.close();
    }
}
