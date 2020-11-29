/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Laberintos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author vic_s
 */
public class Busqueda {
    
    public static int id = 0;

    public ArrayList<Nodo> busquedaSolucion(Problema problema, int profundidad, int estrategia) {
        ArrayList<Celda> visitados = new ArrayList<>();
        ArrayList<Nodo> frontera = new ArrayList<>();

        Celda estado = problema.getInicial();
        String accion = "none"; //la accion del nodo N,E,S,O
        int profundidadNodo = 0;
        int costo_acumulado = 0;
        int heuristica = heuristica(problema, problema.getInicial());
        Nodo nodo = new Nodo(id, estado, null, accion, profundidadNodo, costo_acumulado, heuristica);
        System.out.println("Nodo inicial:" + nodo.toString());
        System.out.println("Nodo final:" + problema.getObjetivo().toString());
        double valor = calcula(estrategia, nodo);
        nodo.setValor(valor);

        frontera.add(nodo);

        boolean solucion = false;
        while (!frontera.isEmpty() && !solucion) {

            nodo = frontera.get(0); //Esto hay que revisarlo
            frontera.remove(0);

            if (problema.isObjetivo(nodo.getEstado())) {
                solucion = true;
            } else if ((!visitados.contains(nodo.getEstado())) && (nodo.getProfundidad() < profundidad)) {

                //echarle un ojito
                ArrayList<Nodo> lista_hijos_nodo = expandir_nodo(problema, nodo, estrategia, visitados);

                for (int i = 0; i < lista_hijos_nodo.size(); i++) {
                    Nodo aux = lista_hijos_nodo.get(i);

                    frontera.add(aux);
                    Collections.sort(frontera, new comparadorNodo());

                }
                frontera = podar(frontera, visitados);

            }
        }
        if (solucion) {
            System.out.println("Resuelto");
            return devolver_camino(nodo);
        } else {
            System.out.println("No tiene solucion");
            return null;
        }
    }

    private ArrayList<Nodo> devolver_camino(Nodo nodo) {
        ArrayList<Nodo> camino = new ArrayList<>();
        while (nodo != null) {
            System.out.println(nodo.toString() + "\n");
            camino.add(nodo);
            nodo = nodo.getPadre();

        }

        return camino;
    }

    private ArrayList<Nodo> expandir_nodo(Problema problema, Nodo actual, int estrategia, ArrayList<Celda> visitados) {

        ArrayList<Nodo> lnodo = new ArrayList<>();

        Celda[][] laberinto = problema.getLaberinto();

        Nodo padre = actual;

        Celda nEstado = actual.getEstado();

        visitados.add(nEstado);
        int fila = nEstado.getFila();
        int columna = nEstado.getColumna();
        int coste = actual.getCosto();
        int superficie = 0;
        int profundidad = actual.getProfundidad();
        int heuristica = 0;
        float valor = 0;
        boolean[] vecinos = laberinto[nEstado.getFila()][nEstado.getColumna()].getVecinos();

        Random r = new Random();
        Nodo hijo;

        // cambiar a expandido solo el padre
        for (int i = 0; i < vecinos.length; i++) { //Recorrer hijos del padre

            if (vecinos[i]) {

                switch (i) {
                    case 0:

                        superficie =laberinto[fila - 1][columna].getSuperficie();
                                        //(id, estado,                      padre, accion, profundidad,      costo,           heuristica)
                        hijo = new Nodo(++id, laberinto[fila - 1][columna], padre, "N", profundidad + 1, coste + superficie + 1, heuristica);
                        heuristica = heuristica(problema, hijo.getEstado());
                        hijo.setHeuristica(heuristica);
                        valor = calcula(estrategia, hijo);
                        hijo.setValor(valor);
                        lnodo.add(hijo);
                        break;
                    case 1:
                        superficie =laberinto[fila][columna + 1].getSuperficie();
                        hijo = new Nodo(++id, laberinto[fila][columna + 1], padre, "E", profundidad + 1, coste + superficie + 1, heuristica);
                        heuristica = heuristica(problema, hijo.getEstado());
                        hijo.setHeuristica(heuristica);
                        valor = calcula(estrategia, hijo);
                        hijo.setValor(valor);
                        lnodo.add(hijo);
                        break;

                    case 2:
                        superficie = laberinto[fila + 1][columna].getSuperficie();
                        hijo = new Nodo(++id, laberinto[fila + 1][columna], padre, "S", profundidad + 1, coste + superficie + 1, heuristica);
                        heuristica = heuristica(problema, hijo.getEstado());
                        hijo.setHeuristica(heuristica);
                        valor = calcula(estrategia, hijo);
                        hijo.setValor(valor);
                        lnodo.add(hijo);
                        break;

                    case 3:
                        superficie = laberinto[fila][columna - 1].getSuperficie();
                        hijo = new Nodo(++id, laberinto[fila][columna - 1], padre, "O", profundidad + 1, coste + superficie + 1, heuristica);
                        heuristica = heuristica(problema, hijo.getEstado());
                        hijo.setHeuristica(heuristica);
                        valor = calcula(estrategia, hijo);
                        hijo.setValor(valor);
                        lnodo.add(hijo);
                        break;

                }
            }
        }
        
        Collections.sort(lnodo, new comparadorNodo());
        return lnodo;
    }

    private float calcula(int estrategia, Nodo nodo) {
        float valor = 0;
        switch (estrategia) {
            case 1: // Profundidad primero en anchura
                valor = nodo.getProfundidad();
                break;
            case 2: // Profundidad primero en profundidad
                float abajo = 1 + nodo.getProfundidad();
                valor = 1 / abajo;
                break;
            case 3: // Costo uniforme
                valor = nodo.getCosto();
                break;
            case 4: // Voraz
                valor = nodo.getHeuristica();
                break;
            case 5: // A*
                valor = nodo.getHeuristica() + nodo.getCosto();
                break;
        }
        return valor;
    }

    private int heuristica(Problema problema, Celda celda) {
        return Math.abs(celda.getFila() - problema.getObjetivo().getFila()) + Math.abs(celda.getColumna() - problema.getObjetivo().getColumna());
    }

    private ArrayList<Nodo> podar(ArrayList<Nodo> frontera, ArrayList<Celda> visitados) {
        for (int i = 0; i < frontera.size(); i++) {
            Nodo aux = frontera.get(i);
            if (visitados.contains(aux.getEstado())) {
                frontera.remove(aux);
            }
        }
        return frontera;
    }

}
