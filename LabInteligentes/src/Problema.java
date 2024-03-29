
public class Problema {

    private Celda inicial;
    private Celda objetivo;
    private Celda[][] laberinto;
    private String maze;

    public Problema(Celda inicial, Celda objetivo, Celda[][] laberinto) {
        this.inicial = inicial;
        this.objetivo = objetivo;
        this.laberinto = laberinto;
    }

    public Celda getInicial() {
        return inicial;
    }

    public Celda getObjetivo() {
        return objetivo;
    }

    public Celda[][] getLaberinto() {
        return laberinto;
    }

    public void setInicial(Celda inicial) {
        this.inicial = inicial;
    }

    public void setObjetivo(Celda objetivo) {
        this.objetivo = objetivo;
    }

    public void setLaberinto(Celda[][] laberinto) {
        this.laberinto = laberinto;
    }
    
    public void setMaze(String nombre) {
    	this.maze = nombre;
    }

    @Override
    public String toString() {
        return "{" + "INITIAL=" + inicial + ", OBJETIVE=" + objetivo+ ", MAZE "+ maze;
    }

    boolean isObjetivo(Celda estado) {
    	
    	if((estado.getFila()==objetivo.getFila())&&(estado.getColumna()==objetivo.getColumna())) {
    		return true;
    	}else {
    		return false;
    	}
        
    }
    
}