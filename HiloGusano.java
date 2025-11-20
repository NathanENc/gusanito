public class HiloGusano extends Thread{
    private char[][] jardin;
    private int filas;
    private int columnas;
    public HiloGusano (char[][] mundo){
        this.jardin = mundo;
        if (mundo != null && mundo.length > 0) {
             this.filas = jardin.length;
             this.columnas = jardin[0].length;
        } else {
             this.filas = 0;
             this.columnas = 0;
        }
    }
    public void caminaRenglon(int r){
        if (r >= 0 && r < filas) {
            for(int x = 0; x < columnas; x++){
                jardin[r][x] = 'W';
            }
        }
    }
    public void caminaColumna(int c){
        if (c >= 0 && c < columnas) {
            for(int x = 0; x < filas; x++){
                jardin[x][c] = 'W';
            }
        }
    }
    public void comerColumna(int c, int traga){
        if (c >= 0 && c < columnas) {
            int limite = Math.min(traga, filas); 
            for(int x = 0; x < limite; x++){
                jardin[x][c] = 'C';
            }
        }
    }
    public void comerRenglon(int r, int traga){
        if (r >= 0 && r < filas) {
            int limite = Math.min(traga, columnas);
            for(int x = 0; x < limite; x++){
                jardin[r][x] = 'C';
            }       
        }
    }
    @Override
    public void run(){
        int ini = 0;
        int vidas = 75; 
        while(vidas > 0){
            try{
                int columnaActual = ini % columnas;
                synchronized(jardin){
                    caminaColumna(columnaActual);
                    ini++;
                }
                sleep(1000);
 
                int renglonActual = ini % filas;
                int tragaRenglon = 3;
 
                synchronized(jardin){
                    comerRenglon(renglonActual, tragaRenglon);
                    ini++;
                }
                sleep(600);
                vidas--;
            } catch (InterruptedException e){
                System.out.println("Interrupcion");
                Thread.currentThread().interrupt(); 
                return;
            }
        }
    }
}

