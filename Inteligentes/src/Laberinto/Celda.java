
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Celda {

	private int id, fila, columna;

	private Celda pariente;

	private boolean visitado;
	private boolean camino;
	private boolean fin;

	private boolean[] muros = { true, true, true, true };

	public Celda(int x, int y) {
		visitado = false;
		fila = x;
		columna = y;

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

	public boolean isCamino() {
		return camino;
	}

	public void setCamino(boolean camino) {
		this.camino = camino;
	}

	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public boolean[] getMuros() {
		return muros;
	}

	public void setMuros(boolean[] muros) {
		this.muros = muros;
	}

	public Celda getPariente() {
		return pariente;
	}

	public void setPariente(Celda pariente) {
		this.pariente = pariente;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		if (fila != other.fila)
			return false;
		if (columna != other.columna)
			return false;
		return true;
	}

	public void completar(Celda actual, Celda[][] lab) {

		Celda aux;
		boolean[] muros = actual.getMuros();
		int fila = actual.getFila();
		int columna = actual.getColumna();
		if ((fila == 0 || fila == lab.length - 1) || (columna == 0 || columna == lab.length - 1)) {

		} else {
			if (muros[0] == false) {
				aux = lab[fila + 1][columna];

				muros = aux.getMuros();
				muros[2] = false;

			} else if (muros[1] == false) {
				aux = lab[fila][columna + 1];

				muros = aux.getMuros();
				muros[3] = false;

			} else if (muros[2] == false) {
				aux = lab[fila - 1][columna];

				muros = aux.getMuros();
				muros[0] = false;

			} else if (muros[3] == false) {
				aux = lab[fila][columna - 1];

				muros = aux.getMuros();
				muros[1] = false;

			}
		}
	}

	public Celda irNorte(Celda actual, Celda[][] lab) {
		Random r = new Random();
		int muro = 0;
		int paredes = 0;
		boolean[] muros = actual.getMuros();
	boolean seguir=true;
		
		for(int i = 0; i < 4;i++) {
			if(muros[i]==false) {
				seguir=false;
			}
		}
		
		if(seguir) {
		for (int i = 0; i < 4; i++) {

			muro = r.nextInt(2);
			if (paredes == 1) {
				break;
			}

			switch (muro) {
			case 0:
				muros[i] = false;
				++paredes;
				break;
			case 1:
				if(muros[i]==false) {
					break;
				}else {
				muros[i] = true;
				break;
				}
			}

		}
		}

		muros[0] = true;
		completar(actual, lab);
		int fila, columna;
		fila = actual.getFila();
		columna = actual.getColumna();

		actual = lab[fila - 1][columna];
		return actual;
	}

	public Celda irEste(Celda actual, Celda[][] lab) {
		Random r = new Random();
		int muro = 0;
		int paredes = 0;
		boolean[] muros = actual.getMuros();
		boolean seguir=true;
		
		for(int i = 0; i < 4;i++) {
			if(muros[i]==false) {
				seguir=false;
			}
		}
		
		if(seguir) {
		for (int i = 0; i < 4; i++) {

			muro = r.nextInt(2);
			if (paredes == 1) {
				break;
			}

			switch (muro) {
			case 0:
				muros[i] = false;
				++paredes;
				break;
			case 1:
				if(muros[i]==false) {
					break;
				}else {
				muros[i] = true;
				break;
				}
			}

		}
		}
		muros[1] = true;
		completar(actual, lab);
		
		int fila, columna;
		fila = actual.getFila();
		columna = actual.getColumna();

		actual = lab[fila][columna + 1];
		return actual;

	}

	public Celda irSur(Celda actual, Celda[][] lab) {
		Random r = new Random();
		int muro = 0;
		int paredes = 0;
		boolean[] muros = actual.getMuros();
	boolean seguir=true;
		
		for(int i = 0; i < 4;i++) {
			if(muros[i]==false) {
				seguir=false;
			}
		}
		
		if(seguir) {
		for (int i = 0; i < 4; i++) {

			muro = r.nextInt(2);
			if (paredes == 1) {
				break;
			}

			switch (muro) {
			case 0:
				muros[i] = false;
				++paredes;
				break;
			case 1:
				if(muros[i]==false) {
					break;
				}else {
				muros[i] = true;
				break;
				}
			}

		}
		}

		muros[2] = true;
		completar(actual, lab);
		int fila, columna;
		fila = actual.getFila();
		columna = actual.getColumna();

		actual = lab[fila + 1][columna];
		return actual;

	}

	public Celda irOeste(Celda actual, Celda[][] lab) {
		Random r = new Random();
		int muro = 0;
		int paredes = 0;
		boolean[] muros = actual.getMuros();

	boolean seguir=true;
		
		for(int i = 0; i < 4;i++) {
			if(muros[i]==false) {
				seguir=false;
			}
		}
		
		if(seguir) {
		for (int i = 0; i < 4; i++) {

			muro = r.nextInt(2);
			if (paredes == 1) {
				break;
			}

			switch (muro) {
			case 0:
				muros[i] = false;
				++paredes;
				break;
			case 1:
				if(muros[i]==false) {
					break;
				}else {
				muros[i] = true;
				break;
				}
			}

		}
		}

		muros[3] = true;
		completar(actual, lab);
		int fila, columna;
		fila = actual.getFila();
		columna = actual.getColumna();

		actual = lab[fila][columna - 1];
		return actual;

	}

	@Override
	public String toString() {
		return "(" + fila + ", " + columna + ") { vecinos: " + Arrays.toString(muros) + "}";

	}

}
