package Laberintos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
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
    public static int id = -1;
    Nodo solucion; //creo el nodo solucion

    public static void main(String[] args) {
        Main m = new Main();
        m.menu();
    }

    public void menu() {

        Scanner teclado = new Scanner(System.in);
        Gestor_Archivos ga = new Gestor_Archivos();
        boolean bucle = false;
        Sucesores sucesor = new Sucesores();
        String rut;
        int estrategia;
        rut = null;
        int option = 0;

        do {
            System.out.println("1.Generar laberinto.\n2.Leer fichero json.\n3.Salir.");
            System.out.println("Introduza la opcion del menu: ");
            try {
                option = teclado.nextInt();
                switch (option) {
                    case 1:
                        String ruta = ruta_json();
                        estrategia = pedir_datos();
                        Wilson w = new Wilson(laberinto.length, laberinto[0].length);
                        w.generar();
                        System.out.println("Laberinto generado.");
                        System.arraycopy(w.getLaberinto(), 0, laberinto, 0, laberinto.length);
                        laberinto = w.getLaberinto();
                        dibujar(ruta);
                        funcionSucesora(laberinto, ruta, sucesor, estrategia);
                        ga.escribirArchivoJson(ruta, laberinto, sucesor);
                        bucle = false;
                        break;
                    case 2:
                        rut = ruta_json();
                        Celda[][] aux = ga.leerJson(rut);
                        laberinto = aux; // Leer json.
                        // funcionSucesora(laberinto, rut, sucesor, estrategia); //Hacer polimorfismo
                        bucle = false;
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
        System.out.println("\nFin del programa");
    }

    public void funcionSucesora(Celda[][] laberinto, String ruta, Sucesores suceso, int estrategia) {

        Gestor_Archivos ga = new Gestor_Archivos();
        Celda estado;
        int heuristica;
        String archivo = "";

        do {
            cInicio = ga.getcInicio();
            System.out.println(cInicio.getFila() + " , " + cInicio.getColumna());
            cFin = ga.getcFin();
        } while (cInicio.equals(cFin));
        suceso.sucesores(cInicio, cFin);
        System.out.println(cFin.getFila() + " , " + cFin.getColumna());
        estado = cInicio;
        heuristica = Math.abs(fila - estado.getFila()) + Math.abs(columna - estado.getColumna());
        Nodo nodo = new Nodo(null, cInicio, ++id, 0, 0, heuristica, estrategia);

        Frontera frontera = new Frontera();
        frontera.insertar(nodo);
        Nodo aux = nodo;
        Nodo siguiente;
        boolean objetivo = false;

        while (!objetivo && !frontera.isEmpty()) {

            aux = frontera.eliminar();

            if (!aux.getEstado().isExpandido()) {

                System.out.print("\nSUC((" + aux.getEstado().getFila() + "," + aux.getEstado().getColumna() + "))=");

                archivo += "\nSUC((" + aux.getEstado().getFila() + "," + aux.getEstado().getColumna() + "))=";
                archivo += expandir(aux, laberinto, frontera, estrategia);

                solucion = funcionObjetivo(frontera);

                if (solucion.getEstado().equals(cFin)) {

                    objetivo = true;
                }

            } else {
                --id;
            }

            siguiente = frontera.eliminar();
        }
        ruta += "\\Sucesores_" + fila + "x" + columna + ".txt";
        File f = new File(ruta);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(archivo);
            fw.close();
            System.out.println("\nFichero txt creado.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Error al crear el fichero txt.");
        }

        ArrayList<Nodo> soluciones = new ArrayList<Nodo>();
        while (solucion.getPadre() != null) {
            soluciones.add(solucion);
            solucion = solucion.getPadre();
        } //esto al final de funcion sucesora
    }

    public Nodo funcionObjetivo(Frontera frontera) {

        boolean objetivo = false;
        Celda aux;
        Nodo auxi = null;

        while (!frontera.isEmpty()) {
            auxi = frontera.eliminar();
            aux = auxi.getEstado();
            if ((aux.getFila()) == cFin.getFila() && (aux.getColumna()) == cFin.getColumna()) {
                objetivo = true;
                break;
            }
        }
        return auxi;

    }

    public String expandir(Nodo actual, Celda[][] laberinto, Frontera frontera, int estrategia) {

        Celda estado = actual.getEstado();
        estado.setExpandido(true);
        int fila = estado.getFila();
        int columna = estado.getColumna();
        int coste = actual.getCosto();
        int profundidad = actual.getProfundidad();
        int heuristica;
        boolean[] vecinos = estado.getVecinos();
        String expansion = "";
        Random r = new Random();
        Nodo n;
        // cambiar a expandido solo el padre
        for (int i = 0; i < vecinos.length; i++) {
            if (vecinos[i]) {
                int tipo;
                switch (i) {
                    case 0:
                        tipo = r.nextInt(3);
                        coste += tipo;
                        heuristica = Math.abs((fila - 1) - cFin.getFila()) + Math.abs(columna - cFin.getColumna());
                        n = new Nodo(actual, laberinto[fila - 1][columna], ++id, actual.getPadre().getCosto() + coste + 1, profundidad + 1, heuristica,
                                estrategia);
                        n.setHeuristica(heuristica);
                        n.setTipo(tipo);
                        n.setAccion('N');
                        frontera.insertar(n);

                        break;
                    case 1:
                        tipo = r.nextInt(3);
                        coste += tipo;
                        heuristica = Math.abs(fila - cFin.getFila()) + Math.abs((columna + 1) - cFin.getColumna());
                        n = new Nodo(actual, laberinto[fila][columna + 1], ++id, coste + 1, profundidad + 1, actual.getPadre().getCosto() + coste + 1,
                                estrategia);
                        n.setHeuristica(heuristica);
                        n.setTipo(tipo);
                        n.setAccion('E');
                        frontera.insertar(n);

                        break;

                    case 2:
                        tipo = r.nextInt(3);
                        coste += tipo;
                        heuristica = Math.abs((fila + 1) - cFin.getFila()) + Math.abs(columna - cFin.getColumna());
                        n = new Nodo(actual, laberinto[fila + 1][columna], ++id, coste + 1, profundidad + 1, actual.getPadre().getCosto() + coste + 1,
                                estrategia);
                        n.setHeuristica(heuristica);
                        n.setTipo(tipo);
                        n.setAccion('S');
                        frontera.insertar(n);

                        break;

                    case 3:
                        tipo = r.nextInt(3);
                        coste += tipo;
                        heuristica = Math.abs(fila - cFin.getFila()) + Math.abs((columna - 1) - cFin.getColumna());
                        n = new Nodo(actual, laberinto[fila][columna - 1], ++id, coste + 1, profundidad + 1, actual.getPadre().getCosto() + coste + 1,
                                estrategia);
                        n.setHeuristica(heuristica);
                        n.setTipo(tipo);
                        n.setAccion('O');
                        frontera.insertar(n);

                        break;
                }
            }
        }
        if (!frontera.isEmpty()) {
            System.out.print(frontera.getLista());
            expansion = frontera.getLista().toString();
        }
        return expansion;
    }

    private int pedir_datos() {
        Scanner teclado = new Scanner(System.in);
        boolean error = false;
        boolean estr = false;
        int estrategia = 0;
        do {
            try {
                System.out.println("Introduce las filas que deseas:");
                fila = teclado.nextInt();
                System.out.println("Introduce las columnas que deseas:");
                columna = teclado.nextInt();
                do {
                    System.out.print("\nElige estrategia para resolver el laberinto.\n 1.Anchura.\n 2,Profundidad.\n 3.Costo uniforme.\n 4. Voraz.\n 5. A*. \n");
                    estrategia = teclado.nextInt();
                    if (estrategia < 1 || estrategia > 5) {
                        System.err.println("Seleccione solo valores indicados en el menu.");
                    } else {
                        estr = true;
                    }
                } while (estr);
            } catch (NumberFormatException e) {
                System.err.println("Error solo datos numericos.");
                error = true;
            }
        } while (error);
        laberinto = new Celda[fila][columna];
        return estrategia;

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

    private void dibujar(String ruta) {
        int tamano_celda = 10; // Tamaño de celda
        try {

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
                    int fila_aux = tamano_celda * i;
                    int columna_aux = tamano_celda * j;

                    if (c[0] == false) {
                        g.drawLine(columna_aux, fila_aux, columna_aux + tamano_celda, fila_aux); // dibujar norte
                    }

                    if (c[1] == false) {
                        g.drawLine(columna_aux + tamano_celda, fila_aux, columna_aux + tamano_celda, fila_aux + tamano_celda); // dibujar
                        // este
                    }

                    if (c[2] == false) {
                        g.drawLine(columna_aux + tamano_celda, fila_aux + tamano_celda, columna_aux, fila_aux + tamano_celda); // dibujar
                        // sur
                    }

                    if (c[3] == false) {
                        g.drawLine(columna_aux, fila_aux + tamano_celda, columna_aux, fila_aux); // dibujar oeste
                    }
                }
            }
            ruta += "\\puzzle_" + laberinto[0].length + "x" + laberinto.length + ".jpg";
            System.out.println(ruta);
            ImageIO.write(lienzo, "png", new File(ruta));
        } catch (IOException ex) {
            System.err.println("Error en el buffer al dibujar");
        } catch (Exception e) {
            System.err.println("Error desconocido.");
        }
        System.out.println("Imagen generada correctamente.");
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
