package Laberintos;

import java.util.Random;

public class Nodo implements Comparable<Nodo> {

    private Nodo padre;
    private Celda estado;
    private int id;
    private int costo;
    private char accion;
    private int profundidad;
    private int heuristica;
    private double valor;

    public Nodo(Nodo padre, Celda estado, int id, int costo, int profundidad, int heuristica, int estrategia) {
        Random r = new Random();
        this.padre = padre;
        this.estado = estado;
        this.id = id;
        int superficie = r.nextInt(3) + costo;
        this.costo = costo + superficie;
        this.profundidad = profundidad;
        this.heuristica = heuristica;
        setValor(estrategia);
    }

    @Override
    public String toString() {
        // ('N', (6, 2), 1)
        return "[" + id + "][" + costo + "," + estado + "," + padre.getId() + "," + accion + "," + profundidad + "," + heuristica + "," + valor + "]";
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

    private void setValor(int estrategia) {
        //this.f = f;
        switch (estrategia) {
            case 1: // Profundidad primero en anchura
                valor = getProfundidad();
                break;
            case 2: // Profundidad primero en profundidad
                float abajo = 1 + getProfundidad();
                valor = 1 / abajo;
                break;
            case 3: // Costo uniforme
                valor = getCosto();
                break;
            case 4: // Voraz
                valor = heuristica;
                break;
            case 5: // A*
                valor = heuristica + costo;
                break;
        }
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
        if (n.getValor() > getValor()) {

            i = -1;

        }
        if (n.getValor() < getValor()) {

            i = 1;

        }
        if (n.getValor() == getValor()) {

            i = 0;

            if (celda.getFila() < estado.getFila()) {
                i = 1;
            } else if (celda.getFila() > estado.getFila()) {
                i = -1;
            } else if (celda.getFila() == estado.getFila()) {
                i = 0;

                if (celda.getColumna() < estado.getColumna()) {
                    i = 1;
                } else if (celda.getColumna() > estado.getColumna()) {
                    i = -1;
                } else if (celda.getColumna() == estado.getColumna()) {
                    i = 0;

                    if (n.getId() < getId()) {
                        i = 1;
                    } else if (n.getId() > getId()) {
                        i = -1;
                    } else if (n.getId() == getId()) {
                        i = 0;
                    }
                }
            }
        }

        return i;
    }

    void setTipo(int tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
