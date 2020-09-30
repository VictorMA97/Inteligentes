package laberinto;

import java.util.Scanner;

public class main {
	
	private int columna;
	private int fila;
	private Celda [][] laberinto;
	private Celda [] vecinos;
	private final String [] id_movimientos= {"N","E","S","O"};
	
	public main() {
		int contador=0;
		pedir_datos();
		laberinto = new Celda [fila][columna];
		for(int i=0; i<fila; i++) {
			for(int j=0; j<columna; j++) {
				contador++;
				laberinto[i][j]=new Celda(contador, i, j);
			}
		}
	}
	public void pedir_datos() {
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Introduzca el numero de columnas que quiera: ");
			columna = sc.nextInt();
			System.out.println("Introduzca el numero de filas que quiera: ");
			fila = sc.nextInt();
		}catch(Exception e) {
			System.out.println("Error al introducir el numero.");
			pedir_datos();
		}
		sc.close();
		
	}

	public static void main(String[] args) {
		new main();
	}

}
