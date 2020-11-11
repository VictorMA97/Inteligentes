

public class Nodo implements Comparable<Nodo> {

    Nodo padre;
    Celda estado;
    int id;
    int costo;
    char accion;
    int profundidad;
    int heuristica;
    double valor;

    public Nodo(Nodo padre, Celda estado, int id, int costo, int profundidad, int heuristica, double valor) {
        this.padre = padre;
        this.estado = estado;
        this.id = id;
        this.costo = costo;
        this.profundidad = profundidad;
        this.heuristica = heuristica;
        this.valor = valor;
    }

    @Override
    public String toString() {
        // ('N', (6, 2), 1)
        return "('" + accion + "', (" + estado.getFila() + " , " + estado.getColumna() + "), 1)";
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void setEstado(Celda estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void setAccion(char accion) {
        this.accion = accion;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public void setHeuristica(int heuristica) {
        this.heuristica = heuristica;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Celda getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }

    public int getCosto() {
        return costo;
    }

    public char getAccion() {
        return accion;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getHeuristica() {
        return heuristica;
    }

    public double getValor() {
        return valor;
    }

    public int compareTo(Nodo n) {
        int i = 0;
        Celda celda = n.getEstado();
        if (n.getValor() < getValor()) {

            i = -1;

        }
        if (n.getValor() > getValor()) {

            i = 1;

        }
        if (n.getValor() == getValor()) {

            i = 0;

            if (celda.getFila() > estado.getFila()) {
                i = 1;
            } else if (celda.getFila() < estado.getFila()) {
                i = -1;
            } else if (celda.getFila() == estado.getFila()) {
                i = 0;

                if (celda.getColumna() > estado.getColumna()) {
                    i = 1;
                } else if (celda.getColumna() < estado.getColumna()) {
                    i = -1;
                } else if (celda.getColumna() == estado.getColumna()) {
                    i = 0;
                }
            }
        }

        return i;
    }

}
