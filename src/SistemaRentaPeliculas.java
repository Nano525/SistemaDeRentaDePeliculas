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

