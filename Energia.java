public class Energia {
    private int id;
    private int consumo_total;
    private int coste_total;
    private int voltaje;

    public Energia(int id, int consumo_total, int coste_total, int voltaje) {
    this.id = id;
    this.consumo_total = consumo_total;
    this.coste_total = coste_total;
    this.voltaje = voltaje;
    }
public static void main(String[] args) {
        Energia e = new Energia(1, 4000000, 500000, 50000000);
        Abrir.conexionEnergia();
        System.out.println("Sistema iniciado");
        
}
}