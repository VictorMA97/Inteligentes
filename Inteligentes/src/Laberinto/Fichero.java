package Laberinto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Fichero {
	private int row;
	private int cols;
	private int max_n;
	private ArrayList id_movimiento;
	private Map<String, String> cells;
	
	public Fichero(Celda[][] laberinto){
		this.row = laberinto.length;
		this.cols = laberinto[0].length;
		this.max_n = 4;
		this.id_movimiento = obtenerId();
		this.cells = obtenerCelda(laberinto);
	}
	
	public ArrayList obtenerId() {
		ArrayList id_movimiento = new ArrayList();
		ArrayList a = new ArrayList();
		a.add(-1);
		a.add(0);
		id_movimiento.add(a);
		a.clear();
		a.add(0);
		a.add(1);
		id_movimiento.add(a);
		a.clear();
		a.add(1);
		a.add(0);
		id_movimiento.add(a);
		a.clear();
		a.add(0);
		a.add(-1);
		id_movimiento.add(a);
		System.out.println(id_movimiento);
		return id_movimiento;
	}
	public Map<String, String> obtenerCelda(Celda[][] lab){
		Map<String, String> cel = new LinkedHashMap<String, String>();
		//Map<String, String> fafa = new HashMap<String, String>();
		for(int i=0; i<lab.length; i++) {
			for(int j=0; j<lab[0].length; j++) {
				Celda celda = lab[i][j];
				cel.put("("+String.valueOf(i)+","+String.valueOf(j)+")", celda.toString());
			}
		}
		return cel;
	}
}
