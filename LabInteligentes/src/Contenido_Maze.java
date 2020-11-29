package Laberintos;


public class Contenido_Maze {
	private String INITIAL;
	private String OBJETIVE;
	private String MAZE;
	
	public Contenido_Maze(Celda inicio, Celda fin, String maze) {
		this.INITIAL = "("+inicio.toString()+")";
		this.OBJETIVE = "("+fin.toString()+")";
		this.MAZE = maze;
		// TODO Auto-generated constructor stub
	}

	public String getINITIAL() {
		return INITIAL;
	}

	public void setINITIAL(String iNITIAL) {
		INITIAL = iNITIAL;
	}

	public String getOBJETIVE() {
		return OBJETIVE;
	}

	public void setOBJETIVE(String oBJETIVE) {
		OBJETIVE = oBJETIVE;
	}

	public String getMAZE() {
		return MAZE;
	}

	public void setMAZE(String mAZE) {
		MAZE = mAZE;
	}
}
