import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        

        System.out.println("--- Configuración del Jardín ---");
        System.out.print("Ingrese número de filas para el jardín: ");
        int filas = leer.nextInt();
        
        System.out.print("Ingrese número de columnas para el jardín: ");
        int columnas = leer.nextInt();
        

        char[][] mapa = new char[filas][columnas];
        

        for(int r = 0; r < filas; r++){
            for(int c = 0; c < columnas; c++){
                mapa[r][c] = '*';
            }
        }
        
        System.out.print("¿Cuántos gusanos quieres crear? ");
        int cantidadGusanos = leer.nextInt();
        

        System.out.print("Ingresa la velocidad (tiempo en ms): ");
        int velocidad = leer.nextInt();


        MonitorMapa monitor = new MonitorMapa(mapa);
        System.out.println("Iniciando simulación...");
        
        monitor.start();
        
        for(int i = 0; i < cantidadGusanos; i++){
    
            HiloGusano gus = new HiloGusano(mapa, velocidad);
            gus.start();
        }
    }
}
