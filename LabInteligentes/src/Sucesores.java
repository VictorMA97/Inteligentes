
public class Sucesores {
	private String INITIAL;
	private String OBJETIVE;
	private String MAZE;
	
	Sucesores (String nombre){
		Main nuevo = new Main();
		Gestor_Archivos gestor = new Gestor_Archivos();
		this.INITIAL = "("+nuevo.getCeldaInicio().getFila()+","+nuevo.getCeldaInicio().getColumna()+")";
		this.OBJETIVE = "("+nuevo.getCeldaFin().getFila()+","+nuevo.getCeldaFin().getColumna()+")";
		this.MAZE = nombre;
	}
}
