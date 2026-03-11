//Marta
public class Centrodedatos {
    private int id;
    private String localizacion;
    private String nombre;
    private int capacidad_maxima;
    private int superficie;

    public Centrodedatos (int id, String localizacion, String nombre, int capacidad_maxima, int superficie) {
    this.id = id;
    this.localizacion = localizacion;
    this.nombre = nombre;
    this.capacidad_maxima = capacidad_maxima;
    this.superficie = superficie;
}
public static void main(String[] args) {
        Centrodedatos c = new Centrodedatos(1, "Donosti", "Base Verde", 5000, 3300000);
        Servidor s = new Servidor(1, "01-05-2025", 600000, 8000000, 39, 5000);
        Abrir.conexionServidor();
        Sensores sen = new Sensores(1, "01-05-2025", "Donostia", 60000, "MWh", "Luz");
        Abrir.conexionSensores();
        Agua a = new Agua(1, 100000, 300000, "Buena");
        Abrir.conexionAgua();
        Energia e = new Energia(1, 4000000, 500000, 50000000);
        Abrir.conexionEnergia();
        System.out.println("Sistema iniciado");
        
}
}
//Marta