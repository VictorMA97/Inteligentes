package Laberintos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Fichero {

    private int rows;
    private int cols;
    private int max_n;
    private ArrayList id_movimiento;
    private Map<String, Celda> cells;

    public Fichero(Celda[][] laberinto) {
        this.rows = laberinto.length;
        this.cols = laberinto[0].length;
        this.max_n = 4;
        this.id_movimiento = obtenerId();
        this.cells = obtenerCelda(laberinto);
    }

    public ArrayList<ArrayList<Integer>> obtenerId() {
        //ArrayList<ArrayList<Integer>> id_movimiento = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        a.add(-1);
        a.add(0);
        id_movimiento.add(a);
        ArrayList<Integer> b = new ArrayList<>();
        b.add(0);
        b.add(1);
        id_movimiento.add(b);
        ArrayList<Integer> c = new ArrayList<>();
        c.add(1);
        c.add(0);
        id_movimiento.add(c);
        ArrayList<Integer> d = new ArrayList<>();
        d.add(0);
        d.add(-1);
        id_movimiento.add(d);
        return id_movimiento;
    }

    public Map<String, Celda> obtenerCelda(Celda[][] lab) {
        //Map<String, Celda> cel = new LinkedHashMap<String, Celda>();

        for (int i = 0; i < lab.length; i++) {
            for (int j = 0; j < lab[0].length; j++) {
                Celda celda = lab[i][j];
                cells.put("(" + String.valueOf(i) + ", " + String.valueOf(j) + ")", celda);
            }
        }
        return cells;
    }
}
