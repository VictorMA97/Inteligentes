package Laberintos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Fichero {
	private int row;
	private int cols;
	private int max_n;
	private ArrayList mov;
	private ArrayList id_mov;
	private Map<String, ContenidoCelda> cells;
	
	public Fichero(Celda[][] laberinto){
		this.row = laberinto.length;
		this.cols = laberinto[0].length;
		this.max_n = 4;
		this.mov = obtenerId();
		this.id_mov = obtenerMov();
		this.cells = obtenerCelda(laberinto);
	}
	public ArrayList obtenerMov() {
		ArrayList id_mov = new ArrayList();
		id_mov.add("N");
		id_mov.add("E");
		id_mov.add("S");
		id_mov.add("O");
		return id_mov;	
	}
	
	public ArrayList<ArrayList<Integer>> obtenerId() {
		ArrayList<ArrayList<Integer>> mov = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(-1);
		a.add(0);
		mov.add(a);
		ArrayList<Integer> b = new ArrayList<Integer>();
		b.add(0);
		b.add(1);
		mov.add(b);
		ArrayList<Integer> c = new ArrayList<Integer>();
		c.add(1);
		c.add(0);
		mov.add(c);
		ArrayList<Integer> d = new ArrayList<Integer>();
		d.add(0);
		d.add(-1);
		mov.add(d);
		return mov;
	}
	public Map<String, ContenidoCelda> obtenerCelda(Celda[][] lab){
		Map<String, ContenidoCelda> cel = new LinkedHashMap<String, ContenidoCelda>();
		for(int i=0; i<lab.length; i++) {
			for(int j=0; j<lab[0].length; j++) {
				Celda celda = lab[i][j];
				ContenidoCelda cell = new ContenidoCelda(celda);
				cel.put("("+String.valueOf(i)+","+String.valueOf(j)+")", cell);
			}
		}
		return cel;
	}
}
