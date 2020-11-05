package Laberinto;

public class ContenidoCelda {
	private int value = 0;
	private boolean[] neighbors;
	
	ContenidoCelda(Celda celda){
		this.neighbors = celda.getVecinos();
	}
	
}
