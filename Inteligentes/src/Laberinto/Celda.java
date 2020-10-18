package Laberintos;

import java.util.Arrays;
import java.util.Random;

public class Celda {

    private int fila, columna;

    private boolean visitado;

    private boolean[] muros = {false, false, false, false};

    public Celda(int x, int y) {
        visitado = false;
        fila = x;
        columna = y;

    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public boolean[] getMuros() {
        return muros;
    }

    public void setMuros(boolean[] muros) {
        this.muros = muros;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Celda other = (Celda) obj;
        if (fila != other.fila) {
            return false;
        }
        if (columna != other.columna) {
            return false;
        }
        return true;
    }

    public Celda irNorte(Celda[][] lab) {
        muros[0] = true;
        return lab[fila - 1][columna];
    }

    public Celda irEste(Celda[][] lab) {
        muros[1] = true;
        return lab[fila][columna + 1];

    }

    public Celda irSur(Celda[][] lab) {
        muros[2] = true;
        return lab[fila + 1][columna];

    }

    public Celda irOeste(Celda[][] lab) {
        muros[3] = true;
        return lab[fila][columna - 1];

    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ") { vecinos: " + Arrays.toString(muros) + "}";

    }

}
