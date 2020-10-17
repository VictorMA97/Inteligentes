package Laberintos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    private int fila, columna;
    private Celda[][] laberinto;

    public static void main(String[] args) {
        Main m=new Main();
        m.menu();
    }

    public void menu() {

        Scanner teclado = new Scanner(System.in);
        Gestor_Archivos ga=new Gestor_Archivos();
        boolean bucle = false;
        String rut;
        rut = null;
        int option=0;
        System.out.println("1.Generar laberinto.\n2.Leer fichero json.\n3.Salir.");
        System.out.println("Introduza la opcion del menu:");
        do {
            try {
                option = teclado.nextInt();
                switch (option) {
                    case 1:
                    	String ruta = ruta_json();
                        pedir_datos();
                        Wilson w = new Wilson(laberinto.length,laberinto[0].length);
                        w.algoritmoWilson();
                        System.out.println("funciona");
                        laberinto = w.getLaberinto();
                        dibujar();
                        Gestor_Archivos.escribirArchivoJson(ruta);
                        bucle=false;
                        break;
                    case 2:
                        rut = leer_json();
                        Celda [][] aux = ga.leerJson(rut);
                        laberinto=aux;     //Leer json.
                        dibujar();    
                        bucle=false;
                        break;

                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Solo valores entre 1 y 3.");
                        bucle = true;
                }
            } catch (NumberFormatException e) {
                System.err.println("Error solo datos numericos.");
                bucle = true;
            }
        } while (bucle);
    }
	
    private void pedir_datos() {
        Scanner teclado = new Scanner(System.in);
        boolean error = false;
        do {
            try {
                System.out.println("Introduce las filas que deseas:");
                fila = teclado.nextInt();
                System.out.println("Introduce las columnas que deseas:");
                columna = teclado.nextInt();

            } catch (Exception e) {
                System.out.println("Error solo datos numericos.");
                error = true;
            }
        } while (error);
        System.out.println(fila+" "+columna);
        laberinto = new Celda[fila][columna];
    }

     private static String ruta_json() {
        Scanner teclado = new Scanner(System.in);
        boolean error = false;
        String ruta = "";
        do {
            try {
                System.out.println("Introduce la carpeta donde quieres guardar el json:");
                ruta = teclado.nextLine();
            } catch (Exception e) {
                System.out.println("Error en la ruta.");
                error = true;
            }
        } while (error);
        return ruta;
    }
    
    private static String leer_json() {
        Scanner teclado = new Scanner(System.in);
        boolean error = false;
        String ruta = "";
        String nombre = "";
        do {
            try {
                System.out.println("Introduce la carpeta donde esta el json:");
                ruta = teclado.nextLine();
                System.out.println("Introduce el nombre del archivo:");
                nombre = teclado.nextLine();

            } catch (Exception e) {
                System.out.println("Error al leer la ruta.");
                error = true;
            }
        } while (error);

        ruta = ruta + "\\" + nombre + ".json";
        return ruta;
    }
    
    private void dibujar(){
        int tamaño_celda=10; //Tamaño de celda
        try {
            System.out.println(laberinto.length);
            
            BufferedImage lienzo = new BufferedImage(laberinto.length*tamaño_celda+5, laberinto[0].length*tamaño_celda+5, BufferedImage.TYPE_4BYTE_ABGR); //Pasar aqui filas y columnas del laberinto *100
            Graphics g = lienzo.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, laberinto.length*tamaño_celda, laberinto[0].length*tamaño_celda);
            g.setColor(Color.black);
            // drawLine(posicion_actualx, posicion_actualy, posicion_destinox, posicion_destinoy)
            for(int i=0; i<laberinto.length; i++){
                for(int j=0; j<laberinto[0].length; j++){
                    System.out.println(laberinto[0].length-1);
                    System.out.println();
                    boolean []c = laberinto[i][j].getMuros();
                    
                    int fila = tamaño_celda*i;
                    int columna = tamaño_celda * j;
                    if(c[0] == false){
                        
                        g.drawLine(columna, fila, columna + tamaño_celda, fila); //dibujar norte
                        //fila+=10;
                    }
                    System.out.println(c[1]);
                    if(c[1] == false){
                        g.drawLine(columna + tamaño_celda, fila, columna + tamaño_celda, fila + tamaño_celda); // dibujar este
                        //columna+=10;
                    }
                    System.out.println(c[2]);
                    if(c[2] == false){
                        g.drawLine(columna + tamaño_celda, fila + tamaño_celda, columna, fila + tamaño_celda); // dibujar sur
                        //fila-=10;
                    }
                    System.out.println(c[3]);
                    if(c[3] == false){
                        g.drawLine(columna, fila + tamaño_celda, columna, fila); //dibujar oeste
                        //columna-=10;
                    }
                }
            }
            //System.out.println(lienzo.getGraphics());
            ImageIO.write(lienzo, "png", new File("imagen.jpg"));  
        } catch (IOException ex) {
            System.err.println("Error en el buffer al dibujar");
        } catch (Exception e){
            System.err.println("Error desconocido.");
        }
        System.out.println("Imagen generada correctamente.");
    }
    
    /*public void paint () throws IOException
    {
        int tamaño_celda=10;
        BufferedImage lienzo = new BufferedImage(laberinto.length*tamaño_celda, laberinto[0].length*tamaño_celda, BufferedImage.TYPE_4BYTE_ABGR); //Pasar aqui filas y columnas del laberinto *100
        Graphics g =lienzo.createGraphics();
        //super.paint(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, laberinto.length*tamaño_celda, laberinto[0].length*tamaño_celda);
        g.setColor(Color.black);
        for(int i=0; i<laberinto.length-1; i++){
                for(int j=0; j<laberinto[0].length-1; j++){
                    System.out.println();
                    boolean []c = laberinto[i][j].getMuros();
                    for(int k=0; k< c.length; k++){
                        System.out.print(c[k]);
                    }
                    
                    int fila = tamaño_celda*i;
                    int columna = tamaño_celda * j;
                    System.out.println("\n"+fila +", "+columna);
                    System.out.println(c[0]);
                    if(c[0] == false){
                        
                        g.drawLine(fila, columna, fila + tamaño_celda, columna); //dibujar norte
                        System.out.println("Dibuja");
                        //fila+=10;
                    }
                    System.out.println(c[1]);
                    if(c[1] == false){
                        g.drawLine(fila + tamaño_celda, columna, fila + tamaño_celda, columna + tamaño_celda); // dibujar este
                        System.out.println("Dibuja");
                        //columna+=10;
                    }
                    System.out.println(c[2]);
                    if(c[2] == false){
                        g.drawLine(fila + tamaño_celda, columna + tamaño_celda, fila, columna + tamaño_celda); // dibujar sur
                        System.out.println("Dibuja");
                        //fila-=10;
                    }
                    System.out.println(c[3]);
                    if(c[3] == false){
                        g.drawLine(fila, columna + tamaño_celda, fila, columna); //dibujar oeste
                        System.out.println("Dibuja");
                        //columna-=10;
                    }
                }
            }
        ImageIO.write(lienzo, "png", new File("imagen.jpg"));
    }*/

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Celda[][] getLaberinto() {
        return laberinto;
    }

    public void setLaberinto(Celda[][] laberinto) {
        this.laberinto = laberinto;
    }
}
