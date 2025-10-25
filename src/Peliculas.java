/**
 * Clase que representa una película en el sistema de rentas
 * Contiene toda la información y funcionalidad de una película
 */
public class Peliculas {
    // Atributos privados de la película
    private String codigo; // Código único identificador
    private String titulo; // Título de la película
    private double duracion; // Duración en minutos
    private int cantidadDisponible; // Copias disponibles para renta
    private int vecesRentada; // Contador de veces que ha sido rentada
    private String genero; // Género de la película

    /**
     * Constructor de la clase Peliculas
     */
    public Peliculas(String codigo,
                     String titulo,
                     double duracion,
                     int cantidadDisponible,
                     int vecesRentada,
                     String genero) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.duracion = duracion;
        this.cantidadDisponible = cantidadDisponible;
        this.vecesRentada = vecesRentada;
        this.genero = genero;
    }

    // Métodos getters y setters para acceso a los atributos

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getVecesRentada() {
        return vecesRentada;
    }

    public void setVecesRentada(int vecesRentada) {
        this.vecesRentada = vecesRentada;
    }

    // Métodos de funcionalidad de la película

    /**
     * Intenta rentar una copia de la película
     */
    public boolean rentar() {
        if (cantidadDisponible > 0) {
            cantidadDisponible--; // Disminuir copias disponibles
            vecesRentada++; // Incrementar contador de rentas
            return true;
        }
        return false; // No hay copias disponibles
    }

    /**
     * Devuelve una copia rentada de la película
     */
    public void devolver() {
        cantidadDisponible++; // Aumentar copias disponibles
    }

    /**
     * Representación en texto de la película
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (%.0f min) - Género: %s - Disponibles: %d - Veces rentada: %d",
                codigo, titulo, duracion, genero, cantidadDisponible, vecesRentada);
    }
}

