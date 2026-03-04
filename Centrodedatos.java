//Marta
public class Centrodedatos {
    private int id;
    private String localizacion;

    public Centrodedatos (int id, String localizacion) {
    this.id = id;
    this.localizacion = localizacion;
    System.out.println("Bienvenido al Cetro de datos de Donosti");
}
}

class Servidor {
    private int id;
    private int fecha;

    public Servidor(int id, int fecha) {
    this.id = id;
    this.fecha = fecha;
}
}
class Sensor {
    private int id;
    private String regulador;
    private int fecha;

    public Sensor(int id, String regulador, int fecha) {
    this.id = id;
    this.regulador = regulador;
    System.out.println("¿Qué quieres regular?");
}
}

public static void main(String[] args) {
    Centrodedatos c = new Centrodedatos(1, "Donosti");
    Servidor s = new Servidor(1, 2024);
    Sensor sensor = new Sensor(1, "Temperatura", 2024);
    System.out.println("Sistema iniciado");
}
//Marta