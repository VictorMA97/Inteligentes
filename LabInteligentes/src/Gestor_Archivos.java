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

    public void leerMaze(String ruta) {
        Gson gson = new Gson();
        ruta += leer_json();
        String fichero = "";
        Celda[][] laberinto = null;

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                fichero += linea;
            }

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Contenido_Maze datos = gson.fromJson(fichero, Contenido_Maze.class);
        String[] inicial = datos.getINITIAL().replace(" ", "").split("");
        String[] fin = datos.getOBJETIVE().replace(" ", "").split("");
        int inicialx = Integer.parseInt(inicial[1]);
        int inicialy = Integer.parseInt(inicial[3]);
        int finx = Integer.parseInt(fin[1]);
        int finy = Integer.parseInt(fin[3]);
        System.out.print(inicialx);

        String nombre = datos.getMAZE();
        cInicio = laberinto[inicialx][inicialy];
        cFin = laberinto[finx][finy];
    }

    public Celda[][] leerJson(String ruta) {

        Main m = new Main();
        JsonParser parser = new JsonParser();
        FileReader fr = null;
        String archivo = leer_json();
        ruta += archivo;
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
                    if (value == 1) {
                        cel.setVisitado(true);
                    } else if (value == 0) {
                        cel.setVisitado(false);
                    }
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
    public void escribirArchivoJson(String ruta, Celda[][] lab, Sucesores suce) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson1 = new GsonBuilder().create();
        String ruta1 = ruta;
        String nombre = "sucesor_" + lab[0].length + "x" + lab.length + "maze.json";
        ruta += "\\sucesor_" + lab[0].length + "x" + lab.length + ".json";
        ruta1 += "\\";
        ruta1 += nombre;
        Fichero fichero = new Fichero(lab);
        //Sucesores sucesor = new Sucesores();
        suce.nombreArchivo(nombre);
        //String json_maze = suce.toString();
        try {
            FileWriter fichero2 = new FileWriter(ruta);
            FileWriter sucesor1 = new FileWriter(ruta1);
            fichero2.write(gson.toJson(fichero));
            sucesor1.write(gson1.toJson(suce));
            fichero2.flush();
            sucesor1.flush();
            fichero2.close();
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
