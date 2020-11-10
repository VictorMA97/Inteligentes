import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

	private int fila, columna;
	private Celda[][] laberinto;
	private Celda cInicio;
	private Celda cFin;
	public static int id = -1;

	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}

	public void menu() {

		Scanner teclado = new Scanner(System.in);
		Gestor_Archivos ga = new Gestor_Archivos();
		boolean bucle = false;
		boolean seguir = true;
		String rut;
		rut = null;
		int option = 0;

		do {
			System.out.println("1.Generar laberinto.\n2.Leer fichero json.\n3.Salir.");
			System.out.println("Introduza la opcion del menu: ");
			try {
				option = teclado.nextInt();
				switch (option) {
				case 1:
					String ruta = ruta_json();
					pedir_datos();
					Wilson w = new Wilson(laberinto.length, laberinto[0].length);
					w.generar();
					System.out.println("Laberinto generado.");
					System.arraycopy(w.getLaberinto(), 0, laberinto, 0, laberinto.length);
					laberinto = w.getLaberinto();
					dibujar(ruta);
					ga.escribirArchivoJson(ruta, laberinto);
					funcionSucesora(laberinto);
					// bucle=false;
					break;
				case 2:
					rut = ruta_json();
					Celda[][] aux = ga.leerJson(rut);
					laberinto = aux; // Leer json.
					dibujar(rut);
					funcionSucesora(laberinto);
					bucle = false;
					break;

				case 3:
					seguir = false;
					break;
				default:
					System.err.println("Solo valores entre 1 y 3.");
					bucle = true;
				}
			} catch (NumberFormatException e) {
				System.err.println("Error solo datos numericos.");
				bucle = true;
			}
		} while (bucle);
		System.out.println("\nFin del programa");
	}

	public void funcionSucesora(Celda[][] laberinto) {

		Random r = new Random();
		Celda inicio, fin;
		Celda estado;
		do {
			inicio = laberinto[r.nextInt(laberinto.length)][r.nextInt(laberinto[0].length)];
			System.out.println(inicio.getFila() + " , " + inicio.getColumna());
			fin = laberinto[r.nextInt(laberinto.length)][r.nextInt(laberinto[0].length)];
		} while (inicio.equals(fin));
		Sucesores sucesores=new Sucesores(inicio,fin);
		System.out.println(fin.getFila() + " , " + fin.getColumna());
		// Celda padre, Celda estado, int id, int costo, int profundidad, int
		// heuristica, double valor
		Nodo nodo = new Nodo(null, inicio, ++id, 0, 0, 0, r.nextInt(101));
		Frontera frontera = new Frontera();
		frontera.insertar(nodo);
		Nodo aux = nodo;
		Nodo siguiente;
		boolean objetivo = false;

		while (!objetivo && !frontera.isEmpty()) {

			aux = frontera.eliminar();

			if (!aux.getEstado().isExpandido()) {

				System.out.print("\nSUC((" + aux.getEstado().getFila() + "," + aux.getEstado().getColumna() + "))=");
				expandir(aux, laberinto, frontera);

				if (funcionObjetivo(frontera, fin)) {
					objetivo = true;
				}
				// frontera.clear();
			} else {
				--id;
			}
			estado = laberinto[r.nextInt(laberinto.length)][r.nextInt(laberinto[0].length)];
			siguiente = new Nodo(null, estado, ++id, aux.getCosto() + 1, aux.getProfundidad() + 1,
					aux.getHeuristica() + 1, r.nextInt(101));
			frontera.insertar(siguiente);

		}

	}

	public boolean funcionObjetivo(Frontera frontera, Celda fin) {

		boolean objetivo = false;
		Celda aux;
		int fila = fin.getFila();
		int columna = fin.getColumna();

		while (!frontera.isEmpty()) {
			aux = frontera.eliminar().getEstado();
			if ((aux.getFila()) == fila && (aux.getColumna()) == columna) {
				objetivo = true;
			}
		}
		return objetivo;

	}

	public void expandir(Nodo actual, Celda[][] laberinto, Frontera frontera) {

		Celda estado = actual.getEstado();
		estado.setExpandido(true);
		int fila = estado.getFila();
		int columna = estado.getColumna();
		int coste = actual.getCosto();
		int profundidad = actual.getProfundidad();
		boolean[] vecinos = estado.getVecinos();
		Random r = new Random();
		Nodo n;
		// cambiar a expandido solo el padre
		for (int i = 0; i < vecinos.length; i++) {
			if (vecinos[i]) {

				switch (i) {
				case 0:

					id += 1;
					n = new Nodo(actual, laberinto[fila - 1][columna], ++id, coste + 1, profundidad + 1, coste + 1,
							r.nextInt(101));
					n.setAccion('N');
					frontera.insertar(n);
					

					break;
				case 1:

					id += 1;
					n = new Nodo(actual, laberinto[fila][columna + 1], ++id, coste + 1, profundidad + 1, coste + 1,
							r.nextInt(101));
					n.setAccion('E');
					frontera.insertar(n);
					
					break;

				case 2:

					id += 1;
					n = new Nodo(actual, laberinto[fila + 1][columna], ++id, coste + 1, profundidad + 1, coste + 1,
							r.nextInt(101));
					n.setAccion('S');
					frontera.insertar(n);
					
					break;

				case 3:

					id += 1;
					n = new Nodo(actual, laberinto[fila][columna - 1], ++id, coste + 1, profundidad + 1, coste + 1,
							r.nextInt(101));
					n.setAccion('O');
					frontera.insertar(n);
					
					break;

				}
			}
		}
		if (!frontera.isEmpty()) {
			System.out.print(frontera.getLista());
		}

	}

	private void pedir_datos() {
		Scanner teclado = new Scanner(System.in);
		boolean error = false;
		do {
			try {
				System.out.println("Introduce las filas que deseas:");
				fila = teclado.nextInt();
				System.out.println("Introduce las columnas que deseas:");
				columna = teclado.nextInt();

			} catch (Exception e) {
				System.out.println("Error solo datos numericos.");
				error = true;
			}
		} while (error);
		laberinto = new Celda[fila][columna];
	}

	private static String ruta_json() {
		Scanner teclado = new Scanner(System.in);
		boolean error = false;
		String ruta = "";
		do {
			try {
				System.out.println("Introduce la carpeta donde quieres guardar el json:");
				ruta = teclado.nextLine();
			} catch (Exception e) {
				System.out.println("Error en la ruta.");
				error = true;
			}
		} while (error);
		return ruta;
	}

	private void dibujar(String ruta) {
		int tamaño_celda = 10; // Tamaño de celda
		try {

			BufferedImage lienzo = new BufferedImage(laberinto[0].length * tamaño_celda + 5,
					laberinto.length * tamaño_celda + 5, BufferedImage.TYPE_4BYTE_ABGR); // Pasar aqui filas y columnas
			// del laberinto *100
			Graphics g = lienzo.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, laberinto[0].length * tamaño_celda + 3, laberinto.length * tamaño_celda + 3);
			g.setColor(Color.black);
			for (int i = 0; i < laberinto.length; i++) {
				for (int j = 0; j < laberinto[0].length; j++) {

					boolean[] c = laberinto[i][j].getVecinos();
					int fila = tamaño_celda * i;
					int columna = tamaño_celda * j;

					if (c[0] == false) {
						g.drawLine(columna, fila, columna + tamaño_celda, fila); // dibujar norte
					}

					if (c[1] == false) {
						g.drawLine(columna + tamaño_celda, fila, columna + tamaño_celda, fila + tamaño_celda); // dibujar
						// este
					}

					if (c[2] == false) {
						g.drawLine(columna + tamaño_celda, fila + tamaño_celda, columna, fila + tamaño_celda); // dibujar
						// sur
					}

					if (c[3] == false) {
						g.drawLine(columna, fila + tamaño_celda, columna, fila); // dibujar oeste
					}
				}
			}
			ruta += "\\puzzle_" + laberinto[0].length + "x" + laberinto.length + ".jpg";
			System.out.println(ruta);
			ImageIO.write(lienzo, "png", new File(ruta));
		} catch (IOException ex) {
			System.err.println("Error en el buffer al dibujar");
		} catch (Exception e) {
			System.err.println("Error desconocido.");
		}
		System.out.println("Imagen generada correctamente.");
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public Celda[][] getLaberinto() {
		return laberinto;
	}

	public void setLaberinto(Celda[][] laberinto) {
		this.laberinto = laberinto;
	}
	public Celda getCeldaInicio() {
		return cInicio;
	}
	public Celda getCeldaFin() {
		return cFin;
	}
}
