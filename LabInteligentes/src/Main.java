
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class Main {

    private int fila, columna;
    private Celda[][] laberinto;
    private Celda cInicio;
    private Celda cFin;

    public static void main(String[] args) {
        Main m = new Main();
        m.menu();
    }

    public void menu() {

        Scanner teclado = new Scanner(System.in);
        Gestor_Archivos ga = new Gestor_Archivos();
        Busqueda b = new Busqueda();
        int estrategia;
        Random r = new Random();
        Celda inicial;
        Celda objetivo;
        Problema problema;
        String rut;
        ArrayList<Nodo> solucion = new ArrayList();

        final int profundidad_maxima = 1000000;
        rut = null;
        int option = 0;

        try {
            System.out.println("1.Generar laberinto.\n2.Leer fichero json.\n3.Salir.");
            System.out.println("Introduza la opcion del menu: ");
            option = teclado.nextInt();
        } catch (Exception e) {
            System.err.println("Error solo datos numericos.");

            menu();
        }
        switch (option) {
            case 1:
                String ruta = ruta_json();
                pedir_datos();
                Wilson w = new Wilson(laberinto.length, laberinto[0].length);
                w.generar();
                System.out.println("Laberinto generado.");
                System.arraycopy(w.getLaberinto(), 0, laberinto, 0, laberinto.length);
                laberinto = w.getLaberinto();
                inicial = laberinto[r.nextInt(fila)][r.nextInt(columna)];
                objetivo = laberinto[r.nextInt(fila)][r.nextInt(columna)];
                problema = new Problema(inicial, objetivo, laberinto);
                estrategia = pedirEstrategia();
                solucion = b.busquedaSolucion(problema, profundidad_maxima, estrategia);
                crear_txt(solucion, ruta, estrategia);
                dibujar(ruta, solucion);
                //funcionSucesora(laberinto, ruta, sucesor);
                ga.escribirArchivoJson(ruta, laberinto, problema);

                break;
            case 2:
                rut = ruta_json();
                laberinto = ga.leerMaze(rut);
                fila = laberinto.length;
                columna = laberinto[0].length;
                estrategia = pedirEstrategia();
                inicial = ga.getcInicio();
                objetivo = ga.getcFin();
                problema = new Problema(inicial, objetivo, laberinto);
                solucion = (ArrayList<Nodo>) b.busquedaSolucion(problema, profundidad_maxima, estrategia).clone();
                crear_txt(solucion, rut, estrategia);
                dibujar(rut, solucion);

                break;

            case 3:
                System.exit(0);
                break;
            default:
                if (option < 1 || option > 3) {
                    System.err.println("Solo valores entre 1 y 3.");
                    menu();
                }
                break;
        }

        System.out.println("\nFin del programa");
    }

    private int pedirEstrategia() {
        Scanner teclado = new Scanner(System.in);
        int estrategia = 0;
        do {
            System.out.print("\nElige estrategia para resolver el laberinto.\n 1.Anchura.\n 2,Profundidad.\n 3.Costo uniforme.\n 4. Voraz.\n 5. A*. \n");
            estrategia = teclado.nextInt();

        } while (estrategia < 1 || estrategia > 5);
        return estrategia;
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

    public void dibujar(String ruta, ArrayList<Nodo> solucion) {
        int tamano_celda = 10; // Tama�o de celda
        ArrayList<Celda> solucionC = new ArrayList<>();
        BufferedImage lienzo = new BufferedImage(laberinto[0].length * tamano_celda + 5,
                laberinto.length * tamano_celda + 5, BufferedImage.TYPE_4BYTE_ABGR); // Pasar aqui filas y columnas
        // del laberinto *100
        Graphics g = lienzo.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, laberinto[0].length * tamano_celda + 3, laberinto.length * tamano_celda + 3);
        g.setColor(Color.black);
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {

                boolean[] c = laberinto[i][j].getVecinos();
                int fila = tamano_celda * i;
                int columna = tamano_celda * j;

                if (c[0] == false) {
                    g.drawLine(columna, fila, columna + tamano_celda, fila); // dibujar norte
                }

                if (c[1] == false) {
                    g.drawLine(columna + tamano_celda, fila, columna + tamano_celda, fila + tamano_celda); // dibujar
                    // este
                }

                if (c[2] == false) {
                    g.drawLine(columna + tamano_celda, fila + tamano_celda, columna, fila + tamano_celda); // dibujar
                    // sur
                }

                if (c[3] == false) {
                    g.drawLine(columna, fila + tamano_celda, columna, fila); // dibujar oeste
                }
            }
        }
       
        for (int i = 0; i < solucion.size(); i++) {
            solucionC.add(solucion.get(i).getEstado());
        }

        for (int i = 0; i < solucion.size(); i++) {
            System.out.println("Prueba:" + solucion.get(i));
        }
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                for (int z = 0; z < solucionC.size(); z++) {
                    if (solucionC.get(z).equals(laberinto[i][j])) {
                        g.setColor(Color.red);
                        g.fillRect(tamano_celda * j, tamano_celda * i, tamano_celda, tamano_celda);
                    }
                }
            }
        }

        try {
            ruta += "\\puzzle_" + laberinto.length + "x" + laberinto[0].length + ".jpg";
            System.out.println(ruta);
            ImageIO.write(lienzo, "png", new File(ruta));
        } catch (IOException ex) {
            System.err.println("Error en el buffer al dibujar");
        } catch (Exception e) {
            System.err.println("Error desconocido.");
        }

        System.out.println("Imagen generada correctamente.");
    }

    public void crear_txt(ArrayList<Nodo> solucion, String ruta, int estrategia) {
        String cadena = "";

        cadena += "[id][cost,state,father_id,action,depth,h,value]\n";

        for(int i = solucion.size()-1;i>=0;i--) {
        	cadena+=solucion.get(i).toString() +"\n";
        }

        String estrate = null;
        switch (estrategia) {
            case 1:
                estrate = "BREADTH";
                break;
            case 2:
                estrate = "DEPTH";
                break;
            case 3:
                estrate = "UNIFORM";
                break;
            case 4:
                estrate = "GREEDY";
                break;
            case 5:
                estrate = "A";
                break;
        }
        String nombre = "\\sol_" + fila + "x" + columna + "_" + estrate + ".txt";
        ruta = ruta + nombre;
        try {
            FileWriter fw = new FileWriter(new File(ruta));
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cadena);
            bw.close();
            System.out.println("\nFichero txt creado.");
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo txt.");
        }
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

    public Celda getCeldaInicio() {
        return cInicio;
    }

    public Celda getCeldaFin() {
        return cFin;
    }

}
