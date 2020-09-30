package laberinto;

public class Main {
	
	private int columnas;
	private int filas;
	private Celda[][] laberinto;
	
	public Main() {
		laberinto=new Celda[filas][columnas];
	}
       
	public int getFilas() {
		return filas;
	}
	public void setFilas(int filas) {
		this.filas = filas;
	}
	public int getColumnas() {
		return columnas;
	}
	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}
	public Celda[][] getLaberinto() {
		return laberinto;
	}
	public void setLaberinto(Celda[][] laberinto) {
		this.laberinto = laberinto;
	}
	

}
