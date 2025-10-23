import java.util.*;

public class SistemaRentaPeliculas {
    private Map<String, Peliculas> catalogo;
    private Map<String, Integer> peliculasRentadas;
    private Scanner scanner;

    public SistemaRentaPeliculas() {
        this.catalogo = new HashMap<>();
        this.peliculasRentadas = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    // Método para agregar película al catálogo
    public boolean agregarPelicula(String codigo, String titulo, double duracion, 
                                 int cantidadDisponible, String genero) {
        if (catalogo.containsKey(codigo)) {
            System.out.println("Error: Ya existe una película con el código " + codigo);
            return false;
        }
        
        Peliculas pelicula = new Peliculas(codigo, titulo, duracion, cantidadDisponible, 0, genero);
        catalogo.put(codigo, pelicula);
        System.out.println("Película agregada exitosamente al catálogo.");
        return true;
    }

    // Método para eliminar película del catálogo
    public boolean eliminarPelicula(String codigo) {
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }
        
        // Verificar si hay copias rentadas
        if (peliculasRentadas.containsKey(codigo) && peliculasRentadas.get(codigo) > 0) {
            System.out.println("Error: No se puede eliminar la película porque tiene copias rentadas.");
            return false;
        }
        
        catalogo.remove(codigo);
        peliculasRentadas.remove(codigo);
        System.out.println("Película eliminada exitosamente del catálogo.");
        return true;
    }

    // Método para rentar una película
    public boolean rentarPelicula(String codigo) {
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }

        Peliculas pelicula = catalogo.get(codigo);
        if (pelicula.rentar()) {
            // Actualizar contador de rentas
            peliculasRentadas.put(codigo, peliculasRentadas.getOrDefault(codigo, 0) + 1);
            System.out.println("Película rentada exitosamente: " + pelicula.getTitulo());
            return true;
        } else {
            System.out.println("Error: No hay copias disponibles de la película " + pelicula.getTitulo());
            return false;
        }
    }

    // Método para devolver una película
    public boolean devolverPelicula(String codigo) {
        if (!catalogo.containsKey(codigo)) {
            System.out.println("Error: No existe una película con el código " + codigo);
            return false;
        }

        if (!peliculasRentadas.containsKey(codigo) || peliculasRentadas.get(codigo) <= 0) {
            System.out.println("Error: No hay copias rentadas de esta película.");
            return false;
        }

        Peliculas pelicula = catalogo.get(codigo);
        pelicula.devolver();
        peliculasRentadas.put(codigo, peliculasRentadas.get(codigo) - 1);

        // Si no hay más copias rentadas, eliminar de la lista
        if (peliculasRentadas.get(codigo) == 0) {
            peliculasRentadas.remove(codigo);
        }

        System.out.println("Película devuelta exitosamente: " + pelicula.getTitulo());
        return true;
    }

    // Reporte: Películas disponibles para renta
    public void reportePeliculasDisponibles() {
        System.out.println("\n=== PELÍCULAS DISPONIBLES PARA RENTA ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        boolean hayDisponibles = false;
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

    // Reporte: Películas actualmente rentadas
    public void reportePeliculasRentadas() {
        System.out.println("\n=== PELÍCULAS ACTUALMENTE RENTADAS ===");
        if (peliculasRentadas.isEmpty()) {
            System.out.println("No hay películas rentadas en este momento.");
            return;
        }

        for (Map.Entry<String, Integer> entry : peliculasRentadas.entrySet()) {
            String codigo = entry.getKey();
            int cantidadRentada = entry.getValue();
            Peliculas pelicula = catalogo.get(codigo);
            System.out.println(pelicula.getTitulo() + " - Copias rentadas: " + cantidadRentada);
        }
    }

    // Reporte: Películas más rentadas
    public void reportePeliculasMasRentadas() {
        System.out.println("\n=== PELÍCULAS MÁS RENTADAS ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        List<Peliculas> peliculas = new ArrayList<>(catalogo.values());
        peliculas.sort((p1, p2) -> Integer.compare(p2.getVecesRentada(), p1.getVecesRentada()));

        for (Peliculas pelicula : peliculas) {
            if (pelicula.getVecesRentada() > 0) {
                System.out.println(pelicula.getTitulo() + " - Veces rentada: " + pelicula.getVecesRentada());
            }
        }
    }

    // Reporte: Películas menos rentadas
    public void reportePeliculasMenosRentadas() {
        System.out.println("\n=== PELÍCULAS MENOS RENTADAS ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }

        List<Peliculas> peliculas = new ArrayList<>(catalogo.values());
        peliculas.sort((p1, p2) -> Integer.compare(p1.getVecesRentada(), p2.getVecesRentada()));

        for (Peliculas pelicula : peliculas) {
            System.out.println(pelicula.getTitulo() + " - Veces rentada: " + pelicula.getVecesRentada());
        }
    }

    // Reporte: Películas clasificadas por género
    public void reportePeliculasPorGenero() {
        System.out.println("\n=== PELÍCULAS CLASIFICADAS POR GÉNERO ===");
        if (catalogo.isEmpty()) {
            System.out.println("No hay películas en el catálogo.");
            return;
        }
   