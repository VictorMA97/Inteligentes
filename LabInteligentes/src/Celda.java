package Laberintos;

public class Celda {

    private int fila, columna;

    private boolean visitado;

    private boolean expandido;

    private boolean[] neighbors = {false, false, false, false};
    
    private int superficie;

    public Celda(int x, int y) {
        visitado = false;
        fila = x;
        columna = y;
        expandido = false;

    }

    public boolean isExpandido() {
        return expandido;
    }

    public void setExpandido(boolean expandido) {
        this.expandido = expandido;
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

    public boolean[] getVecinos() {
        return neighbors;
    }

    public void setVecinos(boolean[] vecinos) {
        this.neighbors = vecinos;
    }
    
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getSuperficie() {
        return superficie;
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

    @Override
    public String toString() {
        return "("+fila+", "+columna+")";

    }

}
