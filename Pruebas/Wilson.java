package laberinto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
public class Wilson {
	
    private int columnas;
    private int filas;  
    private Celda[][] laberinto;
    private final Stack<Celda> stack = new Stack<Celda>();
    private Celda actual;
    private final Random r = new Random();
	
    public void inicializar_laberinto(){
        laberinto=new Celda[filas][columnas];
        for(int i=0; i<laberinto.length;i++)
            for(int j=0; j<laberinto[i].length;j++){
                laberinto[i][j]=new Celda(i,j);   
            }       
    }    
            
    public Wilson() {
        inicializar_laberinto();
        actual = laberinto[r.nextInt(filas)*100][r.nextInt(columnas)*100];
        actual.setVisitado(true);
        actual = laberinto[r.nextInt(filas)][r.nextInt(columnas)];
        for(int i=0;i<laberinto.length;i++){
            for(int j=0; j<laberinto[i].length;j++){
                if(!actual.isVisitado()){
                    carve();
                }else{
                    actual=null;
                }
            }           
        }  		
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

    private void carve() {	
        if (actual.isVisitado()) {
            addPathToMaze();
            List<Celda> notInMaze = new ArrayList<Celda>();
            for(int i=0;i<laberinto.length;i++){
                for(int j=0;j<laberinto[i].length;j++){
                    if(!laberinto[i][j].isVisitado()){
                        notInMaze.add(laberinto[i][j]);
                    }
                }
                
            }          
            if (!notInMaze.isEmpty()) {
                actual = notInMaze.get(r.nextInt(notInMaze.size()));							
            } else {
                return;
            }
	
        }
	
        actual.setCamino(true);
        Celda next = actual.getNonPathNeighbour(laberinto);
        if (next != null) {
            stack.push(actual);
            actual.eliminarMuro(next);
            actual = next;
        } else if (!stack.isEmpty()) {
            try {
                actual = stack.pop();
            } catch (Exception e) {
                e.printStackTrace();		
            }	
        }
    }
    private void addPathToMaze() {
        for(int i=0; i<laberinto.length;i++){
            for(int j=0; j<laberinto[j].length;j++){
                if(laberinto[i][j].isCamino()){
                    laberinto[i][j].setVisitado(true);
                    laberinto[i][j].setCamino(false);
                }
            }
        }
        stack.clear();	
    }	
}