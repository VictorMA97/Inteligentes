package Laberintos;

import java.util.List;
import java.util.Random;

public class Frontera {
    
    List<Nodo> arbol;
    Celda inicio, fin;
    
    public Frontera(Celda[][] lab){
        Random r=new Random();
        inicio=lab[r.nextInt(lab.length-1)][r.nextInt(lab.length-1)];
        fin=lab[r.nextInt(lab.length-1)][r.nextInt(lab.length-1)]; //Que no sean las mismas
    }
    
    public List<Nodo> busqueda_arbol(){
        List<Nodo> frontera = null;
        int id=0;
        int costo=1;
        int profundidad=0;
        int heuristica=costo;
        double valor=1.0; 
        Nodo ini= new Nodo(null, inicio, id, costo, profundidad, heuristica, valor);
        int fila_fin=fin.getFila();
        int col_fin=fin.getColumna();
        
        return frontera;
    }
    /*
    function Tree-Search(problem, strategy) returns a solution, or failure
        initialize the search tree using the initial state of problem 
        loop do
            if there are no candidates for expansion 
                then return failure choose a leaf node for expansion according to strategy 
            if the node contains a goal state 
                then return the corresponding solution
            else expand the node and add the resulting nodes to the search tree
        end
    */
    
}
