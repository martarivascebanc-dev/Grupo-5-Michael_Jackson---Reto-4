public class Agua {
    private int id;
    private int litros_de_agua;
    private int coste_total;
    private String calidad_agua;

    public Agua(int id, int litros_de_agua, int coste_total, String calidad_agua) {
    this.id = id;
    this.litros_de_agua = litros_de_agua;
    this.coste_total = coste_total;
    this.calidad_agua = calidad_agua;
    }
    public static void main(String[] args) {
        Agua a = new Agua(1, 100000, 300000, "Buena");
        Abrir.conexionAgua();
        System.out.println("Sistema iniciado");
        
}
}
