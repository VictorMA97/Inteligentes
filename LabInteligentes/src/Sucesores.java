
public class Sucesores {

    private String INITIAL;
    private String OBJETIVE;
    private String MAZE;

    public void nombreArchivo(String nombre) {
        this.MAZE = nombre;
    }

    public void sucesores(Celda inicio, Celda fin) {
        this.INITIAL = "(" + inicio.getFila() + ", " + inicio.getColumna() + ")";
        this.OBJETIVE = "(" + fin.getFila() + ", " + fin.getColumna() + ")";
    }
    public String toString() {
		return "{"+"INITIAL: " + INITIAL + ", OBJETIVE: " + OBJETIVE + ", MAZE: " + MAZE + "}";
	}
}
