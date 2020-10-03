
import java.util.*;

public class Wilsonn {

	private int columnas = 4;
	private int filas = 4;
	private Celda[][] laberinto;
	private final Stack<Celda> stack = new Stack<Celda>();
	private Celda actual = new Celda(0, 0);
	private final Random r = new Random();

	public void inicializar_laberinto() {
		laberinto = new Celda[filas][columnas];
		for (int i = 0; i < laberinto.length; i++)
			for (int j = 0; j < laberinto[i].length; j++) {
				laberinto[i][j] = new Celda(i, j);
			}
	}

	public Wilsonn() {
		inicializar_laberinto();
		Celda celdaInicial = actual;

		celdaInicial.setVisitado(true);
		
		do {
			try {
				actual = laberinto[r.nextInt(filas)][r.nextInt(columnas)];
				stack.add(actual);
				int opcion = 0;

				do {

					if ((actual.getFila() == 0 || actual.getFila() == laberinto.length - 1)
							|| (actual.getColumna() == 0 || actual.getColumna() == laberinto.length - 1)) {
						opcion = controlarEsquinas();
						
					}else {
						
						do {
							opcion=r.nextInt(5);
						}while(opcion<1);
						
					}
						switch (opcion) {

						case 1:
							actual = actual.irNorte(actual, laberinto);
							stack.add(actual);
							break;

						case 2:
							actual = actual.irEste(actual, laberinto);
							stack.add(actual);
							break;

						case 3:
							actual = actual.irSur(actual, laberinto);
							stack.add(actual);
							break;

						case 4:
							actual = actual.irOeste(actual, laberinto);
							stack.add(actual);
							break;
						default:
							break;

						}
					
				} while (!actual.isVisitado());

			} catch (Exception e) {

				e.printStackTrace();
			}

			stack.clear();
		} while (!laberintoCompleto(laberinto));
	}

	public int controlarEsquinas() {

		int opcion = 0;

		if (actual.getFila() == 0 && actual.getColumna() == 0) { // esquina superior izquierda
			do {
				opcion = r.nextInt(5);

			} while (opcion < 2 || opcion > 3);

		} else if (actual.getFila() == laberinto.length - 1 && actual.getColumna() == 0) { // esquina inferior izquierda
			do {
				opcion = r.nextInt(5);

			} while (opcion < 1 || opcion > 2);

		} else if (actual.getFila() == 0 && actual.getColumna() == laberinto.length - 1) { // esquina superior derecha
			do {
				opcion = r.nextInt(5);

			} while (opcion < 3 || opcion > 4);

		} else if (actual.getFila() == laberinto.length - 1 && actual.getColumna() == laberinto.length - 1) { // esquina
																												// inferior
																												// derecha
			do {
				opcion = r.nextInt(5);

			} while (opcion != 1 || opcion != 4);

		}else {
			opcion=controlarLados();
		}
		return opcion;

	}

	public int controlarLados() {

		int opcion = 0;

		if (actual.getColumna() == 0) { // lado izquierdo
			do {
				opcion = r.nextInt(5);

			} while (opcion == 4);

		} else if (actual.getFila() == 0) { // lado superior
			do {
				opcion = r.nextInt(5);

			} while (opcion == 1);

		} else if (actual.getColumna() == laberinto.length - 1) { // lado derecho
			do {
				opcion = r.nextInt(5);

			} while (opcion == 2);

		} else if (actual.getFila() == laberinto.length - 1) { // lado inferior
			do {
				opcion = r.nextInt(5);

			} while (opcion == 3);

		}
		return opcion;

	}

	public boolean laberintoCompleto(Celda[][] laberinto) {

		boolean laberintoCompleto = true;

		for (int i = 0; i < laberinto.length; i++)
			for (int j = 0; j < laberinto[i].length; j++) {
				if (!laberinto[i][j].isVisitado()) {
					laberintoCompleto = false;
				}
			}

		return laberintoCompleto;

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