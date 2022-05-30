package inter; // Archivo Id.java

import symbols.Tipo;
import analizadorLexico.*;

public class Id extends Expr {

    public int desplazamiento; // direccion relativa

    public Id(Palabra id, Tipo p, int b) {
        super(id, p);
        desplazamiento = b;
    }
}
