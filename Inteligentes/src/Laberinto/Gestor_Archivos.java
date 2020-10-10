package Laberinto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import com.google.gson.*;


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
			System.out.println(elementos.row); 
			System.out.println(elementos.cols);
			System.out.println(elementos.max_vecinos);
			System.out.println(elementos.id_movimiento);
			System.out.println(elementos.celda);
			
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
		BufferedImage lienzo = new BufferedImage(anchura, altura, BufferedImage.TYPE_4BYTE_ABGR);
		//creador de gráficos
		Graphics2D g2d = lienzo.createGraphics();
		
		g2d.setColor(Color.white);
		g2d.fillRect(0,0, anchura, altura);
		
		g2d.setColor(Color.black);
		
	}
	//dibujo del tablero
	//para dibujar cada celda, posión muros de esa celda.
	public static void dibujar() {
		int anchura = 250;
		int altura = 250;
		BufferedImage lienzo = new BufferedImage(anchura,altura, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D pincel = lienzo.createGraphics();
		pincel.setColor(Color.WHITE);//todo lo que dibuje a partir de ahora se dibujara en negro a menos que indique otro color.
		pincel.fillRect(0, 0, anchura-1, altura-1);
		pincel.setColor(Color.BLACK);
		pincel.drawLine(0, 0, anchura-1, altura-1);
		
		/*for(int i = 0; i < laberinto.length; i++) {
			for(int j = 0; j < laberinto.length; i++) {
				muros = laberinto[i][j].getMuros();
				for(int k = 0; k < muros.length; k++) {
					if(muros[k] == false) {
						pincel.drawLine();//origenx, origeny, destino
						pincel.drawLine(0, 0, anchura-1, altura-1);
					}
				}
			}
		}*/		
	}
}
