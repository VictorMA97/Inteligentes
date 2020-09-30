package laberinto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Celda {
	
	private int id, fila, columna;
	
	private Celda pariente;
	
	private boolean visitado;
	private boolean camino;
	private boolean fin;
	
	private boolean [] muros= {true, true, true, true};
	
	public Celda(int x, int y) {
		visitado=false;
		fila=x;
		columna=y;
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

	public void setMuros(boolean [] muros) {
		this.muros = muros;
	}

	public Celda getPariente() {
		return pariente;
	}

	public void setPariente(Celda pariente) {
		this.pariente = pariente;
	}
	
	public void eliminarMuro (Celda c) {
		int x= fila-c.getFila(); //0=N, 1=E, 2=S, 3=O
		
		if(x == 1) {
			muros[3] = false;
			c.muros[1] = false;
		} else if (x == -1) {
			muros[1] = false;
			c.muros[3] = false;
		}
		
		int y = this.columna - c.getColumna();
		
		if(y == 1) {
			muros[0] = false;
			c.muros[2] = false;
		} else if (y == -1) {
			muros[2] = false;
			c.muros[0] = false;
		}
	}
	
	private Celda vecinoAleatorio (List<Celda> vecinos) {
		if (vecinos.size() > 0) {
			return vecinos.get(new Random().nextInt(vecinos.size()));
		} else {
			return null;
		}
	}
	
	private Celda validarVecino (List<Celda> grid, Celda vecino) {
		if (grid.contains(vecino)) {
			return grid.get(grid.indexOf(vecino));
		} else {
			return null;
		}
	}
	
	// Used for Wilson's algorithm
	public Celda getNonPathNeighbour(List<Celda> celdas) {

		List<Celda> vecinos = new ArrayList<>(4);
		
		Celda top = validarVecino(celdas, new Celda(fila, columna - 1));
		Celda right = validarVecino(celdas, new Celda(fila + 1, columna));
		Celda bottom = validarVecino(celdas, new Celda(fila, columna + 1));
		Celda left = validarVecino(celdas, new Celda(fila - 1, columna));
		
		if (top != null) if(!top.camino) vecinos.add(top);
		if (right != null) if(!right.camino) vecinos.add(right);
		if (bottom != null) if(!bottom.camino) vecinos.add(bottom);
		if (left != null) if(!left.camino) vecinos.add(left);
		
		if (vecinos.size() ==  1) {
			return vecinos.get(0);
		}
		return vecinoAleatorio(vecinos);
	}

	
}
