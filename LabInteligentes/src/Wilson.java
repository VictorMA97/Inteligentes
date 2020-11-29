
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wilson {

    private int filas;
    private int columnas;

    private Celda[][] laberinto;

    public Wilson(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        init();
    }

    /**
     * Inicializa el arreglo que mantiene las casillas del laberinto
     */
    public final void init() {
        this.laberinto = new Celda[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                laberinto[i][j] = new Celda(i, j);
            }
        }
    }

    public void generar() {

        //Conjuntos usados en el algoritmo de generacion
        List<Celda> visitados = new ArrayList<Celda>();
        List<Celda> porVisitar = new ArrayList<Celda>();
        Random r = new Random();

        Celda fin = laberinto[r.nextInt(filas - 1)][r.nextInt(columnas - 1)];
        porVisitar.add(fin);

        int filaActual = 0, columnaActual = 0;
        Celda vecino = null;

        //Mientras queden nodos por visitar en la pila seguimos el recorrido
        while (porVisitar.size() != 0) {
            Celda actual = porVisitar.get(0); //Obtenemos la primera casilla de la pila para empezar el recorrido

            visitados.add(actual); //marcamos la casilla actual como visitada

            //int numero=actual.getNumero();
            filaActual = actual.getFila();
            columnaActual = actual.getColumna();

            vecino = actual;
            int mov = 0;
            while (visitados.contains(vecino) && mov <= 4) {

                int opcion = elegirOpcion(filaActual, columnaActual); //obtenemos la direccion del primer vecino de la casilla actual
                switch (opcion) {
                    case 0: //norte
                        vecino = laberinto[filaActual - 1][columnaActual];

                        mov++;
                        if (!visitados.contains(vecino)) {
                            boolean aux[] = actual.getVecinos();
                            aux[0] = true;
                            actual.setVecinos(aux);
                            boolean aux2[] = vecino.getVecinos();
                            aux2[2] = true;
                            vecino.setVecinos(aux2);
                            porVisitar.add(0, vecino); //agregamos el vecino elegido a la lista de nodos por visitar      
                        }

                        break;

                    case 1: //este
                        vecino = laberinto[filaActual][columnaActual + 1];

                        mov++;
                        if (!visitados.contains(vecino)) {
                            boolean aux[] = actual.getVecinos();
                            aux[1] = true;
                            actual.setVecinos(aux);
                            boolean aux2[] = vecino.getVecinos();
                            aux2[3] = true;
                            vecino.setVecinos(aux2);
                            porVisitar.add(0, vecino);
                        }
                        break;

                    case 2: //sur
                        vecino = laberinto[filaActual + 1][columnaActual];

                        mov++;
                        if (!visitados.contains(vecino)) {
                            boolean aux[] = actual.getVecinos();
                            aux[2] = true;
                            actual.setVecinos(aux);
                            boolean aux2[] = vecino.getVecinos();
                            aux2[0] = true;
                            vecino.setVecinos(aux2);
                            porVisitar.add(0, vecino);
                        }
                        break;

                    case 3: //oeste
                        vecino = laberinto[filaActual][columnaActual - 1];

                        mov++;
                        if (!visitados.contains(vecino)) {
                            boolean aux[] = actual.getVecinos();
                            aux[3] = true;
                            actual.setVecinos(aux);
                            boolean aux2[] = vecino.getVecinos();
                            aux2[1] = true;
                            vecino.setVecinos(aux2);
                            porVisitar.add(0, vecino);
                        }
                        break;

                }
                if (mov == 4) {
                    porVisitar.remove(actual);
                }
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Celda[][] getLaberinto() {
        return laberinto;
    }

    private int elegirOpcion(int fila, int columna) {
        Random r = new Random();
        int opcion = r.nextInt(4); //norte =0, este=1, sur=2, oeste=3
        boolean valido = false;
        while (!valido) {
            if (opcion == 0 && fila == 0) {
                opcion = r.nextInt(4);
            } else if (opcion == 1 && columna >= columnas - 1) {
                opcion = r.nextInt(4);
            } else if (opcion == 2 && fila >= filas - 1) {
                opcion = r.nextInt(4);
            } else if (opcion == 3 && columna == 0) {
                opcion = r.nextInt(4);
            } else {
                valido = true;
            }
        }
        return opcion;
    }
}
