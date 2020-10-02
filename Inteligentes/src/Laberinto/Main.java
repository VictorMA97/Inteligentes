package laberinto;

import java.util.Scanner;
import laberinto.Gestor_Archivos.*;
import laberinto.ContenidoFichero.*;

public class Main {
    
    private static int fila, columna;
    private static Celda[][] laberinto;
    private static final int max_vecinos=4;
    
    public static void main(String[] args) {
        menu();			
    }
    
    public static void menu(){
        
        Scanner teclado=new Scanner(System.in);
        boolean bucle = false;
        String rut="";
        System.out.println("1.Generar laberinto.\n2.Leer fichero json.\n3.Salir.");
        do{
            System.out.println("Introduza la opcion del menu:");
            try{
                int option = teclado.nextInt();
                switch(option){
                    case 1:
                        pedir_datos();
                        Wilson w = new Wilson();
                        laberinto=w.getLaberinto();
                        //Dibujamos
                        break;
                    case 2:
                        rut=leer_json();
                        Gestor_Archivos.leerArchivoJson(rut);//Leer json.
                        break;
                    case 3:
                        System.exit(1);
                        break;
                    default:
                        bucle=true;
                        throw new Exception();
                }
            }catch(NumberFormatException e){
                System.err.println("Error solo datos numericos");
            }catch(Exception e){
                e.getMessage();
                //System.err.println("Error solo numeros del 1 al 3.");
            }
            
        }while(bucle);
    }
    private static void pedir_datos(){
        Scanner teclado=new Scanner(System.in);
        boolean error = false;
        do{
            try{
                System.out.println("Introduce las filas que deseas:");
                fila=teclado.nextInt();
                System.out.println("Introduce las columnas que deseas:");
                columna=teclado.nextInt();
            
            }catch(Exception e){
                System.out.println("Error solo datos numericos.");
                error=true;
            }
        }while(error);
        laberinto = new Celda[fila][columna];
        
    }

    private static String leer_json() {
        Scanner teclado=new Scanner(System.in);
        boolean error = false;
        String ruta="";
        String nombre="";
        do{
            try{
                System.out.println("Introduce la carpeta donde esta el json:");
                ruta=teclado.nextLine();
                System.out.println("Introduce el nombre del archivo:");
                nombre=teclado.nextLine();
            
            }catch(Exception e){
                System.out.println("Error al leer la ruta.");
                error=true;
            }
        }while(error);
        
        ruta = ruta + "\\" + nombre +".json";
        return ruta;
    }
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
