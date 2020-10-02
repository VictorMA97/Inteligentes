
import java.util.*;

public class Wilson {

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

	public Wilson() {
		inicializar_laberinto();
		Celda celdaInicial = actual;

		celdaInicial.setVisitado(true);
		boolean opcionCorrecta = false;
		do {
			try {
				actual = laberinto[r.nextInt(filas) * 100][r.nextInt(columnas) * 100];
				stack.add(actual);
				int opcion;
				do {

					opcion = r.nextInt(5);

				} while (opcion < 1);
				
				do {
				switch (opcion) {
				
				case 1:
					actual=actual.irNorte(actual,laberinto);
					stack.add(actual);
					break;

				case 2:
					actual=actual.irEste(actual,laberinto);
					stack.add(actual);
					break;

				case 3:
					actual=actual.irSur(actual,laberinto);
					stack.add(actual);
					break;

				case 4:
					actual=actual.irOeste(actual,laberinto);
					stack.add(actual);
					break;
				default:

				}
				}while(!actual.isVisitado());
					

			} catch (Exception e) {

				e.printStackTrace();
			}
			
			stack.clear();
		} while (!laberintoCompleto(laberinto));
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