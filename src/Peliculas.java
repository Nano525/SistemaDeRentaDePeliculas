public class Peliculas {
    //variables privadas
    private String codigo;
    private String titulo;
    private double duracion;
    private int cantidadDisponible;
    private int vecesRentada;
    private String genero;

    //constructor

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

    //getters y setters


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

    //Metodos
        public boolean rentar() {
            if (cantidadDisponible > 0) {
                cantidadDisponible--;
                vecesRentada++;
                return true;
            }
            return false;
        }

        public void devolver() {
            cantidadDisponible++;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s (%d min) - Género: %s - Disponibles:" +
                            " %d - Veces rentada: %d",
                    codigo, titulo, duracion, genero, cantidadDisponible, vecesRentada);
        }
    }

