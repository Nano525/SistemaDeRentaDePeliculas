public class Main {
    public static void main(String[] args) {
        //Aqui se crea el objeto
        Mouse miMouse = new Mouse("Negro", 3, "Usb", 20000);

        // Y aqui se llaman a sus metodos
        miMouse.hacerClic();
        miMouse.hacerClic2();
    }
}
