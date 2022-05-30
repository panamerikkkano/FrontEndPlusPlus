package symbols; // Archivo Arreglo.java

import analizadorLexico.*;

public class Arreglo extends Tipo {

    public Tipo de; // arreglo *de* tipo
    public int tamanio = 1; // numero de elementos

    public Arreglo(int tm, Tipo p) {
        super("[]", Etiqueta.INDEX, tm * p.anchura);
        tamanio = tm;
        de = p;
    }

    @Override
    public String toString() {
        return "[" + tamanio + "] " + de.toString();
    }
}
