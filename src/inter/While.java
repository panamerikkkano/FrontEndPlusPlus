package inter; // Archivo While.java

import symbols.*;

public class While extends Instr {

    Expr expr;
    Instr instr;
    Instr instr1;
    Instr instr2;
    //Expr expr1;
    public While() {
        expr = null;
        instr = null;
        instr1 = null;
        instr2 = null;
        //expr1 =null;
    }

    public void inic(Expr x, Instr s, Instr s1, Instr s2) {
        expr = x;
        instr = s;
        instr = s1;
        instr = s2;
        //expr1 = x1;
        if (expr.tipo != Tipo.Bool) {
            expr.error("se requiere booleano en while");
        }
    }

    public void gen(int b, int a) {
        despues = a; // guarda la etiqueta a
        expr.salto(0, a);
        int etiqueta = nuevaEtiqueta(); // etiqueta para instr
        emitirEtiqueta(etiqueta);
        instr.gen(etiqueta, b);
        emitir("goto L" + b);
    }
}
