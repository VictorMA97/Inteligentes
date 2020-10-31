package Laberintos;

public class Nodo {
    
    Celda padre;
    Celda estado;
    int id;
    int costo;
    char accion;
    int profundidad;
    int heuristica;
    double valor; 

    public Nodo(Celda padre, Celda estado, int id, int costo, int profundidad, int heuristica, double valor) {
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
        return "Nodo{" + "padre=" + padre + ", estado=" + estado + ", id=" + id + ", costo=" + costo + ", accion=" + accion + ", profundidad=" + profundidad + ", heuristica=" + heuristica + ", valor=" + valor + '}';
    }

    public void setPadre(Celda padre) {
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

    public Celda getPadre() {
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
    
       
}
