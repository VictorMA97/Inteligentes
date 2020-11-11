
public class ContenidoCelda {

    private int value = 0;
    private boolean[] vecinos;

    ContenidoCelda(Celda celda) {
        this.vecinos = celda.getVecinos();

    }

}
