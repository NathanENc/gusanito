
import java.util.Scanner; 

public class Main {
    public static void main(String[] args) {
        int filas = 12;
        int columnas = 16;
        char[][] mapa = new char[filas][columnas];


        for (int r = 0; r < filas; r++) {
            for (int c = 0; c < columnas; c++) {
                mapa[r][c] = '*';
            }
        }


        Scanner leer = new Scanner(System.in);
        System.out.print("¿Cuántos gusanos quieres crear? ");
        int cantidadGusanos = leer.nextInt();


        MonitorMapa monitor = new MonitorMapa(mapa);
        monitor.start();

        System.out.println("Iniciando simulación con " + cantidadGusanos + " gusanos.");


        for (int i = 0; i < cantidadGusanos; i++) {
            HiloGusano gus = new HiloGusano(mapa);
            gus.start();
        }
    }
}
