public class HiloGusano extends Thread {
    private char[][] jardin;
    private int filas;
    private int columnas;
    private int tiempoEspera; 
    
    
    public HiloGusano(char[][] mundo, int velocidad) {
        this.jardin = mundo;
        this.tiempoEspera = velocidad;
        if (mundo != null && mundo.length > 0) {
            this.filas = jardin.length;
            this.columnas = jardin[0].length;
        } else {
            this.filas = 0;
            this.columnas = 0;
        }
    }

    private boolean quedaJardin() {
        synchronized (jardin) {
            for (int r = 0; r < filas; r++) {
                for (int c = 0; c < columnas; c++) {
                    if (jardin[r][c] == '*') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void caminaRenglon(int r) {
        if (r >= 0 && r < filas) {
            for (int x = 0; x < columnas; x++) {
                jardin[r][x] = 'W';
            }
        }
    }

    public void caminaColumna(int c) {
        if (c >= 0 && c < columnas) {
            for (int x = 0; x < filas; x++) {
                jardin[x][c] = 'W';
            }
        }
    }

    public void comerColumna(int c, int traga) {
        if (c >= 0 && c < columnas) {
            int limite = Math.min(traga, filas);
            for (int x = 0; x < limite; x++) {
                jardin[x][c] = 'C';
            }
        }
    }

    public void comerRenglon(int r, int traga) {
        if (r >= 0 && r < filas) {
            int limite = Math.min(traga, columnas);
            for (int x = 0; x < limite; x++) {
                jardin[r][x] = 'C';
            }
        }
    }

    @Override
    public void run() {
        int ini = (int) (Math.random() * 100);

        while (quedaJardin()) {
            try {
                int columnaActual = ini % columnas;
                synchronized (jardin) {
                    caminaColumna(columnaActual);
                    ini++;
                }

                sleep(tiempoEspera);

                int renglonActual = ini % filas;
                int tragaRenglon = 3;

                synchronized (jardin) {
                    comerRenglon(renglonActual, tragaRenglon);
                    ini++;
                }
              
                sleep(tiempoEspera);

            } catch (InterruptedException e) {
                System.out.println("Interrupcion");
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("Gusano finalizado.");
    }
}
        }
        System.out.println("Un gusano ha terminado su trabajo (murió).");
    }
}

