package Laberinto;

public class ContenidoFichero {
	int row;
	int cols;
	int max_vecinos;
	int[][] id_movimiento;
	Celda celda;
	boolean otroElementoNoEspecificado;
	ContenidoFichero(int numFilas, int numColumnas, int max_vecinos2, int[][] id_movimiento2, Celda celda2){
		otroElementoNoEspecificado = false;
	}
	Celda getCelda() {
		return celda;
	}
}

