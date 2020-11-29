
import java.util.Comparator;
 
public class comparadorNodo implements Comparator<Nodo> {
 
    @Override
   public int compare(Nodo n1, Nodo n2) {

        int i = 0;

        if (n1.getValor() < n2.getValor()) {
            i = -1;
        } else if (n1.getValor() > n2.getValor()) {
            i = 1;
        } else {

            if (n1.getEstado().getFila() < n2.getEstado().getFila()) {
                i = -1;
            } else if (n1.getEstado().getFila() > n2.getEstado().getFila()) {
                i = 1;
            } else {

                if (n1.getEstado().getColumna() < n2.getEstado().getColumna()) {
                    i = -1;
                } else if (n1.getEstado().getColumna() > n2.getEstado().getColumna()) {
                    i = 1;
                } else {

                    if (n1.getId() < n2.getId()) {
                        i = -1;
                    } else if (n1.getId() > n2.getId()) {
                        i = 1;
                    }

                }
            }
        }

        return i;
    }
 
}
