public class Mouse {
    //Variables privadas
    private String color;
    private int numeroBotones;
    private String tipoConexion;
    private int dpi;

    //Contructor
    public Mouse(String color, int numeroBotones, String tipoConexion, int dpi) {
        this.color = color;
        this.numeroBotones = numeroBotones;
        this.tipoConexion = tipoConexion;
        this.dpi = dpi;
    }

    //Getters and setters
    public String getColor() {

        return color;
    }

    public void setColor(String color) {

        this.color = color;
    }

    public int getNumeroBotones() {

        return numeroBotones;
    }

    public void setNumeroBotones(int numeroBotones) {

        this.numeroBotones = numeroBotones;
    }

    public String getTipoConexion() {

        return tipoConexion;
    }

    public void setTipoConexion(String tipoConexion) {

        this.tipoConexion = tipoConexion;
    }

    public int getDpi() {

        return dpi;
    }

    public void setDpi(int dpi) {

        this.dpi = dpi;
    }

    //Metodos
    public void hacerClic(){

        System.out.println("Hiciste un clicks");
    }
    public void hacerClic2(){

        System.out.println("Hiciste dos clicks");
    }
    
}
