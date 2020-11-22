package Laberintos;

import java.util.ArrayList;
import java.util.Stack;

public class Busqueda {

    public ArrayList busqueda() {
        ArrayList solucion = new ArrayList<>();
        
        Stack visitado =new Stack();

        return solucion;
    }

    /*    BUSQUEDA(Problema, profundidad, estrategia)


: solución

visitado = vacio
frontera = frontera vacia

#Nodo Inicial
nodo = crea nodo
nodo.padre = nadie
nodo.estado = Problema.EstadoInicial
nodo.costo = 0
nodo.profundidad = 0
nodo.acción = Ninguna
nodo.heurística = Heuristica(Problema, nodo.estado)
nodo.valor = calcula(estrategia,nodo)

insertar nodo en frontera

solución = Falso

Mientras (frontera no es vacia) y (no hay solución) hacer

    nodo = frontera.primer_elemento()
    
    Si Problema.objetivo(nodo.estado) entonces
        solución = Verdad
    Sino Si (nodo.estado no está en visitado) y (nodo.profundidad < profundidad) entonces
        insertar nodo.estado en visitados
        lista_de_nodos_hijos = EXPANDIR_NODO(Problema, nodo, estrategia)
        Para cada nodo_hijo en lista_de_nodos hacer
            insertar nodo_hijo en frontera
Si solución entonces
    devolver camino(nodo)
si no
    devolver no hay solución




EXPANDIR_NODO(Problema, nodo, estrategia): Lista de nodos

crear lista de nodos

Para cada sucesor (acción,estado,costo) en Problema.sucesores(nodo.estado) hacer
    crear nodoHijo
    nodoHijo.estado = estado
    nodoHijo.padre = nodo
    nodoHijo.acción = acción
    nodoHijo.profundidad = nodo.profundidad + 1
    nodoHijo.costo = nodo.costo + costo
    nodoHijo.heuristica = Heurística(Problema,estado)
    nodoHijo.valor = calcula(estrategia,nodoHijo)
    insertar nodoHijo en lista de nodos

devolver lista de nodos
    
     */
}
