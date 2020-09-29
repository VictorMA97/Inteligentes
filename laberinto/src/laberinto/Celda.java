package laberinto;

public class Celda {
	
	private int id;
	private boolean visitado;
	String [] vecinos=new String[4];
	String [] id_movimientos= new String[4];
	
	public Celda() {
		this.visitado=false;
		this.id_movimientos= {'N','E','S','O'};
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
	
}
