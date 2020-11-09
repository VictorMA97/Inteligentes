import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.*;
import java.util.Scanner;
import com.google.gson.GsonBuilder;

public class Gestor_Archivos {

    public Celda[][] leerJson(String ruta) {

        Main m = new Main();
        JsonParser parser = new JsonParser();
        FileReader fr = null;
        String archivo=leer_json();
        ruta+=archivo;
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
                int value = celda.get("value").getAsInt();
                if (value == 1) {
                    cel.setVisitado(true);
                } else if (value == 0) {
                    cel.setVisitado(false);
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
    public void escribirArchivoJson(String ruta, Celda[][] lab) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create(); //no s� si los parametros habr�a que pasarlos a string, o sea hacer la conversi�n
		ruta += "\\puzzle_"+lab[0].length+"x"+lab.length+".json";
		Fichero fichero = new Fichero(lab);
	
    	try {
    		FileWriter fichero2 = new FileWriter(ruta);
    		fichero2.write(gson.toJson(fichero));
    		fichero2.flush();
    		fichero2.close();
    		System.out.println("Fichero creado.");
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
}

