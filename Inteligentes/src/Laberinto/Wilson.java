package Laberintos;

import java.util.*;

public class Wilson {

    private final int columnas;
    private final int filas;
    private Celda[][] laberinto;
    private Stack<Celda> stack = new Stack<>();
    private Celda actual = new Celda(0, 0);
    private final Random r = new Random();

    public Wilson(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
    }

    public Celda[][] inicializar_laberinto() {

        laberinto = new Celda[filas][columnas];
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                laberinto[i][j] = new Celda(i, j);
            }
        }
        return laberinto;
    }

    public void algoritmoWilson() {
        inicializar_laberinto();
        int contadorEnc = 0;
        int opcion;
        do {
            System.out.println("\nFila: " + actual.getFila() + " Columna: " + actual.getColumna());
            laberinto[0][0].setVisitado(true);
            for (int i = 0; i < laberinto.length; i++) {
                System.out.println();
                for (int j = 0; j < laberinto.length; j++) {

                    System.out.print(laberinto[i][j].isVisitado() + " ");
                }

            }

            boolean caminoEncontrado = false;
            boolean inicio = true;
            boolean recoloque = false;

            if (caminoEncontrado && inicio) {
                actual = laberinto[r.nextInt(filas)][r.nextInt(columnas)];
                actual.setVisitado(true);
                stack.add(actual);
                inicio = false;
            }

            opcion = 0;

            if ((actual.getFila() == 0 || actual.getFila() == laberinto.length - 1)
                    || (actual.getColumna() == 0 || actual.getColumna() == laberinto.length - 1)) {
                opcion = controlarEsquinas();

                System.out.println("hola opcion: " + opcion);
                System.out.println("\nFila: " + actual.getFila() + " Columna: " + actual.getColumna());

            } else {

                do {
                    opcion = r.nextInt(5);
                } while (opcion < 1);
                System.out.println("hola2 opcion: " + opcion);

            }
            System.out.println("opciondespues: " + opcion);
            switch (opcion) {

                case 1:
                    actual = actual.irNorte(actual, laberinto);
                    if (existe(actual, stack, opcion, laberinto)) {
                        actual = recolocar(actual, laberinto, opcion);
                        recoloque = true;
                        if (recoloque) {
                            ++contadorEnc;

                        }
                    } else {
                        stack.add(actual);
                    }
                    System.out.println(stack);
                    break;

                case 2:
                    actual = actual.irEste(actual, laberinto);
                    if (existe(actual, stack, opcion, laberinto)) {
                        actual = recolocar(actual, laberinto, opcion);
                        recoloque = true;
                        if (recoloque) {
                            ++contadorEnc;

                        }

                    } else {
                        stack.add(actual);
                    }
                    System.out.println(stack);
                    break;

                case 3:
                    actual = actual.irSur(actual, laberinto);
                    if (existe(actual, stack, opcion, laberinto)) {
                        actual = recolocar(actual, laberinto, opcion);
                        recoloque = true;
                        if (recoloque) {
                            ++contadorEnc;

                        }

                    } else {
                        stack.add(actual);
                    }
                    System.out.println(stack);
                    break;

                case 4:
                    actual = actual.irOeste(actual, laberinto);
                    if (existe(actual, stack, opcion, laberinto)) {
                        actual = recolocar(actual, laberinto, opcion);
                        recoloque = true;
                        if (recoloque) {
                            ++contadorEnc;

                        }
                    } else {
                        stack.add(actual);
                    }
                    System.out.println(stack);
                    break;
                default:
                    break;

            }

            if (actual.isVisitado() == false || recoloque) {
                actual.setVisitado(true);
                if (recoloque) {
                    System.out.println("\nrecoloque\n");
                    if (contadorEnc == 5) {

                        contadorEnc = 0;
                        stack = vaciarPila(stack);
                    }
                }
            } else {
                System.out.println("\nprueba\n");
                caminoEncontrado = true;
                stack.clear();
                do {
                    actual = laberinto[r.nextInt(filas)][r.nextInt(columnas)];
                } while (actual.isVisitado());
                actual.setVisitado(true);
                stack.add(actual);
            }

        } while (!laberintoCompleto(laberinto));
    }

    private Stack<Celda> vaciarPila(Stack<Celda> pila) {
        Celda aux;

        while (!pila.isEmpty()) {

            aux = stack.pop();
            aux.setVisitado(false);

        }
        return pila;

    }

    public boolean existe(Celda actual, Stack<Celda> camino, int opcion, Celda[][] lab) {
        boolean existe = false;
        Celda aux;

        Iterator<Celda> it = camino.iterator();
        while (it.hasNext()) {
            System.out.println("hola: " + camino);
            aux = it.next();
            if (aux.getFila() == actual.getFila() && aux.getColumna() == actual.getColumna()) {

                existe = true;

                break;

            }

        }

        return existe;
    }

    public Celda recolocar(Celda actual, Celda[][] lab, int opcion) {

        if (opcion == 1) {
            actual = actual.irSur(actual, lab);
        } else if (opcion == 2) {
            actual = actual.irOeste(actual, lab);
        } else if (opcion == 3) {
            actual = actual.irNorte(actual, lab);
        } else if (opcion == 4) {
            actual = actual.irEste(actual, lab);
        }
        return actual;
    }

    public int controlarEsquinas() {

        int opcion = 0;

        if (actual.getFila() == 0 && actual.getColumna() == 0) { // esquina superior izquierda
            do {
                opcion = r.nextInt(5);

            } while (opcion < 2 || opcion > 3);

        } else if (actual.getFila() == laberinto.length - 1 && actual.getColumna() == 0) { // esquina inferior izquierda
            do {
                opcion = r.nextInt(5);

            } while (opcion < 1 || opcion > 2);

        } else if (actual.getFila() == 0 && actual.getColumna() == laberinto.length - 1) { // esquina superior derecha
            do {
                opcion = r.nextInt(5);

            } while (opcion < 3 || opcion > 4);

        } else if (actual.getFila() == laberinto.length - 1 && actual.getColumna() == laberinto.length - 1) { // esquina
            // //
            // inferior
            // derecha
            do {
                opcion = r.nextInt(5);

            } while (opcion != 1 && opcion != 4);

        } else {
            opcion = controlarLados();
        }
        return opcion;

    }

    public int controlarLados() {

        int opcion = 0;

        if (actual.getColumna() == 0) { // lado izquierdo
            do {
                opcion = r.nextInt(5);

            } while (opcion == 4 || opcion == 0);

        } else if (actual.getFila() == 0) { // lado superior
            do {
                opcion = r.nextInt(5);

            } while (opcion == 1 || opcion == 0);

        } else if (actual.getColumna() == laberinto.length - 1) { // lado derecho
            do {
                opcion = r.nextInt(5);

            } while (opcion == 2 || opcion == 0);

        } else if (actual.getFila() == laberinto.length - 1) { // lado inferior
            do {
                opcion = r.nextInt(5);

            } while (opcion == 3 || opcion == 0);

        }
        return opcion;

    }

    public boolean laberintoCompleto(Celda[][] laberinto) {

        boolean laberintoCompleto = true;

        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[i].length; j++) {
                if (!laberinto[i][j].isVisitado()) {
                    laberintoCompleto = false;
                }
            }
        }
        return laberintoCompleto;
    }

    public Celda[][] getLaberinto() {
        return laberinto;
    }

    public void setLaberinto(Celda[][] laberinto) {
        this.laberinto = laberinto;
    }
}
