
public class Sucesores {
	private String INITIAL;
	private String OBJETIVE;
	private String MAZE;
	Celda inicio;
	Celda fin;
	
	public Sucesores(Celda inicio,Celda fin) {
		this.inicio=inicio;
		this.fin=fin;
	}
	
	
	public Celda getInicio() {
		return inicio;
	}


	public void setInicio(Celda inicio) {
		this.inicio = inicio;
	}


	public Celda getFin() {
		return fin;
	}


	public void setFin(Celda fin) {
		this.fin = fin;
	}


	Sucesores (String nombre){
		Main nuevo = new Main();
		Gestor_Archivos gestor = new Gestor_Archivos();
		this.INITIAL = "("+nuevo.getCeldaInicio().getFila()+","+nuevo.getCeldaInicio().getColumna()+")";
		this.OBJETIVE = "("+nuevo.getCeldaFin().getFila()+","+nuevo.getCeldaFin().getColumna()+")";
		this.MAZE = nombre;
	}
}
