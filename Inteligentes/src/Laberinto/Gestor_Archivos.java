package Laberintos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.*;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Gestor_Archivos {

    public Celda[][] leerJson(String ruta) {
        
        Main m=new Main();
        JsonParser parser = new JsonParser();
        Scanner sc= new Scanner(System.in);
        FileReader fr = null;
        try {
            fr = new FileReader(ruta);
        }  catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
            System.exit(1);
        } catch (IOException ex) {
            System.out.println("No se ha podido abrir el archivo");
            System.exit(1);
        } 
        JsonElement datos = parser.parse(fr);       
        
        JsonObject obj = datos.getAsJsonObject();
        
        int fila = obj.get("rows").getAsJsonPrimitive().getAsInt();
        int columna = obj.get("cols").getAsJsonPrimitive().getAsInt();
        m.setFila(fila);
        m.setColumna(columna);
        
        
        Celda[][] laberinto=new Celda[fila][columna];
        
        JsonObject cell = obj.get("cells").getAsJsonObject();
        for(int i=0; i< laberinto.length; i++){
            for(int j=0; j< laberinto[0].length;j++){
                Celda cel= new Celda(i,j);
                JsonObject celda = cell.get("("+i+", "+j+")").getAsJsonObject();
                int value = celda.get("value").getAsInt();
                if(value==1){
                    cel.setVisitado(true);
                } else if(value==0){
                    cel.setVisitado(false);
                }
                JsonArray neighbors= celda.get("neighbors").getAsJsonArray();
                boolean[] muros = new boolean [neighbors.size()];
                for(int k = 0; k <neighbors.size(); k++){
                    muros[k]=neighbors.get(k).getAsBoolean();
                }
                cel.setMuros(muros);
                laberinto[i][j]=cel;
            }
        }
        //m.setLaberinto(laberinto.clone());
        
        return laberinto;
    }

    /*Escritura del json, para la escritura no necesito la celda en si, es mas cada celda del resultado*/
    public static void escribirArchivoJson(String ruta) {
    Gson gson = new Gson(); //no s� si los parametros habr�a que pasarlos a string, o sea hacer la conversi�n
    ruta += "\\Resultado.json";
    Main main = new Main();
    JSONObject archivo = new JSONObject();
    JSONObject cells = obtenerCells();
    JSONArray id_movimiento = obtenerId();
    archivo.put("row", main.getFila());
    archivo.put("cols", main.getColumna());
    archivo.put("max_n", 4); 
    archivo.put("id_movimiento", id_movimiento);
    archivo.put("cells", cells);
    
    try {
    	FileWriter fichero2 = new FileWriter(ruta);
    	fichero2.write(archivo.toString());
    	fichero2.flush();
    	fichero2.close();
    	System.out.println("Fichero creado.");
    }catch(IOException ex) {
    	System.out.println("Error al escribir json");
    }
    
}
public static JSONArray obtenerId() {
	JSONArray movimiento = new JSONArray();
    JSONArray lista = new JSONArray();
    lista.put(-1);
    lista.put(0);
	movimiento.put(lista);
	lista = remove(lista);
	lista.put(0);
	lista.put(1);
	movimiento.put(lista);
	lista = remove(lista);
	lista.put(1);
	lista.put(0);
	movimiento.put(lista);
	lista = remove(lista);
	lista.put(0);
	lista.put(-1);
	movimiento.put(lista);
    return movimiento;
}
public static JSONArray remove(JSONArray lista) {
	for(int i=0; i< lista.length();i++) {
		lista.remove(i);
	}
	return lista;
}
public static JSONObject obtenerCells() {
	Main main = new Main();
	Wilson wilson = new Wilson(main.getFila(), main.getColumna());
	Celda[][] laberinto = wilson.getLaberinto();
	JSONObject cells = new JSONObject();
	for(int i=0; i<laberinto.length; i++) {
		for(int j=0; j<laberinto.length; j++) {
			JSONObject celda = new JSONObject();
			boolean[] interiorCelda = laberinto[i][j].getVecinos();
			celda.put("neighbors", interiorCelda);
			cells.put("("+String.valueOf(i)+","+String.valueOf(j)+")",celda);
		}
	}
	return cells;
}
}
