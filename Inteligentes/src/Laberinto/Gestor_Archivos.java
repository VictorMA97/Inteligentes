package Laberinto;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gestor_Archivos {
	/*Lectura del json*/
	public static void leerArchivoJson(String ruta) {
		Gson gson = new Gson();
		String contenidoArchivo = "";
		try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
			String linea;
			while((linea = br.readLine()) != null) {
				contenidoArchivo += linea;
			}
		}catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		/*para obtener los datos del json*/
		ContenidoFichero elementos = gson.fromJson(contenidoArchivo, ContenidoFichero.class);
		System.out.println(elementos.getCelda().toStringS());
	}/*Escritura del json*/
	public static void escribirArchivoJson(String ruta, int numFilas, int numColumnas, int max_vecinos, int[][] id_movimiento, Celda celda) {
		Gson gson = new Gson(); //no sé si los parametros habría que pasarlos a string, o sea hacer la conversión
		ruta += "\\Resultado.json";
		ContenidoFichero fichero = new ContenidoFichero(numFilas, numColumnas, max_vecinos, id_movimiento, celda);
		String json = gson.toJson(fichero);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			bw.write(json);
			System.out.println("Fichero creado, mire el explorador de archivos. En la ruta: " + ruta);
		}catch(IOException ex) {
			System.out.println("Problema en la introducción de la ruta para escribir el fichero.");
		}
	}
	
	/*Creación de los dibujos y el jpg*/
	
}
