package Laberintos;

public class Nodo{

    private Nodo padre;
    private Celda estado;
    private int id;
    private int costo;
    private String accion;
    private int profundidad;
    private int heuristica;
    private double valor;

    public Nodo(int id, Celda estado, Nodo padre, String accion, int profundidad, int costo, int heuristica) {
        this.padre = padre;
        this.estado = estado;
        this.id = id;
        this.accion = accion;
        this.costo = costo;
        this.profundidad = profundidad;
        this.heuristica = heuristica;
    }

    Nodo() {

    }

    @Override
    public String toString() {
        // ('N', (6, 2), 1)
        //[id][cost,state,father_id,action,depth,h,value]
        //[0][0,(0, 0),None,None,0,198,0]

        if (padre == null) {
            return "[" + id + "][" + costo + ",(" + estado.getFila() + ", " + estado.getColumna() + ")," + "none" + "," + accion + "," + profundidad + "," + heuristica + "," + valor + "]";
        } else {
            return "[" + id + "][" + costo + ",(" + estado.getFila() + ", " + estado.getColumna() + ")," + padre.getId() + "," + accion + "," + profundidad + "," + heuristica + "," + valor + "]";
        }

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

    public void setAccion(String accion) {
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

    public String getAccion() {
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
}
