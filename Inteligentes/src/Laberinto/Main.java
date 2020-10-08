package Laberinto;


import java.util.Scanner;

public class Main {

    private static int fila, columna;
    private static Celda[][] laberinto;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {

        Scanner teclado = new Scanner(System.in);
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
                        pedir_datos();
                        Wilson w = new Wilson(laberinto.length,laberinto.length);
                        w.algoritmoWilson();
                        System.out.println("funciona");
                        laberinto = w.getLaberinto();
                        //Gestor_Archivos.dibujarPNG();//Dibujamos
                        bucle=false;
                        break;
                    case 2:
                        rut = leer_json();
                        Gestor_Archivos.leerArchivoJson(rut);//Leer json.
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

    private static void pedir_datos() {
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
