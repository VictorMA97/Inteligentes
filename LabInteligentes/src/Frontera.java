package Laberintos;

import java.util.PriorityQueue;

public class Frontera {

    private PriorityQueue<Nodo> lista;

    public Frontera() {
        setLista(new PriorityQueue<Nodo>());
    }

    public void insertar(Nodo c) {
        getLista().add(c);
    }

    public Nodo eliminar() {
        Nodo c = getLista().peek();
        getLista().remove(c);
        return c;
    }

    public boolean isEmpty() {
        return getLista().isEmpty();
    }

    public PriorityQueue<Nodo> getLista() {
        return lista;
    }

    @Override
	public String toString() {
		return "" + lista + "";
	}

	public void setLista(PriorityQueue<Nodo> lista) {
        this.lista = lista;
    }
}
