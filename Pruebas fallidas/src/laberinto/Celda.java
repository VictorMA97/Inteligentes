package laberinto;

public class Celda {
	
	private int id;
	private int fila;
	private int columna;
	private boolean visitado;
	private boolean [] vecinos=new boolean[4];
	
	public Celda(int id,int x, int y) {
		this.id=id;
		visitado=false;
		fila=x;
		columna=y;
	}
	
	public boolean isVisitado() {
		return visitado;
	}
	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public boolean [] getVecinos() {
		return vecinos;
	}

	public void setVecinos(boolean [] vecinos) {
		this.vecinos = vecinos;
	}
	
}
