package laberintos;

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
	
	private boolean [] muros= {true,true,true,true};
	
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

		
		
		public Celda irNorte(Celda actual , Celda[][] lab) {
			Random r=new Random();
			int muro=0;
			boolean[] muros=actual.getMuros();
			for(int i = 0; i<4; i++) {
				if (i==0) {
					muros[i]=false;
				}else {
					muro=r.nextInt(2);
					switch(muro) {
					case 0:
						muros[i]=false;
						break;
					case 1:
						muros[i]=true;
						break;
					}
				}
			}
			int fila, columna;
			fila=actual.getFila();
			columna=actual.getColumna();
			
			actual=lab[fila-1][columna];
			return actual;
		}

		public Celda irEste(Celda actual, Celda[][] lab) {
			Random r=new Random();
			int muro=0;
			boolean[] muros=actual.getMuros();
			for(int i = 0; i<4; i++) {
				if (i==1) {
					muros[i]=false;
				}else {
					muro=r.nextInt(2);
					switch(muro) {
					case 0:
						muros[i]=false;
						break;
					case 1:
						muros[i]=true;
						break;
					}
				}
			}
			int fila, columna;
			fila=actual.getFila();
			columna=actual.getColumna();
			
			actual=lab[fila][columna+1];
			return actual;
			
		}

		public Celda irSur(Celda actual, Celda[][] lab) {
			Random r=new Random();
			int muro=0;
			boolean[] muros=actual.getMuros();
			for(int i = 0; i<4; i++) {
				if (i==2) {
					muros[i]=false;
				}else {
					muro=r.nextInt(2);
					switch(muro) {
					case 0:
						muros[i]=false;
						break;
					case 1:
						muros[i]=true;
						break;
					}
				}
			}
			int fila, columna;
			fila=actual.getFila();
			columna=actual.getColumna();
			
			actual=lab[fila+1][columna];
			return actual;
			
		}

		public Celda irOeste(Celda actual, Celda[][] lab) {
			Random r=new Random();
			int muro=0;
			boolean[] muros=actual.getMuros();
			for(int i = 0; i<4; i++) {
				if (i==3) {
					muros[i]=false;
				}else {
					muro=r.nextInt(2);
					switch(muro) {
					case 0:
						muros[i]=false;
						break;
					case 1:
						muros[i]=true;
						break;
					}
				}
			}
			int fila, columna;
			fila=actual.getFila();
			columna=actual.getColumna();
		
			actual=lab[fila][columna-1];
			return actual;
			
		}
      
		@Override
		public String toString() {
			return "(" + fila + ", " + columna + ") { vecinos: " +  Arrays.toString(muros) + "}";
					
		}
        
}
