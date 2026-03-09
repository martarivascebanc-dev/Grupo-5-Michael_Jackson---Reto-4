public class Sensores {
    private int id;
    private String fecha;
    private String lugar;
    private int valor_medio;
    private String unidad_de_medida;
    private String tipo_sensor;

    public Sensores(int id, String fecha, String lugar, int valor_medio, String unidad_de_medida, String tipo_sensor) {
    this.id = id;
    this.fecha = fecha;
    this.lugar = lugar;
    this.valor_medio = valor_medio;
    this.unidad_de_medida = unidad_de_medida;
    this.tipo_sensor = tipo_sensor;
    }
public static void main(String[] args) {
        Sensores sen = new Sensores(1, "01-05-2025", "Donostia", 60000, "MWh", "Luz");
        Abrir.conexionSensores();
        System.out.println("Sistema iniciado");
        
}
}