package laberinto;

public class main {
	
	int columna;
	int fila;
	Celda [][] laberinto;
	Celda [] vecinos;
	final String [] id_movimientos= {"N","E","S","O"};
	int [][] movimientos= {(-1,0),(0,1),(1,0),(0,-1)};
	
	public void iniciar() {
		int contador=0;
		laberinto = new Celda [fila][columna];
		for(int i=0; i<fila; i++) {
			for(int j=0; j<columna; j++) {
				contador++;
				laberinto[i][j]=new Celda(contador, i, j);
			}
		}
	}

	public static void main(String[] args) {
		

	}

}
