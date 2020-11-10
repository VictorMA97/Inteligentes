package Laberintos;

public class Sucesores {

    private String INITIAL;
    private String OBJETIVE;
    private String MAZE;
    Celda inicio;
    Celda fin;

    public Celda getInicio() {
        return inicio;
    }

    public void setInicio(Celda inicio) {
        this.inicio = inicio;
    }

    public Celda getFin() {
        return fin;
    }

    public void setFin(Celda fin) {
        this.fin = fin;
    }

    public void nombreArchivo(String nombre) {
        this.MAZE = nombre;
    }

    public void sucesores(Celda inicio, Celda fin) {
        Main nuevo = new Main();
        //Gestor_Archivos gestor = new Gestor_Archivos();
        int fila = inicio.getFila();
        int columna = inicio.getColumna();
        this.INITIAL = "(" + fila + ", " + columna + ")";
        this.OBJETIVE = "(" + fila + ", " + columna + ")";
        //this.MAZE = nombre;
    }
}
