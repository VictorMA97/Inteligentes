package Laberintos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;
import java.util.Scanner;

public class Gestor_Archivos {

    private Celda cInicio;
    private Celda cFin;
    private String nombre;

    public Celda[][] leerMaze(String ruta) {
        Gson gson = new Gson();
        String ruta_problemas = ruta + leer_json();
        String fichero = "";
        Contenido_Maze datos;
        String[] inicial;
        String fin;

        FileReader fr = null;
        try (BufferedReader br = new BufferedReader(fr = new FileReader(ruta_problemas))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo");
            System.exit(1);
        }
        datos = gson.fromJson(fichero, Contenido_Maze.class);
        inicial = datos.getINITIAL().replace(" ", "").split("");
        System.out.println("Celda inicial");
        for(int i = 0; i< inicial.length;i++){
            System.out.println(inicial[i]);
        }
        
        fin = datos.getOBJETIVE().replace(" ", "");
        String fin_aux= fin.replace("(", "");
        fin_aux = fin_aux.replace(")", "");
        System.out.println(fin_aux);
        String [] cFin1 = fin_aux.split(",");
        System.out.println("Celda objetivo");
        for(int i = 0; i< cFin1.length;i++){
            System.out.println(cFin1[i]);
        }
        
        nombre = datos.getMAZE();
        int inicialx = Integer.parseInt(inicial[1]);
        int inicialy = Integer.parseInt(inicial[3]);
        int finx = Integer.parseInt(cFin1[0]);
        int finy = Integer.parseInt(cFin1[1]);
        Celda[][] laberinto = leerJson(ruta);
        cInicio = laberinto[inicialx][inicialy];
        cFin = laberinto[finx][finy];

        System.out.println("La celda inicial es: " + cInicio);
        System.out.println("La celda objetivo es: " + cFin);

        return laberinto;
    }
    
    /*public Celda[][] leerMaze(String ruta) {
        String ruta_problemas = ruta + leer_json();
        JsonParser parser = new JsonParser();
        int[] inicial = new int[2];
        int[] fin = new int[2];

        FileReader fr = null;
        try {
            fr = new FileReader(ruta_problemas);
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
            System.exit(1);
        }
        JsonElement datos = parser.parse(fr);
        JsonObject obj = datos.getAsJsonObject();
        JsonArray coord = obj.get("INITIAL").getAsJsonArray();
        inicial[0] = coord.get(0).getAsInt();
        inicial[1] = coord.get(1).getAsInt();
        JsonArray coord1 = obj.get("OBJETIVE").getAsJsonArray();
        fin[0] = coord1.get(0).getAsInt();
        fin[1] = coord1.get(1).getAsInt();
        Celda[][] laberinto = leerJson(ruta);
        cInicio = laberinto[inicial[0]][inicial[1]];
        cFin = laberinto[fin[0]][fin[1]];

        System.out.println("La celda inicial es: " + cInicio);
        System.out.println("La celda objetivo es: " + cFin);

        return laberinto;
    }*/

    public Celda[][] leerJson(String ruta) {

        Main m = new Main();
        JsonParser parser = new JsonParser();
        FileReader fr = null;
        //String archivo = leer_json();
        //ruta += archivo;
        ruta += "\\" + nombre;
        try {
            fr = new FileReader(ruta);
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el archivo");
            System.exit(1);
        }
        JsonElement datos = parser.parse(fr);

        JsonObject obj = datos.getAsJsonObject();

        int fila = obj.get("rows").getAsJsonPrimitive().getAsInt();
        int columna = obj.get("cols").getAsJsonPrimitive().getAsInt();
        m.setFila(fila);
        m.setColumna(columna);

        Celda[][] laberinto = new Celda[fila][columna];

        JsonObject cell = obj.get("cells").getAsJsonObject();
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                Celda cel = new Celda(i, j);
                JsonObject celda = cell.get("(" + i + ", " + j + ")").getAsJsonObject();

                if (celda.get("visitado") != null) {
                    if (celda.get("visitado").getAsBoolean() == true || celda.get("visitado").getAsBoolean() == false) {
                        cel.setVisitado(celda.get("visitado").getAsBoolean());
                    }
                } else {
                    int value = celda.get("value").getAsInt();
                    cel.setSuperficie(value);
                }

                JsonArray neighbors = celda.get("neighbors").getAsJsonArray();
                boolean[] muros = new boolean[neighbors.size()];
                for (int k = 0; k < neighbors.size(); k++) {
                    muros[k] = neighbors.get(k).getAsBoolean();
                }
                cel.setVecinos(muros);
                laberinto[i][j] = cel;
            }
        }
        //m.setLaberinto(laberinto.clone());

        return laberinto;
    }

    /*Escritura del json, para la escritura no necesito la celda en si, es mas cada celda del resultado*/
    public void escribirArchivoJson(String ruta, Celda[][] lab, Problema suce) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson1 = new GsonBuilder().create();
        String ruta1 = ruta;
        String nombre = "sucesor_" + lab[0].length + "x" + lab.length + ".json";
        String nombre_maze = "sucesor_" + lab.length + "x" + lab[0].length + "_maze.json";
        ruta += "\\" + nombre_maze;
        ruta1 += "\\";
        ruta1 += nombre;
        Fichero fichero = new Fichero(lab);
        //Sucesores sucesor = new Sucesores();
        //Contenido_Maze archivo_IOM = new Contenifo_Maze(suce.getInicial(), suce.getObjetivo(),nombre_maze);
        //String json_maze = suce.toString();
        try {
            FileWriter sucesor1;
            try (FileWriter fichero2 = new FileWriter(ruta)) {
                sucesor1 = new FileWriter(ruta1);
                fichero2.write(gson.toJson(fichero));
                //sucesor1.write(gson1.toJson());
                fichero2.flush();
                //sucesor1.flush();
            }
            sucesor1.close();
            System.out.println("Fichero json creado.");
        } catch (IOException ex) {
            System.out.println("Error al escribir json");
        }
    }

    private static String leer_json() {
        Scanner teclado = new Scanner(System.in);
        boolean error = false;
        String ruta = "";
        String nombre = "";
        do {
            try {
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

    public Celda getcInicio() {
        return cInicio;
    }

    public Celda getcFin() {
        return cFin;
    }

}
