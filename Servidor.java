public class Servidor {
    private int id;
    private String fecha;
    private int energia_consumida;
    private int potencia_maxima;
    private int temperatura;
    private int memoria_ram;

    public Servidor(int id, String fecha, int energia_consumida, int potencia_maxima, int temperatura, int memoria_ram) {
    this.id = id;
    this.fecha = fecha;
    this.energia_consumida = energia_consumida;
    this.potencia_maxima = potencia_maxima;
    this.temperatura = temperatura;
    this.memoria_ram = memoria_ram;   
}
public static void main(String[] args) {
        Servidor s = new Servidor(1, "01-05-2025", 600000, 8000000, 39, 5000);
        Abrir.conexionServidor();
        System.out.println("Sistema iniciado");
        
}
}