package Laberinto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.*;
import java.util.*;


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
			ContenidoFichero elementos = gson.fromJson(contenidoArchivo, ContenidoFichero.class);
			System.out.println(elementos.getrow()); //C:\Users\beatr\Documents\Laberinto_inteligentes\BC1_1\Inteligentes
			System.out.println(elementos.getcols());
			contenidoArchivo = contenidoArchivo.replace(" ","");
			String[] cells = (String[]) contenidoArchivo.split("cells");
			String cont = cells[1];
			String [] datos = cont.split("\t");
			for(int i=1;i<datos.length-1;i++) {
				System.out.println(datos[i]);
			}
			
		}catch(FileNotFoundException ex) {
			System.out.println("No se ha encontrado el archivo");
		}catch(IOException ex) {
			System.out.println("No se ha podido abrir el archivo");
		}
		
	}
	/*Escritura del json, para la escritura no necesito la celda en si, es mas cada celda del resultado*/
	public static void escribirArchivoJson(String ruta, int numFilas, int numColumnas, Celda celda) {
		Gson gson = new Gson(); //no sé si los parametros habría que pasarlos a string, o sea hacer la conversión
		ruta += "\\Resultado.json";
		ContenidoFichero fichero = new ContenidoFichero(numFilas, numColumnas, celda);
		String json = gson.toJson(fichero);
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			bw.write(json);
			System.out.println("Fichero creado, mire el explorador de archivos. En la ruta: " + ruta);
		}catch(IOException ex) {
			System.out.println("Problema en la introducción de la ruta para escribir el fichero.");
		}
	}
	
	//Creación de los dibujos y el jpg
	public static void dibujarPNG() {
		int anchura = 250;
		int altura = 250;
		//constructor de imagenes predefinidas
		BufferedImage bufferedImage = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB);
		//creador de gráficos
		Graphics2D g2d = bufferedImage.createGraphics();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0,0, anchura, altura);
		
		g2d.setColor(Color.black);
		
	}
	//dibujo del tablero
	
}
